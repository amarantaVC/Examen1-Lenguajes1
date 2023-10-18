# EXAMEN 1 - CI3641 - PREGUNTA 1
# AMARANTA VILLEGAS 16-11247

## Instrucciones de Ejecucion

1. Abra la terminal o linea de comandos de linux o alguna de sus distribuciones
2. Navegue al directorio donde se encuentra el codigo fuente.

3. proceda a instalarlar GNAT por medio de los siguientes comandos en el siguiente orden :

``` sudo apt-get update ```

``` sudo apt-get install gnat ```

``` sudo apt-get install gnat-gps```

Una vez realizado este paso.

4. verificamos si GNAT se instalo correctamente 
``` gnatmake -v ```

5. Compile y ejecute el programa con los siguientes comandos:

    ### 1.a) EJERCICIO DE ROTAR STRING

    ``` gnatmake rotandostring.adb ```
    
    ```  ./rotandostring ```

    ### 1.b) EJERCICIO DE MATRIZ TRANSPUESTA

    ``` gnatmake MatrizTranspuesta.adb ```

    ```  ./MatrizTranspuesta ```


6. Requisitos del Sistema


- Se requiere un compilador GNAT para compilar el código.
- Sistema operativo: Linux (puede funcionar en otros sistemas, pero se ha probado en este).

