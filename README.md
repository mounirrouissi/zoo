Sure, here's a sample README file for a Spring Boot Angular application for zoo management that is run using Docker Compose:

```markdown
# Zoo Management Application

This is a Zoo Management application developed using Spring Boot and Angular.

## Technologies Used

- Backend: Spring Boot
- Frontend: Angular
- Containerization: Docker

## Prerequisites

Ensure you have the following installed on your system:

- Java 8 or higher
- Node.js
- Docker
- Docker Compose

## Running the Application

1. **Backend Setup**

Navigate to the backend directory:

```bash
cd backend
```

Build the Spring Boot Application:

```bash
./mvnw package
```

2. **Frontend Setup**

Navigate to the frontend directory:

```bash
cd ../frontend
```

Install the dependencies:

```bash
npm install
```

Build the Angular Application:

```bash
ng build
```

3. **Docker Compose**

Navigate back to the root directory:

```bash
cd ..
```

Run the application using Docker Compose:

```
docker-compose up

then acess the app on http://localhost:4200/
