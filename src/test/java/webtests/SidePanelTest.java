package webtests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class SidePanelTest extends BaseTest {

    @BeforeMethod
    public void openTestingPage() {
        open(urlPath);
        $("input[placeholder='Username']").setValue("Admin");
        $("input[placeholder='Password']").setValue("admin123");
        $("button[type='submit']").click();
    }
    @AfterMethod
    public void closeTestingPage() {
        closeWebDriver();
    }

    @Test
    public void testMaintenanceSearch() {
        $$(".oxd-sidepanel-body li.oxd-main-menu-item-wrapper")
        .shouldHave(CollectionCondition.size(11));

        $("input[placeholder='Search']").setValue("Maintenance");
        ElementsCollection elements = $$(".oxd-sidepanel-body li.oxd-main-menu-item-wrapper")
        .shouldHave(CollectionCondition.size(1));
        elements.first().shouldHave(Condition.text("Maintenance"));
    }
}
