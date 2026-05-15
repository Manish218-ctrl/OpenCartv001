package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_RF_007_ValidateRegisterPasswordMismatchTest extends BaseClass {

    @Test
    public void verify_password_mismatch_warning() {

        logger.info(
                "***** Starting TC_RF_007_ValidateRegisterPasswordMismatchTest *****"
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();
            hp.clickRegister();

            AccountRegistrationPage regPage =
                    new AccountRegistrationPage(getDriver());

            String email =
                    randomString() + "@gmail.com";

            regPage.setFirstName(randomString());
            regPage.setLastName(randomString());
            regPage.setEmail(email);
            regPage.setTelephone(randomNumber());

            regPage.setPassword("12345");

            regPage.setConfirmPassword("abcde");

            regPage.setPrivacyPolicy();

            regPage.clickContinue();

            String warning =
                    regPage.getPasswordMismatchWarning().trim();

            Assert.assertEquals(
                    warning,
                    "Password confirmation does not match password!",
                    "Password mismatch warning not displayed correctly"
            );

            logger.info(
                    "Test Passed: Password mismatch warning displayed as expected."
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
                "***** Finished TC_RF_007_ValidateRegisterPasswordMismatchTest *****"
        );
    }
}