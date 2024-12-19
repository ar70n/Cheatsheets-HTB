# Escritura

Tenemos dos etapas de encriptación:

- Etapa 1**: Cifrado basado en una permutación aleatoria de `[0, 8)`.

- Etapa 2**: La hora actual se utiliza como semilla y se añade al mensaje cifrado. Realiza una operación XOR con `0x42` para obtener la matriz de bytes final.

Podemos descifrar utilizando el siguiente método:

1. Para recuperar la hora, simplemente obtenemos los últimos 18 bytes de nuestras salida y realizamos una operación XOR con con `0x42`.

2. Introducimos esta hora en la Etapa 2 para obtener el mensaje cifrado de la Etapa 1 (eliminando los últimos 18 caracteres, ya que la hora no está relacionada con el mensaje original)

3. Invierte el cifrado de la etapa 1 forzando la clave con permutaciones `¡8!`.

Ver script.py

Referencias: https://github.com/project-sekai-ctf/sekaictf-2022/blob/main/crypto/time-capsule/solution/README.md