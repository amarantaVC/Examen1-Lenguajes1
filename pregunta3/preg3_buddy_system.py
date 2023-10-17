# Jennifer Gamez 16-10396
"""
El "buddy system" se refiere a un algoritmo utilizado para administrar la 
asignación y liberación de bloques de memoria. Este sistema divide la 
memoria en bloques de tamaños específicos, y cada bloque se puede dividir_bloques 
en dos bloques "hermanos" del mismo tamaño.
"""

from collections import deque


class Bloque:
    def __init__(self):
        self.proceso = ""
        self.bloque_mayor = None
        self.hijoDer = None
        self.hijoIzq = None
        self.bloque_disp = True
        self.posicion = 0

    def bloque_libre(self):
        return self.bloque_disp

    def orden(self):
        return self.posicion


class BuddySystem:

    # Constructor de la clase BuddySystem
    def __init__(self, n):
        self.orden = n
        self.memoria = [deque() for _ in range(n + 1)]
        self.nombre_proc = []
        self.orden_proc = []
        self.memoria[n].append(Bloque())
        self.memoria[n][0].ord = n

    # buscar_bloque busca un bloque libre en la memoria de tamaño n o superior, 
    # sino hay bloques libres retorna none (no hay bloques disponibles), de lo 
    # contrario retorna el bloque libre.
    def buscar_bloque(self, n):
        bloque_encontrado = None

        if n > self.orden:
            return None
        
        for i in self.memoria[n]:
            if i.bloque_libre():
                bloque_encontrado = i
                break
        if bloque_encontrado is None:
            bloque_encontrado = self.buscar_bloque(n + 1)

        return bloque_encontrado

    # dividir_bloque divide el bloque en dos partes hasta que alcance el tamaño
    # n de forma recursiva.
    def dividir_bloques(self, bloque, n):
        bloque_dividido = bloque
        if bloque_dividido.orden() > n:
            hijo_der = Bloque()
            hijo_izq = Bloque()
            hijo_der.bloque_mayor = bloque_dividido
            hijo_izq.bloque_mayor = bloque_dividido
            hijo_der.ord = bloque_dividido.orden() - 1
            hijo_izq.ord = bloque_dividido.orden() - 1
            bloque_dividido.hijoDer = hijo_der
            bloque_dividido.hijoIzq = hijo_izq
            bloque_dividido.bloque_disp = False
            self.memoria[bloque_dividido.orden() - 1].append(hijo_der)
            self.memoria[bloque_dividido.orden() - 1].append(hijo_izq)
            bloque_dividido = self.dividir_bloques(hijo_izq, n)
        return bloque_dividido

    # unir_bloque une dos bloques si ambos estan disponibles.
    def unir_bloques(self, bloque_1, bloque_2):
        if bloque_1.bloque_libre() and bloque_2.bloque_libre():
            self.memoria[bloque_1.ord].remove(bloque_1)
            self.memoria[bloque_1.ord].remove(bloque_2)
            bloque_1.bloque_mayor.bloque_disp = True
            if bloque_1.bloque_mayor.bloque_mayor is not None:
                self.unir_bloques(bloque_1.bloque_mayor.bloque_mayor.hijoDer, bloque_1.bloque_mayor.bloque_mayor.hijoIzq)

    # reservar_bloque este busca un bloque de memoria igual o mas grande que el solicitado
    # si lo encuentra lo reserva en la memoria.
    def reservar_bloque(self, nombre_proceso, n):
        bloque_reservado = None
        i = self.orden

        while i > 0:
            if 2 ** (i - 1) < n < 2 ** i + 1:
                break
            i -= 1

        if 2 ** i < n:
            return False
        
        bloque_reservado = self.buscar_bloque(i)

        if bloque_reservado is not None and bloque_reservado.orden() > i:
            bloque_reservado = self.dividir_bloques(bloque_reservado, i)

        elif bloque_reservado is None:
            return False
        
        bloque_reservado.proceso = nombre_proceso
        bloque_reservado.bloque_disp = False
        self.nombre_proc.append(nombre_proceso)
        self.orden_proc.append(i)

        return True
    
    # liberar_bloque libera el proceso si este se encuentra en memoria.
    def liberar_bloque(self, nombre_proceso):
        
        bloque_liberado = False

        if nombre_proceso in self.nombre_proc:
            i = self.nombre_proc.index(nombre_proceso) 
            if i != -1:
                bloque_liberado = True
                orden = self.orden_proc[i]

                for bloque in self.memoria[orden]:

                    if bloque.proceso == nombre_proceso:
                        bloque.proceso = ""
                        bloque.bloque_disp = True

                        if bloque.bloque_mayor is not None:
                            self.unir_bloques(bloque.bloque_mayor.bloque_mayor.hijoDer, bloque.bloque_mayor.bloque_mayor.hijoIzq)
                        
                self.orden_proc.pop(self.orden_proc.index(self.orden_proc[i]))
                self.nombre_proc.pop(self.nombre_proc.index(nombre_proceso))
            
        return bloque_liberado

    # num_bloque_disp este muestra el numero de bloque disponible de cada bloque en memoria.
    def num_bloques_disp(self, n):
        n_bloques = 0
        if n <= self.orden:
            for bloque in self.memoria[n]:
                if bloque.bloque_disp:
                    n_bloques += 1
        return n_bloques
    
