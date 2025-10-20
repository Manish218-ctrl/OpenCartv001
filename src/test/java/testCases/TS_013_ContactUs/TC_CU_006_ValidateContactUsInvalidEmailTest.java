package testCases.TS_013_ContactUs;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_006_ValidateContactUsInvalidEmailTest extends BaseClass {

    @Test
    public void validateInvalidEmailInContactForm() {
        logger.info("Starting the test: validateInvalidEmailInContactForm");

        // Step 1: Open the application URL
        driver.get(appURL);
        logger.info("Application URL opened");

        // Step 2: Navigate to 'Contact Us' page
        HomePage homepage = new HomePage(driver);
        homepage.clickFooterContactUsLink();
        logger.info("Navigated to 'Contact Us' page");

        // Step 3: Verify 'Contact Us' page title
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String pageTitle = contactUsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Contact Us"), "Contact Us page title is incorrect!");

        // Step 4: Enter valid 'Your Name' and 'Enquiry' details
        contactUsPage.nameField.clear();
        contactUsPage.nameField.sendKeys("Test User");
        contactUsPage.enterEnquiry("This is a test enquiry.");

        // Step 5: Enter invalid email addresses and verify validation messages
        String[] invalidEmails = {"Test", "Test1@", "Automa@gmail", "Automatest@gmail."};

        for (String email : invalidEmails) {
            contactUsPage.emailField.clear();
            contactUsPage.emailField.sendKeys(email);
            contactUsPage.clickSubmitButton();

            WebElement emailError = contactUsPage.emailErrorMessage;
            Assert.assertTrue(emailError.isDisplayed(), "Error message not displayed for invalid email: " + email);
            logger.info("Displayed error message for invalid email: " + email);
        }

        logger.info("Test completed successfully.");
    }
}
