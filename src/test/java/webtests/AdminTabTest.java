package webtests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

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

    @DataProvider(name =  "jobTitleGenerator")
    public Object[][] jobTitleGenerator() {
        int numberOfJobs = 5;
        int numberOfFeatures = 1;

        Object[][] data = new Object[numberOfJobs][numberOfFeatures];
        Faker faker = new Faker();

        for (Object[] jobData: data) {
            jobData[0] = faker.job().title();
        }
        return data;
    }

    @Test
    public void testJobAccountAssistantExist() {
        String jobDropdownArrowXPath = "//nav[@aria-label='Topbar Menu']//span[contains(text(), 'Job')]/i";
        $x(jobDropdownArrowXPath).click();
        String jobTitlesTab = "//*[contains(text(), 'Job Titles')]";
        $x(jobTitlesTab).click();
        $x("//*[contains(text(), 'Account Assistant')]").shouldBe(Condition.visible);
        String jobLocation = "//*[contains(text(), 'Account Assistant')]";
    }

    @Test(dataProvider = "jobTitleGenerator")
    public void testJobAdd(String jobTitle) {
        // add job
        // return
        // check if job added
        // delete job

        String jobDropdownArrowXPath = "//nav[@aria-label='Topbar Menu']//span[contains(text(), 'Job')]/i";
        $x(jobDropdownArrowXPath).click();
        String jobTitlesTab = "//*[contains(text(), 'Job Titles')]";
        $x(jobTitlesTab).click();
        $x("//*[contains(@class, 'bi-plus')]/parent::button").click();
        $x("//*[contains(@class, 'oxd-input-field-required')]/../following-sibling::*/input").setValue(jobTitle);
        $x("//button[@type='submit']").click();

        $(".orangehrm-container").shouldBe(Condition.visible);
        String jobLocation = "//*[contains(text(), '" +
        jobTitle +
        "')]";
        $x(jobLocation).should(Condition.exist);

        String deleteLocation = jobLocation +
        "//ancestor::*[contains(@class, 'oxd-table-card')]//*[contains(@class, 'bi-trash')]//parent::button";
        SelenideElement deleteButton = $x(deleteLocation);
        deleteButton.click();
        $(".oxd-button--label-danger").click();
    }

}
