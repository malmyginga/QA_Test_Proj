package backendtests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DataDrivenTest extends BaseTest {

    @Test(dataProviderClass = DataForTest.class, dataProvider = "dataForPost")
    public void testPostToUserWithNameAndJob(String name, String job) {
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

//    Put test flow:
//    Put data to API
//    Get data from API
//    Delete data from API
    @Test(dataProvider = "dataForDelete", dataProviderClass = DataForTest.class)
    public void testPutToUserWithNameAndJob(String name, String job) {

        given()
            .filter(new AllureRestAssured())
        .when()
            .delete(urlPath)
        .then()
            .statusCode(204)
            .log()
            .all();
    }

//    Delete test flow:
//    Put data to API
//    Delete data from API
//    Try to retrieve deleted data from API
    @Test(dataProvider = "dataForDelete", dataProviderClass = DataForTest.class)
    public void testDeleteFromUserWithNameAndJob(String name, String job) {

    }
}
