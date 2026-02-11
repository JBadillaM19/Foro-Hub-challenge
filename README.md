# 📨 Foro Hub - Challenge Back-End
![Fecha](https://img.shields.io/badge/Release%20date-February%202026-yellow)
![Status](https://img.shields.io/badge/Status-completado-green)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-orange)

Bienvenido a **Foro Hub**, una API REST robusta desarrollada en Java con Spring Boot 4. Este proyecto es una solución integral para la gestión de un foro de discusión, permitiendo interactuar con tópicos, usuarios y seguridad avanzada, cumpliendo con los estándares de una arquitectura limpia y escalable. 
> **⚠️ Nota importante:** Este proyecto es parte de un challenge del programa Oracle Next Education con Alura Latam, es una **API Back-End** (no cuenta con interfaz de usuario o Front-End). Su funcionamiento está diseñado para ser consumido y probado mediante herramientas de simulación de peticiones HTTP como **Postman** o **Insomnia**.
---

## 🚀 Funcionalidades
* **Gestión de Tópicos (CRUD):** Registro, listado, visualización detallada, actualización y eliminación de tópicos.
* **Autenticación y Autorización:** Control de acceso riguroso mediante **Spring Security**.
* **Seguridad Stateless:** Implementación de tokens **JWT (JSON Web Tokens)** para una comunicación segura y sin estado.
* **Persistencia de Datos:** Base de datos relacional **MySQL** con **Flyway** para la gestión de versiones del esquema (Migrations).
* **Validaciones de Negocio:** Lógica personalizada para asegurar que no existan tópicos duplicados y que los datos obligatorios estén presentes.

---

## 🛠️ Tecnologías Utilizadas
* **Java 25**
* **Spring Boot 4**
* **Spring Data JPA** (Hibernate)
* **Spring Security**
* **Auth0 JWT** (Librería para generación y validación de tokens)
* **MySQL**
* **Flyway** (Gestión de base de datos)
* **Maven** (Gestor de dependencias)
* **Lombok** (Productividad del código)

---

## 🔐 Configuración de Seguridad

Para garantizar la integridad de los tokens, la API utiliza una clave secreta y un emisor específico. Debes configurar estas propiedades en el archivo `src/main/resources/application.properties`:

```properties
# Clave secreta para firmar los tokens JWT
api.security.token.secret=${JWT_SECRET:tu_clave_secreta_aqui}

# Nombre del emisor del token (Debe coincidir en generación y validación)
# Valor esperado: API Foro_Hub
```

#### Nota de seguridad: El sistema utiliza una arquitectura Stateless. Cada petición (excepto el login) debe incluir el encabezado Authorization: Bearer <tu_token_jwt>.

---

## 📖 Endpoints Principales

| Método | Endpoint | Descripción | Body / Parámetros | Acceso |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/login` | Autenticación y obtención de JWT | `login`, `clave` (JSON) | **Público** |
| **GET** | `/topicos` | Listado paginado de tópicos | `page`, `size`, `sort` (Query) | **Privado** |
| **GET** | `/topicos/{id}` | Detalle de un tópico único | `id` (Path Variable) | **Privado** |
| **POST** | `/topicos` | Registro de nuevo tópico | `titulo`, `mensaje`, `idUsuario`, `nombreCurso` | **Privado** |
| **PUT** | `/topicos/{id}` | Actualización de datos | `titulo`, `mensaje` (JSON) | **Privado** |
| **DELETE** | `/topicos/{id}` | Eliminación de un tópico | `id` (Path Variable) | **Privado** |

### Ejemplo de Body para Registro que se tiene que enviar que la petición sea exitosa (POST `/topicos`)
```json
{
  "titulo": "Duda sobre Spring Security",
  "mensaje": "No logro entender el filtro de autenticación",
  "idUsuario": 1,
  "nombreCurso": "Spring Boot 3"
}
```

---

## ⚙️ Instalación y Ejecución
Clonar el repositorio: git clone `<url-del-repo>`

Configurar Base de Datos: Crea una base de datos en MySQL y ajusta las credenciales en application.properties.

Ejecutar Migraciones: Flyway creará las tablas automáticamente al iniciar la aplicación.

Lanzar la App: Ejecuta el comando ./mvnw spring-boot:run o inicia desde tu IDE.

Probar: Utiliza Postman enviando las credenciales en formato JSON al endpoint de login.

---
## 🧪 Guía de Pruebas con Postman
Para probar esta API correctamente, sigue este flujo de trabajo:

Obtener el Token: * Realiza una petición POST a http://localhost:8080/login con un JSON que contenga tus credenciales.

Copia el valor del token recibido en la respuesta.

Configurar la Autorización: * En cualquier otro endpoint (ej. GET /topicos), ve a la pestaña Authorization en Postman.

Selecciona Auth Type: Bearer Token.

Pega el token en el campo correspondiente.

Realizar Peticiones: * Ahora puedes enviar la petición y recibirás un 200 OK. Si el token falta o es incorrecto, recibirás un 401 Unauthorized.

---

## 📈 Próximos Pasos y Mejoras (Roadmap)
Para llevar este proyecto al siguiente nivel, se contemplan las siguientes implementaciones:

Sistema de Respuestas (/respuestas): Implementar la capacidad de que los usuarios interactuén y comenten los tópicos creados

Perfiles de Usuario: Añadir roles (ROLE_USER, ROLE_ADMIN) para restringir la eliminación de tópicos solo a moderadores o al autor original.

Búsqueda Avanzada: Filtros dinámicos por nombre de curso, etiquetas o fecha de creación.

Documentación Interactiva: Implementación de Swagger/OpenAPI para facilitar el consumo de la API por el equipo de Front-End.

---

## ✒️ Autor
Desarrollado por Josué Badilla Madrigal como parte del programa ONE.
