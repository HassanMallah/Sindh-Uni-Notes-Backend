# Sindh Uni Notes — Backend

REST API for the **Sindh Uni Notes** platform — a resource-sharing system built to help CS students at the University of Sindh access lecture notes, past papers, and study materials.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.3 |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| Utilities | Lombok |

## Features

- RESTful API built with Spring Web MVC
- PostgreSQL integration via Spring Data JPA
- Google Drive integration for file storage and retrieval
- Clean layered architecture (Controller → Service → Repository)

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL running locally

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/HassanMallah/Sindh-Uni-Notes-Backend.git
   cd Sindh-Uni-Notes-Backend
   ```

2. **Configure the database**

   Create a PostgreSQL database and update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sindhuninotes
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   The API will be available at `http://localhost:8080`.

## Project Structure

```
src/
└── main/
    └── java/com/hassan/
        ├── controller/    # REST controllers
        ├── service/       # Business logic
        ├── repository/    # JPA repositories
        └── model/         # JPA entities
```

## Author

**Hassan Mallah**  
CS Student, University of Sindh, Jamshoro  
[GitHub](https://github.com/HassanMallah) · [Portfolio](https://hassanmallah.github.io)
