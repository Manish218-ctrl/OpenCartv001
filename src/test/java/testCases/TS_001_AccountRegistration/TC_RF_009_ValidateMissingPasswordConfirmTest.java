package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import pageObjects.AccountRegistrationPage;
import testBase.BaseClass;

public class TC_RF_009_ValidateMissingPasswordConfirmTest extends BaseClass {

    @Test
    public void verify_register_without_password_confirm() {

        logger.info(
                "***** Starting TC_RF_009_ValidateMissingPasswordConfirmTest *****"
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();
            hp.clickRegister();

          /*  RegisterPage regPage =
                    new RegisterPage(getDriver());*/

            AccountRegistrationPage regPage =
                    new AccountRegistrationPage(getDriver());

            regPage.setFirstName("John");

            regPage.setLastName("Doe");

            String email =
                    randomString() + "@gmail.com";

            regPage.setEmail(email);

            regPage.setTelephone(randomNumber());

            regPage.setPassword("Test@123");

            regPage.setPrivacyPolicy();

            regPage.clickContinue();


            String actualWarning = regPage.getPasswordMismatchWarning();

            String expectedWarning = "Password confirmation does not match password!";

            logger.info(
                    "Actual Warning: " + actualWarning
            );

            Assert.assertEquals(
                    actualWarning,
                    expectedWarning,
                    "Warning message for missing Password Confirm not displayed correctly!"
            );

        } catch (Exception e) {

            logger.error(
                    "Test Failed due to exception: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test execution failed due to exception: "
                            + e.getMessage()
            );
        }

        logger.info(
                "***** Finished TC_RF_009_ValidateMissingPasswordConfirmTest *****"
        );
    }
}