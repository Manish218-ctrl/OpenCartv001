package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LF_007_ValidateBackButtonLoginTest extends BaseClass {

    @Test
    public void verify_login_with_browser_back_button() {
        logger.info("***** Starting TC_LF_007_ValidateBackButtonLoginTest *****");

        try {
            driver.get(rb.getString("appURL"));
            logger.info("URL opened");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(rb.getString("email"));
            lp.setPassword(rb.getString("password"));
            lp.clickLogin();

            MyAccountPage myAcc = new MyAccountPage(driver);
            Assert.assertTrue(myAcc.isUserLoggedIn(), "Login failed - My Account page not displayed!");

            // press back button
            driver.navigate().back();
            logger.info("Pressed browser back button");

            // Navigate forward again (to return to MyAccountPage)
            driver.navigate().forward();
            driver.navigate().refresh();
            logger.info("Navigated forward and refreshed");

            // Verify user is still logged in (session should persist)
            Assert.assertTrue(myAcc.isUserLoggedIn(), "User got logged out after pressing back button!");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_LF_007_ValidateBackButtonLoginTest *****");
    }
}
