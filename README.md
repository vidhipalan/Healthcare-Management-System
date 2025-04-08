<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Healthcare Management System – Microservices Architecture</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 40px;
            background-color: #f9f9f9;
            color: #333;
        }
        h1, h2, h3 {
            color: #2c3e50;
        }
        pre {
            background: #eee;
            padding: 10px;
            overflow-x: auto;
        }
        code {
            background: #f4f4f4;
            padding: 2px 4px;
            border-radius: 4px;
        }
        ul {
            list-style-type: disc;
            margin-left: 20px;
        }
        .section {
            margin-bottom: 40px;
        }
    </style>
</head>
<body>
    <div class="content">
        # Healthcare Management System – Microservices Architecture<br><br>This project implements a microservices-based Healthcare Management System using **Payara Micro**, **Jakarta EE**, and **Docker** as part of Assignment 8 for CS 548: *Enterprise Software Architecture and Design* (Spring 2025).<br><br>---<br><br>## 📦 Project Structure<br><br>### 🔧 Core Microservice Modules<br>- **clinic-microservice**  <br>  Implements the backend microservice that directly interacts with the domain model and PostgreSQL database. Exposes REST endpoints for `Patient` and `Provider` services.<br><br>- **clinic-microservice-client**  <br>  A client library for frontend applications to access the backend microservice via REST, replacing the older `clinic-service` module.<br><br>### 💻 Frontend Modules<br>- **clinic-rest**  <br>  REST API frontend that now uses the microservice client.<br><br>- **clinic-webapp**  <br>  Web-based UI application consuming the microservice through `clinic-microservice-client`.<br><br>### 📚 Supporting Modules<br>- `clinic-domain`, `clinic-dto`, `clinic-init`, `clinic-rest-client`, `clinic-root`, `clinic-service-client`<br><br>---<br><br>## 🗃️ Database Setup<br><br>### Shell Script (`cs548db.sh`)<br>This script creates the `cs548user`, the `cs548` database, and sets the appropriate privileges.<br><br><pre><code class="language-bash"><br>#!/bin/bash<br>set -e<br>psql -v ON_ERROR_STOP=1 -v password="$DATABASE_PASSWORD" \<br>     --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL<br>    CREATE USER cs548user PASSWORD :'password';<br>    CREATE DATABASE cs548 WITH OWNER cs548user;<br>    GRANT ALL PRIVILEGES ON DATABASE cs548 TO cs548user;<br>    \c cs548 postgres<br>    GRANT ALL ON SCHEMA public TO cs548user;<br>EOSQL<br></code></pre><br><br>### Dockerfile for PostgreSQL<br><br><pre><code class="language-dockerfile"><br>FROM postgres<br>COPY --chown=postgres:postgres cs548db.sh /docker-entrypoint-initdb.d/<br></code></pre><br><br>### Run the DB Container<br><br><pre><code class="language-bash"><br>docker build -t cs548/clinic-database .<br>docker run -d --name cs548db --network cs548-network -p 5432:5432 \<br>  -v /your/data/path:/var/lib/postgresql/data \<br>  -e POSTGRES_PASSWORD=postgres_pw \<br>  -e DATABASE_PASSWORD=app_pw \<br>  cs548/clinic-database<br></code></pre><br><br>---<br><br>## 🌐 Deploy Microservice<br><br>### Dockerfile for `clinic-domain`<br><br><pre><code class="language-dockerfile"><br>FROM payara/micro:6.2025.1-jdk21<br>COPY --chown=payara:payara clinic-domain.war ${DEPLOY_DIR}<br>CMD ["--contextroot", "api", "--deploy", "/opt/payara/deployments/clinic-domain.war"]<br></code></pre><br><br>### Run Microservice Container<br><br><pre><code class="language-bash"><br>docker build -t cs548/clinic-domain .<br>docker run -d --name clinic-domain --network cs548-network -p 5050:8080 \<br>  -e DATABASE_USERNAME=cs548user \<br>  -e DATABASE_PASSWORD=app_pw \<br>  -e DATABASE=cs548 \<br>  -e DATABASE_HOST=cs548db \<br>  cs548/clinic-domain<br></code></pre><br><br>> Note: Port `5050` is exposed only to demonstrate health/readiness endpoints.<br><br>---<br><br>## ✅ Health & Readiness Checks<br><br>Endpoints:<br>- `/health`<br>- `/health/live`<br>- `/health/ready`<br><br>These endpoints use MicroProfile Health to ensure JVM memory and database availability.<br><br>---<br><br>## 🧠 Microservice Client Details<br><br>Implements `IPatientService` and `IProviderService` using:<br>- MicroProfile REST Client<br>- REST interface: `IPatientMicroService`, `IProviderMicroService`<br>- Configured using `microprofile-config.properties`<br><br>Example:<br><pre><code class="language-properties"><br>clinic-domain.api/mp-rest/uri=http://clinic-domain:8080/api/<br></code></pre><br><br>---<br><br>## 📸 Submission Checklist<br><br>### ✅ Deliverables<br>- All IntelliJ modules:<br>  - clinic-domain<br>  - clinic-dto<br>  - clinic-init<br>  - clinic-microservice<br>  - clinic-microservice-client<br>  - clinic-rest<br>  - clinic-rest-client<br>  - clinic-root<br>  - clinic-service-client<br>  - clinic-webapp<br><br>- Dockerfiles (with supporting inputs)<br>- Archive files:<br>  - `clinic-webapp.war`<br>  - `clinic-rest.war`<br>  - `clinic-domain.war`<br>  - `clinic-rest-client.jar`<br><br>- 📽️ **Demo Videos**:<br>  1. Launch services and show health endpoints.<br>  2. Demonstrate full web application + client interaction.<br>  3. Show logs confirming microservice and client activity.<br><br>- 📝 Completed Rubric file<br><br>---<br><br>## 👤 Author<br>*Please display your name at the beginning of the video (no email or CWID).*<br><br>---<br><br>## ⚠️ Notes<br>- Keep passwords out of source code.<br>- Prefer Docker Secrets for secure DB credentials.<br>- All videos must be in MP4 format and included in your submission ZIP.<br><br>---<br>
    </div>
</body>
</html>
