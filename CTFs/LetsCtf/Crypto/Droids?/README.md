# Enunciado

AtihRIU{iwtht_pgtci_iwt_sgdxsh_ndjgt_addzxcv_udg}

# Solucion

El enunciado nos da un string que está encriptado. Nos damos cuenta de que tiene estructura de flag por las llaves y por las mayusculas y minusculas "LetsCTF" encajaría en el principio del string. Pero esto es solo una observación.

Mi primera suposición fue que podría ser un cifrado cesar y efectivamente si cogemos la "A" y la "L" y hacemos una resta en ASCII vemos que están a 11 letras de distancia, ahora cogemos la "t" y la "e" y vemos que estńa a 11 letras de distancia (desde la t). Estamos intentando descifrar el cifrado sabiendo que el principio de la flag es LetsCTF

Escribimos un script en python que nos haga la conversion y acabamos.

```python

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
```