# Contar las longitudes de las secuencias de ceros y unos
import re
import pandas as pd

pd.set_option('display.max_rows', None)

def read_ascii_file(filename):
    ascii_string = ""
    with open(filename, 'r') as file:
        while True:
            char = file.read(1)
            if not char:
                break
            ascii_string += char
    return ascii_string


def save_binary_string(binary_string, filename):
    # Convertir el string de 0s y 1s a bytes
    data_bytes = int(binary_string, 2).to_bytes((len(binary_string) + 7) // 8, byteorder='big')

    # Abrir un archivo en modo binario de escritura
    with open(filename, 'wb') as f:
        # Escribir los bytes en el archivo
        f.write(data_bytes)


binary_string = read_ascii_file('binario.txt')
# print(binary_string)  

save_binary_string(binary_string, 'binario.bin')

# Encuentra todas las secuencias de ceros y unos
sequences = re.findall(r'0+|1+', binary_string)

# Cuenta las longitudes de cada secuencia
lengths = [len(seq) for seq in sequences]

# Agrupar las longitudes en un DataFrame para analizar mejor
lengths_df = pd.DataFrame(lengths, columns=['Length'])

# Mostrar todas las filas del DataFrame
print(lengths_df)
