package backendtests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutTest extends BaseTest {

    @Test
    public void test_01() {

        Map<String, Object> map = new HashMap<String, Object>();

        Faker faker = new Faker(new Locale("en"));
        String name = faker.name().fullName();
        String job = faker.job().title();

        map.put("name", name);
        map.put("job", job);

        JSONObject request = new JSONObject(map);

        given()
            .header("Content-type", "application/json")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(request.toJSONString())
        .when()
            .put("https://reqres.in/api/users/2")
        .then()
            .statusCode(200)
            .log().all();
    }
}
