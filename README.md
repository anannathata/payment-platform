# Payment Platform

Microservices-based payment platform built with Java, Spring Boot, RabbitMQ, PostgreSQL and Docker.

This project simulates a distributed payment ecosystem using event-driven architecture and backend engineering best practices.

---

# Architecture

```text
client
   ↓
auth-service
   ↓ JWT
transaction-service
   ↓ RabbitMQ event
notification-service
```

---

# Services

## auth-service

Responsible for:

- authentication
- JWT generation
- credential validation

### Endpoint

```http
POST /auth/login
```

Runs on:

```text
localhost:8082
```

---

## transaction-service

Responsible for:

- transaction creation
- PostgreSQL persistence
- JWT-protected endpoints
- RabbitMQ event publishing

### Endpoint

```http
POST /transactions
Authorization: Bearer <token>
```

Runs on:

```text
localhost:8080
```

---

## notification-service

Responsible for:

- consuming transaction events
- asynchronous processing

Runs on:

```text
localhost:8081
```

---

# Technologies

## Backend

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Spring AMQP
- Maven

## Infrastructure

- PostgreSQL
- RabbitMQ
- Docker
- Docker Compose

## Quality

- JUnit 5
- Mockito
- GitHub Actions
- CI/CD
- Conventional Commits

---

# Running Locally

## Clone repository

```bash
git clone https://github.com/anannathata/payment-platform.git

cd payment-platform
```

---

## Start infrastructure

```bash
docker compose up -d
```

RabbitMQ Management UI:

```text
http://localhost:15672
```

Credentials:

```text
username: paymentuser
password: paymentpass
```

---

## Run services

### auth-service

```bash
cd auth-service
./mvnw spring-boot:run
```

### transaction-service

```bash
cd transaction-service
./mvnw spring-boot:run
```

### notification-service

```bash
cd notification-service
./mvnw spring-boot:run
```

---

# Testing

Run tests locally:

```bash
./mvnw test
```

---

# CI/CD

GitHub Actions pipeline validates:

- Maven build
- automated tests
- dependency resolution

Workflow:

```text
Feature Branch
    ↓
Pull Request
    ↓
GitHub Actions
    ↓
Merge to Main
```

---

# Development Workflow

- feature branches
- pull requests
- conventional commits
- CI/CD with GitHub Actions