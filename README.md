# SVVT_Project

A Java + Maven test automation project (page-object style) with Allure reporting.

## Quick overview
- Project root: `pom.xml`
- Production code: `src/main/java`
- Tests: `src/test/java` (tests use Page Objects located in `src/test/java/pages`)
- Test helpers / factories: `src/test/java/TestBase/Factory`
- Test resources: `src/test/resources`
- Allure results: `target/allure-results`

This README explains how to build and run tests, generate/view Allure reports, and gives practical tips for stabilizing flaky UI tests.

---

## Prerequisites
- Java 11+ (or whatever version is declared in `pom.xml`).
- Maven 3.6+

---

## Build & run tests
From the project root run:

- Run the full test suite:

  mvn clean test

- Run a single test class (JUnit 5):

  mvn -Dtest=tests.LoginTest test

- Run a single test method (JUnit 5):

  mvn -Dtest=tests.LoginTest#userCanLogin test

- Run tests and skip unit tests while building artifacts:

  mvn clean install -DskipTests

Notes:
- If your environment requires a specific JDK, set `JAVA_HOME` accordingly before running Maven.
- Use the `-Dtest` syntax shown above for quick iterative runs.

---

## Allure reports (Maven-only)
This project uses the Allure Maven plugin and the Allure JUnit adapter so you can generate and serve reports using only Maven (no separate Allure CLI install required).

1) Add the Allure plugin to your `pom.xml` under `<build><plugins>` (example):

```xml
<plugin>
  <groupId>io.qameta.allure</groupId>
  <artifactId>allure-maven</artifactId>
  <version>2.11.2</version>
</plugin>
```

2) Add the Allure JUnit adapter to your `<dependencies>` (test scope):

```xml
<dependency>
  <groupId>io.qameta.allure</groupId>
  <artifactId>allure-junit5</artifactId>
  <version>2.21.0</version>
  <scope>test</scope>
</dependency>
```

3) Run tests and serve the Allure report using Maven (recommended for local development):

```
# run tests and serve a temporary Allure report in your browser
mvn clean test allure:serve
```

This command runs the tests, collects results into `target/allure-results`, generates the report, and opens it in your default browser. No separate Allure binary required.

4) To generate a persistent report without auto-opening, run:

```
mvn clean test allure:report
```

The generated report artifacts will be placed under `target` (plugin-common locations include `target/allure-report` or `target/site/allure-maven`). You can serve or copy those artifacts as needed in CI.

If `target/allure-results` is empty after running tests, ensure the Allure adapter dependency is present in `pom.xml` and tests run under JUnit 5.

---
