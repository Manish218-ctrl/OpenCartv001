package testCases.TS_027_ContactUs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_010_ValidateContactUsPageBreadcrumbValidationTest extends BaseClass {


    private HomePage homepage;
    private ContactUsPage contactUsPage;

    @BeforeClass
    public void setUp() {

        // Initialize the HomePage and ContactUsPage objects

        homepage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
    }

    @Test
    public void validateBreadcrumbVisibility() {
        // Step 1: Open the Application URL
        driver.get(appURL);
        logger.info("Application URL opened: " + appURL);

        // Step 2: Click on 'Contact Us' footer link to navigate to Contact Us page
        homepage.clickFooterContactUsLink();
        logger.info("Navigated to Contact Us page.");

        // Step 3: Validate that the breadcrumb is displayed
        WebElement breadcrumb = driver.findElement(By.xpath("/html/body/div[2]/ul"));
        Assert.assertTrue(breadcrumb.isDisplayed(), "Breadcrumb is not displayed on the Contact Us page.");

        logger.info("Breadcrumb is correctly displayed on the Contact Us page.");
    }
}
