package tests;

import database.DbConnection;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTest {

    @Test
    public void checkQuery() throws SQLException {

        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM public.\"Employee\" LIMIT 5"
            );

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        }
    }
}