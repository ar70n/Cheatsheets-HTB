from Crypto.PublicKey import RSA

RSAkeys = RSA.generate(2048)
p = RSAkeys.p
q = RSAkeys.q
n = RSAkeys.n
e = RSAkeys.e
m = b"LetsCTF{<REDACTED>}"
c = pow(int.from_bytes(m, "big"), e, n)



mask = int("55" * 128, 16)

r = p & mask
mask = mask << 1
r += q & mask


# mask      = 0101 0101 0101 ...0101
# mask << 1 = 1010 1010 1010 ...1010
# r = (p & mask) + (q &(mask << 1))
# Al sumarse no va a haber carry, porque los bits de p & mask y q & mask << 1 no se solapan


print(f"p = {p}")
print(f"q = {q}")


pa = ""
qa = ""
i = 0
for elem in bin(r).lstrip("0b"):
    if i % 2 == 0:
        pa+= elem
        qa+= '0'
    else:
        qa+= elem
        pa+= '0'
    i += 1
pa = int(pa,2)
qa = int(qa,2)


# Asumiendo que tienes las funciones next_prime() y is_prime() definidas

# Incrementa pa y qa solo en las posiciones donde hay un bit faltante
mask_pa = int('10' * (len(bin(max(p, q))) // 2), 2)  # Máscara para los bits perdidos de p
mask_qa = mask_pa << 1  # Máscara para los bits perdidos de q

while pa * qa != n:
    if pa & mask_pa != p & mask_pa:
        pa = next_prime(pa)
    if qa & mask_qa != q & mask_qa:
        qa = next_prime(qa)

print(f"Recovered p = {pa}")
print(f"Recovered q = {qa}")

# print(f"p = {pa}")
# print(f"q = {qa}")


print(f"n = {n}")
print(f"e = {e}")
print(f"c = {c}")
print(f"r = {r}")


