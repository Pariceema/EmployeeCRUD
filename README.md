# 🚀 Employee CRUD Management System

A production-ready RESTful Employee Management System built using **Java 21**, **Spring Boot**, **Spring Security**, **JWT Authentication**, **MySQL**, and **Docker**.

This project demonstrates modern backend development practices including secure authentication, layered architecture, database integration, containerization, and REST API development.

---

# 📌 Features

- 🔐 JWT Authentication & Authorization
- 👤 User Registration
- 🔑 User Login
- 👨‍💼 Employee CRUD Operations
- 🏢 Department Management
- 🗄️ MySQL Database Integration
- ⚡ Spring Boot REST APIs
- 🔒 Password Encryption using BCrypt
- 🐳 Dockerized Application
- 📦 Docker Compose Support
- 🏗️ Layered Architecture
- 🛠 Exception Handling

---

# 🛠 Tech Stack

| Technology | Version |
|------------|----------|
| Java | 21 |
| Spring Boot | 3.x |
| Spring Security | Latest |
| JWT | JSON Web Token |
| Spring Data JPA | Latest |
| Hibernate | ORM |
| MySQL | 8 |
| Maven | Build Tool |
| Docker | Latest |
| Docker Compose | Latest |

---

# 📁 Project Structure

```
EmployeeCRUD
│
├── src
│   ├── main
│   │   ├── java
│   │   │    ├── controller
│   │   │    ├── service
│   │   │    ├── repository
│   │   │    ├── entity
│   │   │    ├── dto
│   │   │    ├── security
│   │   │    ├── config
│   │   │    └── exception
│   │   │
│   │   └── resources
│   │        └── application.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 🏗 Architecture

```
Client
   │
   ▼
REST API
(Spring Boot)
   │
   ▼
Spring Security
   │
JWT Authentication
   │
   ▼
Service Layer
   │
   ▼
Repository Layer (JPA)
   │
   ▼
MySQL Database
```

---

# 🔐 Authentication Flow

```
User Register
      │
      ▼
Password Encrypted
      │
      ▼
Saved in MySQL
      │
      ▼
User Login
      │
      ▼
JWT Token Generated
      │
      ▼
Client Sends JWT
      │
      ▼
Protected APIs Access
```

---

# 🐳 Docker Support

## Build Containers

```bash
docker compose up --build
```

## Stop Containers

```bash
docker compose down
```

---

# ▶ Run Without Docker

Clone Repository

```bash
git clone https://github.com/Pariceema/EmployeeCRUD.git
```

Go to Project

```bash
cd EmployeeCRUD
```

Run

```bash
mvn spring-boot:run
```

---

# ⚙ Environment Variables

Create a `.env` file:

```properties
DB_URL=jdbc:mysql://mysql:3306/employeecrud
DB_USERNAME=root
DB_PASSWORD=root
JWT_SECRETKEY=YOUR_SECRET_KEY
```

> **Do not commit your `.env` file to GitHub.**

---

# 📮 API Endpoints

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | /api/userlogin/register |
| POST | /api/userlogin/login |

## Employee

| Method | Endpoint |
|---------|----------|
| GET | /api/employees |
| GET | /api/employees/{id} |
| POST | /api/employees |
| PUT | /api/employees/{id} |
| DELETE | /api/employees/{id} |

> Update the endpoint names if your controller mappings differ.

---

# 🧪 Testing

API testing was performed using **Postman**.

---

# 🔒 Security

- JWT Authentication
- BCrypt Password Encryption
- Spring Security
- Stateless Authentication

---

# 🚀 Future Improvements

- Swagger/OpenAPI Documentation
- Role-Based Authorization (Admin/User)
- Unit & Integration Testing
- GitHub Actions CI/CD
- Docker Hub Image Publishing
- AWS Deployment
- Kubernetes Deployment

---

# 👩‍💻 Author

**Pariceema Macwan**

Java Backend Developer

---

# ⭐ If you like this project

Please give this repository a **Star ⭐**.

---

## 📄 License

This project is created for learning and portfolio purposes.

## Swagger Documentation (Coming Soon)