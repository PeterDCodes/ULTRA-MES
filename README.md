# ULTRA-MES

Requires JDK 21 and Maven.

Build the application:

```sh
mvn package
```

Run the executable JAR:

```sh
java -jar target/ULTRA-MES-1.0-SNAPSHOT.jar
```

Alternatively, run `org.ultra.Main` from your IDE after reloading the Maven project,
or use `mvn spring-boot:run`.

The current entry point prints `hello` and exits. It does not start a web server
or open a database connection. `AreaController` is a placeholder without routes.
Repository operations require PostgreSQL and the schema in
`src/main/java/org/ultra/Database/ref.sql`. That script drops existing tables;
use it only against a disposable development database.
