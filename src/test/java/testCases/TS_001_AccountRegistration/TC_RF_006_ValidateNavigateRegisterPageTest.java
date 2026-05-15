package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC_RF_006_ValidateNavigateRegisterPageTest extends BaseClass {

    @Test
    public void verify_navigation_to_register_page() {

        logger.info(
                "***** Starting TC_RF_006_ValidateNavigateRegisterPageTest *****"
        );

        try {

            HomePage hp = new HomePage(getDriver());

            RegisterPage regPage =
                    new RegisterPage(getDriver());

            hp.clickMyAccount();
            hp.clickRegister();

            Assert.assertTrue(
                    regPage.isRegisterPageDisplayed(),
                    "Register page not opened via My Account → Register"
            );

            getDriver().navigate().back();

            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage login =
                    new LoginPage(getDriver());

            login.clickContinueButtonNewCustomer();

            Assert.assertTrue(
                    regPage.isRegisterPageDisplayed(),
                    "Register page not opened via Login → Continue (New Customer)"
            );

            getDriver().navigate().back();
            getDriver().navigate().back();

            hp.clickMyAccount();
            hp.clickLogin();

            hp.clickRightColumnRegister();

            Assert.assertTrue(
                    regPage.isRegisterPageDisplayed(),
                    "Register page not opened via Right Column Register"
            );

            logger.info(
                    "Test Passed: All navigation paths to Register Account page validated."
            );

        } catch (Exception e) {

            logger.error(
                    "Test failed: " + e.getMessage()
            );

            Assert.fail(
                    "Test failed: " + e.getMessage()
            );
        }

        logger.info(
                "***** Finished TC_RF_006_ValidateNavigateRegisterPageTest *****"
        );
    }
}