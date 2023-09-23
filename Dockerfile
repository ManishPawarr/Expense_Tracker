FROM maven:3.8.3-openjdk-17 AS build
COPY . .


FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/ProjectTry3-0.0.1-SNAPSHOT.jar ProjectTry3.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ProjectTry3.jar"]