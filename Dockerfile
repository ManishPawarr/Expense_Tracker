# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the POM file to download dependencies
COPY pom.xml .

# Copy the entire project source
COPY src ./src

# Build the application using Maven
RUN mvn clean install -DskipTests

# Stage 2: Create the final image with the built JAR file
FROM openjdk:17.0.1-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage to the current directory
COPY --from=build /app/target/ProjectTry3-0.0.1-SNAPSHOT.jar ProjectTry3.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "ProjectTry3.jar"]
