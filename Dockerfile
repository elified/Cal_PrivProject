# yes I used AI for this don't judge me
# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy the Maven config first so dependencies can be cached.
COPY pom.xml ./
RUN mvn -B dependency:go-offline

# Copy the source files and the web page assets the server will serve.
COPY src ./src
COPY Site ./Site
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /app

# Copy the built jar and the web assets that the Java HTTP server serves.
COPY --from=build /workspace/target/Starter-1.0-SNAPSHOT.jar /app/app.jar
COPY --from=build /workspace/Site /app/Site

# These environment variables are ready for a future MySQL database connection.
ENV DB_HOST=mysql \
    DB_PORT=3306 \
    DB_NAME=calendar \
    DB_USER=root \
    DB_PASSWORD_FILE=/db/password

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
