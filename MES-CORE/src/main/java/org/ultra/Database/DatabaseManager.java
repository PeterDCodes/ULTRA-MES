package org.ultra.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private static final HikariDataSource ultraDatabase;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                "jdbc:postgresql://localhost:5432/ULTRA"
        );
        config.setUsername(
                "postgres"
        );
        config.setPassword(
                "0299"
        );

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);

        ultraDatabase = new HikariDataSource(config);
    }

    public static Connection getConnection()
            throws SQLException {
        return ultraDatabase.getConnection();
    }

    public static void shutdown() {
        ultraDatabase.close();
    }
}