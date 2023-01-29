package databasetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest extends BaseTest {

    @Test(dataProvider = "actorDataProvider", dataProviderClass = InsertTest.class)
    public void testDeleteActorById(String firstName, String lastName) {
        String insertQuery = "insert into actor(first_name, last_name) values(?, ?)";
        String deleteQuery = "delete from actor where actor_id=?";
        String selectQuery = "select count(*) from actor where actor_id=?";

        int actorId;
        int count = 0;
        try {
            //Insert
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            insertStatement.executeUpdate();
            ResultSet insertResultSet = insertStatement.getGeneratedKeys();
            insertResultSet.next();
            actorId = insertResultSet.getInt(1);
            insertResultSet.close();
            insertStatement.close();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, actorId);
            deleteStatement.executeUpdate();
            deleteStatement.close();

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, actorId);
            ResultSet selectResultSet = selectStatement.executeQuery();
            selectResultSet.next();
            Assert.assertEquals(selectResultSet.getInt(1), 0);
            selectResultSet.close();
            selectStatement.close();

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    public void testDeleteFromActorTableWhereIdIsHigher200() {
        String deleteQuery = "delete from actor where actor_id > 200";
        String selectQuery = "select count(*) from actor where actor_id > 200";

        int countActorIdBiggerThan200 = 0;
        try {

            Statement deleteStatement = connection.createStatement();
            deleteStatement.executeUpdate(deleteQuery);
            deleteStatement.close();

            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            resultSet.next();
            countActorIdBiggerThan200 = resultSet.getInt(1);
            selectStatement.close();

            Assert.assertEquals(countActorIdBiggerThan200, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
