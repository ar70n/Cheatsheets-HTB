Importante para el final: https://medium.com/@kartik.sharma522/mounting-bit-locker-encrypted-vhd-files-in-linux-4b3f543251f0


## Sobre como monatr y desmontar particiones segun copilot

Para montar un archivo VHD (Virtual Hard Disk) en Linux, puedes seguir estos pasos. Necesitarás tener instalado qemu-nbd, que es parte de QEMU, una solución de virtualización de software libre.


1. **Instalar qemu-nbd**(si aún no está instalado):  
En distribuciones basadas en Debian/Ubuntu:
    
        sudo apt-get update
        sudo apt-get install qemu-utils
   En distribuciones basadas en Red Hat/Fedora:
   
        sudo dnf install qemu

2. **Cargar el módulo nbd**:  
Antes de montar el VHD, necesitas asegurarte de que el módulo del kernel `nbd` está cargado.

        sudo modprobe nbd max_part=8

3. **Conectar el archivo VHD usando qemu-nbd**:  
Conecta el archivo VHD a un dispositivo nbd. Reemplaza `/ruta/al/archivo.vhd` con la ruta a tu archivo VHD.

        sudo qemu-nbd -c /dev/nbd0 /ruta/al/archivo.vhd

4. **Montar la particón**:  
Ahora, necesitas montar la partición específica del dispositivo nbd. Si no estás seguro de qué partición montar, puedes usar `fdisk` para listarlas.

        sudo fdisk -l /dev/nbd0

   Esto te mostrará las particiones disponibles. Luego, puedes montar la partición deseada (por ejemplo, `/dev/nbd0p1`) en un punto de montaje de tu elección (`/mnt` en este ejemplo).

5. **Acceder a los datos**:  
Ahora puedes acceder a los datos del VHD desde el punto de montaje especificado (`/mnt` en este ejemplo).

6. **Desmontar y desconectar**:  
Una vez que hayas terminado, asegúrate de desmontar y desconectar el dispositivo correctamente para evitar la corrupción de datos.

        sudo umount /mnt
        sudo qemu-nbd -d /dev/nbd0
   Y opcionalmente, si ya no necesitas el módulo `nbd`, puedes descargarlo:   
        
        sudo rmmod nbd

Este proceso te permite acceder al contenido de un archivo VHD en Linux como si fuera un disco físico más en el sistema.