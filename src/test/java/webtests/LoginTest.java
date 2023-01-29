package webtests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void openTestingPage() {
        open(urlPath);
    }
    @AfterMethod
    public void closeTestingPage() {
        closeWebDriver();
    }

    @Test
    public void testLogin() {
        $("input[placeholder='Username']").setValue("Admin");
        $("input[placeholder='Password']").setValue("admin123");
        $("button[type='submit']").click();

        $("img.oxd-userdropdown-img").should(exist);
    }

    //Negative test
    @Test
    public void testLoginNoInput() {
        $("button[type='submit']").click();
        $$("span.oxd-input-field-error-message").shouldHave(size(2));
    }

    //try to reset password test
    @Test
    public void testResetPassword() {
        $(".orangehrm-login-forgot-header").click();
        $("input[placeholder='Username']").setValue("Admin");
        $("button[type='submit']").click();
        $(".oxd-text.orangehrm-forgot-password-title").should(exist);
    }

    //negative test
    @Test
    public void testResetPasswordNoInput() {
        $(".orangehrm-login-forgot-header").click();
        $("button[type='submit']").click();
        $$("span.oxd-input-field-error-message").shouldHave(size(1));
    }

    @Test
    public void testResetPasswordCancelButton() {
        $(".orangehrm-login-forgot-header").click();
        $(".orangehrm-forgot-password-button--cancel").click();
        $(".orangehrm-login-title").should(exist);
    }

    @Test
    public void testYouTubeLink() {
        $("a[href*=youtube]").click();
        switchTo().window("OrangeHRM Inc - YouTube");
        switchTo().window("OrangeHRM");
        $(".orangehrm-login-title").should(exist);
    }
}
