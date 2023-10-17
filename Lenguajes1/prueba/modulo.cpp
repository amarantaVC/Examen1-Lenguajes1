#include <iostream>
#include <vector>
#include <cmath>

class Vector {
public:
    double x, y, z;

    Vector(double x = 0, double y = 0, double z = 0) : x(x), y(y), z(z) {}

    // Operador de suma
    Vector operator+(const Vector& other) const {
        return Vector(x + other.x, y + other.y, z + other.z);
    }

    // Operador de resta
    Vector operator-(const Vector& other) const {
        return Vector(x - other.x, y - other.y, z - other.z);
    }

    // Operador de producto cruz
    Vector operator*(const Vector& other) const {
        return Vector(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }

    // Operador de producto punto
    double operator%(const Vector& other) const {
        return x * other.x + y * other.y + z * other.z;
    }

    // Operador de producto por escalar (derecha)
    Vector operator*(double scalar) const {
        return Vector(x * scalar, y * scalar, z * scalar);
    }

    // Operador de norma
    double operator&() const {
        return std::sqrt(x * x + y * y + z * z);
    }
};


