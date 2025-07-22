# Spring Boot JWT Authentication Example

This project demonstrates a simple implementation of **Spring Security with JWT** for user authentication in a Spring Boot application.

### 🔐 Features

- User registration (`/api/auth/register`)
- User login with username and password (`/api/auth/login`)
- JWT token generation upon successful login
- Token-based authentication for protected endpoints
- Secure password storage using BCrypt

---

## 🚀 Tech Stack

- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JSON Web Token (JWT)

---

# 🛠️ Setup Instructions

## 1. Clone the repo

```
git clone https://github.com/your-username/spring-security-jwt-auth.git
cd spring-security-jwt-auth
```

## . Configure your database in application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```


## 🧪 API Endpoints
```/Register
POST /register
Content-Type: application/json

{
  "username": "testuser",
  "password": "testpass"
}
```

```/Login

POST /login
Content-Type: application/json

{
  "username": "testuser",
  "password": "testpass"
}
```
✅ Returns a JWT token on successful login.

## 🔒 Secured Endpoints
Include the token in the request header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🔧 Dependencies Used and Their Purpose
- spring-boot-starter-security
Enables Spring Security features like authentication, authorization, filters, and password encoding.

- jjwt-api
Provides the main API to create, parse, and validate JWT tokens.

- jjwt-impl
Contains the internal implementation required by jjwt-api for signing and verifying tokens (runtime-only).

- jjwt-jackson
Integrates Jackson for JSON parsing inside JWT claims (used for custom payloads).

- spring-boot-starter-web
Enables REST API development using Spring MVC and embedded Tomcat server.

- spring-boot-starter-data-jpa
Provides JPA and Hibernate support for database operations using repository interfaces.

- postgresql
PostgreSQL JDBC driver to connect your application to a PostgreSQL database.

- spring-security-test
Adds support for writing test cases for secured endpoints (mock authentication).
