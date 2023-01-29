package databasetests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest extends BaseTest {

    @DataProvider(name = "actorUpdatedDataProvider")
    public Object[][] actorUpdatedDataProvider() {
        int numberOfActors = 3;
        int numberOfData = 3;

        Faker faker = new Faker();
        Object[][] actorsData = new Object[numberOfActors][numberOfData];
        for (Object[] actorData: actorsData) {
            actorData[0] = faker.name().firstName();
            actorData[1] = faker.name().lastName();
            actorData[2] = faker.name().lastName();
        }

        return actorsData;
    }

    @Test(dataProvider = "actorUpdatedDataProvider")
    public void testUpdateActorTable(String firstName, String lastName, String lastNameUpdated) {
        String insertQuery = "insert into actor(first_name, last_name) values(?, ?)";
        String updateQuery = "update actor set last_name=? where actor_id=?";
        String selectQuery = "select last_name from actor where actor_id=?";
        String deleteQuery = "delete from actor where actor_id=?";

        int actorId;

        try {

            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            insertStatement.executeUpdate();
            ResultSet insertResultSet = insertStatement.getGeneratedKeys();
            insertResultSet.next();
            actorId = insertResultSet.getInt(1);
            insertResultSet.close();
            insertStatement.close();

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, lastNameUpdated);
            updateStatement.setInt(2, actorId);
            updateStatement.executeUpdate();
            updateStatement.close();

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, actorId);
            ResultSet selectResultSet = selectStatement.executeQuery();
            selectResultSet.next();
            Assert.assertEquals(selectResultSet.getString(1), lastNameUpdated);
            selectResultSet.close();
            selectStatement.close();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, actorId);
            deleteStatement.executeUpdate();
            deleteStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
