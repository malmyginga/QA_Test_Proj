package databasetests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseTest {
    public Connection connection = null;

    @BeforeClass
    public void connect() {
        connection = (new DataBaseConnect()).connect();
        // delete database
        // fill database with data
    }

    @AfterClass
    public void disconnect() {
        
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
