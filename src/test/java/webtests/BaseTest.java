package webtests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected String urlPath = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeSuite
    public void setRunHeadless() {
        Configuration.headless = true;
    }
}
