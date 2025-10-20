package testCases.TS_014_NewsLetter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_NLT_001_ValidateNewsletterSubscriptionPageNavigationTest extends BaseClass {

    WebElement optNewsletterYes;

    @Test
    public void validateNewsletterPage() throws InterruptedException {

        // Step 1: Navigate to the Home Page
        logger.info("Step 1: Navigating to the application URL: " + appURL);
        driver.get(appURL);
        logger.info("Application URL opened: " + appURL);

        // Step 2: Login to the application
        logger.info("Step 2: Logging into the application using credentials.");
       // performLogin();
        logger.info("Logged into the application successfully.");

        // Step 1: Open HomePage
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();
        logger.info("Navigated to Login Page");

        // Step 2: Enter login credentials
        LoginPage lp = new LoginPage(driver);
        lp.setEmail(p.getProperty("email"));   // from config.properties
        lp.setPassword(p.getProperty("password")); // from config.properties
        lp.clickLogin();
        logger.info("Entered valid credentials and clicked Login");

        // Step 3: Click on the 'Newsletter' link after login
        logger.info("Step 3: Clicking on the 'Newsletter' link on the homepage.");
        HomePage homePage = new HomePage(driver);
        homePage.clickNewsletterLink();  // Assuming this method exists in HomePage
        logger.info("Navigated to 'Newsletter' page.");

        // Step 4: Wait until the page title contains "Newsletter Subscription"
        logger.info("Step 4: Waiting for the page title to contain 'Newsletter Subscription'.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Newsletter Subscription"));
        logger.info("Page title contains 'Newsletter Subscription'.");

        // Step 5: Verify Page Title
        logger.info("Step 5: Verifying the page title.");
        NewsletterPage newsletterPage = new NewsletterPage(driver);
        String pageTitleElement = newsletterPage.getPageTitle();
        Assert.assertEquals(pageTitleElement, "Newsletter Subscription", "Page Title mismatch.");
        logger.info("Page title verified successfully: 'Newsletter Subscription'.");

        // Step 6: Verify Page Heading
        logger.info("Step 6: Verifying the page heading.");
        String PageHeading = newsletterPage.getPageHeading();
        Assert.assertEquals(PageHeading, "Newsletter Subscription", "Page Heading mismatch.");
        logger.info("Page heading verified successfully: 'Newsletter Subscription'.");


        // Step 7: Verify if the correct option is selected (default behavior)
        logger.info("Step 7: Verifying if 'Yes' option is selected by default.");
        boolean isYesSelected = newsletterPage.isYesOptionSelected();
        Assert.assertTrue(isYesSelected, "'Yes' option is not selected by default.");
        logger.info("'Yes' option is selected by default.");

        // Step 8: Verify if 'No' option is not selected by default
        logger.info("Step 8: Verifying if 'No' option is not selected by default.");
        boolean isNoSelected = newsletterPage.isNoOptionSelected();
        Assert.assertFalse(isNoSelected, "'No' option should not be selected by default.");
        logger.info("'No' option is not selected by default.");

        Thread.sleep(2800);

        // Step 9: Click Continue button
        logger.info("Step 9: Clicking the 'Continue' button.");
        newsletterPage.clickContinue();
        logger.info("Continue button clicked.");



        logger.info("Newsletter Page URL, Title, and Heading validated successfully.");
    }
}
