package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

    public class TC_RF_006_ValidateNavigateRegisterPageTest extends BaseClass {

        @Test
        public void verify_navigation_to_register_page() {
            logger.info("***** Starting TC_RF_006_ValidateNavigateRegisterPageTest *****");

            try {
                Homepage hp = new Homepage(driver);
                RegisterPage regPage = new RegisterPage(driver);

                // Path 1: My Account → Register
                hp.clickMyAccount();
                hp.clickRegister();
                Assert.assertTrue(regPage.isRegisterPageDisplayed(),
                        "Register page not opened via My Account → Register");

                driver.navigate().back();

                // Path 2: My Account → Login → Continue (New Customer)
                hp.clickMyAccount();
                hp.clickLogin();
                LoginPage login = new LoginPage(driver);
                login.clickContinueButtonNewCustomer();
                Assert.assertTrue(regPage.isRegisterPageDisplayed(),
                        "Register page not opened via Login → Continue (New Customer)");

                driver.navigate().back();
                driver.navigate().back(); // back to Home page

                // Path 3: Right Column → Register (first go to Login page to see the right column)
                hp.clickMyAccount();
                hp.clickLogin();
                hp.clickRightColumnRegister();
                Assert.assertTrue(regPage.isRegisterPageDisplayed(),
                        "Register page not opened via Right Column Register");

                logger.info("Test Passed: All navigation paths to Register Account page validated.");
            }
            catch (Exception e) {
                logger.error("Test failed: " + e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_RF_006_ValidateNavigateRegisterPageTest *****");
        }
    }
