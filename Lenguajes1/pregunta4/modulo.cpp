/*
CI3641 - LENGUAJES DE PROGRAMACION I 
EXAMEN1 - PREGUNTA 4
AMARANTA VILLEGAS 16-11247
Libreria de vectores y operaciones
*/


#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;
//Construimos una clase vector

class Vector {

public:
    double a,b,c;

    //Constructor 
    Vector(){
        this->a = 0;
        this->b = 0;
        this->c = 0;
    }

    Vector(double _a , double _b, double _c ): a(_a), b(_b), c(_c){}
        
        //Suma de vectores 
        Vector operator+(const Vector v){
            return Vector(a + v.a, b + v.b, c + v.c);
        }

        //Resta de vectores 
        Vector operator-(const Vector v){
            return Vector(a - v.a, b - v.b, c - v.c);
        }

        // Producto cruz de vectores
        Vector operator*(const Vector v){
            return Vector(b * v.c - c * v.b, c * v.a - a * v.c, a * v.b - b * v.a);
        }

        //Producto punto 
        double operator%(Vector other){
            return (this-> a * other.a + this-> b * other.b + this->c * other.c);
        }

        // Norma del vector
        double operator&() {
            return sqrt(a * a + b * b + c * c);
        }
     
        Vector operator+(double scalar){
            return Vector(a + scalar, b + scalar, c + scalar);
        }

        Vector operator-(double scalar){
            return Vector(a - scalar, b - scalar, c - scalar);
        }

        Vector operator*(double scalar){
            return Vector(a * scalar, b * scalar, c * scalar);
        }
};
 