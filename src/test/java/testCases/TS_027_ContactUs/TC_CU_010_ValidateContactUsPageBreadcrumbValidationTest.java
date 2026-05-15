package testCases.TS_027_ContactUs;

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
        homepage = new HomePage(getDriver());
        contactUsPage = new ContactUsPage(getDriver());
    }

    @Test
    public void validateBreadcrumbVisibility() {
        getDriver().get(appURL);
        logger.info("Application URL opened: " + appURL);

        homepage.clickFooterContactUsLink();
        logger.info("Navigated to Contact Us page.");

        Assert.assertTrue(contactUsPage.isBreadcrumbDisplayed(),
                "Breadcrumb is not displayed on the Contact Us page.");

        logger.info("Breadcrumb is correctly displayed on the Contact Us page.");
    }
}