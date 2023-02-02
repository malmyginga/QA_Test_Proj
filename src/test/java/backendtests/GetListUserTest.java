package backendtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetListUserTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.users;
    }

    @Test
    public void testListUsers() {
        given()
        .queryParam("page", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("total", equalTo(12))
            .body("data.size()", equalTo(6));
    }

    @Test
    public void testDelayedResponse() {
        given()
            .queryParam("delay", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("data.size()", equalTo(6));
    }

    @Test
    public void testDataId0OnPage2Is7() {
        given()
            .queryParam("page", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("data.id[0]", equalTo(7));
    }

    @Test
    public void testDataId1OnPage2Is8() {
        given()
            .queryParam("page", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("data.id[1]", equalTo(8));
    }

    @Test
    public void testDataFirstNameOnPage2HasMichaelAndLindsay() {
        given()
            .queryParam("page", 2)
        .when()
            .get()
        .then()
            .body("data.first_name", hasItems("Michael", "Lindsay"));
    }
}
