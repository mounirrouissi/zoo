# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the Docker image
WORKDIR /app


COPY target/animals-app.jar /app/app.jar

# Expose port 8080 for the application
EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
