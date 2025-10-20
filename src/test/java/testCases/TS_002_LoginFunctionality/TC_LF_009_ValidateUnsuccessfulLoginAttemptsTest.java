package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_LF_009_ValidateUnsuccessfulLoginAttemptsTest extends BaseClass {


    @Test
    public void verify_unsuccessful_login_attempts() {
        logger.info("***** Starting TC_LF_009_ValidateUnsuccessfulLoginAttemptsTest *****");

        try {
            driver.get(rb.getString("appURL")); // opens https://demo.opencart.com
            logger.info("URL opened");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);

            // Invalid credentials (test data)
            String invalidEmail = "xyzabc123@gmail.com";
            String invalidPassword = "xyzabc123";

            // Perform 5 unsuccessful login attempts
            for (int i = 1; i <= 5; i++) {
                lp.setEmail(invalidEmail);
                lp.setPassword(invalidPassword);
                lp.clickLogin();

                String warning = lp.getWarningMessage();
                Assert.assertTrue(
                        warning.contains("Warning: No match for E-Mail Address and/or Password."),
                        "Expected generic warning not displayed on attempt " + i
                );
                logger.info("Attempt " + i + " - Generic warning displayed.");
            }
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            Assert.fail();
        }

        logger.info("***** Finished TC_LF_009_ValidateUnsuccessfulLoginAttemptsTest *****");
    }
}
