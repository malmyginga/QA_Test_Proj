package backendtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected String baseURI = "https://reqres.in/api/";

    @BeforeSuite
    public void setBaseURI() {
        RestAssured.baseURI = baseURI;
    }
}
