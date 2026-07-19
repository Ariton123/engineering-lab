# Library and Reading Room Availability System

## Overview

This repository preserves a group project completed for the **Design and Architecture of Software** course during the Bachelor's degree.

The project proposed a web-based system for helping users find available books in libraries and available study places in reading rooms. It combined requirements engineering, open-data extraction, database modeling, software architecture work, and later implementation stages maintained in a shared team repository.

The coursework and report are primarily written in Macedonian.

## Project Team

- Bisera Bojkovska
- Jovana Gelebesheva
- Marko Andonov
- Ariton Verush
- Antonio Gilev

## System Concept

The proposed system distinguishes between public users and authorized library or reading-room employees.

Public users can:

- browse libraries and reading rooms;
- search books by title, author, or genre;
- check book availability;
- view available study places;
- review location information and navigation options;
- access the system without creating an account.

Authorized employees can:

- register and sign in;
- add and update book information;
- update book availability;
- update the current number of free study places.

## Coursework Components

### 1. Software Requirements Specification

The first assignment documents the proposed system using an IEEE 830-inspired requirements structure. It covers:

- project purpose and scope;
- actors and user characteristics;
- functional requirements;
- external interfaces;
- performance expectations;
- design constraints;
- availability, reliability, security, and quality attributes;
- a basic system-context/use-case diagram.

The original Macedonian report is available in `requirements/`.

### 2. Open-Data Preparation

The project used OpenStreetMap data to identify libraries and reading rooms. The preserved pipeline demonstrates filtering and converting map data into a CSV dataset containing identifiers, coordinates, and names.

### 3. Database Design

The database artifacts model:

- libraries and reading rooms;
- books;
- many-to-many relationships between books and libraries;
- counters and availability-related fields;
- location and optional contact information.

Cleaned sample datasets and a portable SQL schema are provided in `database/`.

### 4. Later Architecture and Implementation Work

Assignments 2-4 were maintained in the original team's shared GitHub repository. Their links are preserved in `implementation/README.md` rather than duplicating externally owned source code.

## Repository Structure

```text
software-design-and-architecture-library-system/
├── README.md
├── NOTICE.md
├── .gitignore
├── requirements/
│   └── software-requirements-specification-mk.pdf
├── data-pipeline/
│   ├── ATTRIBUTION.md
│   ├── library-reading-room-locations.csv
│   └── osm-library-extraction-command.txt
├── database/
│   ├── application-postgresql.example.properties
│   ├── sample-book-library-links.csv
│   ├── sample-books.csv
│   ├── sample-libraries.csv
│   └── schema.sql
└── implementation/
    └── README.md
```

## Technologies and Concepts

- Software requirements engineering
- IEEE 830-style specification
- Software architecture and design
- Client-server web architecture
- Java / Spring-style configuration
- PostgreSQL
- Relational database modeling
- OpenStreetMap data
- `osmfilter` and `osmconvert`
- Functional and non-functional requirements
- Role-based system behavior

## Academic Context

Completed as group coursework for the **Design and Architecture of Software** course during the Bachelor's degree.

## Status

Completed historical coursework project, cleaned and preserved as part of the Bachelor engineering portfolio.
