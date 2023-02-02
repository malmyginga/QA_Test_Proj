package backendtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class PostTest extends BaseTest {
    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.users;
    }
    @Test(dataProvider = "firstNameAndJobProvider", dataProviderClass = DataForTest.class)
    public void testPostUser(String firstName, String job) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", firstName);
        map.put("job", job);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(map)
        .when()
            .post()
        .then()
            .body("name", equalTo(firstName))
            .body("job", equalTo(job))
            .body("$", hasKey("id"))
            .body("$", hasKey("createdAt"))
            .statusCode(201);
    }
}
