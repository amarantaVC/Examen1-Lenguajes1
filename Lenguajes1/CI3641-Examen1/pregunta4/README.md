# EXAMEN 1 - CI3641 - PREGUNTA 4
# AMARANTA VILLEGAS 16-11247

## Instrucciones de Ejecucion

1. Abra la terminal o linea de comandos de linux o alguna de sus distribuciones
2. Navegue al directorio donde se encuentra el codigo fuente.
3. Verifique si tiene la librearia gcovr mediante el siguiente comando:
``` gcovr --version```
si la libreria no esta instalada, proceda a instalarla por medio del comando:

``` sudo apt install gcovr ```

Una vez realizado este paso.

4. Compile el programa ejecutando los siguientes comandos:

``` g++ --coverage -c -o modelo.o modelo.cpp  ```

``` g++ --coverage -c -o testmodulo.o testmodulo.cpp ```

``` g++ --coverage -o ejecutable modulo.o testmodulo.o ```


5. Ejecute el programa con:

``` ./ejecutable ```

6. Requisitos del Sistema


- Se requiere un compilador C++ (g++ recomendado) para compilar el código.
- Sistema operativo: Linux (puede funcionar en otros sistemas, pero se ha probado en este).

