# Use a lightweight JDK 21 image (matches your Maven build)
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your target folder into the container
# Note: Using the wildcard * handles the versioning (0.0.1-SNAPSHOT)
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]