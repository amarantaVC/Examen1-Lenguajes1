/*
CI3641 - LENGUAJES DE PROGRAMACION I 
EXAMEN1 - PREGUNTA 4
AMARANTA VILLEGAS 16-11247
Cliente de la libreria de vectores y operaciones 
para hacer pruebas

*/

#include "modulo.cpp"
using namespace std;
#include <cassert>

int main() {

    //construimos los vectores
    
    Vector a(32, 0, -10);
    Vector b(377.25, 598, 152);
    Vector c(-462, -5, 63);   
    Vector d(-16, 11 ,247);

    Vector e;
    
    cout << "--------------------------------------------------------------------------------\n" << endl;
    cout << "Pruebas\n" << endl;
    cout << "--------------------------------------------------------------------------------\n" << endl;
                
    //imprimir vectores
    cout << "a : ( " << a.a <<"," << a.b << "," << a.c << " )\n" << endl;
    cout << "b : ( " << b.a <<"," << b.b << "," << b.c << " )\n" << endl;
    cout << "c : ( " << c.a <<"," << c.b << "," << c.c << " )\n" << endl;
    cout << "d : ( " << d.a <<"," << d.b << "," << d.c << " )\n" << endl;
    cout << "e : ( " << e.a <<"," << e.b << "," << e.c << " )\n" << endl;
    
    // prueba de suma de vectores
    Vector sum = b + c;
    cout << "Suma de vectores: " << sum.a << " " << sum.b << " " << sum.c << "\n"<< endl;

    // prueba de resta de vectores
    Vector resta = a * d + c;

    cout << "Resta de vectores: " << resta.a << " " << resta.b << " " << resta.c << "\n"<< endl;

    // prueba operacion 1

    Vector Operacion1 = (b + d) * (c - a);

    cout << "(b + d) * (c - a): " << Operacion1.a << " " << Operacion1.b << " " << Operacion1.c << "\n"<< endl;

    // prueba operacion 2

    double operacion2 = a % (c * b);

    cout << "a % (c * b) : " << operacion2 << "\n"<< endl;

    // prueba operacion 3

    Vector operacion3 = b + 9;
    cout << "b + 9: (" << operacion3.a << ", " << operacion3.b << ", " << operacion3.c << ")" << "\n"<< endl;
    
    Vector operacion4 = a * 6.2 + b;
    cout << "a * 6.2 + b: (" << operacion4.a << ", " << operacion4.b << ", " << operacion4.c << ")" << "\n"<< endl;

    // prueba operacion 5

    double operacion5 = a.norm();
    cout << "norma de a: " << operacion5 << "\n"<< endl;

    // prueba operacion 6
    Vector operacion6 = a + b + c + d - 362;
    cout << "a + b + c + d - 362: (" << operacion6.a << ", " << operacion6.b << ", " << operacion6.c << ")" << "\n"<< endl;


    return 0;

}   