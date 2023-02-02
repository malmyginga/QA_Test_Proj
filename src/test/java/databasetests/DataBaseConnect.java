package databasetests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {

    //Local Database
    private final String url = "jdbc:postgresql://localhost:5432/dvdrental";
    private final String user = "postgres";
    private final String password = "283756";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return connection;
    }

}
