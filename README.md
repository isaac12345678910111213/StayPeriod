# StayPeriod API

API REST desarrollada para la gestión y búsqueda de alquileres dirigidos a estudiantes universitarios. El sistema permite registrar propiedades, consultar opciones de alojamiento y administrar la información relacionada con los alquileres disponibles.

## Descripción

StayPeriod API es una aplicación desarrollada en Java que proporciona servicios REST para facilitar la búsqueda y gestión de alquileres estudiantiles.

La API permite:

- Consultar alquileres disponibles.
- Registrar nuevas propiedades.
- Actualizar información de alquileres.
- Eliminar registros de alquileres.
- Gestionar información de propietarios y estudiantes.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JSON
- Git y GitHub
- Postman

## Arquitectura del Proyecto

El proyecto sigue una arquitectura por capas para mejorar la organización y el mantenimiento del código:

### Controller

Gestiona las solicitudes HTTP y las respuestas de la API.

### Service

Contiene la lógica de negocio y las validaciones.

### Repository

Gestiona el acceso a la base de datos mediante JPA.

### Entity

Representa las entidades del sistema y su correspondencia con las tablas de la base de datos.

## Estructura General

```text
src/main/java
├── controller
├── service
├── repository
├── entity
├── dto
└── config

src/main/resources
└── application.properties
```

## Requisitos del Sistema

Antes de ejecutar el proyecto es necesario tener instalado:

- Java JDK 17 o superior
- Maven
- MySQL Server
- Git

## Instalación

### Ingresar al directorio

```bash
cd StayPeriod-api
```

### Instalar dependencias

```bash
mvn clean install
```

## Ejecución

Iniciar la aplicación:

```bash
mvn spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

## Formato de Intercambio de Datos

Todas las solicitudes y respuestas utilizan formato JSON.

## Endpoints

| Método HTTP | Endpoint | Descripción |
|------------|-----------|-------------|
|-----Módulo de Autenticación----------|
|POST| /api/auth/login → iniciar sesión
|POST| /api/auth/register → registrar usuario
|GET| /api/auth/profile → obtener perfil autenticado
|-----Módulo de Usuarios---------------|
|GET| /api/users → listar usuarios
|GET| /api/users/{rol} → obtener usuario por rol
|GET| /api/users/{id} → obtener usuario por ID
|POST| /api/users → crear usuario
|PUT| /api/users/{id} → actualizar usuario
|DELETE| /api/users/{id} → eliminar usuario
|------Módulo de Domicilio--------------|
|GET| /api/ address → listar domicilios
|GET| /api/ address /{id} → obtener espacio por ID
|POST| /api/ address → crear domicilio
|PUT| /api/ address /{id} → actualizar domicilio
|DELETE| /api/ address /{id} → eliminar domicilio
|-------Módulo de Solicitud-------------|
|GET| /api/ application → listar solicitud
|GET| /api/ application /{id} → obtener solicitud por ID
|POST| /api/ application → crear solicitud
|PUT| /api/ application /{id} → actualizar solicitud
|DELETE| /api/ application /{id} → cancelar solicitud
|-------Módulo de Publicación-----------|
|GET| /api/ publication → listar publicaciones
|GET|/api/ publication/{fecha} → obtener publicación por Fecha
|GET| /api/ publication /{id} → obtener publicación por ID
|POST| /api/ publication → crear publicación
|PUT| /api/ publication /{id} → actualizar publicación
|DELETE| /api/ publication /{id} → cancelar publicación


## Manejo de Errores

La API utiliza códigos de estado HTTP estándar.

| Código | Descripción |
|----------|-------------|
| 200 | Solicitud exitosa |
| 201 | Recurso creado correctamente |
| 400 | Error en la solicitud |
| 404 | Recurso no encontrado |
| 500 | Error interno del servidor |

## Equipo de Desarrollo

Bryan Almanza Espinoza C20268
Dariel Josue Rojas C06901
Isaac Díaz Rodríguez C38971

## Estado del Proyecto

En desarrollo académico.

## Licencia

Proyecto desarrollado con fines educativos y académicos.
