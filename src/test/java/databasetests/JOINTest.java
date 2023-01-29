package databasetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JOINTest extends BaseTest {

    @Test
    public void testRentalIdPaymentSumIs12_95() {
        String query = "select r.rental_id, sum(amount) " +
        "from rental r join payment p on r.rental_id = p.rental_id " +
        "group by r.rental_id " +
        "having count(*) > 1";

        double paymentSum = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            paymentSum = resultSet.getDouble(2);
            resultSet.close();
            statement.close();

            Assert.assertEquals(paymentSum, 12.95);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    // Number of active customers from 'West Bengali' district is equal to 8
    public void testNumberOfActiveCustomersFromWestBengaliIsEqualTo8() {
        // address + customer
        String query = "select count(*), district " +
        "from customer c join address a " +
        "on c.address_id = a.address_id " +
        "where district='West Bengali' and active=1 " +
        "group by district";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            statement.close();

            Assert.assertEquals(count, 8);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    public void testNumberOfStoresInThailandIs3() {
        // address + city
        String query = "select count(*), country " +
        "from address a " +
        "join city c " +
        "on a.city_id = c.city_id " +
        "join country " +
        "on c.country_id = country.country_id " +
        "where country like 'Thai%' " +
        "group by country";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            statement.close();

            Assert.assertEquals(count, 3);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
