# Cal_PrivProject

A small Java calendar application made to practise Java and have some fun.

## Prerequisites

- Java 21
- Maven
- Docker Desktop with Docker Compose

## Configuration

Create a `.env` file in the project root. This file is required by Docker Compose
to configure the database:

```env
DB_USER=root
DB_PWD=password
```

Keep this file local and do not commit it, because it contains database
credentials. The current Compose setup uses the MySQL `root` user, so keep
`DB_USER=root`. You may choose a different password, but use the same value for
`DB_PWD` whenever you start the database and the application.

## Run the application

The MySQL database must be running before the application is started. From the
project root, run:

```bash
docker compose up -d mysql
mvn clean javafx:run
```

The first Compose startup creates the `calendar` database, the `events` table,
and a few sample events. The JavaFX calendar application then connects to the
database on `localhost:3306`.

The Dockerfile runs the command-line version because JavaFX requires a desktop
display. To run that version in Docker instead, use:

```bash
docker compose run --rm app
```

To stop the database after using the application:

```bash
docker compose down
```

To remove the database volume as well and start with a fresh database next
time, run:

```bash
docker compose down -v
```

## Tests and build

Run the tests with:

```bash
mvn test
```

Create the packaged application with:

```bash
mvn clean package
```

The project also contains a command-line entry point, `startupCli.StartupCli`,
in addition to the JavaFX entry point, `startupGui.Launcher`.
