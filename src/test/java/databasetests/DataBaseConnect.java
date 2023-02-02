package databasetests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnect {

    //Local Database
    private String url = "jdbc:postgresql://localhost:5432/dvdrental";
    private String user = "postgres";
    private String password = "283756";
    public Connection connect() {
        try {
            InputStream inputStream = new FileInputStream("./src/test/resources/database.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("database.url");
            user = properties.getProperty("database.user");
            password = properties.getProperty("database.password");
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

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
