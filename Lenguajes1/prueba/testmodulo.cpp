#include "modulo.cpp"
using namespace std;
#include <cassert>

int main() {
    
    Vector a(1,2,3);
    Vector b(4,5,6);
    Vector c(7,8,9);

    Vector i = a + b;
    Vector d = a * b + c;
    Vector e = (b + b) * (c - a);
    double f = a % (c * b);
    Vector g = b + 3;
    Vector h = a * 3.0 + &b;

    // Imprimir resultados
    std::cout << "a + b = (" << i.x << ", " << i.y << ", " << i.z << ")\n";
    std::cout << "a * b + c = (" << d.x << ", " << d.y << ", " << d.z << ")\n";
    std::cout << "(b + b) * (c - a) = (" << e.x << ", " << e.y << ", " << e.z << ")\n";
    std::cout << "a % (c * b) = " << f << "\n";
    std::cout << "b + 3 = (" << g.x << ", " << g.y << ", " << g.z << ")\n";
    std::cout << "a * 3.0 + &b = (" << h.x << ", " << h.y << ", " << h.z << ")\n";

    return 0;

}