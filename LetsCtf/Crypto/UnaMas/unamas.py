import random

OUT = open('salida.txt', 'w')
FLAG = 'LetsCTF{????????????????????????????????}' #LetsCTF{[0-9a-f]{32}}
N = 64

l = len(FLAG) # 41
arr = [random.randint(1,pow(2, N)) for _ in range(l)] # [1, 2^64] x 41
OUT.write(f'{arr}\n')

s_arr = []
for i in range(len(FLAG) - 1):
    s_i = sum([arr[j]*ord(FLAG[j]) for j in range(l)])
    s_arr.append(s_i)
    arr = [arr[-1]] + arr[:-1]

OUT.write(f'{s_arr}\n')
