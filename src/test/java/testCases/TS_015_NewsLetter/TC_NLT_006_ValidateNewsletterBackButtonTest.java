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
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_NLT_006_ValidateNewsletterBackButtonTest extends BaseClass {

        // Initialize logger for the class
        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_006_ValidateNewsletterBackButtonTest.class);

        @BeforeClass
        public void setupTestCase() throws IOException {
            // Initialize WebDriver for the test case
            logger.info("Initializing WebDriver for TC_NLT_006.");
            setup("windows", "chrome");
        }

        @Test
        public void validateBackButtonFunctionalityInNewsletterPage() {
            logger.info("Starting test: validateBackButtonFunctionalityInNewsletterPage");

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
            String password = "'nA#$%?w72=!b*7";  // Modify password
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            logger.info("User logged in successfully with username: " + username);

            // Step 2: Click on 'Newsletter' Right Column option
            homepage.clickRightColumnNewsletter();
            logger.info("Navigating to 'Newsletter Subscription' page.");

            // Step 3: Click on the 'Back' button in the Newsletter page
            NewsletterPage newsletterPage = new NewsletterPage(driver);
            newsletterPage.clickBackButton(); // Assuming there's a 'Back' button method
            logger.info("Clicked on 'Back' button in Newsletter page.");

            // Step 4: Verify that the user is redirected to 'My Account' page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageExists();
            Assert.assertTrue(isMyAccountPageDisplayed, "User is not redirected to 'My Account' page.");
            logger.info("Successfully redirected to 'My Account' page.");
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

