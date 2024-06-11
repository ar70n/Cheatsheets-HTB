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