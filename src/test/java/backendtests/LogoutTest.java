package backendtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogoutTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.logout;
    }

    @Test
    public void testLogout() {
        given()
        .when()
        .then()
        .statusCode(200);
    }
}
