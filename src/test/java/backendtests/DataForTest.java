package backendtests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class DataForTest {

    @DataProvider(name = "dataForGet")
    public static Object[][] dataForGet() {
        Object[][] data = new Object[10][1];

        Random random = new Random();
        int lowerbound = 13;
        int upperbound = 512317;
        for (Object[] oneInteger: data) {
            oneInteger[0] = random.nextInt(upperbound - lowerbound) + lowerbound;
        }
        return data;
    }

    @DataProvider(name = "dataForPost")
    public static Object[][] dataForPost() {
        int numberOfData = 3;
        int numberOfFeature = 2;
        Object[][] data = new Object[numberOfData][numberOfFeature];

        Faker faker = new Faker();
        for (Object[] featureArray: data) {
            featureArray[0] = faker.name().fullName();
            featureArray[1] = faker.job().title();
        }
        return data;
    }

    @DataProvider(name = "dataForDelete")
    public static Object[][] dataForDelete() {
        int numberOfData = 3;
        int numberOfFeature = 2;
        Object[][] data = new Object[numberOfData][numberOfFeature];
        return data;
    }
}
