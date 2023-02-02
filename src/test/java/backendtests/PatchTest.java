package backendtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class PatchTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.usersId;
    }

    @Test(dataProvider = "funnyFirstNameAndJobProvider", dataProviderClass = DataForTest.class)
    public void testPatchUserWithId2(String firstName, String job) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", firstName);
        map.put("job", job);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("id", 2)
            .body(map)
        .when()
            .patch()
        .then()
            .statusCode(200)
            .body("name", equalTo(firstName))
            .body("job", equalTo(job))
            .body("$", hasKey("updatedAt"));
    }
}
