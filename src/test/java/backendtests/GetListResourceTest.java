package backendtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetListResourceTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.unknown;
    }

    @Test
    public void testListResourcePage1() {

        given()
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("page", equalTo(1))
            .body("per_page", equalTo(6))
            .body("total", equalTo(12))
            .body("total_pages", equalTo(2))
            .body("data.size()", equalTo(6));
    }

    @Test
    public void testListResourcePage2() {
        given()
            .queryParam("page", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("per_page", equalTo(6))
            .body("total", equalTo(12))
            .body("total_pages", equalTo(2))
            .body("data.size()", equalTo(6));
    }
}
