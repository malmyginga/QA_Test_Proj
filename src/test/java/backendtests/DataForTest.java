package backendtests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class DataForTest {

    @DataProvider(name = "validIdProvider")
    public Object[][] validIdProvider() {
        int numberOfData = 13;
        int numberOfFeatures = 1;

        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Random random = new Random();
        for (int i = 0; i < numberOfData; i++) {
            data[i][0] = i + 1;
        }
        return data;
    }
    @DataProvider(name = "outOfRangeIdProvider")
    public Object[][] outOfRangeIdProvider() {
        int numberOfData = 10;
        int numberOfFeatures = 1;

        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Random random = new Random();
        int lowerBound = 13;
        for (Object[] oneData: data) {
            oneData[0] = lowerBound+ random.nextInt(Integer.MAX_VALUE - lowerBound);
        }
        return data;
    }

    @DataProvider(name = "firstNameAndJobProvider")
    public static Object[][] firstNameAndJobProvider() {
        int numberOfData = 3;
        int numberOfFeatures = 2;
        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Faker faker = new Faker();

        for (Object[] featureArray: data) {
            featureArray[0] = faker.name().name();
            featureArray[1] = faker.job().title();
        }
        return data;
    }

    @DataProvider(name = "funnyFirstNameAndJobProvider")
    public static Object[][] funnyFirstNameAndJobProvider() {
        int numberOfData = 5;
        int numberOfFeatures = 2;
        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Faker faker = new Faker();

        for (Object[] featureArray: data) {
            featureArray[0] = faker.funnyName().name();
            featureArray[1] = faker.job().title();
        }
        return data;
    }

    @DataProvider(name = "emailAndPasswordProvider")
    public Object[][] emailAndPasswordProvider() {
        int numberOfData = 5;
        int numberOfFeatures = 2;
        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Faker faker = new Faker();
        for (Object[] oneData: data) {
            oneData[0] = faker.internet().emailAddress();
            oneData[1] = faker.internet().password(8, 16, true, true);
        }
        return data;
    }

    @DataProvider(name = "emailProvider")
    public Object[][] emailProvider() {
        int numberOfData = 5;
        int numberOfFeatures = 1;
        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Faker faker = new Faker();
        for (Object[] oneData: data) {
            oneData[0] = faker.internet().emailAddress();
        }
        return data;
    }

    @DataProvider(name = "passwordProvider")
    public Object[][] passwordProvider() {
        int numberOfData = 5;
        int numberOfFeatures = 1;
        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Faker faker = new Faker();
        for (Object[] oneData: data) {
            oneData[0] = faker.internet().password(8, 16, true, true);
        }
        return data;
    }

    @DataProvider(name = "validIdAndPasswordProvider")
    public Object[][] validIdAndPasswordProvider() {
        int numberOfData = 12;
        int numberOfFeatures = 2;

        Object[][] data = new Object[numberOfData][numberOfFeatures];

        Faker faker = new Faker();
        for (int i = 0; i < numberOfData; i++) {
            data[i][0] = i + 1;
            data[i][1] = faker.internet().password(8, 16, true, true);
        }
        return data;
    }
}
