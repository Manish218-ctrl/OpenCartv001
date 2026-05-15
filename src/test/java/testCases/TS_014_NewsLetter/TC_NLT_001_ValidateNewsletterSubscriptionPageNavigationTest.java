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

        //Navigate to the Home Page
        logger.info("Navigating to the application URL: " + appURL);
        getDriver().get(appURL);
        logger.info("Application URL opened: " + appURL);

        //Login to the application
        logger.info("Logging into the application using credentials.");
       // performLogin();
        logger.info("Logged into the application successfully.");

        //Open HomePage
        HomePage hp = new HomePage(getDriver());
        hp.clickMyAccount();
        hp.clickLogin();
        logger.info("Navigated to Login Page");

        //Enter login credentials
        LoginPage lp = new LoginPage(getDriver());
        lp.setEmail(p.getProperty("email"));
        lp.setPassword(p.getProperty("password")); // from config.properties
        lp.clickLogin();
        logger.info("Entered valid credentials and clicked Login");

        //Click on the Newsletter link after login
        logger.info("Clicking on the Newsletter link on the homepage.");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickNewsletterLink();  // Assuming this method exists in HomePage
        logger.info("Navigated to Newsletter page.");

        //Wait until the page title contains "Newsletter Subscription"
        logger.info("Waiting for the page title to contain Newsletter Subscription.");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Newsletter Subscription"));
        logger.info("Page title contains Newsletter Subscription.");

        //Verify Page Title
        logger.info("Verifying the page title.");
        NewsletterPage newsletterPage = new NewsletterPage(getDriver());
        String pageTitleElement = newsletterPage.getPageTitle();
        Assert.assertEquals(pageTitleElement, "Newsletter Subscription", "Page Title mismatch.");
        logger.info("Page title verified successfully: Newsletter Subscription.");

        //Verify Page Heading
        logger.info("Verifying the page heading.");
        String PageHeading = newsletterPage.getPageHeading();
        Assert.assertEquals(PageHeading, "Newsletter Subscription", "Page Heading mismatch.");
        logger.info("Page heading verified successfully: Newsletter Subscription.");

        //Verify if the correct option is selected (default behavior)
        logger.info("Verifying if Yes option is selected by default.");
        boolean isYesSelected = newsletterPage.isYesOptionSelected();
        Assert.assertTrue(isYesSelected, "Yes option is not selected by default.");
        logger.info("Yes option is selected by default.");

        //Verify if No option is not selected by default
        logger.info("Verifying if No option is not selected by default.");
        boolean isNoSelected = newsletterPage.isNoOptionSelected();
        Assert.assertFalse(isNoSelected, "No option should not be selected by default.");
        logger.info("No option is not selected by default.");

        //Click Continue button
        logger.info("Clicking the Continue button.");
        newsletterPage.clickContinue();
        logger.info("Continue button clicked.");



        logger.info("Newsletter Page URL, Title, and Heading validated successfully.");
    }
}
