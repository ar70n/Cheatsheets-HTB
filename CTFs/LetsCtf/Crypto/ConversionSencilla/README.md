# Enunciado

Ayúdame a devolverlo a su estado original, no sé cómo hacerlo...

# Solución

El reto nos proporciona un código en Python y el output de ejecutar dicho código.

### Código Original: easy_conversion.py

```python
from const import flag

def bytes_to_integer(x: bytes) -> int:
    x = int.from_bytes(x, byteorder="big")
    return x

print(bytes_to_integer(flag))
```

El código importa la flag en bytes y la convierte al entero en decimal de esos bytes en big endian. Para resolver el reto, simplemente tenemos que pasar el entero a bytes de nuevo en big endian.   

Es importante tener en cuenta que al pasar el entero a bytes, la función `to_bytes` necesita la longitud en bits del entero. Como lo estamos pasando a bytes, esta longitud tiene que ser múltiplo de 8 (la longitud de un byte). Sumando 7 bits y dividiendo entre 8 nos aseguramos de que la longitud sea adecuada.

### Código de Solución: solucion.py

```python
def integer_to_bytes(x:int)->bytes:
    x = x.to_bytes((x.bit_length()+7) // 8 , 'big')
    return x

# Abre el archivo en modo de lectura
with open('output.txt', 'r') as f:
    # Lee el contenido del archivo
    contenido = f.read()

# Intenta convertir el contenido a un entero
try:
    numero = int(contenido)
    print(numero)
except ValueError:
    print("El contenido del archivo no se puede convertir a un entero")

# Convierte el entero a bytes
bytes = integer_to_bytes(numero)
print(bytes)
```