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

    public class TC_NLT_005_ValidateNewsletterPageNavigationAfterLoginTest extends BaseClass {

        // Logger initialization
        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_005_ValidateNewsletterPageNavigationAfterLoginTest.class);

        @BeforeClass
        public void setupTestCase() throws IOException {
            // Initializing WebDriver for the test case
            logger.info("Initializing WebDriver for TC_NLT_005.");
            setup("windows", "chrome");  // Modify for the specific browser
        }

        @Test
        public void validateNewsletterPageNavigationAfterLogin() {
            logger.info("Starting test: validateNewsletterPageNavigationAfterLogin");

            // Step 1: Open the application URL and login
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            logger.info("Opened application URL: https://tutorialsninja.com/demo/index.php?route=common/home");

            Homepage homepage = new Homepage(driver);

            // Click on 'My Account' and 'Login' to login
            homepage.clickMyAccount();
            homepage.clickLogin();
            logger.info("Navigating to Login page.");

            // Perform login with valid credentials
            String username = "jojol83635@besaies.com";
            String password = "'nA#$%?w72=!b*7\n";
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            logger.info("User logged in successfully with username: " + username);

            // Step 2: Click on 'Newsletter' link in the Footer after login
            homepage.clickFooterNewsletterLink();
            logger.info("Clicked on 'Newsletter' link from the Footer.");

            // Step 3: Validate the user is redirected to the 'Newsletter Subscription' page
            NewsletterPage newsletterPage = new NewsletterPage(driver);
            String actualPageTitle = newsletterPage.getPageTitle();
            String expectedPageTitle = "Newsletter Subscription"; // Modify if needed
            Assert.assertEquals(actualPageTitle, expectedPageTitle, "User is not redirected to the Newsletter Subscription page.");
            logger.info("Successfully redirected to the 'Newsletter Subscription' page.");
        }

        @AfterClass
        public void tearDown() {
            // Close the browser after the test is complete
            logger.info("Test completed. Closing the browser.");
            if (driver != null) {
                driver.quit();
            }
        }
    }

