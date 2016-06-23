# BLPTest

**Requerimientos:**
  1. Debe crear una aplicación web que capture información de contacto con los siguientes datos: Nombre, Apellido, Correo Electrónico, Teléfono, Fecha de Nacimiento, Comentario.
  2. Los datos deben ser almacenados en una base de datos MySQL, la tabla debe contener información adicional sobre la fecha de registro y la información proporcionada por el navegador (Dirección IP, Agente, Sistema Operativo, etc).
  3. Debe utilizar el stack de desarrollo web de Java: JPA + EJB + JSF.
  4. Debe utilizar el servidor de aplicaciones Wildfly 10.

**Ambiente**
  - Sistema Operativo: Xubuntu 16.4
  - JDK: 8+, JEE7
  - IDE: Netbeans 8.1
  - Web Serevr: Wildfly 10 final
  - Base de Datos: MySQL 5.7
  - Framework: PrimeFaces 6.0 (puede usar el que se encuentra dentro del repositorio llamado: lib.zip)
  
**Instalación / Configuración**
  - Instalación de MySQL: apt-get install mysql-server
  - Instalación de WildFly:
    - Se descarga el .tgz en la version final, desde http://www.wildfly.org/downloads/
    - Una vez descargado se descomprime y se mueve el directorio al lugar deseado en mi caso: sudo mv wildfly-10.0.0.Final /usr/local/
    - Se cambian los privilegios y tambien el grupo al que pertenece el directorio:
      - sudo chgrp -R developer wildfly-10.0.0.Final
      - sudo chmod -R g+wx wildfly-10.0.0.Final
  - Creación de la Base de Datos, estructura de la tabla y el usuario: mysql -u root -p < script_mysql_test.sql
  - Descarga del JDBC Driver para MySQL: https://www.mysql.com/products/connector/
  - Crear un usuario ejecutando el script: WILDFLY_HOME/bin/add-user.sh
  - Creación del modulo para MySQL:
    - Crear el folder mysql/main en el directorio: WILDFLY_HOME/modules/system/layers/base/com
    - Copiar el JDBC Driver en el directorio: WILDFLY_HOME/modules/system/layers/base/com/mysql/main
    - Copiar el archivo module.xml que se encuentra en este repositorio
    - Ejecutar el script: jboss-cli.sh --connect, que se encuentra en el directorio: WILDFLY_HOME/bin
    - Ejecutar el siguiente comando: /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-class-name=com.mysql.jdbc.Driver)
    - Ingresar a: http://localhost:9990
    - Ingresar a Configuración -> Subsystems -> Datasources -> Non-XA agregar uno nuevo
    - Se selecciona MySQL Datasources y se continua llenado los datos que pida el wizar. Al momento de ingresar el usuario la contraceña para la conexión usar el que se creo con el script: script_mysql_test.sql
    - Una vez creado ingresar al nuevo Datasource y deshabilitarlo
    - Una vez deshabilitado procedemos a editar en el Tab "Pool" editamos los siguientes parametros:
      - Min Pool Size: 5
      - Max Pool Size: 15
    - Guardamos los cambios y procedemos a habilitar nuevamente el Datasource. 

