# URL Shortener Application

This is a simple URL shortener application built with Spring Boot.

## Features

- User authentication (Register/Login)
- Create short URLs for long URLs
- Redirect from short URL to the original URL
- Track the number of clicks on each short URL

## Technologies Used

- Java 21
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Lombok
- JWT for authentication

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/url-shortener-sb.git
   cd url-shortener-sb
   ```

2. **Create a MySQL database:**
   ```sql
   CREATE DATABASE url_shortener_db;
   ```

3. **Configure the application:**
   Open `src/main/resources/application.properties` and update the following properties with your MySQL database credentials and a JWT secret:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   jwt.secret=your_jwt_secret_key_your_jwt_secret_key_your_jwt_secret_key
   jwt.expiration=86400000
   ```

4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The application will start on `http://localhost:8080`.

## API Endpoints

### Authentication

- `POST /api/auth/register`: Register a new user.
  - Request body: `{"username": "user", "email": "user@example.com", "password": "password"}`
- `POST /api/auth/login`: Login and get a JWT token.
  - Request body: `{"username": "user", "password": "password"}`

### URL Shortener

- `POST /api/urls`: Create a new short URL (Authentication required).
  - Request body: `{"originalUrl": "https://www.google.com"}`
  - Headers: `Authorization: Bearer <your_jwt_token>`
- `GET /{shortUrl}`: Redirect to the original URL.

## Running Tests

To run the integration tests, use the following command:
```bash
./mvnw test
```
