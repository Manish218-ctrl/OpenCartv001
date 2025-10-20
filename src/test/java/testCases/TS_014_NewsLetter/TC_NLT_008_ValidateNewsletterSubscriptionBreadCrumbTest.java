package testCases.TS_014_NewsLetter;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

public class TC_NLT_008_ValidateNewsletterSubscriptionBreadCrumbTest extends BaseClass {

    // These are class-level Page Object variables, initialized in the @Test method
    HomePage homepage;
    NewsletterPage newsletterPage;
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(TC_NLT_008_ValidateNewsletterSubscriptionBreadCrumbTest.class);


    @Test
    public void validateNewsletterSubscriptionPage() {
        logger.info("Starting the test case: TC_NLT_008 - Newsletter Subscription Validation BreadCrumb Test");

        // 1. Perform Login (sets up WebDriver and logs in)
        performLogin();

        // 2. Initialize Page Objects using the inherited 'driver' instance
        homepage = new HomePage(driver);
        newsletterPage = new NewsletterPage(driver);
        // loginPage is typically not needed here, but kept for completeness
        loginPage = new LoginPage(driver);


        // Step 2: Click on 'Newsletter' link from the right column of the HomePage
        logger.info("Step 2: Clicking on the 'Newsletter' link from the right column.");
        try {
            homepage.clickRightColumnNewsletter(); // Uses the correctly initialized class-level 'homepage'
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
            // Assert that the breadcrumb contains BOTH required parts
            Assert.assertTrue(breadcrumb.contains("Account") && breadcrumb.contains("Newsletter"),
                    "Breadcrumb is not correct. Breadcrumb: " + breadcrumb);
            logger.info("Breadcrumb validation passed. Breadcrumb: " + breadcrumb);
        } catch (AssertionError e) {
            logger.error("Breadcrumb validation failed. Expected breadcrumb to contain 'Home' and 'Newsletter', but got: " + breadcrumb, e);
            Assert.fail("Breadcrumb validation failed: " + e.getMessage());
        }


                logger.info("Breadcrumb validation passed. Breadcrumb: " + breadcrumb);


        logger.info("Test case TC_NLT_008 completed successfully.");
    }
}