package testCases.TS_015_NewsLetter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_NLT_003_ValidateNewsletterPageNavigationFromRightColumnTest extends BaseClass  {

    // Initialize the logger
    private static final Logger logger = LoggerFactory.getLogger(TC_NLT_003_ValidateNewsletterPageNavigationFromRightColumnTest.class);

    @BeforeClass
    public void setupTestCase() throws IOException {
        // Initialize the WebDriver and perform the setup
        logger.info("Setting up the WebDriver for the test case.");
        setup("windows", "chrome");  // You can modify this based on your configuration
    }

    @Test
    public void validateNewsletterPageNavigation() {
        logger.info("Starting test: validateNewsletterPageNavigation");

        // Step 1: Open the application URL and ensure the user is not logged in
        logger.info("Opening application URL: https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");

        // Step 1.1: Click on "My Account" dropdown and then click "Login"
        logger.info("Clicking on 'My Account' dropdown and selecting 'Login'.");
        Homepage homepage = new Homepage(driver);
        homepage.clickMyAccount();  // Click the "My Account" dropdown
        homepage.clickLogin();      // Click the "Login" link

        // Step 2: Click on 'Newsletter' link in Right Column
        logger.info("Clicking on the 'Newsletter' link in the Right Column.");
        homepage.clickRightColumnNewsletter();

        // Step 3: Ensure that the user is redirected to the Login page
        logger.info("Verifying that the user is redirected to the Login page.");
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed.");
        logger.info("Login page is displayed as expected.");

        // Step 4: Login with valid credentials
        String username = "vatipih340@colimarl.com";  // Use appropriate username
        String password = "=w4,SdJG*,=8@a%";  // Use appropriate password
        logger.info("Logging in with username: " + username);
        loginPage.login(username, password);
        logger.info("Login attempt made with provided credentials.");

        // Step 5: Verify the user is redirected to the Newsletter Subscription page
        logger.info("Verifying that the user is redirected to the Newsletter Subscription page.");
        NewsletterPage newsletterPage = new NewsletterPage(driver);
        String actualPageTitle = newsletterPage.getPageTitle();
        String expectedPageTitle = "Newsletter Subscription"; // Modify if necessary
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "User is not redirected to the Newsletter Subscription page.");
        logger.info("Successfully redirected to the 'Newsletter Subscription' page.");

    }


}
