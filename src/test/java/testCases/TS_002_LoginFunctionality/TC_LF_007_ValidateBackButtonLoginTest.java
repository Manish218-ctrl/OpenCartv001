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
            getDriver().get(p.getProperty("appURL"));
            logger.info("URL opened");

            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(getDriver());
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            MyAccountPage myAcc = new MyAccountPage(getDriver());
            Assert.assertTrue(myAcc.isUserLoggedIn(), "Login failed - My Account page not displayed!");

            getDriver().navigate().back();
            logger.info("Pressed browser back button");

            getDriver().navigate().forward();
            getDriver().navigate().refresh();
            logger.info("Navigated forward and refreshed");

            Assert.assertTrue(myAcc.isUserLoggedIn(), "User got logged out after pressing back button!");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_LF_007_ValidateBackButtonLoginTest *****");
    }
}
