package testCases.TS_027_ContactUs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_007_ValidateContactUsNoDetailsSubmittedTest extends BaseClass {

    @Test
    public void validateNoDetailsSubmitted() {
        logger.info("Starting the test: validateNoDetailsSubmitted");

        getDriver().get(appURL);
        logger.info("Application URL opened");

        HomePage homepage = new HomePage(getDriver());
        homepage.clickFooterContactUsLink();
        logger.info("Navigated to Contact Us page");

        ContactUsPage contactUsPage = new ContactUsPage(getDriver());
        String pageTitle = contactUsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Contact Us"), "Contact Us page title is incorrect!");

        contactUsPage.nameField.clear();
        contactUsPage.emailField.clear();
        contactUsPage.enquiryField.clear();

        contactUsPage.clickSubmitButton();
        logger.info("Clicked on Submit button");

        Assert.assertTrue(contactUsPage.isNameErrorDisplayed(),
                "Error message for Your Name not displayed");
        Assert.assertTrue(contactUsPage.isEmailErrorDisplayed(),
                "Error message for E-Mail Address not displayed");
        Assert.assertTrue(contactUsPage.isEnquiryErrorDisplayed(),
                "Error message for Enquiry not displayed");

        logger.info("Field level validation messages displayed successfully");
        logger.info("Test completed successfully.");
    }
}