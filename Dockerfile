# -------- BUILD STAGE --------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy only pom.xml first (faster builds)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy rest of code
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# -------- RUN STAGE --------
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy jar safely
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]