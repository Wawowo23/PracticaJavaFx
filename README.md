### APLICACION DE FICHAJES ENTRADA/SALIDA
# ÍNDICE
1.- Descripción
2.- Requisitos
3.- Manual de Instalación
4.- Manual de uso general
5.- Manual de uso para el usuario
6.- Creador
# Descripción
Esta aplicación es un software sencillo de control de fichajes de entrada y salida para cualquier empresa. Este software permite tener una manera sencilla, segura y eficiente de
registrar cuando los empleados entran y salen de la empresa. Cuenta con un diseño muy sencillo e intuitivo por lo que cualquier empleado de la empresa será
capaz de entender como funciona esta aplicación sin tener ningún tipo de problema.
# REQUISITOS
Los requisitos que se necesitan para la ejecución del software son mínimos:
  * Para poder instalar y ejecutar el progrma debe tener el JDK actualizado a la versión más reciente. A continuación la explicación de qué es el JDK:
    - Java™ Development Kit (JDK) es un software para los desarrolladores de Java. Incluye el intérprete Java, clases Java y herramientas de desarrollo Java (JDT). Te permite escribir aplicaciones que se desarrollan una sola vez y se ejecutan en cualquier lugar de cualquier máquina virtual Java.
  * Una vez explicado el JDK, para poder actualizarlo deberás acceder a la página web oficial: https://www.oracle.com/es/java/technologies/downloads/
  * Cuando estés en la página oficial deberás bajar y seleccionar tu sistema operativo para instalar el paquete y de esa manera actualizarlo.
    
![Captura de pantalla 2024-11-14 162729](https://github.com/user-attachments/assets/74b6704e-60e6-4178-ae80-d17462e029b3)

  * Por último solo debes ejecutar el instalador y seguir los pasos que indica el programa para actualizarlo.
    
![Captura de pantalla 2024-11-14 162920](https://github.com/user-attachments/assets/b835e18d-3492-4774-b413-5b33a1f44e4f)

# Manual de Instalación
El proceso de instalación del software es muy sencillo:
  * Lo primero que tendremos que hacer será acceder al repositorio de GitHub donde se encuentra la aplicación: https://github.com/Wawowo23/PracticaJavaFx
  * Una vez estemos en la pagina veremos que hay un icono en verde que dice Code, lo presionaremos y obtendremos la siguiente ventana

![image](https://github.com/user-attachments/assets/570e1c06-a744-43ff-8857-678f6c10962f)

* Tendremos que pulsar el botón de Download ZIP y ya descargaremos el repositorio a nuestro dispositivo. Una vez tengamos el archivo .zip en nuestro dispositivo
* tendremos que descomprimirlo, dentro encontraremos 3 archivos esenciales: fichajesJavaFX.jar, proyectoFichajes.bat y FichajesApp.vbs.
* Estos 3 archivos deben encotrarse siempre en la misma carpeta, esto es crucial para el funcionamiento de la aplicacion.
* Antes de poder ejecutarlo necesitamos una ultima cosa que es instalar JavaFx de la siguiente página: https://gluonhq.com/products/javafx/

![image](https://github.com/user-attachments/assets/e16e698b-b1c3-4a01-b763-b4999f77e989)

* Una vez descargado tendremos que descomprimir el archivo y meter la carpeta que hayamos obtenido en la carpeta donde tengamos la aplicación,
* la carpeta donde tengamos la aplicacion debe quedar así:
  
![image](https://github.com/user-attachments/assets/00f2b9e8-3ce9-42d2-8f7a-66a081cfe864)

* Una vez tengamos así la carpeta podremos dar doble click al archivo "lanzarFichajes" y se abrirá la aplicación, como ya se ha advertido antes es necesario tener todos
* estos archivos siempre juntos en una misma carpeta pero esto no impide que se haga un acceso directo de "lanzarFichajes" y poner este en cualquier lugar de nuestro equipo.

# Manual de uso General
Esta aplicación está ligada a una base de datos, esto significa que opera en base a unos empleados ya insertados a una base de datos y los fichajes de los usuarios los guarda en 
la misma base de datos. Para poder usar correctamente la aplicación se requiere tener creada uns base de datos con una tabla de usuarios y otra tabla con los fichajes, las estructuras
de las tablas son las siguientes.

![image](https://github.com/user-attachments/assets/fc0c4247-519c-4ef2-b0ec-8f8a8a5a058f)

![image](https://github.com/user-attachments/assets/1ed1b420-d1ab-4473-8ad8-f15a28589f81)

Esta base de datos está alojada en mysql así que debemos tenerlo abierto siempre que queramos usar la aplicación para que esta pueda conectarse a la base de datos.

# Manual de uso para el Usuario

* Como ya se ha explicado previamente se debe ejecutar el archivo "lanzarFichajes" para poder abrir la aplicación.
* Una vez abierta la aplicación el empleado solo deberá introducir su clave para fichar automáticamente. Dependiendo de si es un fichaje de entrada o de salida se entregará un mensaje
* u otro, también se contempla la posibilidad de que el código introducido sea incorrecto saltando en este caso un mensaje para avisar de que la clave es incorrecta.
* Una vez salga el mensaje de fichaje correcto podrá aceptar y la aplicación se cerrará automáticamente habiendo cumplido su propósito.

![image](https://github.com/user-attachments/assets/7e1ecaa7-a88d-41d4-95c4-641738eee78f)


![image](https://github.com/user-attachments/assets/a237e26d-e4b2-4251-a6a5-f387d723d275)

* En el caso de que la clave introducida corresponda con la del admin no se fichará sino que en su lugar se mostrará un listado de todos los fichajes de todos los empleados
* ordenados por sus fechas.

![image](https://github.com/user-attachments/assets/a85bd529-ec7a-4669-b963-8a7860dff5b2)

#Creador
Este programa ha sido creado por Miguel Ángel Cámara Casado.
