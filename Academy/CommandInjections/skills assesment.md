# Skills Asessment

Nos logueamos en la web con las credenciales proporcionadas y vemos que tenemos un buscador implementado en el front-end y otro en el back-end pero que no parece funcionar.

Jugando un poco con las distintas acciones que nos permite hacer la web vemos una que es interesate: la opcion de mover/copiar un archivo.

Lo que estamos intentanto hacer es encontrar algun parametro inyectable. En relacion con las acciones de copiar/mover encontramos las seiguientes **requests**:

- http://\<IP>:\<PORT>/index.php?to=&from=
- http://\<IP>:\<PORT>/index.php?to=&from=&finished=1
- http://\<IP>:\<PORT>/index.php?to=&from=%finished=1&moved=1

Lo que hice fue para cada una de estas **GET** requests probar parametro a parametro ver si alguno de ellos era inyectable y efectivamente en la request de tipo `http://\<IP>:\<PORT>/index.php?to=&from=%finished=1&moved=1` el parametro `from`era inyectable. Para comprobar sin un parametro es inyectable tenemos que porbar los distintos operadores que nos permiten inyectar comandos y ver si alguno de estos nos da una respuesta diferente a lo esperado.

Por lo que ahora tenemos que contruir un payload que nos devuelva el contenido de `/flag.txt` lo cual lo podemos hacer facilmente con:

```
$echo -n 'cat /flag.txt' | base64                                      
Y2F0IC9mbGFnLnR4dA==
```

Nuestro payload será (paara que pase los distintos filtros):

`http://\<IP>:\<PORT>/index.php?to=&from=696212415.txt$(bash<<<$(base64%09-d<<<Y2F0IC9mbGFnLnR4dA==))&finish=1&move=1`

