package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_LF_009_ValidateUnsuccessfulLoginAttemptsTest extends BaseClass {

    /**
     * Test Case ID: TC_LF_009
     * Module: (TS_002) Login Functionality
     *
     * Description:
     * Validate the behavior of unsuccessful login attempts with invalid credentials.
     *
     * Steps:
     * 1. Open the Application URL (https://demo.opencart.com) in a supported browser.
     * 2. Click on 'My Account' dropmenu.
     * 3. Click on 'Login' option.
     * 4. Enter invalid email and password.
     * 5. Click on 'Login' button.
     * 6. Repeat steps 4–5 five times.
     *
     * Test Data:
     * Email Address: xyzabc123@gmail.com
     * Password: xyzabc123
     *
     * Expected Result:
     * For each of the 5 attempts, a generic warning message should be displayed:
     * "Warning: No match for E-Mail Address and/or Password."
     *
     * Note:
     * The demo site (https://demo.opencart.com) does not enforce account lockout
     * after multiple failed attempts, so we validate the generic warning instead.
     */
    @Test
    public void verify_unsuccessful_login_attempts() {
        logger.info("***** Starting TC_LF_009_ValidateUnsuccessfulLoginAttemptsTest *****");

        try {
            driver.get(rb.getString("appURL")); // opens https://demo.opencart.com
            logger.info("URL opened");

            Homepage hp = new Homepage(driver);
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
