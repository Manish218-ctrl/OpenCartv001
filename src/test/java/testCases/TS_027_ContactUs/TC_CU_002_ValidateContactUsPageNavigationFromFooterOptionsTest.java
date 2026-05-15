package testCases.TS_027_ContactUs;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ContactUsPage;
import testBase.BaseClass;

    public class TC_CU_002_ValidateContactUsPageNavigationFromFooterOptionsTest extends BaseClass {

        @Test
        public void validateNavigationToContactUsPageFromFooter() {
            logger.info("Starting the test: validateNavigationToContactUsPageFromFooter");

            //Open the application URL
            logger.info("Opening application URL...");
            getDriver().get("https://tutorialsninja.com/demo/index.php?route=common/home");  // Replace with the actual application URL

            //Click the Contact Us link in the Footer options
            HomePage homepage = new HomePage(getDriver());
            logger.info("Clicking on Contact Us footer link...");
            homepage.clickFooterContactUsLink();  // This is the method you will create in HomePage

            //Verify that the user is on the Contact Us page
            ContactUsPage contactUsPage = new ContactUsPage(getDriver());
            logger.info("Verifying the page title...");
            String pageTitle = contactUsPage.getPageTitle();
            Assert.assertTrue(pageTitle.contains("Contact Us"), "User is not navigated to the Contact Us page.");

            logger.info("Test completed successfully.");
        }
    }



