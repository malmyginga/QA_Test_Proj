package databasetests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseTest {
    public Connection connection = null;

    @BeforeTest
    public void connect() {
        connection = (new DataBaseConnect()).connect();
    }

    @AfterTest
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
