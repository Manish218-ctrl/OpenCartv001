package testCases.TS_013_ContactUs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_005_ValidateContactUsFormSubmissionTest extends BaseClass {

    @Test
    public void validateContactUsFormSubmission() {

        logger.info("Starting the test: validateContactUsFormSubmission");

        // Step 1: Open the application URL
        logger.info("Opening the application URL: " + appURL);
        driver.get(appURL);  // Open application URL from config.properties

        // Step 2: Log in with credentials from config.properties
        logger.info("Performing login with username: " + username);
        performLogin();  // This method logs in the user
        logger.info("Login successful");

        // Step 3: Click on 'Contact Us' footer link
        HomePage homepage = new HomePage(driver);
        logger.info("Clicking on 'Contact Us' footer link");
        homepage.clickFooterContactUsLink();

        // Step 4: Verify 'Contact Us' page title
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String pageTitle = contactUsPage.getPageTitle();
        logger.info("Verifying 'Contact Us' page title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("Contact Us"), "Contact Us page title is incorrect!");

        // Step 5: Verify the 'Your Name' and 'E-Mail Address' fields are pre-filled
        logger.info("Verifying the 'Your Name' and 'E-Mail Address' fields are pre-filled correctly");
        WebElement nameField = driver.findElement(By.name("name"));
        WebElement emailField = driver.findElement(By.name("email"));

        // Verify if the name and email fields are correctly pre-filled
        String loggedInUserName = p.getProperty("name");
        String loggedInEmail = p.getProperty("email");

        logger.info("Expected username: " + loggedInUserName + ", Found username: " + nameField.getAttribute("value"));
        logger.info("Expected email: " + loggedInEmail + ", Found email: " + emailField.getAttribute("value"));

        Assert.assertTrue(nameField.getAttribute("value").equals(loggedInUserName), "Your Name is not pre-filled correctly!");
        Assert.assertTrue(emailField.getAttribute("value").equals(loggedInEmail), "E-Mail Address is not pre-filled correctly!");

        // Step 6: Enter enquiry text and submit the form
        logger.info("Entering text into the 'Enquiry' field and submitting the form");
        WebElement enquiryField = driver.findElement(By.name("enquiry"));
        enquiryField.sendKeys("This is a test enquiry message.");

        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        logger.info("Clicking on 'Submit' button");
        submitButton.click();


        // Step 8: Click 'Continue' to go back to the homepage
        logger.info("Clicking on 'Continue' to return to the homepage");
        WebElement continueButton = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        continueButton.click();

        // Step 9: Verify that the user is redirected to the Home page
        logger.info("Verifying if the user is redirected to the Home page");
        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL after clicking 'Continue': " + currentUrl);
        Assert.assertTrue(currentUrl.contains("index.php?route=common/home"), "User was not redirected to the homepage!");

        // Test completed successfully
        logger.info("Test completed successfully.");
    }
}
