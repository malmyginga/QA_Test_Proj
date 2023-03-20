package databasetests;

import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Story("Backend DB")
@Epic("")
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

    // TODO:
    @Description("")
    @Test(dataProvider = "actorUpdatedDataProvider")
    public void testUpdateActorTable(String firstName, String lastName, String lastNameUpdated) {
        String insertQuery = "insert into actor(first_name, last_name) values(?, ?)";
        // check that inserted
        String updateQuery = "update actor set last_name=? where actor_id=?";
        String selectQuery = "select last_name from actor where actor_id=?";
        String deleteQuery = "delete from actor where actor_id=?";

        int actorId;

        try {

            //Insert
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

                insertStatement.setString(1, firstName);
                insertStatement.setString(2, lastName);
                insertStatement.executeUpdate();

                try (ResultSet insertResultSet = insertStatement.getGeneratedKeys()) {
                    insertResultSet.next();
                    actorId = insertResultSet.getInt(1);
                }
            }

            //Update
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, lastNameUpdated);
                updateStatement.setInt(2, actorId);
                updateStatement.executeUpdate();
            }

            //Select
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

                selectStatement.setInt(1, actorId);

                try (ResultSet selectResultSet = selectStatement.executeQuery()) {
                    selectResultSet.next();
                    Assert.assertEquals(selectResultSet.getString(1), lastNameUpdated);
                }
            }

            //Delete
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, actorId);
                deleteStatement.executeUpdate();
            }

            //Check if deleted

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
