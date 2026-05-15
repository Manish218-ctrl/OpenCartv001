package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC_LF_010_ValidateNavigationFromLoginPageTest extends BaseClass {

    @Test
    public void verify_navigation_from_login_page() {
        logger.info("***** Starting TC_LF_010_ValidateNavigationFromLoginPageTest *****");

        try {
            getDriver().get(p.getProperty("appURL"));
            logger.info("URL opened");

            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(getDriver());
            Assert.assertTrue(lp.isLoginPageDisplayed(), "Login page not displayed!");

            RegisterPage rp = lp.clickContinueButtonNewCustomer();
            Assert.assertTrue(rp.isRegisterPageDisplayed(), "Register Account page not displayed!");

            getDriver().navigate().back();
            Assert.assertTrue(lp.isLoginPageDisplayed(), "Login page not displayed after navigating back!");

            hp.clickMyAccount();
            hp.clickLogin();
            Assert.assertTrue(lp.isLoginPageDisplayed(), "Login page not displayed after navigating via header!");

            logger.info("***** Finished TC_LF_010_ValidateNavigationFromLoginPageTest *****");

        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
}
