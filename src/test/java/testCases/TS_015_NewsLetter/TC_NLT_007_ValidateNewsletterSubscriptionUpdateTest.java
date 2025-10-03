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

public class TC_NLT_007_ValidateNewsletterSubscriptionUpdateTest extends BaseClass {

        private static final Logger logger = LoggerFactory.getLogger(TC_NLT_007_ValidateNewsletterSubscriptionUpdateTest.class);

        @BeforeClass
        public void setupTestCase() throws IOException {
            logger.info("Initializing WebDriver for TC_NLT_007.");
            setup("windows", "chrome");
        }

        @Test
        public void validateNewsletterSubscriptionUpdate() {
            logger.info("Starting test: validateNewsletterSubscriptionUpdate");

            // Step 1: Open the application URL and login
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            logger.info("Opened application URL: https://tutorialsninja.com/demo/index.php?route=common/home");

            Homepage homepage = new Homepage(driver);

            // Step 1.1: Click on 'My Account' and 'Login' to login
            homepage.clickMyAccount();
            homepage.clickLogin();
            logger.info("Navigating to Login page.");

            // Step 1.2: Perform login with valid credentials
            String username = "jojol83635@besaies.com";  // Modify with valid username
            String password = "'nA#$%?w72=!b*7";  // Modify with valid password
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            logger.info("User logged in successfully with username: " + username);

            // Step 2: Click on 'Newsletter' Right Column option
            homepage.clickRightColumnNewsletter();
            logger.info("Navigating to 'Newsletter Subscription' page.");

            // Step 3: Check and select 'Yes' or 'No' radio option based on the current selection
            NewsletterPage newsletterPage = new NewsletterPage(driver);
            if (newsletterPage.isNoOptionSelected()) {
                newsletterPage.selectYesOption();
                logger.info("'Yes' option selected.");
            } else if (newsletterPage.isYesOptionSelected()) {
                newsletterPage.selectNoOption();
                logger.info("'No' option selected.");
            }

            // Step 4: Click on 'Continue' button
            newsletterPage.clickContinue();
            logger.info("Clicked on 'Continue' button.");

            // Step 5: Verify success message
            String successMessage = newsletterPage.getSuccessMessage();
            Assert.assertEquals(successMessage, "Success: Your newsletter subscription has been successfully updated!", "Success message not displayed.");
            logger.info("Success message displayed as expected.");

            // Step 6: Verify the user is redirected to 'My Account' page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            Assert.assertTrue(myAccountPage.isMyAccountPageExists(), "User is not redirected to 'My Account' page.");
            logger.info("Successfully redirected to 'My Account' page.");

          /*  // Step 7: Click on 'Newsletter' Right Column option to verify updated selection
            homepage.clickRightColumnNewsletter();
            Assert.assertTrue(newsletterPage.isSelectedOptionUpdated(), "Updated subscription option is not displayed.");
            logger.info("Updated subscription option is displayed correctly.");
*/
            // Step 7: Click on 'Newsletter' Right Column option to verify updated selection
            homepage.clickRightColumnNewsletter();

// Determine the expected option (based on what was selected)
            String expectedOption = newsletterPage.isYesOptionSelected() ? "Yes" : "No";
            Assert.assertTrue(newsletterPage.isSelectedOptionUpdated(expectedOption),
                    "Updated subscription option is not displayed.");
            logger.info("Updated subscription option is displayed correctly.");

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

