package backendtests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostTest extends BaseTest {

    @Test
    public void test_01_post() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Gleb");
        map.put("job", "QA engineer");

        JSONObject request = new JSONObject(map);

        given()
            .header("Content-type", "application/json")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(request.toJSONString())
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201);
    }

    @Test(dataProviderClass = DataForTest.class, dataProvider = "dataForPost")
    public void test_01_post(String name, String job) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("job", job);

        JSONObject request = new JSONObject(map);

        given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
        .when()
        .post("https://reqres.in/api/users")
        .then()
        .statusCode(201);
    }
}
