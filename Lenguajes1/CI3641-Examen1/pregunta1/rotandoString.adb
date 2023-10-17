-- CI3461 - LENGUAJES DE PROGRAMACION I
-- EXAMEN 1 PREGUNTA 1
--  AMARANTA VILLEGAS 16-11247
-- LENGUAJE POR LA LETRA A ESCODIGO: ADA 
-- EJERCICIO DE ROTAR


with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO;


procedure rotandoString is

    -- Funcion que rota una palabra w k veces
    function Rotar(w : String; k : Integer) return String is
    begin
        if k = 0 or else w'Length = 0 then
            return w;
        else
            return Rotar(w(w'First + 1 .. w'Last) & w(w'First), k - 1);
        end if;
    end Rotar;

    -- Imprimimos el resultado
    procedure ImprimirRotacion(w : String; k : Integer) is
        RotacionFinal : String := Rotar(w, k);
    begin
        Put_Line("Rotar(""" & w & """, " & Integer'Image(k) & ") = """ & RotacionFinal & """");
    end ImprimirRotacion;

    -- Solicitamos la palabra y el valor de N al usuario
    procedure InputPalabra is
        Input : String(1 .. 30);
        N : Integer;
        Ultimo : Natural;
    begin
        Ada.Text_IO.Put_Line("--- IMPLEMENTACION EN ROTAR EN LENGUAJE ADA ---");
        Put("Ingrese la palabra que desea rotar: ");
        Get_Line(Item => Input, Last => Ultimo);
        Ada.Text_IO.Put("Introduce el valor de N: ");
        Ada.Integer_Text_IO.Get(N);
        ImprimirRotacion(Input(1 .. Ultimo), N);
    end InputPalabra;

begin

    InputPalabra;

end rotandoString;
