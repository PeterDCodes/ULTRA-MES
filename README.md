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

The entry point starts the Spring Boot web server. Repository operations require
PostgreSQL and the schema in `src/main/java/org/ultra/database/ref.sql`.
That script drops existing tables;
use it only against a disposable development database.

For the auth demo, run `org.ultra.middleware.JwtGenerator` from your IDE with
the Maven classpath. It prints a token for `FAKE-DEVICE`, valid for 15 minutes,
signed with `src/main/resources/keys/private.pem` (PKCS#8). The server verifies
it using `keys/public.pem` (X.509) from the classpath.

Send the token with API requests:

```sh
curl -H 'Authorization: Bearer <paste-token-here>' http://localhost:8080/areas
```

The authentication filter returns HTTP 401 for missing, invalid, or expired
tokens. A valid token allows the request through; `/areas` also needs PostgreSQL.
The bundled keys and fixed device identity are for the local demo only.
