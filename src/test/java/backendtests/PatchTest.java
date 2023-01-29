package backendtests;

import com.github.javafaker.Faker;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PatchTest extends BaseTest {

    @Test
    public void test_03_patch() {
        // gson Jackson Json Simple json
        Map<String, Object> map = new HashMap<String, Object>();

        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        String job = faker.job().title();

        map.put("name", name);
        map.put("job", job);

        JSONObject request = new JSONObject(map);

        given()
            .filter(new AllureRestAssured())
            .header("Content-type", "application/json")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(request.toJSONString())
        .when()
            .patch(urlPath)
        .then()
            .statusCode(200);
    }
}
