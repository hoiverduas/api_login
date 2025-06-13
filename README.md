Autenticación con DummyJSON – Spring Boot API
✅ Descripción
Esta API REST permite autenticarse contra el servicio externo DummyJSON, registrar los accesos en una base de datos PostgreSQL y consultar información del usuario autenticado, así como la lista de usuarios de prueba disponibles.

🚀 Tecnologías Usadas
Java 21

Spring Boot 3

Spring Web / Feign Client

PostgreSQL + JPA (Hibernate)

Lombok

Gradele

📦 Endpoints Disponibles
🔐 1. Autenticación de Usuario
POST  http://localhost:8080/api/v1/auth/login
Autentica un usuario contra DummyJSON y guarda el registro en la base de datos.

🧾 Ejemplo de Request
json
Copiar
Editar

{
  "username": "emilys",
  "password": "emilyspass"
}

🧪 Curl
bash
Copiar
Editar
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "emilys", "password": "emilyspass"}'
  
✅ Ejemplo de Response
json
Copiar
Editar
{
  "id": 1,
  "username": "emilys",
  "email": "emily.johnson@x.dummyjson.com",
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5..."
}


👤 2. Obtener Usuario Autenticado
GET  http://localhost:8080/api/v1/auth/me
Obtiene la información del usuario autenticado.

agrega  desde postman en headers

Key: Authorization
Value: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

🔐 Header
makefile
Copiar
Editar
Authorization: accessToken

🧪 Curl
bash
Copiar
Editar
curl -X GET http://localhost:8080/api/v1/auth/me
  -H "Authorization: <accessToken>"


  
📋 3. Obtener Lista de Usuarios de Prueba

GET /api/v1/auth/users
Retorna todos los usuarios disponibles en DummyJSON (para pruebas de login).


🧪 Curl
bash
Copiar
Editar
curl -X GET http://localhost:8080/api/v1/auth/users
