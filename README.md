# Book Club API Tests

This project is designed for automated API testing of the **Book Club** service.

## Technology Stack

- Java
- Gradle
- JUnit 5
- REST Assured
- Lombok
- AssertJ
- Allure Report
- Allure REST Assured
- JSON Schema Validation
- Datafaker
- Jenkins

## Test Coverage

The project includes automated API tests for:

- User registration
- User login
- User logout
- Getting current user information
- Updating user information with PUT
- Updating user information with PATCH
- Deleting current user
- Negative validation scenarios

## Run Tests

Run the tests with:

```bash
./gradlew clean test
```

## Allure Report

To generate and open the Allure report locally:

```bash
./gradlew allureServe
```

## Jenkins

The project is integrated with Jenkins for automated test execution and Allure report generation.

## Author

**Jibek Tumenbaeva**