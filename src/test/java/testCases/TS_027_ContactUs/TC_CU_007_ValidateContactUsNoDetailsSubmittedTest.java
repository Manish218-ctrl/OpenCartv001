package testCases.TS_027_ContactUs;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_CU_007_ValidateContactUsNoDetailsSubmittedTest extends BaseClass {

        @Test
        public void validateNoDetailsSubmitted() {
            logger.info("Starting the test: validateNoDetailsSubmitted");

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

            // Step 4: Do not fill any fields
            contactUsPage.nameField.clear();
            contactUsPage.emailField.clear();
            contactUsPage.enquiryField.clear();

            // Step 5: Click 'Submit' button
            contactUsPage.clickSubmitButton();
            logger.info("Clicked on 'Submit' button");


            // Step 6: Validate field level error messages for all fields
            WebElement nameError = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[1]/div/div"));
            WebElement emailError = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[2]/div/div"));
            WebElement enquiryError = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[3]/div/div"));


            // Validate error messages
            Assert.assertTrue(nameError.isDisplayed(), "Error message for 'Your Name' not displayed");
            Assert.assertTrue(emailError.isDisplayed(), "Error message for 'E-Mail Address' not displayed");
            Assert.assertTrue(enquiryError.isDisplayed(), "Error message for 'Enquiry' not displayed");

            logger.info("Field level validation messages displayed successfully");


            logger.info("Test completed successfully.");
        }
    }



