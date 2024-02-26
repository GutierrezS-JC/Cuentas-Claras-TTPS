# Cuentas Claras
<img src="https://github.com/GutierrezS-JC/Cuentas-Claras-TTPS/blob/main/cuentas_claras_demo.gif">

## Descripción
El objetivo final del proyecto consistió en el diseño e implementación de un sistema que permita la gestión eficiente de gastos entre distintos de usuarios. 

Cuentas claras ofrece a los usuarios la posibilidad de crear, gestionar y liquidar gastos de manera colaborativa, facilitando así la organización y seguimiento de las finanzas compartidas. 

## Pre-requisitos

Primero debemos asegurarnos de tener Node.js, Angular CLI y Java JDK (17) instalados. 

- [Node.js](https://nodejs.org/)
- [Angular CLI](https://cli.angular.io/)
- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

Ademas el proyecto hace uso de una base de datos **MySQL** para almacenar la informacion de la aplicación por lo que también deberá asegurarse de tenerlo instalado. 

El nombre de la base de datos puede ser modificado dentro de la aplicación backend (Spring Boot) en el archivo **application.properties** sin embargo se sugiere el uso de *cuentas_claras*.

## Configuración del Entorno

### Clonar el Repositorio
```bash
git clone https://github.com/GutierrezS-JC/Cuentas-Claras-TTPS
cd Cuentas-Claras-TTPS
```

### Backend (Spring Boot)

1. Abri el proyecto navegando al directorio **backend** ``cd backend`` e importarlo en tu IDE de preferencia
   
2. Configuración de la base de datos en el archivo application.properties (opcional).
   ```
   #configuration (applicaction.properties)
   
     spring.jpa.hibernate.ddl-auto=update
     spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos?useSSL=false&allowPublicKeyRetrieval=true
     spring.datasource.hikari.data-source-properties.useSSL=false
     server.ssl.enabled=false
     
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
     spring.datasource.username=root
     spring.datasource.password=tu_password
     server.port=9090
     
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```
   Aunque el archivo application.properties ya se encuentra configurado con los datos necesarios y que fueron usados a lo largo del desarrollo de nuestra aplicación puede realizar cambios si lo considera necesario.
   Lo importante es que el nombre de la base de datos coincida con el nombre en este archivo ademas de tener las credenciales correctas.
   
   El puerto (9090) tambien puede ser modificado por el de su preferencia.
   
3. Ahora podes ejecutar la aplicación Spring Boot con ``./mvnw spring-boot:run``  
   Aunque es más facil hacerle click al icono de start de tu IDE favorito :)
   
    - La aplicación backend estará disponible en ``http://localhost:9090`` (Es decir, hace uso del puerto 9090).
    - Para explorar la documentación de la API, se puede acceder a Swagger UI en ``http://localhost:9090/swagger-ui/index.html#/``

### Frontend (Angular)

1. Abrimos el proyecto

   ```bash
   cd frontend
   code .
   ```

2. Instalamos las dependencias usando el comando ``npm install``

3. Por ultimo levantamos el servidor de desarrollo con el comando ``ng serve``

    - La aplicación frontend estará disponible en ``http://localhost:4200``
  
## Funcionalidades 

### Propuestas en el TP
 1. Registración de usuario
 2. Login de usuario
 3. Administracion de grupos
 4. Administracion de gastos
    - Implementado en el entregable 6.
    - Pendiente en el entregable 7.
 5. Administracion de pagos
    - No implementado.
 6. Listado de contactos/amigos
 7. Mantenimiento del saldo
 8. Categoria de los grupos
 9. Categoria de los gastos

### Extras
 1. Perfil de usuario
    - Con el objetivo de que cada usuario puede visualizar mas detalles adicionales sobre el usuario/contacto en la aplicacion
    - Se visualizan datos del usuario, grupos a los que pertenece y amigos/contactos que tiene
    - Se verifica si el usuario logueado esta visualizando su propio perfil (Mi perfil) pero quedan pendientes las opciones qeu se deberian habilitar en este caso
      
 2. Notificaciones con SweetAlert2
    - Las alertas default de los navegadores no siempre logran explicar o mostrar con facilidad que es lo que desean informar.
    - Se hace uso de esta libreria para las alertas de exito, rechazo e informativas.
      
 3. Token implementado con Spring Security
    - Para obtener un sistema de autenticacion mas robusto se decidio hacer uso de esta dependencia tanto para el inicio de sesion como para el registro.
    - Ademas se implementaron roles de usuario

## Autores
- :black_nib: [Juan Cruz Gutierrez](https://github.com/GutierrezS-JC)
  - Registro, Login, Home, Grupos, Contactos, Perfil de usuario, Guards, Interceptors
  - Endpoints para las vistas/componentes mencionados anteriormente, Spring Security, JWT Token, Roles
- :black_nib: [Matias Adorno](https://github.com/Matokuun)
  - Registro, Login, Navbar (Y sus respectivos endpoints)
- :black_nib: [Juliana Mattei](https://github.com/JulianaMattei)
  - Home, Gastos, Navbar (Y sus respectivos endpoints)

