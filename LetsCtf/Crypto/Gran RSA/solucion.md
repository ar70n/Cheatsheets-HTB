Notas:
 - Fuerza bruta con los k posibles
 - getPrime(2048)^2 es mucho menor que factorial(k)
 - gcd para sacar getPrime(256)


## Analizando el enunciado

El enunciado nos adjunta un archivo `ecrypt.py`

Este codgio genera los dos primos p y q de 1024 bits cada uno.
Despues, primero genera unas claves con un exponente publico `e`desconocido.

Depsues genera un entero aleatorio $k\in[600,1200]$ y calcula su factorial $f=k!$ para calcular el valor leak que es hilo del que va a habe rque tirar:

$$leak = \left( e^2 + (e\cdot d - 1)\cdot f\right)\cdot r + k$$

Donde $r$ es un numero primero de 256 bits desconocido.

Ahora genera otras claves esta vez con el exponente publico `e=65537`, se encypta la flag y se nos muestran los valores `n,c,leak`


## Solucion

La primera idea que se nos tiene que venir a la mente es hacer bruteforce de los valores de `k`ya que para el $k\in[600,1200]$ correcto tendremos 


$$leak - k  = \left(e^2 + (e\cdot d - 1)\cdot f\right)\cdot r \implies  leak - k = e^2\cdot r + (e\cdot d - 1)\cdot f\cdot r$$

Tomando $leak-k$ como divideno, $(e\cdot d-1)f$ como divisor (ya que $k! >> e^2$) y $e^2\cdot r$ como resto podemos deducir lo siguiente:

$$\frac{leak-k}{f} =^{div. entera} (e\cdot d -1)r$$
$$leak-k ~~mod(f)=e^2\cdot r$$

Entonces podemos usar el máximo común divisor para enonctrar `r=getPrime(256)`

A continuación, podemos hallar los 2047 bits
de la primera clave RSA calculando la raíz cuadrada del resto partido por r.

Ahora ya podemos calcular $\phi(n)$ ya que 

$$e\cdot d -1 \equiv 0~ (\phi(n)) \implies e\cdot d - 1 = \phi(n)\cdot t , ~~t\in\mathbb(Z)$$

(Esta parte no la entiendo muy bien)

En teoría no nos hace falta calcular $t$ porque el modulo inverso de $\phi(n)$ es igual que el modulo inverso de $\phi(n)\cdot t, ~\forall ~t$

Por lo que calculando $d_0 = 65537^{-1}~~mod(t\cdot \phi(n))$ podremos obtener el mensaje original:

$$
\begin{align}
c^{d_0} & = c^{(65537^{-1} \mod{t \cdot \phi(n)})} \\
& = c^{65537^{-1} + l \cdot t \cdot \phi(n)} = c^{65537^{-1}} \cdot c^{l \cdot t \cdot \phi(n)} \quad l \in \mathbb{Z} \\
& = c^{65537^{-1}} = (m^{65537})^{65537^{-1}} \\ 
& = m \mod{n}
\end{align}$$




Ver `script.py` para la implementacion.



Referencias: https://7rocky.github.io/en/ctf/other/teamitaly-ctf/big-rsa/