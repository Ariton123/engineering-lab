# Buy or Rent a Car - Database Coursework Project

## Overview

**Buy or Rent a Car** is an individual higher-grade project completed for the **Databases** course during the Bachelor's degree.

The project combines relational database design with a Java Spring Boot web application for advertising, buying, and renting cars. The coursework was originally developed in Macedonian and includes the conceptual design, ER model, relational schema, PostgreSQL scripts, analytical queries, and a functional web prototype.

## Academic Context

- **Course:** Databases
- **Project type:** Individual project for a higher course grade
- **Language of coursework:** Macedonian
- **Period:** Bachelor's studies

## Project Scope

The system models two main user roles:

- **Vehicle owners**, who can publish and manage car listings and process incoming orders
- **Buyers**, who can browse vehicles, create orders, inspect their order history, and record payment completion

The database-oriented part covers:

- conceptual and relational modeling
- primary and foreign key constraints
- buyers, owners, cars, orders, confirmations, and payments
- views for listings, order inspection, and detailed vehicle information
- transaction examples for registration and order processing
- analytical SQL reports grouped by owner, month, and vehicle brand

## Web Application Features

The archived Spring Boot prototype includes:

- account registration and authentication
- role-based access for buyers and owners
- vehicle listing and owner-side CRUD operations
- browsing available vehicles
- creating and reviewing orders
- owner approval of pending orders
- buyer and owner detail views
- payment-status handling
- Thymeleaf-based server-rendered pages

## Technologies

- Java 11
- Spring Boot 2.6.2
- Spring MVC
- Spring Data JPA / Hibernate
- Spring Security
- Thymeleaf
- PostgreSQL
- H2 Database
- Maven
- HTML and Bootstrap

## Repository Structure

```text
buy-or-rent-a-car-database-project/
├── README.md
├── NOTICE.md
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── database/
│   ├── postgresql-coursework-schema.sql
│   ├── views-and-transaction-examples.sql
│   └── analytical-reports.sql
└── docs/
    ├── project-proposal-mk.pdf
    ├── er-diagram.png
    ├── relational-schema.pdf
    └── application-functionalities-mk.pdf
```

## Running the Application

The default configuration uses an in-memory H2 database so that no local database password is required.

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
mvnw.cmd spring-boot:run
```

Then open:

```text
http://localhost:9090
```

The H2 console is available for local coursework inspection at:

```text
http://localhost:9090/h2
```

To use PostgreSQL, provide environment variables and activate the `postgresql` profile:

```bash
SPRING_PROFILES_ACTIVE=postgresql DB_URL=jdbc:postgresql://localhost:5432/buyCar DB_USERNAME=postgres DB_PASSWORD=your_password ./mvnw spring-boot:run
```

## Public Portfolio Cleanup

This version removes generated build output, IDE configuration, duplicated project nesting, a hard-coded PostgreSQL password, and commented personal seed records. The application uses H2 by default, while PostgreSQL configuration is driven by environment variables.

The SQL demonstration data is fictional and the accompanying scripts were reorganized into a clearer public portfolio structure.

## Status

Completed and archived Bachelor coursework project.

The code is preserved primarily as a record of early full-stack and database-development experience rather than as a production-ready marketplace.
