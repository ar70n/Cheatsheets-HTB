import os

from Crypto.Util.number import bytes_to_long, getPrime

flag = os.environb.get(b"FLAG", b"LetsCTF{th1s_1s_4_f4k3_fl4g}")

p = getPrime(1024)
q = getPrime(1024)
n = p * q
e = 0x10001
d = pow(e, -1, (p - 1) * (q - 1))

m = bytes_to_long(flag)
c = pow(m, e, n)
s = (pow(p, q, n) + pow(q, p, n)) % n

print(n)
print(e)
print(c)
print(s)
