package databasetests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NumberOfRowsTest extends BaseTest {
    @DataProvider(name = "tablesSizes")
    public Object[][] tablesSizes() {
        return new Object[][]{
        {"actor", 200},
        {"film_actor", 5462},
        {"film", 1000},
        {"address", 603},
        {"category", 16},
        {"city", 600},
        {"rental", 16044}
        };
    }

    @Test(dataProvider = "tablesSizes")
    public void testTablesNumberOfRows(String tableName, int numberOfRows) {
        String query = "select count(*) from " + tableName;
        int count;

        try {

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                count = resultSet.getInt(1);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        Assert.assertEquals(count, numberOfRows);
    }
}
