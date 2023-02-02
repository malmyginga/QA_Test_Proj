package backendtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.login;
    }

    @Test(dataProvider = "emailProvider", dataProviderClass = DataForTest.class)
    public void testLoginWithEmailAndNoPassword(String email) {
        Map<String, Object> map = new HashMap<>();

        map.put("email", email);

        given()
            .contentType(ContentType.JSON)
            .body(map)
        .when()
            .post()
        .then()
            .statusCode(400)
            .body("error", equalTo("Missing password"));
    }

    @Test(dataProvider = "emailAndPasswordProvider", dataProviderClass = DataForTest.class)
    public void testLoginWithUnregisteredUser(String email, String password) {
        //Only users with email in the API can be registered

        Map<String, Object> map = new HashMap<>();

        map.put("email", email);
        map.put("password", password);

        given()
            .contentType(ContentType.JSON)
            .body(map)
        .when()
            .post()
        .then()
            .statusCode(400)
            .body("error", equalTo("user not found"));
    }
}
