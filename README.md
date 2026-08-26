<h1 align="center">Book Club API Autotests</h1>

<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/swagger/swagger-original.svg"
       width="90"
       height="90"
       title="Book Club API"/>
</p>

<p align="center">
Automated API testing project for the <b>Book Club</b> service.
</p>

---

## About the Project

This project was developed for automated testing of the **Book Club REST API**.

The project covers user registration, authentication and user profile management.
Both positive and negative API scenarios are implemented.

API requests are performed using **REST Assured**.  
Response validation is implemented using **AssertJ** and **JSON Schema**.

---

## Test Coverage

The project includes **13 automated API tests**.

### Positive Scenarios

1. Successful user registration
2. Successful user login
3. Successful user logout
4. Get current user
5. Successful user update with PUT
6. Successful user update with PATCH
7. Delete current user

### Negative Scenarios

8. Registration of an existing user
9. Registration with blank username
10. Login with wrong credentials
11. Login with blank password
12. Logout with blank refresh token
13. User update with invalid PUT data

---

## Technologies

<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="50" height="50" title="Java"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-original.svg" width="50" height="50" title="Gradle"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/junit/junit-original.svg" width="50" height="50" title="JUnit 5"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/swagger/swagger-original.svg" width="50" height="50" title="REST API"/>
  <img src="https://cdn.simpleicons.org/jenkins" width="50" height="50" title="Jenkins"/>
  <img src="https://cdn.simpleicons.org/jira" width="50" height="50" title="Jira"/>
  <img src="https://cdn.simpleicons.org/github" width="50" height="50" title="GitHub"/>
  <img src="https://cdn.simpleicons.org/telegram/26A5E4" width="50" height="50" title="Telegram"/>
</p>

<p align="center">
Java • Gradle • JUnit 5 • REST Assured • AssertJ • Allure Report • Allure TestOps • JSON Schema Validation • Datafaker
</p>

---

## Running the Tests

To run all API tests locally:

```bash
./gradlew clean test
```

---

## Remote Test Execution in Jenkins

The project is configured for automated API test execution in **Jenkins**.

### [Open Jenkins Job](https://jenkins.qa.guru/job/book-club-api-tests/)

The following command is used to run the tests in Jenkins:

```bash
clean test
```

An example of a successful Jenkins build:

![Jenkins Build](media/jenkins.jpg)

---

## Integrations

### Allure Report

[**Allure Report**](https://jenkins.qa.guru/job/book-club-api-tests/40/allure/) is used to visualize API test execution results.

The report contains:

- test execution status;
- test steps;
- API requests;
- API responses;
- execution history.

![Allure Report](media/allure-report.jpg)

---

### [Allure TestOps](https://allure.qa.guru/project/5356/dashboards)

The project is integrated with **Allure TestOps** for automated test case management and test execution analysis.

The project contains **13 automated API test cases**.

Allure TestOps is used for:

- automated test case management;
- launch history;
- execution results;
- test steps;
- test automation statistics;
- Jira integration.

![Allure TestOps](media/allure-testops.jpg)

---

### Jira

The project is integrated with **Jira** for task management and linking automated tests with project tasks.

**Jira task:** [MUL-42 — API autotests for Book Club service](https://jira.qa.guru/browse/MUL-42)

![Jira](media/jira.jpg)

---

## Telegram Notifications

Telegram notifications are configured to send Jenkins test execution results and a link to the Allure Report.

An example of a successful notification with **13 of 13 API scenarios passed** is shown below:

![Telegram Notification](media/telegram.jpg)

Due to network restrictions in the Jenkins environment, Telegram notifications may occasionally be unavailable.

---

## Repository

### [GitHub Repository](https://github.com/tumenbaevaj/book-club-api-tests)