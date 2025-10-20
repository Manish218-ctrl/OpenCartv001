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
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_NLT_006_ValidateNewsletterBackButtonTest extends BaseClass {

        // Initialize logger for the class
        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_006_ValidateNewsletterBackButtonTest.class);


        @Test
        public void validateBackButtonFunctionalityInNewsletterPage() {
            logger.info("Starting test: validateBackButtonFunctionalityInNewsletterPage");

           performLogin();

            // Step 2: Click on 'Newsletter' Right Column option
            HomePage homepage= new HomePage(driver);

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

