package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC_RF_010_ValidateMissingPrivacyPolicyTest extends BaseClass {

    @Test
    public void verify_registration_without_privacy_policy() {

        logger.info(
                "***** Starting TC_RF_010_ValidateMissingPrivacyPolicyTest *****"
        );

        try {

            getDriver().get(
                    p.getProperty("appURL")
            );

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();
            hp.clickRegister();

            RegisterPage rp =
                    new RegisterPage(getDriver());

            rp.setFirstName(randomString());
            rp.setLastName(randomString());
            rp.setEmail(randomString() + "@gmail.com");
            rp.setTelephone(randomNumber());
            rp.setPassword("test123");
            rp.setConfirmPassword("test123");

            rp.clickContinue();

            String actualWarning =
                    rp.getWarningMessage();

            String expectedWarning =
                    "Warning: You must agree to the Privacy Policy!";

            Assert.assertTrue(
                    actualWarning.contains(expectedWarning),
                    "Expected warning message not displayed! Actual: "
                            + actualWarning
            );

        } catch (Exception e) {

            logger.error(
                    "Test Failed: " + e.getMessage()
            );

            Assert.fail(
                    "Test Failed due to exception: "
                            + e.getMessage()
            );
        }

        logger.info(
                "***** Finished TC_RF_010_ValidateMissingPrivacyPolicyTest *****"
        );
    }
}