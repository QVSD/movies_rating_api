# Movie Rating API

A **RESTful backend** built with **Spring Boot 3**, **Java 21**, and **JWT Authentication**, allowing users to register,
log in, rate movies, and view aggregated movie ratings.

This project demonstrates a clean, modular backend architecture using **Spring Security**, **OpenAPI/Swagger**, and *
*role-based access control (RBAC)*.

---

## Tech Stack

| Layer         | Technology                             |
|---------------|----------------------------------------|
| Language      | Java 21                                |
| Framework     | Spring Boot 3.5.6                      |
| Database      | MySQL + Spring Data JPA                |
| Security      | JWT (JSON Web Token) + Spring Security |
| Validation    | Jakarta Validation (`@Valid`)          |
| Documentation | OpenAPI / Swagger UI                   |
| Build Tool    | Gradle                                 |

---

## Features

User authentication and registration (JWT-based)
Role-based access control (ADMIN / USER)
CRUD operations for Movies and Ratings
Average movie rating calculation
Validation and exception handling
OpenAPI documentation for all endpoints
Global error handling for clean API responses

---

## Project Structure

```
src/main/java/com/example/movies_rating_api
│
├── controller/
│   ├── UserController.java
│   ├── MovieController.java
│   ├── RatingController.java
│   └── auth/AuthController.java
│
├── service/
│   ├── UserService.java
│   ├── MovieService.java
│   └── RatingService.java
│
├── repository/
│   ├── UserRepository.java
│   ├── MovieRepository.java
│   └── RatingRepository.java
│
├── model/
│   ├── User.java
│   ├── Movie.java
│   └── Rating.java
│
├── dto/
│   ├── UserDTO.java
│   ├── UserRegisterDTO.java
│   ├── MovieDTO.java
│   ├── RatingDTO.java
│   └── AuthResponse.java
│
├── security/
│   ├── JwtService.java
│   ├── JwtAuthFilter.java
│   ├── SecurityConfig.java
│   ├── ApplicationConfig.java
│   └── CustomUserDetailsService.java
│
└── exception/
    ├── GlobalExceptionHandler.java
    ├── ErrorResponse.java
    ├── UserNotFoundException.java
    └── ...
```

---

## Setup & Run

### Clone the repository

```bash
git clone https://github.com/QVSD/movie-rating-api.git
cd movie-rating-api
```

### Configure your MySQL database

Create a schema named `movies_api` and update your credentials in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movies_api
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=super_secret_key_123
jwt.expiration=3600000
```

### Run the application

```bash
./gradlew bootRun
```

### Access Swagger UI

➡️ [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Authentication Flow

### Register

`POST /auth/register`

```json
{
  "username": "alexcoco",
  "email": "alexcoco@mail.com",
  "password": "1234"
}
```

### Login

`POST /auth/login`

```json
{
  "email": "alex@mail.com",
  "password": "12345"
}
```

**Response:**

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
}
```

### Refresh Token

`POST /auth/refresh`
Body (plain text):

```
<refresh-token-here>
```

---

## Movies API

### Add a movie (ADMIN only)

`POST /movies`

```json
{
  "title": "Inception",
  "description": "A mind-bending thriller by Christopher Nolan.",
  "releaseYear": 2010
}
```

### Get all movies

`GET /movies`

### Rate a movie (USER)

`POST /ratings`

```json
{
  "value": 5,
  "comment": "Masterpiece!",
  "userId": 1,
  "movieId": 1
}
```

---

## Roles & Permissions

| Endpoint              | USER | ADMIN |
|-----------------------|------|-------|
| `POST /auth/**`       | YES  | YES   |
| `POST /users`         | YES  | YES   |
| `GET /movies`         | YES  | YES   |
| `POST /ratings`       | YES  | YES   |
| `POST /movies`        | NO   | YES   |
| `DELETE /movies/{id}` | NO   | YES   |

---

## Example Error Response

```json
{
  "timestamp": "2025-10-08T12:15:30",
  "status": 400,
  "message": "password: Password must be at least 4 characters"
}
```

---

## Future Improvements

- Add unit & integration tests, 
- Add pagination for movies
- Add Docker Compose setup
- Add refresh token rotation
- Build a React or Angular frontend

---

## Author

**QVSD [Null]**
- Freedom for the soul

---

## License

This project is licensed under the **MIT License** – feel free to use and modify it.

---