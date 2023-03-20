package backendtests;

import com.github.javafaker.Faker;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class PutTest extends BaseTest {

    @BeforeClass
    public void setBasePath() {
        RestAssured.basePath = EndPoints.usersId;
    }

    @Test(dataProvider = "funnyFirstNameAndJobProvider", dataProviderClass = DataForTest.class)
    public void testPutUserWithId2(String firstName, String job) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", firstName);
        map.put("job", job);

        given()
            .pathParam("id", 2)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(map)
        .when()
            .put()
        .then()
            .statusCode(200)
            .body("name", equalTo(firstName))
            .body("job", equalTo(job))
            .body("$", hasKey("updatedAt"));
    }

//    @Test
//    public testPutUserAndDelete() {
//        Test flow
//    }
}
