# Sportify Hub

## Overview

Sportify Hub is a Java Spring Boot web application prototype developed for the **Fuzzy Sets and Systems II** course.

The project explores the use of fuzzy logic for personalized exercise recommendations. Instead of relying only on strict, crisp categories, the system uses more flexible user inputs such as age range, available free time, fitness goals, body-related attributes, and preferred sport categories to generate exercise suggestions.

## Project Context

Modern exercise recommendation systems often provide generic or rigid suggestions. Sportify Hub was created as a coursework prototype to explore how fuzzy logic can support more adaptive and human-centered recommendations.

The system focuses on the idea that exercise planning should not always be based on strict schedules or exact categories. Instead, it should support more flexible and relative user preferences, such as:

* approximate age classification
* flexible available-time intervals
* user fitness goals
* favorite sport categories
* personalized recommendation output

## Features

* User registration and login flow
* User profile with exercise-related attributes
* Selection of fuzzy categories such as age, height, weight, gender, goal, and favorite sport
* Exercise recommendation screen
* Recommendation logic based on user preferences and fuzzy-style category handling
* Web interface using HTML templates
* Java Spring Boot backend structure

## Technologies Used

* Java
* Spring Boot
* Maven
* HTML
* CSS
* Thymeleaf templates
* IntelliJ IDEA

## Project Structure

```text
sportify-hub/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── sportifyhub/
│   │   │           ├── config/
│   │   │           ├── controller/
│   │   │           ├── model/
│   │   │           ├── repository/
│   │   │           ├── service/
│   │   │           └── SportifyhubApplication.java
│   │   └── resources/
│   │       └── templates/
│   └── test/
├── pom.xml
├── README.md
└── .gitignore
```

## How to Run

Make sure Java and Maven are installed.

Clone the repository and enter the project folder:

```bash
cd sportify-hub
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

Then open the local application in the browser, usually at:

```text
http://localhost:8080
```

## Academic Context

This project was completed as part of the **Fuzzy Sets and Systems II** course during the first semester of the master's degree.

The final presentation described Sportify Hub as a fuzzy-logic exercise recommendation system and presented the motivation, problem statement, human-centered approach, web prototype, mobile design concept, comparison with crisp-logic systems, and future improvement ideas.

## Status

Completed coursework prototype.

The project is preserved as part of an academic and technical portfolio. Future improvements could include stronger fuzzy-rule implementation, rating-based recommendation refinement, sport recommendation sharing between similar users, and a more polished user interface.
