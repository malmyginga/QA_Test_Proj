package databasetests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertTest extends BaseTest {

    @DataProvider(name = "actorDataProvider")
    public Object[][] actorDataProvider() {
        Faker faker = new Faker();
        int numberOfRecords = 10;
        int numberOfColumns = 2;
        Object[][] actorsData = new Object[numberOfRecords][numberOfColumns];
        for (Object[] actorData: actorsData) {
           actorData[0] = faker.name().firstName();
           actorData[1] = faker.name().lastName();
        }
        return actorsData;
    }

//    Actor table: actor_id(int), first_name(varchar), last_name(varchar), last_update(time_stamp)
//    Test insert of a record to actor tables
//    Flow:
//    1. Insert data
//    2. Read data with select
//    3. Check If read data is equal to input data
//    4. Delete inputted data (because we are testing the flow of inserting)
    @Test(dataProvider = "actorDataProvider")
    public void testInsertActor(String firstName, String lastName) {
        String insertQuery = "insert into actor(first_name, last_name) values(?, ?)";
        String selectQuery = "select count(*) from actor where first_name=? and last_name=?";
        String deleteQuery = "delete from actor where first_name=? and last_name=?";
        long id = 0;
        try {
            //Insert
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            int affectedRows = insertStatement.executeUpdate();
            insertStatement.close();

            //Select
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, firstName);
            selectStatement.setString(2, lastName);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            Assert.assertEquals(resultSet.getInt(1), 1);
            resultSet.close();
            selectStatement.close();

            //Delete
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, firstName);
            deleteStatement.setString(2, lastName);
            deleteStatement.executeUpdate();
            deleteStatement.close();

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @DataProvider(name = "actors10RecordsDataProvider")
    public Object[][] actors10RecordsDataProvider() {
        int numberOfRecords = 10;
        int numberOfData = 2;
        Object[][] actorData = new Object[numberOfData][1];

        Faker faker = new Faker();
        for (Object[] actor: actorData) {
            List<Actor> actors = new ArrayList<Actor>(); // 2 - firstName and lastName
            for (int i = 0; i < numberOfRecords; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                actors.add(new Actor(firstName, lastName));
            }
            actor[0] = actors;
        }
        return actorData;
    }

    @Test(dataProvider = "actors10RecordsDataProvider")
    public void testInsert10Records(List<Actor> actors) {
        String insertQuery = "insert into actor(first_name, last_name) values(?, ?)";
        String selectQuery = "select count(*) from actor where first_name=? and last_name=?";
        String deleteQuery = "delete from actor where first_name=? and last_name=?";


        int count = 0;
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            for (Actor actor: actors) {
                insertStatement.setString(1, actor.getFirstName());
                insertStatement.setString(2, actor.getLastName());
                insertStatement.addBatch();
            }
            insertStatement.executeBatch();
            insertStatement.close();

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            for (Actor actor: actors) {
                selectStatement.setString(1, actor.getFirstName());
                selectStatement.setString(2, actor.getLastName());

                ResultSet resultSet = selectStatement.executeQuery();
                resultSet.next();
                count += resultSet.getInt(1);
                resultSet.close();
            }
            selectStatement.close();

            Assert.assertEquals(count, 10);

            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            for (Actor actor: actors) {
                deleteStatement.setString(1, actor.getFirstName());
                deleteStatement.setString(2, actor.getLastName());
                deleteStatement.addBatch();
            }

            deleteStatement.executeBatch();
            deleteStatement.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
