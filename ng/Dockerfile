
# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
# Set the working directory in the Docker image
WORKDIR /app


COPY target/animals-app.jar /app/app.jar

# Expose port 8080 for the application
EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]


