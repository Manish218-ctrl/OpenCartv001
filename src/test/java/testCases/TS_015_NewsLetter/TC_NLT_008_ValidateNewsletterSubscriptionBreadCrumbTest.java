package testCases.TS_015_NewsLetter;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_NLT_008_ValidateNewsletterSubscriptionBreadCrumbTest extends BaseClass {

    WebDriver driver;
    Homepage homepage;
    NewsletterPage newsletterPage;
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(TC_NLT_008_ValidateNewsletterSubscriptionBreadCrumbTest.class);

    @BeforeClass
    public void setupTestCase() throws IOException {
        // Initialize WebDriver for the test case
        logger.info("Initializing WebDriver for TC_NLT_008.");
        setup("windows", "chrome");  // Setup the WebDriver
        homepage = new Homepage(driver);
        newsletterPage = new NewsletterPage(driver);
        loginPage = new LoginPage(driver);  // Ensure LoginPage is correctly initialized with the driver

        logger.info("Test case setup completed for TC_NLT_008.");
    }

    @Test
    public void validateNewsletterSubscriptionPage() {
        logger.info("Starting the test case: TC_NLT_008 - Newsletter Subscription Validation BreadCrumb Test");

        // Step 1: Open the application URL and login
        logger.info("Step 1: Opening the application and performing login.");

        String username = "jojol83635@besaies.com";
        String password = "'nA#$%?w72=!b*7";

        logger.debug("Login credentials used - Username: " + username + " | Password: " + password);
        try {
            loginPage.login(username, password);  // Ensure the login method is correctly defined
            logger.info("Login successful with username: " + username);
        } catch (Exception e) {
            logger.error("Login failed with username: " + username, e);
            Assert.fail("Login failed: " + e.getMessage());
        }

        // Step 2: Click on 'Newsletter' link from the right column of the Homepage
        logger.info("Step 2: Clicking on the 'Newsletter' link from the right column.");
        try {
            homepage.clickRightColumnNewsletter();
            logger.info("Successfully clicked on 'Newsletter' link from the right column.");
        } catch (Exception e) {
            logger.error("Failed to click 'Newsletter' link from the right column.", e);
            Assert.fail("Failed to click 'Newsletter' link: " + e.getMessage());
        }

        // Step 3: Validate the Page URL
        logger.info("Step 3: Validating the Page URL.");
        String currentURL = driver.getCurrentUrl();
        logger.debug("Current URL is: " + currentURL);
        try {
            Assert.assertTrue(currentURL.contains("newsletter"), "URL is not as expected. URL: " + currentURL);
            logger.info("Validated the current URL. Current URL contains 'newsletter'.");
        } catch (AssertionError e) {
            logger.error("URL validation failed. Expected URL to contain 'newsletter', but got: " + currentURL, e);
            Assert.fail("URL validation failed: " + e.getMessage());
        }

        // Step 4: Validate the Page Title
        logger.info("Step 4: Validating the Page Title.");
        String pageTitle = newsletterPage.getPageTitle();
        logger.debug("Page Title is: " + pageTitle);
        try {
            Assert.assertEquals(pageTitle, "Newsletter Subscription", "Page title is not correct.");
            logger.info("Page title is validated successfully. Page Title: " + pageTitle);
        } catch (AssertionError e) {
            logger.error("Page title validation failed. Expected 'Newsletter Subscription', but got: " + pageTitle, e);
            Assert.fail("Page title validation failed: " + e.getMessage());
        }

        // Step 5: Validate the Page Heading
        logger.info("Step 5: Validating the Page Heading.");
        String pageHeading = newsletterPage.getPageHeading();
        logger.debug("Page Heading is: " + pageHeading);
        try {
            Assert.assertEquals(pageHeading, "Newsletter Subscription", "Page heading is not correct.");
            logger.info("Page heading is validated successfully. Page Heading: " + pageHeading);
        } catch (AssertionError e) {
            logger.error("Page heading validation failed. Expected 'Newsletter Subscription', but got: " + pageHeading, e);
            Assert.fail("Page heading validation failed: " + e.getMessage());
        }

        // Step 6: Validate the Breadcrumb
        logger.info("Step 6: Validating the Breadcrumb.");
        String breadcrumb = homepage.getBreadcrumb();
        logger.debug("Breadcrumb is: " + breadcrumb);
        try {
            Assert.assertTrue(breadcrumb.contains("Home") && breadcrumb.contains("Newsletter"),
                    "Breadcrumb is not correct. Breadcrumb: " + breadcrumb);
            logger.info("Breadcrumb validation passed. Breadcrumb: " + breadcrumb);
        } catch (AssertionError e) {
            logger.error("Breadcrumb validation failed. Expected breadcrumb to contain 'Home' and 'Newsletter', but got: " + breadcrumb, e);
            Assert.fail("Breadcrumb validation failed: " + e.getMessage());
        }

        logger.info("Test case TC_NLT_008 completed successfully.");
    }
}
