# yes I used AI for this don't judge me
# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy the Maven config first so dependencies can be cached.
COPY pom.xml ./
RUN mvn -B dependency:go-offline

# Copy the source files and build the application.
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /app

# Copy the built application JAR.
COPY --from=build /workspace/target/Starter-1.0-SNAPSHOT.jar /app/app.jar

ENV DB_HOST=mysql \
    DB_PORT=3306 \
    DB_NAME=calendar \
    DB_USER=root \
    DB_PWD=password

# The GUI needs a desktop display, so the container runs the headless CLI.
ENTRYPOINT ["java", "-cp", "/app/app.jar", "startupCli.StartupCli"]
