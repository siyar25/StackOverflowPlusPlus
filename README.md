# Stackoverflow++

### A School project by the not-that-well-known _Lambda Males_.

## Table of Contents

- [The Lambda Males](#the-lambda-males)
- [About the Project](#about-the-project)
- [Key Features](#key-features)
- [Used Technologies](#used-technologies)
    - [Frontend](#frontend)
    - [Backend](#backend)
    - [Database](#database)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Frontend setup](#frontend-setup)
    - [Database setup](#database-setup)
    - [Backend setup](#backend-setup)

## The Lambda Males

- Márton BARNA ([@BrownieMaar](https://github.com/BrownieMaar))
- Siyar FAROUQ ([@siyar25](https://github.com/siyar25))
- Dénes FÜLÖP ([@fulopdenes](https://github.com/fulopdenes))
- Zoltán MIHÁLYFI ([@miz092](https://github.com/miz092))

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

## About the Project

This was our last team project in [Codecool](https://codecool.com/)'s 10-month Full-Stack Developer course's OOP module. It was the first time we used Java with Spring Boot in a full-stack application.

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

---

**This repository is only a _cleaner_ copy of the project's original repositories!**

- You can find the original backend repository [here](https://github.com/BrownieMaar/stackoverflow-tw-Lambda).
- And the original frontend repository [here](https://github.com/BrownieMaar/stackoverflow-frontend).

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

---

## Key Features

The features that we had to implement is listed in the [Tasks.md](Tasks.md) file, in the root folder.

## Used Technologies

### Frontend

- JavaScript
- Node.js
- React.js
- Vite

### Backend

- Java 17
- Spring Boot

### Database

- PostgreSQL

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

## Getting Started

### Prerequisites

Before you begin, ensure you have the following tools and dependencies installed:

- **[Git](https://git-scm.com/)**: The version control system to clone the repository.
- **[Node.js](https://nodejs.org/)**: Required for frontend development.
- **[npm (Node Package Manager)](https://www.npmjs.com/)**: Comes with Node.js; you'll need it for managing frontend dependencies.
- **[Maven](https://maven.apache.org/)**: The build tool for the backend.
- **[Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)**: Ensure you have Java 17 installed on your system.
- **[PostgreSQL Database](https://www.postgresql.org/)**: Create a new PostgreSQL database called `stackoverflow`.

---

1. Clone the repository to your local machine using **Git**:

   ```bash
   git clone git@github.com:siyar25/StackOverflowPlusPlus.git
   ```

2. Navigate to the project directory:
   ```bash
   cd StackOverflowPlusPlus
   ```

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

### Frontend setup

1. Navigate to the frontend directory:

   ```bash
   cd frontend
   ```

2. Install the necessary dependencies by running:

   ```bash
   npm install
   ```

3. Start up the frontend by running:

   ```bash
   npm run dev
   ```

   > The application needs the backend server running on port `8080`. If the backend server is running on another port, it can be changed in the `frontend/vite.config.js` file.

4. Now your frontend should be up and running. Open your web browser and access the application at http://localhost:3000.

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

### Database setup

1. Username and password for the database need to be passed to the application as _environment variables_. Or change these lines:

   ```java
   public static final String dbUserName = System.getenv(USERNAME);
   public static final String dbPassword = System.getenv(PASSWORD);
   ```

   - Change `System.getenv(USERNAME)` to your database username
   - Change `System.getenv(Password)` to your database user password

2. To initialize the database with placeholder data, run the `init.sql` file in the `backend/src/main/resources` folder.

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>

### Backend setup

Now, you can build and run the backend application. Spring Boot will launch the server, and the endpoints will become live.

1. Change your directory to the backend folder:

   ```bash
   cd backend
   ```

2. Build the backend:

   ```bash
   mvn clean install
   ```

3. Run the backend:
   ```bash
   mvn spring-boot:run
   ```

- Once the server is up and running, you can access the backend API endpoints
- The backend will run on the `8080` port by default

<p align="right">(<a href="#table-of-contents">back to top</a>)</p>
