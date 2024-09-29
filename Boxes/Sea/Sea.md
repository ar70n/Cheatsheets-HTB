Mirando el codugo fuente de la pagina vemos que el tema que esta usando el CMS es bike por lo que vamos a intentar encontrar mas informacion para determinar de que CMS se trata y que version

ffuf -u http://sea.htb/themes/bike/FUZZ -w /usr/share/seclists/Discovery/Web-Content/common.txt -ic -c

        /'___\  /'___\           /'___\
       /\ \__/ /\ \__/  __  __  /\ \__/
       \ \ ,__\\ \ ,__\/\ \/\ \ \ \ ,__\
        \ \ \_/ \ \ \_/\ \ \_\ \ \ \ \_/
         \ \_\   \ \_\  \ \____/  \ \_\
          \/_/    \/_/   \/___/    \/_/

       v2.1.0-dev
________________________________________________

 :: Method           : GET
 :: URL              : http://sea.htb/themes/bike/FUZZ
 :: Wordlist         : FUZZ: /usr/share/seclists/Discovery/Web-Content/common.txt
 :: Follow redirects : false
 :: Calibration      : false
 :: Timeout          : 10
 :: Threads          : 40
 :: Matcher          : Response status: 200-299,301,302,307,401,403,405,500
________________________________________________

.htpasswd               [Status: 403, Size: 199, Words: 14, Lines: 8, Duration: 2354ms]
.htaccess               [Status: 403, Size: 199, Words: 14, Lines: 8, Duration: 2355ms]
404                     [Status: 200, Size: 3341, Words: 530, Lines: 85, Duration: 44ms]
Documents and Settings  [Status: 403, Size: 199, Words: 14, Lines: 8, Duration: 60ms]
.hta                    [Status: 403, Size: 199, Words: 14, Lines: 8, Duration: 3244ms]
LICENSE                 [Status: 200, Size: 1067, Words: 152, Lines: 22, Duration: 59ms]
Program Files           [Status: 403, Size: 199, Words: 14, Lines: 8, Duration: 49ms]
css                     [Status: 301, Size: 239, Words: 14, Lines: 8, Duration: 44ms]
home                    [Status: 200, Size: 3650, Words: 582, Lines: 87, Duration: 43ms]
img                     [Status: 301, Size: 239, Words: 14, Lines: 8, Duration: 41ms]
reports list            [Status: 403, Size: 199, Words: 14, Lines: 8, Duration: 43ms]
summary                 [Status: 200, Size: 66, Words: 9, Lines: 2, Duration: 100ms]
version                 [Status: 200, Size: 6, Words: 1, Lines: 2, Duration: 47ms]
:: Progress: [4734/4734] :: Job [1/1] :: 522 req/sec :: Duration: [0:00:15] :: Errors: 0 ::


