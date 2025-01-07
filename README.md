# ForoHubAlura
# JLForoHub

## Descripción

**JLForoHub** es una plataforma de foro en línea que permite a los usuarios registrarse, iniciar sesión y participar en conversaciones en diversos temas. La aplicación está construida utilizando Spring Boot y otros componentes modernos para asegurar un rendimiento eficiente y una experiencia de usuario fluida.

## Autor

- **Autor**: Jolumiba
- **GitHub**: [jolumiba](https://github.com/jolumiba)

## Repositorio

Este proyecto se encuentra en el repositorio de GitHub: [ForoHubAlura](https://github.com/jolumiba/ForoHubAlura)

## Características

- Autenticación y autorización de usuarios.
- Gestión de publicaciones y comentarios.
- Interfaz amigable y fácil de usar.
- Soporte para bases de datos MySQL o PostgreSQL.
  
## Tecnologías Utilizadas

- **Spring Boot**: Framework principal para el backend.
- **MySQL** o **PostgreSQL**: Base de datos relacional para almacenar los datos.
- **JWT**: Para la autenticación y autorización segura.
- **Thymeleaf**: Motor de plantillas para la vista en el frontend.

## Instalación

### Prerequisitos

1. Java 11 o superior.
2. MySQL o PostgreSQL (dependiendo de tu configuración).
3. Maven o Gradle para gestionar las dependencias.

### Pasos para ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/jolumiba/ForoHubAlura.git

Accede al directorio del proyecto:

bash
Copiar código
cd ForoHubAlura
Configura la base de datos en el archivo application.properties (en src/main/resources):

Para MySQL:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
Para PostgreSQL:

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/foro_hub
spring.datasource.username=your_postgresql_username
spring.datasource.password=your_postgresql_password
Ejecuta el proyecto:

Si usas Maven:

bash
Copiar código
mvn spring-boot:run
Si usas Gradle:

bash
Copiar código
gradle bootRun
Accede a la aplicación en tu navegador en la URL: http://localhost:8080.
