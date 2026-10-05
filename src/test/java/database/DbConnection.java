package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL =
            "jdbc:postgresql://ep-solitary-band-b15fidql-pooler.c-5.eu-central-1.aws.neon.tech:5432/neondb?sslmode=require";

    private static final String USER = "neondb_owner";

    private static final String PASSWORD = System.getenv("NEON_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
