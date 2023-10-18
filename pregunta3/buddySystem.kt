/*
CI3641 - LENGUAJES DE PROGRAMACION I 
EXAMEN1 - PREGUNTA 3
AMARANTA VILLEGAS 16-11247
Libreria de Buddy System

*/

class buddySystem(private val tamano: Int) {

    // Definimos nuestras variables globales
    private val libres = MutableList(32) { mutableListOf<Pair<Int, Int>>() }
    private val bloqueados = mutableMapOf<String, Pair<Int, Int>>()

    //utilizamos init para inicializar las variables de la clase
    init {
        val x = calcularPotenciaDos(tamano)
        libres[x].add(0 to tamano - 1)
    }

    // Definimos los métodos de la clase los dividimos entre publicos y privados

    // METODOS PRIVADOS
    
    // funcion calcularCapacidad que calcula la capacidad de un bloque de memoria y recibe un parametro bloque que es un par de enteros y devuelve un entero que es la capacidad del bloque de memoria
    private fun calcularCapacidad(bloque: Pair<Int, Int>): Int {

        // Calculamos la capacidad del bloque
        val capacidad = bloque.second - bloque.first + 1
        return capacidad
    }

    // funcion calcularPotenciaDos que calcula la potencia de dos de un numero y recibe un parametro n que es un entero y devuelve un entero que es la potencia de dos de n 
    private fun calcularPotenciaDos(n: Int): Int {
        val x = Math.ceil(Math.log(n.toDouble()) / Math.log(2.0)).toInt();

        return x
    }

    // funcion obtenerDireccion que obtiene la direccion de un bloque de memoria y recibe dos parametros bloqueado y x que son enteros y devuelve un entero que es la direccion del bloque de memoria
    private fun obtenerDireccion(bloqueado: Pair<Int, Int>, x: Int): Int {

        // Calculamos la direccion del bloque
        val direccion: Int

        // Si el bloque esta en la primera mitad de la memoria entonces la direccion es el bloque menos la potencia de dos
        if ((bloqueado.first / (1 shl x)) % 2 == 0) {
            direccion = bloqueado.first - (1 shl x)
        // Si el bloque esta en la segunda mitad de la memoria entonces la direccion es el bloque mas la potencia de dos

        //shl es un operador que desplaza los bits de un numero a la izquierda
        } else {
            direccion = bloqueado.first + (1 shl x)
        }
        return direccion
    }
    
    // funcion encontrarBloque que encuentra un bloque de memoria libre y recibe un parametro potencia que es un entero y se crea un par de enteros que indica el bloque de memoria libre
    private fun encontrarBloque(potencia: Int): Pair<Int, Int>? {
        for (i in potencia until libres.size) {
            if (libres[i].isNotEmpty()) {
                var bloqueActual = libres[i].removeAt(0)
                for (j in (i - 1) downTo potencia) {
                    val Nuevo = bloqueActual.first to bloqueActual.first + (bloqueActual.second - bloqueActual.first) / 2
                    val Nuevo2 = bloqueActual.first + (bloqueActual.second - bloqueActual.first + 1) / 2 to bloqueActual.second
                    libres[j].add(Nuevo)
                    libres[j].add(Nuevo2)
                    bloqueActual = libres[j].removeAt(0)
                }
                return bloqueActual
            }
        }
        return null
    }

    // METODOS PUBLICOS

    // funcion mostrar que muestra el estado de la memoria
    fun mostrar() {
        println("| MEMORIA LIBRE | :")

        println("${libres} \n")

        bloqueados.forEach { (llave, bloqueado) ->
            println("[ASOCIADO]\n"+ "$llave asociado a bloque $bloqueado")
        }

    }

    // funcion reservar que reserva un espacio de memoria para un nombre y recibe dos parametros cantidad y nombre
    // cantidad es un entero que indica la cantidad de memoria que se desea reservar
    // nombre es un string que indica el nombre del usuario que desea reservar la memoria
    fun reservar(cantidad: Int, nombre: String): Boolean {
        if (nombre in bloqueados) {
            println("[ERROR]\n" + "$nombre ya tiene un espacio reservado")
            return false
        }

        val x = calcularPotenciaDos(cantidad)
        val bloqueado = encontrarBloque(x)

        if (bloqueado != null) {
            bloqueados[nombre] = bloqueado
            println("[RESERVADO]\n" + "Bloque $bloqueado para $nombre")
            return true
        }

        println("[ERROR]\n" + "Error: No se pudo $cantidad de memoria para $nombre ")
        return false
    }

    // funcion liberar que libera un espacio de memoria para un nombre y recibe un parametro nombre
    // nombre es un string que indica el nombre del usuario que desea liberar la memoria
    fun liberar(nombre: String): Boolean {
        val bloqueado = bloqueados[nombre]

        // Si el nombre no esta en la lista de bloqueados, entonces no hay espacio asignado

        if (bloqueado == null) {
            println("[ERROR]\n" + "Para $nombre NO HAY ESPACION ASIGNADO")
            return false
        }

        // Comenzamos a liberar el espacio de memoria
        val x = calcularPotenciaDos(calcularCapacidad(bloqueado))
        libres[x].add(bloqueado)

    
        val obtenerDireccion = obtenerDireccion(bloqueado, x)

    
        val i = libres[x].indexOfFirst { it.first == obtenerDireccion }

        if (i != -1) {
            val newbloqueado = if (bloqueado.first % (1 shl x) == 0) {
                bloqueado.first to bloqueado.first + (2 shl x) - 1
            } else {
                obtenerDireccion to obtenerDireccion + (2 shl x) - 1
            }
            libres[x + 1].add(newbloqueado)
            libres[x].removeAt(i)
            libres[x].removeAt(libres[x].size - 1)
        }

        // Eliminamos el nombre de la lista de bloqueados
        bloqueados.remove(nombre)
        println("[LIBERADO]\n" + "Bloque $nombre $bloqueado")
        return true
    }


}

