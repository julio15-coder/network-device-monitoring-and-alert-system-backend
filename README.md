# Network Device Monitoring and Alert System

Spring Boot REST API for monitoring network devices, alerts, network admins, zones, expertises and incident logs.

## Student

- Name: MUHIZI BANDORA JULIUS
- Student ID: 27339

## Technologies

- Java 21
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL

## How to run

1. Install Java 21 and PostgreSQL.
2. Create the database:
   ```sql
   CREATE DATABASE ndmas_db;
   ```
3. Set the database password as an environment variable.
   - Windows PowerShell:
     ```
     $env:DB_PASSWORD="your_postgres_password"
     ```
   - Linux / macOS:
     ```
     export DB_PASSWORD=your_postgres_password
     ```
4. Run the application:
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux / macOS: `./mvnw spring-boot:run`
5. The API runs on `http://localhost:8082`. Tables are created automatically (`ddl-auto=update`).
6. Import `network-device-monitoring-and-alert-system-midterm.postman_collection.json` into Postman and enter the data through the save endpoints in this order: expertises, zones, network admins, devices, then alerts. Because of rule D1, new alerts must have a raisedDate of today or later.

## Main endpoints

| Area | Base path |
|---|---|
| Expertises | `/api/expertises` |
| Zones | `/api/zones` |
| Network admins | `/api/network-admins` |
| Devices | `/api/devices` |
| Alerts | `/api/alerts` |
| Incident logs | `/api/incident-logs` |
| Dashboard | `/api/dashboard/summary` |
