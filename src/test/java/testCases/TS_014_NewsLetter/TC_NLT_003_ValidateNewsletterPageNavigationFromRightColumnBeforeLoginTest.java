package testCases.TS_014_NewsLetter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

public class TC_NLT_003_ValidateNewsletterPageNavigationFromRightColumnBeforeLoginTest extends BaseClass  {

    private static final Logger logger = LoggerFactory.getLogger(TC_NLT_003_ValidateNewsletterPageNavigationFromRightColumnBeforeLoginTest.class);



    @Test
    public void validateNewsletterPageNavigation() {
        logger.info("Starting test: validateNewsletterPageNavigation");

        //Open the application URL and ensure the user is not logged in
        logger.info("Opening application URL: https://tutorialsninja.com/demo/index.php?route=common/home");
        getDriver().get("https://tutorialsninja.com/demo/index.php?route=common/home");


        HomePage hp = new HomePage(getDriver());
        HomePage homepage = new HomePage(getDriver());

        hp.clickMyAccount();
        hp.clickLogin();
        logger.info("Navigated to Login Page");

        //Enter login credentials
        LoginPage lp = new LoginPage(getDriver());
        lp.setEmail(p.getProperty("email"));   // from config.properties
        lp.setPassword(p.getProperty("password")); // from config.properties
        lp.clickLogin();
        logger.info("Entered valid credentials and clicked Login");

        //Click on Newsletter link in Right Column
        logger.info("Clicking on the Newsletter link in the Right Column.");
        homepage.clickRightColumnNewsletter();


        //Verify the user is redirected to the Newsletter Subscription page
        logger.info("Verifying that the user is redirected to the Newsletter Subscription page.");
        NewsletterPage newsletterPage = new NewsletterPage(getDriver());
        String actualPageTitle = newsletterPage.getPageTitle();
        String expectedPageTitle = "Newsletter Subscription"; // Modify if necessary
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "User is not redirected to the Newsletter Subscription page.");
        logger.info("Successfully redirected to the Newsletter Subscription page.");

    }


}
