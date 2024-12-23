# Sistema de Gestión de Tareas para Estudiantes

Este proyecto es un sistema de gestión de tareas diseñado para estudiantes. Utiliza una arquitectura de microservicios para garantizar escalabilidad, modularidad y facilidad de mantenimiento. Los microservicios incluyen un servidor Eureka, un servicio de usuarios con JWT para autenticación y un servicio para gestionar tareas y materias.

## Tabla de Contenidos

- [Características](#características)
- [Arquitectura del Sistema](#arquitectura-del-sistema)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Microservicios](#microservicios)
  - [Eureka Server](#eureka-server)
  - [Servicio de Usuarios](#servicio-de-usuarios)
  - [Servicio de Gestión de Tareas y Materias](#servicio-de-gestión-de-tareas-y-materias)
- [Configuración](#configuración)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- **Gestión de tareas y materias**: Los estudiantes pueden crear, actualizar y eliminar tareas y asignarlas a materias.
- **Autenticación segura**: Uso de JWT (JSON Web Token) para la autenticación y autorización de usuarios.
- **Servicio de descubrimiento**: Implementación de Eureka Server para el registro y descubrimiento de microservicios.
- **Diseño modular**: Cada funcionalidad clave está separada en un microservicio independiente.

## Arquitectura del Sistema

El sistema consta de los siguientes componentes principales:

- **Eureka Server**: Maneja el registro y descubrimiento de microservicios.
- **Servicio de Usuarios**: Gestión de autenticación y autorización con JWT.
- **Servicio de Gestión de Tareas y Materias**: Manejo de las operaciones CRUD para tareas y materias.


## Requisitos

- **Java 17 o superior**
- **Maven 3.8 o superior**
- **Docker (opcional)**
- **Base de datos MySQL o PostgreSQL**
- **Redis (opcional para almacenamiento en caché)**

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/usuario/sistema-gestion-tareas.git
   cd sistema-gestion-tareas
   ```
2. Configura las variables de entorno requeridas (ver [Configuración](#configuración)).
3. Construye los microservicios:
   ```bash
   mvn clean install
   ```
4. Ejecuta cada microservicio desde su directorio usando Maven o Docker.

## Microservicios

### Eureka Server

- **Descripción**: Servicio de descubrimiento para registrar y localizar otros microservicios.
- **Puerto predeterminado**: `8761`
- **Inicio**:
  ```bash
  mvn spring-boot:run
  ```

### Servicio de Usuarios

- **Descripción**: Proporciona endpoints para registro, inicio de sesión y gestión de usuarios con JWT.
- **Puerto predeterminado**: `8081`
- **Endpoints principales**:
  - `POST /api/users/register`: Registra un nuevo usuario.
  - `POST /api/users/login`: Inicia sesión y genera un token JWT.
  - `GET /api/users/me`: Obtiene los detalles del usuario autenticado.
- **Inicio**:
  ```bash
  mvn spring-boot:run
  ```

### Servicio de Gestión de Tareas y Materias

- **Descripción**: Maneja la creación, actualización, eliminación y consulta de tareas y materias.
- **Puerto predeterminado**: `8082`
- **Endpoints principales**:
  - `POST /api/tasks`: Crea una nueva tarea.
  - `GET /api/tasks`: Lista todas las tareas.
  - `PUT /api/tasks/{id}`: Actualiza una tarea existente.
  - `DELETE /api/tasks/{id}`: Elimina una tarea.
- **Inicio**:
  ```bash
  mvn spring-boot:run
  ```

## Configuración

Cada microservicio utiliza un archivo `application.properties` o `application.yml` para la configuración. Asegúrate de configurar:

- **Base de datos**: Configura las credenciales y URL para tu base de datos en los microservicios que lo requieran.
- **JWT**: Proporciona una clave secreta para la generación de tokens JWT en el servicio de usuarios.
- **Eureka Server**: Configura los microservicios para que se registren en el servidor Eureka.

Ejemplo de configuración para el Servicio de Usuarios:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_usuarios
spring.datasource.username=root
spring.datasource.password=123456
jwt.secret=miClaveSecreta
```

## Contribuciones

¡Contribuciones son bienvenidas! Por favor, sigue los siguientes pasos:

1. Realiza un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad o corrección.
3. Envía un pull request describiendo tus cambios.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
