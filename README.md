# Collaborative Concurrent Document Editor

## Overview

The Collaborative Concurrent Document Editor is a project consisting of several microservices developed in Spring Boot. It enables multiple users to collaborate on documents concurrently. The project includes services for user authentication, document management, and real-time editing.

---

## Prerequisites

Before running the application, ensure you have the following installed:

- **Maven**: Build tool (binary available in the git repository as well).
- **Java 21**: Required to run the Spring Boot JAR files.
- **Docker**: For containerization of services.
- **Node.js** and **npm**: Needed for running the frontend client.

---

## Running the Application

Each microservice is built as a separate Spring Boot project in Java. The following commands are used to build and run the services.

### Backend (Spring Boot)

- **Build and download dependencies**:
  ```bash
  mvn clean install
  ```
- **Compile the project:**

  ```bash
  mvn compile
  ```
- **Run the Spring Boot application:**
  ```bash
  mvn spring-boot:run
  ```
- **Create the JAR file for deployment:**
  ```bash
  mvn package
  ```
- **Run the application from the JAR file:**
  ```bash
  java -jar <jarfilename>.jar
  ```
- **Frontend (React):**
To run the frontend client, navigate to the frontend project directory and use the following commands:

   - **Install required Node modules:**
    ```bash
    npm install
    ```
  - **Run the development server:**
  ```bash
  npm run dev
  ```
