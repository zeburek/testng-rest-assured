# Description

A simple project with example of usage Rest-Assured with TestNG and Allure.

Used libraries:

- io.rest-assured:rest-assured
- io.qameta.allure:allure-gradle
- io.qameta.allure:allure-testng
- org.apache.logging.log4j:log4j-*
- com.google.code.gson:gson
- com.github.javafaker:javafaker
- org.testng:testng

Structure:

```
src/
├── main/
│   ├── java/ru/zeburek/testngrestassured/ # REST API client here
│   │   ├── types/ # JSON types schemas for using with GSON
│   ├── resources/ # Config file for log4j2 here
├── test/
│   ├── java/ru/zeburek/testngrestassured/ # Tests and additional data here
```

# Requirements

- Java 8

# Executing Tests

```bash
# Windows
gradlew.bat test allureReport allureServe

# Linux
./gradlew test allureReport allureServe
```