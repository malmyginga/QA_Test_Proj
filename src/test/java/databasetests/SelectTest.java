package databasetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest extends BaseTest {

    @Test
    public void testZeroCageActorExists() {
        String query = "select count(*) " +
        "from actor " +
        "where first_name = 'Zero' and last_name = 'Cage'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            statement.close();

            Assert.assertEquals(count, 1);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }
}
