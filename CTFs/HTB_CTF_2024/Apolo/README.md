Para la maquina Apolo que es una maquina facil (no te vas a encontrar ninguna mas facil), hay muchas de este estilo en HTB.
Agregamos el dominio de la maquina a nuestro archivo de hosts y hacemos un scan de puertos que no nos va a devolver nada interesante.
Mientras haces un poco de reconocimiento web y navegando por la propia web encontramos que hay un vHost (ai.apolo.htb). Accediendo a este despues de agregarlo en nuestro archivo de host ya vemos una pagina de inicio de sesion y si vemos el codigo fuente vemos que se esta usando una especie de framework que se llama flowise.
Una busqueda rapida en searchsploit nos da una vulnerabilidad que bypassea el inicio de sesion porque exponen sus credenciales en un endpoint de libre acceso en la api que usan.
Con las credenciales podemos hacer ssh al host y obtener la flag de usuario. Ahora para la escalada de privilegios usando sudo -l vemos que podemos usar como sudo el binario rclone.
Despues de investigar un poco que es lo que hacia y si tenia vulnerablibidades vemos que si creamos un link simbolico y usamos rclone se cambian los permisos de propietario sobre el archivo del que hemos hecho el linkado. Esto nos permite darnos permisos de propietario sobre la carpeta root y sobre root.txt obteniendo la segunda flag.

Referecnias: https://images.chainguard.dev/security/CVE-2024-52522
