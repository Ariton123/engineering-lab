# Software Quality and Testing - Laboratory Coursework

## Overview

This folder preserves five laboratory assignments completed for the **Software Quality and Testing** course during the Bachelor's degree.

The coursework is primarily written in Macedonian and documents an introductory progression through specification-based testing, structural testing, performance testing, logical coverage, and mutation testing. Java source code, test implementations, a JMeter test plan, public assignment reports, and the final PIT mutation-testing report are included.

## Topics Covered

### Assignment 1 - Input-Domain Partitioning

- Interface-based and functionality-based partitioning
- Characteristics and partitions
- Base Choice Coverage
- Java implementation and JUnit tests

### Assignment 2 - Data-Flow Testing

- Control-flow graph analysis
- Definitions and uses of variables
- `du-pairs` and `du-paths`
- Test paths for data-flow coverage

### Assignment 3 - JMeter Performance Testing

- Load testing and stress testing
- JMeter timers and listeners
- HTTP request test plan
- Result visualization and performance-testing concepts

### Assignment 4 - Logical Coverage

- Boolean predicate analysis
- Truth-table construction
- Correlated Active Clause Coverage (CACC)
- Restricted Active Clause Coverage (RACC)
- Java implementation and test cases

### Assignment 5 - Mutation Testing

- Java conference-registration example
- Unit-test development
- PIT mutation testing
- Generated line, mutation, and test-strength metrics

The final preserved PIT report records:

- **Line coverage:** 100% (`99/99`)
- **Mutation coverage:** 79% (`44/56`)
- **Test strength:** 79% (`44/56`)

## Repository Structure

```text
software-quality-and-testing-labs/
├── README.md
├── NOTICE.md
├── .gitignore
├── assignment-01-input-partitioning/
│   ├── report/
│   ├── src/
│   └── tests/
├── assignment-02-data-flow-testing/
│   └── report/
├── assignment-03-jmeter-performance-testing/
│   ├── report/
│   └── jmeter/
├── assignment-04-logic-coverage/
│   ├── report/
│   ├── src/
│   └── tests/
└── assignment-05-mutation-testing/
    ├── src/
    ├── tests/
    └── pit-report/
```

## Technologies and Tools

- Java
- JUnit
- TestNG references in the original coursework
- Apache JMeter
- PIT mutation testing
- IntelliJ IDEA during development

## Academic Context

This collection is preserved as completed Bachelor laboratory coursework. It demonstrates the progression from test-case design and structural analysis to performance and mutation testing.

Student identification numbers, generated build output, IDE settings, duplicate project copies, obsolete PIT reports, and redundant document formats were removed from the public portfolio version.

## Status

Completed academic coursework. The source code and reports are retained for historical and educational portfolio purposes rather than presented as production-ready testing infrastructure.
