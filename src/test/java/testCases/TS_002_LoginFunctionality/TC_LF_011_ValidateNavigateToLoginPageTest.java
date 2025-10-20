package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

    public class TC_LF_011_ValidateNavigateToLoginPageTest extends BaseClass {

        @Test
        public void verify_navigation_to_login_page() {
            logger.info("***** Starting TC_LF_011_ValidateNavigateToLoginPageTest *****");

            try {
                driver.get(rb.getString("appURL"));
                logger.info("URL opened");

                HomePage home = new HomePage(driver);
                LoginPage login = new LoginPage(driver);
                RegisterPage register = new RegisterPage(driver);

                // --- Way 1: From Register Page ---
                home.clickMyAccount();
                home.clickRegister();
                Assert.assertTrue(register.isRegisterPageDisplayed(), "Register Page not displayed!");
                logger.info("Navigated to Register Page");

                // Click on Login link inside Register Page
                register.clickLoginLink();
                Assert.assertTrue(login.isLoginPageDisplayed(), "Login Page not displayed via Register Page link!");
                logger.info("Navigated to Login Page via Register Page link");

                driver.navigate().back();  // back to Register page

                // --- Way 2: From Right Column ---
                login.clickRightColumnLogin();
                Assert.assertTrue(login.isLoginPageDisplayed(), "Login Page not displayed via Right Column!");
                logger.info("Navigated to Login Page via Right Column");

                driver.navigate().back();  // back to previous page

                // --- Way 3: From My Account Dropmenu ---
                home.clickMyAccount();
                home.clickLogin();
                Assert.assertTrue(login.isLoginPageDisplayed(), "Login Page not displayed via My Account dropmenu!");
                logger.info("Navigated to Login Page via My Account dropmenu");

            } catch (Exception e) {
                logger.error("Test Failed due to exception: " + e.getMessage());
                Assert.fail("Test Failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_LF_011_ValidateNavigateToLoginPageTest *****");
        }
    }



