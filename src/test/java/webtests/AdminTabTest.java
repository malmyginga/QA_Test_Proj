package webtests;

import com.codeborne.selenide.CollectionCondition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class AdminTabTest extends BaseTest {

    @BeforeMethod
    public void openTestingPage() {
        open(urlPath);
        $("input[placeholder='Username']").setValue("Admin");
        $("input[placeholder='Password']").setValue("admin123");
        $("button[type='submit']").click();
        $x("//*[@class='oxd-main-menu']//*[text()='Admin']").click();
    }

    @AfterMethod
    public void closeTestingPage() {
        closeWebDriver();
    }

    @Test
    public void testAdminTopbarMenuUserManagementDropdownSizeIs1() {
        String jobDropdownArrowXPath = "//nav[@aria-label='Topbar Menu']//span[contains(text(), 'User')]/i";
        $x(jobDropdownArrowXPath).click();
        $$x(jobDropdownArrowXPath + "/../following-sibling::ul/li").shouldHave(CollectionCondition.size(1));
    }

    @Test
    public void testAdminTopbarMenuJobDropdownSizeIs5() {
        String jobDropdownArrowXPath = "//nav[@aria-label='Topbar Menu']//span[contains(text(), 'Job')]/i";
        $x(jobDropdownArrowXPath).click();
        $$x(jobDropdownArrowXPath + "/../following-sibling::ul/li").shouldHave(CollectionCondition.size(5));
    }

    @Test
    public void testAdminTopbarMenuOrganizationDropdownSizeIs3() {
        String jobDropdownArrowXPath = "//nav[@aria-label='Topbar Menu']//span[contains(text(), 'Organization')]/i";
        $x(jobDropdownArrowXPath).click();
        $$x(jobDropdownArrowXPath + "/../following-sibling::ul/li").shouldHave(CollectionCondition.size(3));
    }

    @Test
    public void testIframe() {

    }
}
