package testCases.TS_027_ContactUs;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ContactUsPage;
import testBase.BaseClass;

    public class TC_CU_001_ValidateContactUsPageNavigationFromHeaderOptionsTest extends BaseClass {

        @Test
        public void validateNavigationToContactUsPage() {
            logger.info("Starting the test: validateNavigationToContactUsPage");

            // Step 1: Open the application URL
            logger.info("Opening application URL...");
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");

            // Step 2: Navigate to 'Contact Us' page by clicking the 'Phone' icon in the header
            HomePage homepage = new HomePage(driver);
            logger.info("Clicking on 'Phone' icon from header options...");
            homepage.clickContactUsHeaderOption();

            // Step 3: Verify that the user is on the 'Contact Us' page
            ContactUsPage contactUsPage = new ContactUsPage(driver);
            logger.info("Verifying the page title...");
            String pageTitle = contactUsPage.getPageTitle();
            Assert.assertTrue(pageTitle.contains("Contact Us"), "User is not navigated to the 'Contact Us' page.");

            logger.info("Test completed successfully.");
        }
    }



