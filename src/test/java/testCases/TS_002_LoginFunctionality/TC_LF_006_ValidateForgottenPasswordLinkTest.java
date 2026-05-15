package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ForgotPasswordPage;
import testBase.BaseClass;

    public class TC_LF_006_ValidateForgottenPasswordLinkTest extends BaseClass {

        @Test
        public void verify_forgotten_password_link() {
            logger.info("***** Starting TC_LF_006_ValidateForgottenPasswordLinkTest *****");

            try {
                HomePage hp = new HomePage(getDriver());
                hp.clickMyAccount();
                hp.clickLogin();
                logger.info("Clicked on My Account -> Login");

                LoginPage lp = new LoginPage(getDriver());
                Assert.assertTrue(lp.isLoginPageDisplayed(),
                        "Login page should be displayed");
                logger.info("Login page displayed successfully");

                lp.clickForgotPassword();
                logger.info("Clicked on Forgotten Password link");

                ForgotPasswordPage fp = new ForgotPasswordPage(getDriver());
                Assert.assertTrue(lp.isForgotPasswordPageDisplayed(),
                        "Forgotten Password page should be displayed");
                logger.info("Forgotten Password page displayed successfully");

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_LF_006_ValidateForgottenPasswordLinkTest *****");
        }
    }



