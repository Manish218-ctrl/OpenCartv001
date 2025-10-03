package testCases.TS_002_LoginFunctionality;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LF_004_ValidateKeyboardLoginTest extends BaseClass {

    @Test
    public void verify_login_with_keyboard_keys() {
        logger.info("***** Starting TC_LF_004_ValidateKeyboardLoginTest *****");

        try {
            // Step 1: Open Homepage and go to Login Page
            Homepage hp = new Homepage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);

            // Step 2: Navigate with TAB to Email field and enter email
            lp.getEmailField().sendKeys(Keys.TAB); // Focus on email
            lp.getEmailField().sendKeys(rb.getString("email")); // From config.properties

            // Step 3: Navigate with TAB to Password field and enter password
            lp.getPasswordField().sendKeys(Keys.TAB);
            lp.getPasswordField().sendKeys(rb.getString("password"));

            // Step 4: Navigate with TAB to Login button and press ENTER
            lp.getLoginButton().sendKeys(Keys.ENTER);

            // Step 5: Validate successful login

            MyAccountPage myAcc = new MyAccountPage(driver);
            boolean loginStatus = myAcc.isMyAccountPageExists();

            Assert.assertTrue(loginStatus, "Login failed using keyboard keys");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Exception occurred during keyboard login test");
        }

        logger.info("***** Finished TC_LF_004_ValidateKeyboardLoginTest *****");
    }
}
