
# Mini Bank System

This project is a **Mini Bank System** developed using **Java 17**, **Spring Boot**, **Maven**, and **H2 Database**. The application allows you to create, update, and search bank customers with auditing features using **Hibernate Envers**.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Clone and Setup](#clone-and-setup)
- [Running the Project](#running-the-project)
- [Unit Tests](#unit-tests)
- [Contact](#contact)

## Features

- Customer creation and update
- Customer search functionality
- Auditing using Hibernate Envers
- In-memory H2 Database
- Transaction management
- Liquibase for database migrations

## Technologies Used

- **Java 17**
- **Spring Boot** (Web, Data JPA)
- **Hibernate Envers** (for auditing)
- **H2 Database** (In-memory)
- **Liquibase** (Database migration)
- **Maven** (Build tool)

## Prerequisites

Before you can run the project, ensure you have the following installed:

- **Java 17** (Ensure JAVA_HOME is set correctly)
- **Maven** (To build and run the project)

## Clone and Setup

To get started with this project, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/AlanKuc97/mbs.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd mbs
   ```

3. **Install the dependencies:**

   Use Maven to install all required dependencies.

   ```bash
   mvn clean install
   ```

## Running the Project

Once the dependencies are installed, you can run the application by following these steps:

1. **Start the application:**

   ```bash
   mvn spring-boot:run
   ```

   This will start the Spring Boot application on the default port **8080**.

2. **Access the application:**

   Open your web browser and navigate to:

   ```
   http://localhost:8080
   ```

3. **Database access:**

   You can also access the **H2 Console** by navigating to:

   ```
   http://localhost:8080/h2-console
   ```

   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `user`
   - Password: `pass`

## Unit Tests

The project includes unit tests for the service functionalities. To run the tests, use the following command:

```bash
mvn test
```

This will execute all the unit tests, ensuring that the core functionalities of the project work as expected.
