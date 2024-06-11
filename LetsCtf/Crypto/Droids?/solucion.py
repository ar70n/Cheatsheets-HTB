
## Solucion 1
def cifradoCesar(texto, clave, alfabeto='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'):
    textocifrado=str() # Cadena vacía (también '')
    for letra in texto:
        if letra in alfabeto: # Si el caracter está en alfabeto, se cambia
            letra=alfabeto[(alfabeto.index(letra)+clave)%len(alfabeto)]
        textocifrado+=letra # Se almacena en el cifrado (cambiada o tal cual)
    return textocifrado



abc="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" #alfabeto a utilizar

cifrado = "AtihRIU{iwtht_pgtci_iwt_sgdxsh_ndjgt_addzxcv_udg}"
for k in range(len(abc)):#Posibles claves
    print(k, cifradoCesar(cifrado, k, abc))

## Solucion 2 distinguiendo mayusculas y minusculas

def descifrar_cesar(cifrado, desplazamiento):
    descifrado = ""

    for c in cifrado:
        if c.isalpha():
            ascii_offset = ord('a') if c.islower() else ord('A')
            descifrado += chr((ord(c) - ascii_offset - desplazamiento) % 26 + ascii_offset)
        else:
            descifrado += c

    return descifrado

cifrado = "AtihRIU{iwtht_pgtci_iwt_sgdxsh_ndjgt_addzxcv_udg}"
for i in range(26):
    print(f"Desplazamiento {i}: {descifrar_cesar(cifrado, i)}")