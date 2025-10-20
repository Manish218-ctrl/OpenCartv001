package testCases.TS_014_NewsLetter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

    public class TC_NLT_004_ValidateNewsletterPageNavigationFromFooterTest extends BaseClass {

        // Initialize the logger
        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_004_ValidateNewsletterPageNavigationFromFooterTest.class);


        @Test
        public void validateNewsletterPageNavigationFromFooter() {
            logger.info("Starting test: validateNewsletterPageNavigationFromFooter");

            performLogin();

            MyAccountPage accountPage=new MyAccountPage(driver);

            accountPage.clicknewsletterfooterlnk();


            // Step 4: Verify the user is redirected to the Newsletter Subscription page
            logger.info("Verifying that the user is redirected to the Newsletter Subscription page.");
            NewsletterPage newsletterPage = new NewsletterPage(driver);
            String actualPageTitle = newsletterPage.getPageTitle();
            String expectedPageTitle = "Newsletter Subscription"; // Modify if necessary
            Assert.assertEquals(actualPageTitle, expectedPageTitle, "User is not redirected to the Newsletter Subscription page.");
            logger.info("Successfully redirected to the 'Newsletter Subscription' page.");
        }

        @AfterClass
        public void tearDown() {
            // Close the browser after the test
            logger.info("Test execution completed. Closing the browser.");
            if (driver != null) {
                driver.quit(); // Close all browser windows
            }
        }
    }


