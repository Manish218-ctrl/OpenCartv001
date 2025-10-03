package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.ForgotPasswordPage;
import testBase.BaseClass;

    public class TC_LF_006_ValidateForgottenPasswordLinkTest extends BaseClass {

        @Test
        public void verify_forgotten_password_link() {
            logger.info("***** Starting TC_LF_006_ValidateForgottenPasswordLinkTest *****");

            try {
                // Step 1: Navigate to Home page and click My Account -> Login
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();
                logger.info("Clicked on My Account -> Login");

                // Step 2: Verify Login page is displayed
                LoginPage lp = new LoginPage(driver);
                Assert.assertTrue(lp.isLoginPageDisplayed(),
                        "Login page should be displayed");
                logger.info("Login page displayed successfully");

                // Step 3: Click Forgotten Password link
                lp.clickForgotPassword();
                logger.info("Clicked on Forgotten Password link");

                // Step 4: Verify Forgotten Password page is displayed
                ForgotPasswordPage fp = new ForgotPasswordPage(driver);
                Assert.assertTrue(fp.isForgotPasswordPageDisplayed(),
                        "Forgotten Password page should be displayed");
                logger.info("Forgotten Password page displayed successfully");

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_LF_006_ValidateForgottenPasswordLinkTest *****");
        }
    }



