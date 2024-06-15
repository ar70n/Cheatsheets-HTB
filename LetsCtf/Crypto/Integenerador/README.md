# Enunciado

Este generador devuelve un integer de 16 dígitos si introduces cualquier integer entre 0 y 2^35. ¿Podrías decirnos cuáles son los valores de flag1, flag2 y flag3?

    Formato del flag: LetsCTF{flag1_flag2_flag3}

# Solución

Dado el generador de enteros, mi primer enfoque fue encontrar la flag por fuerza bruta.
Probé todos los posibles números desde el 0 hasta $2^{35}$ y ver cuándo alguno de estos coincidía con alguno de los números del output.txt dado.


El primer flag fue instantáneo ya que es el número 0, la segunda flag también fue relativamente rápida, pero para la tercera estaba tardando demasiado, así que mientras tanto pensé en cómo podía descifrarla manualmente o con un script.


Pero antes intenté también empezar desde $2^{35}$ hasta el 0 a ver si por casualidad la encontraba más rápido y para mi sorpresa la encontró instantáneamente, por lo que no tuve que pensar nada.

