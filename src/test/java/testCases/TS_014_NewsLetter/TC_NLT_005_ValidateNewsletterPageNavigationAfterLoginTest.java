package testCases.TS_014_NewsLetter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

    public class TC_NLT_005_ValidateNewsletterPageNavigationAfterLoginTest extends BaseClass {

        // Logger initialization
        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_005_ValidateNewsletterPageNavigationAfterLoginTest.class);


        @Test
        public void validateNewsletterPageNavigationAfterLogin() {
            logger.info("Starting test: validateNewsletterPageNavigationAfterLogin");

            performLogin();


            //Click on Newsletter link in the Footer after login
            HomePage homepage=new HomePage(getDriver());

            homepage.clickFooterNewsletterLink();
            logger.info("Clicked on Newsletter link from the Footer.");

            //Validate the user is redirected to the Newsletter Subscription page
            NewsletterPage newsletterPage = new NewsletterPage(getDriver());
            String actualPageTitle = newsletterPage.getPageTitle();
            String expectedPageTitle = "Newsletter Subscription"; // Modify if needed
            Assert.assertEquals(actualPageTitle, expectedPageTitle, "User is not redirected to the Newsletter Subscription page.");
            logger.info("Successfully redirected to the Newsletter Subscription page.");
        }

        @AfterClass
        public void tearDown() {
            // Close the browser after the test is complete
            logger.info("Test completed. Closing the browser.");
            if (getDriver() != null) {
                getDriver().quit();
            }
        }
    }

