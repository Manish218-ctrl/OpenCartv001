package testCases.TS_013_ContactUs;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_CU_004_ValidateContactUsPageValidationTest extends BaseClass {

        @Test
        public void validateContactUsPageFieldsAndDetails() {
            logger.info("Starting the test: validateContactUsPageFieldsAndDetails");

            // Step 1: Open the application URL

            driver.get(appURL);  // Use the loaded appURL from config.properties

            // Step 2: Click on 'Contact Us' footer link
            HomePage homepage = new HomePage(driver);
            homepage.clickFooterContactUsLink();

            // Step 3: Verify 'Contact Us' page title
            ContactUsPage contactUsPage = new ContactUsPage(driver);
            String pageTitle = contactUsPage.getPageTitle();
            Assert.assertTrue(pageTitle.contains("Contact Us"), "Contact Us page title is incorrect!");

            // Step 4: Verify 'Our Location' details
            WebElement locationDetails = driver.findElement(By.xpath("/html/body/div[2]/div/div/h3"));
            Assert.assertNotNull(locationDetails, "Our Location details are missing on Contact Us page!");

            // Step 5: Verify 'Your Store' and 'Telephone' details
            WebElement storeDetails = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[1]/strong"));
            WebElement phoneDetails = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/strong"));
            Assert.assertNotNull(storeDetails, "Store details are missing!");
            Assert.assertNotNull(phoneDetails, "Telephone details are missing!");

            // Step 6: Verify Contact Form fields - 'Your Name', 'E-Mail Address', and 'Enquiry'
            WebElement nameField = driver.findElement(By.name("name"));
            WebElement emailField = driver.findElement(By.name("email"));
            WebElement enquiryField = driver.findElement(By.name("enquiry"));

            Assert.assertTrue(nameField.isDisplayed(), "Your Name field is missing!");
            Assert.assertTrue(emailField.isDisplayed(), "E-Mail Address field is missing!");
            Assert.assertTrue(enquiryField.isDisplayed(), "Enquiry field is missing!");

            logger.info("Test completed successfully.");
        }
    }




