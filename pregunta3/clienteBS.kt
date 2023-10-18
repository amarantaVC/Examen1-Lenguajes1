/*
CI3641 - LENGUAJES DE PROGRAMACION I 
EXAMEN1 - PREGUNTA 3
AMARANTA VILLEGAS 16-11247
Cliente de Buddy System

*/

import buddySystem
fun main(args: Array<String>) {

    if (args.size != 1) {
        println("[ERROR]: debe ingresar un solo parametro")
    }

    // instanciar in buddySystem con una cantidad indicada de memoria
    val buddySystem = buddySystem(args[0].toInt())

    // pedir instrucciones al usuario
    while (true) {
        println("----------------------------------------\n")
        println("|      SIMULADOR BUSSY SYSTEM         |" )
        println("Seleccione una de las siguientes opciones:")
        println("1. RESERVAR  <cantidad> <nombre>" )
        println("2. LIBERAR <nombre>")
        println("3. MOSTRAR ")
        println("4. SALIR ")

        println("------------------------------------------\n")
        print("Ingrese una opcion entre 1/2/3/4: ")
        
        // leer la opcion del usuario ingresada
        val opcion = readLine()

        // ejecutar la opcion ingresada entre 1/2/3/4
        when (opcion) {
            // si la opcion es 1, reservar un bloque de memoria
            "1" -> {
                println("Ingrese Cantidad y Nombre separados por un espacio: ")
                val entrada = readLine()
                val parametros = entrada!!.split(" ")

                if (parametros.size == 2){
                    try {
                        val cantidad = parametros[0].toInt()
                        val nombre = parametros[1]
                        if (cantidad > 0 && cantidad <= args[0].toInt()){
                            buddySystem.reservar(cantidad, nombre)
                            println("[ Reservado ]")
                        }
                        else{
                            println("Error, cantidad debe ser mayor a 0 y menor o igual a ${args[0].toInt()}")
                        }
                    } catch (e: NumberFormatException){
                        println("Error, cantidad debe ser un numero entero")
                    }

                } else {
                    println("Error, cantidad y nombre deben separse por espacio.")
                }

            }
            // si la opcion es 2, liberar un bloque de memoria
            "2" -> {
                println("Ingrese el nombre a liberar :")
                val nombre = readLine()
                if (nombre != null) {
                    if (buddySystem.liberar(nombre)) {
                        println("[ Memoria liberada].")
                    } else {
                        println("Error, este nombre no esta asignado a ningún espacio de memoria.")
                    }
                } else {
                    println("Error, ingrese nombre.")
                }
            }
            // si la opcion es 3, mostrar la memoria
            "3" -> {
                buddySystem.mostrar()
            }
            // si la opcion es 4, salir del programa
            "4" -> {
                println("\n----------------------------------------")
                println("Saliendo del simulador...")
            
                break
            }
            // si la opcion no es ninguna de las anteriores, mostrar un warning de error y pedir una opcion valida
            else -> {
                println("ERROR, seleccione una opción entre (1/2/3/4).")
            }
        }
    }
}