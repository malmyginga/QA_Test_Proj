package backendtests;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetTest extends BaseTest {

    @Test
    public void testListUsers() {
        String testUrlPath = urlPath + "users?page=2";

        given()
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("total", equalTo(12))
            .body("data.size()", equalTo(6));
    }

    @Test
    public void testSingleUser() {
        String testUrlPath = urlPath + "users/2";

        given()
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.first_name", equalTo("Janet"))
            .body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void testSingleUserNotFound() {
        String testUrlPath = urlPath + "users/23";

        given()
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(404);
    }

    @Test
    public void testListResource() {
        String testUrlPath = urlPath + "unknown";

        given()
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(200)
            .body("page", equalTo(1))
            .body("per_page", equalTo(6))
            .body("total", equalTo(12))
            .body("total_pages", equalTo(2))
            .body("data.size()", equalTo(6));
    }


    @Test
    public void testSingleResource() {
        String testUrlPath = urlPath + "unknown/2";

        given()
        .when()
            .get(testUrlPath)
        .then()
            .body("data.id", equalTo(2))
            .body("data.name", equalTo("fuchsia rose"));
    }

    @Test(dataProviderClass = DataForTest.class, dataProvider = "dataForGet")
    public void testSingleResourceNotFound(@NotNull Integer pathValue) {
        String testUrlPath = urlPath + "unknown/" + pathValue.toString();

        given()
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(404);
    }

    @Test
    public void testDelayedResponse() {
        String testUrlPath = urlPath + "users?delay=2";

        given()
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(200)
            .body("data.size()", equalTo(6));
    }

    @Feature("GET Test")
    @Story("Verify data get users page 2")
    @Description("Status code of get is 200 and data.id[0] equal to 7")
    @Test
    public void testDataId0OnPage2Is7() {
        String testUrlPath = urlPath + "users?page=2";

        given()
            .filter(new AllureRestAssured())
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(200)
            .body("data.id[0]", equalTo(7));
    }

    @Feature("GET Test")
    @Test
    public void testDataId1Is8() {
        String testUrlPath = urlPath + "users?page=2";

        given()
            .filter(new AllureRestAssured())
        .when()
            .get(testUrlPath)
        .then()
            .statusCode(200)
            .body("data.id[1]", equalTo(8))
            .log()
            .all();

    }

    @Test
    public void testDataFirstNameHasMichaelAndLindsay() {
        String testUrlPath = urlPath + "users?page=2";

        given()
            .filter(new AllureRestAssured())
        .when()
            .get(testUrlPath)
        .then()
            .body("data.first_name", hasItems("Michael", "Lindsay"));
    }
}
