-- CI3461 - LENGUAJES DE PROGRAMACION I
-- EXAMEN 1 PREGUNTA 1
--  AMARANTA VILLEGAS 16-11247
-- LENGUAJE POR LA LETRA A ESCODIGO: ADA 
-- EJERCICIO DE MULTIPLICACION DE MATRIZ TRANSPUESTA 

with Ada.Text_IO;
with Ada.Integer_Text_IO;

procedure MatrizTranspuesta is
   N : Integer;
   
   -- DEFINIMOS LA MATRIZ ORIGINAL
   type Matriz_Original is array (1 .. 25, 1 .. 25) of Integer;  
   ORIGINAL : Matriz_Original;
   
   -- DEFINIMOS TRANSPUESTA
   type Matriz_Transpuesta is array (1 .. 25, 1 .. 25) of Integer;
   TRANSPUESTA : Matriz_Transpuesta;
   
   -- Definir la matriz de resultado
   type Matriz_Resultado is array (1 .. 100, 1 .. 100) of Integer;
   RESULTADO : Matriz_Resultado;

begin
   Ada.Text_IO.Put("Introduce el valor de N: ");
   Ada.Integer_Text_IO.Get(N);

   -- Llenamos la matriz ORIGINAL 
   for Row in 1 .. N loop
      for Col in 1 .. N loop
         Ada.Text_IO.Put("Introduce el valor para ORIGINAL(" & Integer'Image(Row) & "," & Integer'Image(Col) & "): ");
         Ada.Integer_Text_IO.Get(ORIGINAL(Row, Col));
      end loop;
   end loop;

   -- Calculamos la matriz TRANSPUESTA
   for Row in 1 .. N loop
      for Col in 1 .. N loop
         TRANSPUESTA(Col, Row) := ORIGINAL(Row, Col);
      end loop;
   end loop;


   -- Calcular el producto ORIGINAL * TRANSPUESTA
   for i in 1 .. N loop
      for j in 1 .. N loop
         RESULTADO(i, j) := 0;
         for k in 1 .. N loop
            RESULTADO(i, j) := RESULTADO(i, j) + ORIGINAL(i, k) * TRANSPUESTA(k, j);
         end loop;
      end loop;
   end loop;

   -- Imprimimos la matriz ORIGINAL
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put_Line("Matriz ORIGINAL:");
   for Row in 1 .. N loop
      for Col in 1 .. N loop
         Ada.Text_IO.Put(ORIGINAL(Row, Col)'Image & " ");
      end loop;
      Ada.Text_IO.New_Line;
   end loop;

   -- Imprimimos la matriz TRANSPUESTA
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put_Line("Matriz TRANSPUESTA:");
   for Row in 1 .. N loop
      for Col in 1 .. N loop
         Ada.Text_IO.Put(TRANSPUESTA(Row, Col)'Image & " ");
      end loop;
      Ada.Text_IO.New_Line;
   end loop;

   -- Imprimimos la matriz RESULTADO
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put_Line("Resultado de ORIGINAL * TRANSPUESTA:");
   for Row in 1 .. N loop
      for Col in 1 .. N loop
         Ada.Text_IO.Put(RESULTADO(Row, Col)'Image & " ");
      end loop;
      Ada.Text_IO.New_Line;
   end loop;
end MatrizTranspuesta;