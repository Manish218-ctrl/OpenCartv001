package testCases.TS_002_LoginFunctionality;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LF_004_ValidateKeyboardLoginTest extends BaseClass {

    @Test
    public void verify_login_with_keyboard_keys() {
        logger.info("***** Starting TC_LF_004_ValidateKeyboardLoginTest *****");

        try {
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(getDriver());

            lp.getEmailField().sendKeys(Keys.TAB); // Focus on email
            lp.getEmailField().sendKeys(p.getProperty("email")); // From config.properties

            lp.getPasswordField().sendKeys(Keys.TAB);
            lp.getPasswordField().sendKeys(p.getProperty("password"));

            lp.getLoginButton().sendKeys(Keys.ENTER);

            MyAccountPage myAcc = new MyAccountPage(getDriver());
            boolean loginStatus = myAcc.isMyAccountPageExists();

            Assert.assertTrue(loginStatus, "Login failed using keyboard keys");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Exception occurred during keyboard login test");
        }

        logger.info("***** Finished TC_LF_004_ValidateKeyboardLoginTest *****");
    }
}
