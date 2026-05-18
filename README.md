# Payment Platform

Microservices-based payment platform built with Java, Spring Boot, RabbitMQ, Docker and CI/CD practices.

## Overview

This project simulates a distributed payment processing ecosystem inspired by real-world financial platforms.

The architecture is based on independent microservices communicating asynchronously through RabbitMQ.

Current implemented services:

* `transaction-service` → creates and stores payment transactions
* `notification-service` → consumes transaction events asynchronously

## Architecture

```text
transaction-service
        |
        | publish event
        v
    RabbitMQ
        |
        | consume event
        v
notification-service
```

## Technologies

### Backend

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring AMQP
* Maven
* Lombok

### Infrastructure

* PostgreSQL
* RabbitMQ
* Docker
* Docker Compose

### Quality & DevOps

* JUnit 5
* Mockito
* GitHub Actions
* CI/CD pipeline
* Feature branch workflow
* Pull Requests
* Conventional Commits

---

# Services

## transaction-service

Responsible for:

* transaction creation
* persistence with PostgreSQL
* publishing transaction events to RabbitMQ
* API documentation
* validation and exception handling

### Features

* RESTful API
* DTO validation
* Global exception handling
* Swagger/OpenAPI
* Event publishing
* Unit tests

### Endpoint

#### Create transaction

```http
POST /transactions
```

Example request:

```json
{
  "accountId": "11111111-1111-1111-1111-111111111111",
  "name": "nana",
  "amount": 299.90
}
```

---

## notification-service

Responsible for:

* consuming transaction events
* asynchronous processing
* event-driven communication

### Features

* RabbitMQ consumer
* JSON event conversion
* asynchronous event processing

---

# Running Locally

## Requirements

* Java 17+
* Maven
* Docker Desktop

---

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

Services:

* PostgreSQL → `localhost:5432`
* RabbitMQ → `localhost:5672`
* RabbitMQ Management → `http://localhost:15672`

RabbitMQ credentials:

```text
username: paymentuser
password: paymentpass
```

---

## Run transaction-service

```bash
cd transaction-service

./mvnw spring-boot:run
```

Runs on:

```text
http://localhost:8080
```

---

## Run notification-service

```bash
cd notification-service

./mvnw spring-boot:run
```

Runs on:

```text
http://localhost:8081
```

---

# RabbitMQ Flow

1. `transaction-service` creates a transaction
2. Event is published to RabbitMQ
3. `notification-service` consumes the event
4. Queue message is acknowledged and removed

---

# CI/CD

This project uses GitHub Actions for Continuous Integration.

Pipeline validates:

* Maven build
* automated tests
* dependency resolution

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

# Git Workflow

This repository follows:

* feature branch strategy
* pull request reviews
* semantic commits
* incremental delivery

Examples:

```text
feat(transaction-service): publish transaction created events with RabbitMQ
fix(transaction-service): restore RabbitMQ dependency
chore(git): ignore vscode workspace files
```
---
