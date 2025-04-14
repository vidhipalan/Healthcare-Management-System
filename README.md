
        # Healthcare Management System – Microservices Architecture

This project implements a microservices-based Healthcare Management System using **Payara Micro**, **Jakarta EE**, and **Docker** as part of Assignment 8 for CS 548: *Enterprise Software Architecture and Design* (Spring 2025).

---

## 📦 Project Structure

### 🔧 Core Microservice Modules
- **clinic-microservice**  
  Implements the backend microservice that directly interacts with the domain model and PostgreSQL database. Exposes REST endpoints for `Patient` and `Provider` services.

- **clinic-microservice-client**  
  A client library for frontend applications to access the backend microservice via REST, replacing the older `clinic-service` module.

### 💻 Frontend Modules
- **clinic-rest**  
  REST API frontend that now uses the microservice client.

- **clinic-webapp**  
  Web-based UI application consuming the microservice through `clinic-microservice-client`.

### 📚 Supporting Modules
- `clinic-domain`, `clinic-dto`, `clinic-init`, `clinic-rest-client`, `clinic-root`, `clinic-service-client`

---

## 🗃️ Database Setup

### Shell Script (`cs548db.sh`)
This script creates the `cs548user`, the `cs548` database, and sets the appropriate privileges.


#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 -v password="$DATABASE_PASSWORD" \
     --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER cs548user PASSWORD :'password';
    CREATE DATABASE cs548 WITH OWNER cs548user;
    GRANT ALL PRIVILEGES ON DATABASE cs548 TO cs548user;
    \c cs548 postgres
    GRANT ALL ON SCHEMA public TO cs548user;
EOSQL


### Dockerfile for PostgreSQL


FROM postgres
COPY --chown=postgres:postgres cs548db.sh /docker-entrypoint-initdb.d/


### Run the DB Container


docker build -t cs548/clinic-database .
docker run -d --name cs548db --network cs548-network -p 5432:5432 \
  -v /your/data/path:/var/lib/postgresql/data \
  -e POSTGRES_PASSWORD=postgres_pw \
  -e DATABASE_PASSWORD=app_pw \
  cs548/clinic-database


---

## 🌐 Deploy Microservice

### Dockerfile for `clinic-domain`


FROM payara/micro:6.2025.1-jdk21
COPY --chown=payara:payara clinic-domain.war ${DEPLOY_DIR}
CMD ["--contextroot", "api", "--deploy", "/opt/payara/deployments/clinic-domain.war"]


### Run Microservice Container


docker build -t cs548/clinic-domain .
docker run -d --name clinic-domain --network cs548-network -p 5050:8080 \
  -e DATABASE_USERNAME=cs548user \
  -e DATABASE_PASSWORD=app_pw \
  -e DATABASE=cs548 \
  -e DATABASE_HOST=cs548db \
  cs548/clinic-domain


> Note: Port `5050` is exposed only to demonstrate health/readiness endpoints.

---

## ✅ Health & Readiness Checks

Endpoints:
- `/health`
- `/health/live`
- `/health/ready`

These endpoints use MicroProfile Health to ensure JVM memory and database availability.

---

## 🧠 Microservice Client Details

Implements `IPatientService` and `IProviderService` using:
- MicroProfile REST Client
- REST interface: `IPatientMicroService`, `IProviderMicroService`
- Configured using `microprofile-config.properties`

---

## 📸 Submission Checklist

### ✅ Deliverables
- All IntelliJ modules:
  - clinic-domain
  - clinic-dto
  - clinic-init
  - clinic-microservice
  - clinic-microservice-client
  - clinic-rest
  - clinic-rest-client
  - clinic-root
  - clinic-service-client
  - clinic-webapp

- Dockerfiles (with supporting inputs)
- Archive files:
  - `clinic-webapp.war`
  - `clinic-rest.war`
  - `clinic-domain.war`
  - `clinic-rest-client.jar`

- 📽️ **Demo Videos**:
  1. Launch services and show health endpoints.
  2. Demonstrate full web application + client interaction.
  3. Show logs confirming microservice and client activity.

---

## 👤 Author
Vidhi Ashok Palan
Graduate Student – Stevens Institute of Technology

---

