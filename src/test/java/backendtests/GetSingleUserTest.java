package backendtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSingleUserTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.usersId;
    }

    @Test
    public void testSingleUserDataWithId2() {
        given()
            .pathParam("id", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.first_name", equalTo("Janet"))
            .body("data.last_name", equalTo("Weaver"));
    }

    @Test(dataProvider = "outOfRangeIdProvider", dataProviderClass = DataForTest.class)
    public void testSingleUserNotFound(int id) {
        given()
            .pathParam("id", id)
        .when()
            .get()
        .then()
            .statusCode(404);
    }
}
