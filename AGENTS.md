# Repository Guidelines

## Project Structure & Module Organization

ULTRA-MES is a Java 21 Maven application using PostgreSQL JDBC and HikariCP.

- `src/main/java/org/ultra/Main.java`: application entry point and site-creation example.
- `src/main/java/Sites/`: site model, repository persistence, and handler operations.
- `src/main/java/Database/DatabaseManager.java`: connection pool configuration and lifecycle.
- `src/main/java/Database/ref.sql`: reference schema for sites, machines, products, and work orders.
- `src/main/resources/`: currently empty; place runtime resources here.
- `src/test/java/`: currently empty; place automated tests here.
- `target/`: generated build output; do not commit it.

## Build, Test, and Development Commands

Install JDK 21 and Maven; no Maven wrapper is currently provided.

- `mvn compile`: compile production sources.
- `mvn test`: compile and run configured tests.
- `mvn clean package`: rebuild and produce the application JAR.

Run `org.ultra.Main` from an IDE with Maven dependencies on the classpath. It requires PostgreSQL and inserts a site record. The JAR is not configured as a standalone executable. Current unfinished declarations in `SiteRepository` and `SiteHandler` prevent compilation; address these before expecting builds to pass.

## Coding Style & Naming Conventions

Use four-space indentation, UTF-8, and braces on the declaration line. Use PascalCase for classes and camelCase for methods and fields. Preserve existing `Sites` and `Database` package names when extending those modules. Keep SQL persistence in repositories and orchestration in handlers. Use prepared statements and try-with-resources for JDBC operations. No formatter or lint tool is configured.

## Testing Guidelines

No test framework or coverage threshold is configured. Add a suitable test dependency to `pom.xml` when introducing tests. Mirror production packages under `src/test/java` and name classes `*Test`. Cover site creation, generated identifiers, and database failures. Use an isolated PostgreSQL database for integration tests.

## Commit & Pull Request Guidelines

Git history is unavailable in this checkout, so no established commit convention can be verified. Use concise imperative subjects such as `Add site lookup`. Pull requests should describe behavior changes, link relevant issues, report validation results and blockers, and explain schema changes.

## Security & Configuration

Externalize database credentials before sharing or deploying configuration; do not add secrets. `ref.sql` drops tables with `CASCADE`: run it only against a disposable development database.
