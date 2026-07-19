# Coursework and Maintenance Notice

This repository preserves an individual Bachelor coursework project originally developed in 2021-2022.

## Public Cleanup Performed

- removed IntelliJ IDEA configuration and Maven build output
- removed the duplicated nested project directory
- removed a hard-coded local PostgreSQL password
- removed commented seed records containing personal-style contact details
- changed the default runtime profile from PostgreSQL to an in-memory H2 database
- added environment-variable-based PostgreSQL configuration
- reorganized and sanitized the SQL reference scripts
- corrected a small registration redirect typo, H2 console route, session-cookie cleanup name, and payment-form route
- replaced SQL demonstration records with clearly fictional examples
- removed document metadata while preserving the original visible coursework

## Technical Limitations

- The project uses Spring Boot 2.6.2 and Java 11-era dependencies.
- The code reflects student coursework and has not been modernized into a current production architecture.
- The security configuration disables CSRF protection and exposes the H2 console for local demonstration; it must not be deployed publicly without review.
- The SQL scripts are cleaned academic reference material, not audited production migrations.
- Some naming and validation choices remain in Macedonian because they reflect the original coursework.

The original application logic is otherwise preserved to document the project's academic and developmental context.
