package backendtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.usersId;
    }

    @Test(dataProvider = "validIdProvider", dataProviderClass = DataForTest.class)
    public void testDeleteUserWithId(int id) {
        given()
            .pathParam("id", id)
        .when()
            .delete()
        .then()
            .statusCode(204);
    }
}
