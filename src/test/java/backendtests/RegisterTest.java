package backendtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class RegisterTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.register;
    }

    @Test(dataProvider = "validIdAndPasswordProvider", dataProviderClass = DataForTest.class)
    public void testGetUserWithValidIdAndRegister(int id, String password) {
        //Only users with email in the API can be registered

        //Get valid email from valid id
        RestAssured.basePath = EndPoints.usersId;
        Response response =
        given()
            .pathParam("id", id)
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
        .body("$", hasKey("data"))
        .body("$", hasKey("support"))
        .extract()
            .response();

        String email = response.jsonPath().getString("data.email");


        HashMap<String, Object> map = new HashMap<>();

        map.put("email", email);
        map.put("password", password);

        RestAssured.basePath = EndPoints.register;

        //Register user with valid email
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(map)
        .when()
            .post()
        .then()
            .statusCode(200)
            .body("$", hasKey("id"))
            .body("$", hasKey("token"));
    }

    @Test(dataProvider = "emailProvider", dataProviderClass = DataForTest.class)
    public void testRegisterWithEmail(String email) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", email);

        given()
            .contentType(ContentType.JSON)
            .body(map)
        .when()
            .post()
        .then()
            .statusCode(400)
            .body("$", hasKey("error"))
            .body("error", equalTo("Missing password"));
    }
}
