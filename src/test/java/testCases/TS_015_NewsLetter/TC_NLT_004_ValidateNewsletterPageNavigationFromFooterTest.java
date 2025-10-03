package testCases.TS_015_NewsLetter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

    public class TC_NLT_004_ValidateNewsletterPageNavigationFromFooterTest extends BaseClass {

        // Initialize the logger
        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_004_ValidateNewsletterPageNavigationFromFooterTest.class);

        @BeforeClass
        public void setupTestCase() throws IOException {
            // Initialize the WebDriver and perform the setup
            logger.info("Setting up the WebDriver for the test case.");
            setup("windows", "chrome");  // Modify based on your configuration (e.g., Chrome, Edge, etc.)
        }

        @Test
        public void validateNewsletterPageNavigationFromFooter() {
            logger.info("Starting test: validateNewsletterPageNavigationFromFooter");

            // Step 1: Open the application URL and ensure the user is not logged in
            logger.info("Opening application URL: https://tutorialsninja.com/demo/index.php?route=common/home");
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");

            // Step 1.1: Ensure user is not logged in (if logged out, proceed to the next step)
            Homepage homepage = new Homepage(driver);
            homepage.clickFooterNewsletterLink();  // Clicking on the 'Newsletter' footer link

            // Step 2: Ensure that the user is redirected to the Login page
            logger.info("Verifying that the user is redirected to the Login page.");
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed.");
            logger.info("Login page is displayed as expected.");

            // Step 3: Login with valid credentials
            String username = "jojol83635@besaies.com";
            String password = "'nA#$%?w72=!b*7";
            logger.info("Logging in with username: " + username);
            loginPage.login(username, password);
            logger.info("Login attempt made with provided credentials.");

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


