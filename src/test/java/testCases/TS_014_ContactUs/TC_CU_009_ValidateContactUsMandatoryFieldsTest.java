package testCases.TS_014_ContactUs;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_CU_009_ValidateContactUsMandatoryFieldsTest extends BaseClass {

        // Create a WebDriver instance
        private Homepage homepage;
        private ContactUsPage contactUsPage;

        @BeforeClass
        public void setUp() {
            // Initialize the Homepage and ContactUsPage objects
            homepage = new Homepage(driver);
            contactUsPage = new ContactUsPage(driver);
        }

        @Test
        public void validateMandatoryFields() {
            // Step 1: Open the Application URL
            driver.get(appURL);
            logger.info("Application URL opened: " + appURL);

            // Step 2: Click on 'Contact Us' footer link to navigate to Contact Us page
            homepage.clickFooterContactUsLink();
            logger.info("Navigated to Contact Us page.");

            // Step 3: Validate the mandatory fields (should have red '*' symbol)

            // Validate that the 'Your Name' field has the mandatory '*' symbol
            WebElement nameMandatory = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[1]/label"));
            Assert.assertTrue(nameMandatory.isDisplayed(), "'Your Name' field is not marked as mandatory.");

            // Validate that the 'E-Mail Address' field has the mandatory '*' symbol
            WebElement emailMandatory = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[2]/label"));
            Assert.assertTrue(emailMandatory.isDisplayed(), "'E-Mail Address' field is not marked as mandatory.");

            // Validate that the 'Enquiry' field has the mandatory '*' symbol
            WebElement enquiryMandatory = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[3]/label"));
            Assert.assertTrue(enquiryMandatory.isDisplayed(), "'Enquiry' field is not marked as mandatory.");

            logger.info("Verified mandatory fields on the Contact Us page.");
        }
    }