# main, programa inicial
def main():
    particiones = []
    linea = ""
    instruccion = []

    orden_max = int(input("Ingrese el orden máximo de bloques: "))

    # Calcular el número binario en función del orden máximo
    binario = bin(2 ** orden_max - 1)[2:]

    for i, bit in enumerate(binario[::-1]):
        if bit == '1':
            particiones.append(BuddySystem(i))

    print("Manejador de memoria que implementa el buddy system.")
    print("Acciones: RESERVAR, LIBERAR, MOSTRAR, AYUDA y SALIR.")

    # Constantemente le pide una acción al usuario.
    while True:
        linea = input("> ")
        instruccion = linea.split()

        if instruccion[0].upper() == "RESERVAR":
            resultado = False

            for particion in particiones:
                resultado = particion.reservar_bloque(instruccion[2], int(instruccion[1]))

                if resultado:
                    break

            if not resultado:
                print("Error, no hay suficientes bloques disponibles de manera contigua.")

        elif instruccion[0].upper() == "LIBERAR":
            resultado = False

            for particion in particiones:
                resultado = particion.liberar_bloque(instruccion[1])

                if resultado:
                    break

            if not resultado:
                print(f"Error, no hay ningun {instruccion[1]} asociado a la memoria.")
        
        elif instruccion[0].upper() == "MOSTRAR":
            salida_memoria = ""

            for i in range(orden_max):
                bloques_disp = 0
                mem_disp = 2 ** i

                for particion in particiones:
                    bloques_disp += particion.num_bloques_disp(i)

                salida_memoria += f"\nBloques de tamaño {mem_disp} disponibles:\n\tHay {bloques_disp} bloques disponibles.\n"
        
            salida_memoria += "\nLista con los procesos que tienen bloques de memoria reservados.\n"

            for particion in particiones:
                for i in range(len(particion.nombre_proc)):
                    espacio = 2 ** particion.orden_proc[i]
                    salida_memoria += f"Nombre: {particion.nombre_proc[i]} -> Tamaño de bloque asignado: {espacio}\n"
            
            print(salida_memoria)

        elif instruccion[0].upper() == "SALIR":
            break

        elif instruccion[0].upper() == "AYUDA":
            print("Instrucciones del programa:")
            print("1. Para reservar un bloque de memoria, escriba: RESERVAR tamaño_del_bloque nombre_del_proceso")
            print("   Ejemplo: RESERVAR Proceso1 32")
            print("2. Para liberar un bloque de memoria asociado a un proceso, escriba: LIBERAR nombre_del_proceso")
            print("   Ejemplo: LIBERAR Proceso1")
            print("3. Para mostrar el estado actual de la memoria y la lista de procesos, escriba: MOSTRAR")
            print("4. Para ver estas instrucciones nuevamente, escriba: AYUDA")
            print("5. Para salir del programa, escriba: SALIR.\n")

        else:
            print("Error, por favor intente nuevamente. Consulte el instructivo si necesita ayuda.")

if __name__ == "__main__":
    main()


