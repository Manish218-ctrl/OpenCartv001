package testCases.TS_027_ContactUs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_005_ValidateContactUsFormSubmissionTest extends BaseClass {

    @Test
    public void validateContactUsFormSubmission() {

        logger.info("Starting the test: validateContactUsFormSubmission");

        performLogin();

        logger.info("Login successful");

        HomePage homepage = new HomePage(getDriver());
        ContactUsPage contactUsPage = new ContactUsPage(getDriver());

        logger.info("Clicking on Contact Us footer link");

        homepage.clickFooterContactUsLink();

        String pageTitle = contactUsPage.getPageTitle();

        logger.info("Verifying Contact Us page title: {}", pageTitle);

        Assert.assertTrue(pageTitle.contains("Contact Us"),
                "Contact Us page title is incorrect!");

        logger.info("Verifying the Your Name and E-Mail Address fields are pre-filled correctly");

        String actualName = contactUsPage.getNameFieldValue();
        String actualEmail = contactUsPage.getEmailFieldValue();

        String loggedInUserName = p.getProperty("name");
        String loggedInEmail = p.getProperty("email");

        logger.info("Expected username: {} , Found username: {}",
                loggedInUserName, actualName);

        logger.info("Expected email: {} , Found email: {}",
                loggedInEmail, actualEmail);

        Assert.assertEquals(actualName, loggedInUserName,
                "Your Name is not pre-filled correctly!");

        Assert.assertEquals(actualEmail, loggedInEmail,
                "E-Mail Address is not pre-filled correctly!");

        logger.info("Entering enquiry text and submitting the form");

        contactUsPage.submitEnquiry("This is a test enquiry message.");

        contactUsPage.clickContinueButton();

        logger.info("Clicked Continue to return to homepage");

        String currentUrl = contactUsPage.getCurrentPageURL();

        logger.info("Current URL after clicking Continue: {}", currentUrl);

        Assert.assertTrue(currentUrl.contains("index.php?route=common/home"),
                "User was not redirected to the homepage!");

        logger.info("Test completed successfully.");
    }
}