# API Autotests for Book Club

<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/swagger/swagger-original.svg"
       alt="Book Club API"
       width="150">
</p>

This project is designed to automate API testing of the **Book Club REST API** service.

## Test Coverage

The project includes **13 automated API tests**:

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

## Technologies

<p align="center">
  <img width="55" title="IntelliJ IDEA" alt="IntelliJ IDEA"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg">
  <img width="55" title="Java" alt="Java"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg">
  <img width="55" title="Gradle" alt="Gradle"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg">
  <img width="55" title="JUnit 5" alt="JUnit 5"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg">
  <img width="55" title="Swagger" alt="Swagger"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/swagger/swagger-original.svg">
  <img width="55" title="GitHub" alt="GitHub"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/github/github-original.svg">
  <img width="55" title="Jenkins" alt="Jenkins"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg">
  <img width="55" title="Jira" alt="Jira"
       src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jira/jira-original.svg">
  <img width="55" title="Telegram" alt="Telegram"
       src="https://cdn.simpleicons.org/telegram/26A5E4">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/REST_Assured-6DB33F?style=for-the-badge">
  <img src="https://img.shields.io/badge/AssertJ-FF6F00?style=for-the-badge">
  <img src="https://img.shields.io/badge/JSON_Schema-4B8BBE?style=for-the-badge">
  <img src="https://img.shields.io/badge/Allure_Report-F4A261?style=for-the-badge">
  <img src="https://img.shields.io/badge/Allure_TestOps-8A2BE2?style=for-the-badge">
  <img src="https://img.shields.io/badge/Datafaker-2E8B57?style=for-the-badge">
</p>

## Running the Tests

The following command is used to run the tests locally:

```bash
./gradlew clean test
```

## Remote Test Execution in Jenkins

The project is configured for automated API test execution in **Jenkins**.

### [Open Jenkins Job](https://jenkins.qa.guru/job/book-club-api-tests/)

The following command is used to run the tests in Jenkins:

```bash
clean test
```

An example of a successful Jenkins build:

![Jenkins Build](media/jenkins.jpg)

## Integrations

### [Allure Report](https://jenkins.qa.guru/job/book-club-api-tests/40/allure/)

Allure Report is used to visualize API test execution results.

The report contains:

- test execution status;
- test steps;
- API requests;
- API responses;
- execution history.

![Allure Report](media/allure-report.jpg)

### [Allure TestOps](https://allure.qa.guru/project/5356/dashboards)

Test execution results are sent from Jenkins to **Allure TestOps**.

Allure TestOps is used for:

- automated test case management;
- launch history;
- execution results;
- test steps;
- test automation statistics;
- Jira integration.

The project contains **13 automated API test cases**.

![Allure TestOps](media/allure-testops.jpg)

### [Jira](https://jira.qa.guru/browse/MUL-42)

The following Jira issue was created for the project:

**MUL-42 — API autotests for Book Club service**

The Jira issue describes the API automation project and its integrations with Jenkins, Allure Report and Allure TestOps.

![Jira Integration](media/jira.jpg)

## Telegram Notifications

Telegram notifications are configured to send test execution results and a link to the Allure Report after the Jenkins build.

An example of a successful notification with **13 of 13 API scenarios passed** is shown below:

![Telegram Notification](media/telegram.jpg)

Due to network restrictions in the Jenkins environment, connection to the Telegram API may occasionally be unavailable.

## Repository

### [GitHub Repository](https://github.com/tumenbaevaj/book-club-api-tests)