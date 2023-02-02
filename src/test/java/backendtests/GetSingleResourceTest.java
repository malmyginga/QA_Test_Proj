package backendtests;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSingleResourceTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.unknownId;
    }

    @Test
    public void testSingleResourceWithId2() {
        given()
            .pathParam("id", 2)
        .when()
            .get()
        .then()
            .body("data.id", equalTo(2))
            .body("data.name", equalTo("fuchsia rose"));
    }

    @Test(dataProvider = "outOfRangeIdProvider", dataProviderClass = DataForTest.class)
    public void testSingleResourceNotFound(int id) {
        given()
            .pathParam("id", id)
        .when()
            .get()
        .then()
            .statusCode(404);
    }
}
