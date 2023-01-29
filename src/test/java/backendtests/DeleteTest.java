package backendtests;

import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteTest extends BaseTest {

    @Test
    public void test_04_delete() {

        //we can put data to API
        //we can get same data from API to ensure it is there
        //we can delete data from API
        //try to get deleted data

        given()
        .when()
            .delete(urlPath)
        .then()
            .statusCode(204)
            .log()
            .all();
    }

    //    @Parameters("parameter") then data will be from TestNG.xml
    @Test(dataProvider = "dataForDelete", dataProviderClass = DataForTest.class)
    public void test_04_delete(String name, String job) {

        //we can put data to API
        //we can get same data from API to ensure it is there
        //we can delete data from API
        //try to get deleted data
        given()
        .filter(new AllureRestAssured())
        .when()
        .delete(urlPath)
        .then()
        .statusCode(204)
        .log()
        .all();
    }
}
