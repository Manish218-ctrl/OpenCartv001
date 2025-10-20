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
            // Step 1: Open homepage
            driver.get(rb.getString("appURL"));
            logger.info("URL opened");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            // Step 2: Navigate to LoginPage
            LoginPage lp = new LoginPage(driver);
            Assert.assertTrue(lp.isLoginPageDisplayed(), "Login page not displayed!");

            // Step 3: Click on Continue under New Customer section
            RegisterPage rp = lp.clickContinueButtonNewCustomer();
            Assert.assertTrue(rp.isRegisterPageDisplayed(), "Register Account page not displayed!");

            // Step 4: Navigate back to Login page
            driver.navigate().back();
            Assert.assertTrue(lp.isLoginPageDisplayed(), "Login page not displayed after navigating back!");

            // Step 5: Validate navigation options (example: Footer, Header etc.)
            hp.clickMyAccount();   // Example navigation
            hp.clickLogin();       // Back to login again
            Assert.assertTrue(lp.isLoginPageDisplayed(), "Login page not displayed after navigating via header!");

            logger.info("***** Finished TC_LF_010_ValidateNavigationFromLoginPageTest *****");

        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
}
