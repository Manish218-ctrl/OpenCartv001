package testCases.TS_001_AccountRegistration;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_RF_007_ValidateRegisterPasswordMismatchTest extends BaseClass {

        @Test
        public void verify_password_mismatch_warning() {
            logger.info("***** Starting TC_RF_007_ValidateRegisterPasswordMismatchTest *****");

            try {
                // Step 1: Navigate to Register Page
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickRegister();

                // Step 2: Fill Registration form with mismatched Password & Confirm Password
                AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
                String email = randomString() + "@gmail.com";

                regPage.setFirstName(randomString());
                regPage.setLastName(randomString());
                regPage.setEmail(email);
                regPage.setTelephone(randomNumber());

                regPage.setPassword("12345");       // password
                regPage.setConfirmPassword("abcde"); // different confirm password

                regPage.setPrivacyPolicy();
                regPage.clickContinue();            // ER-1

                // Step 3: Validate warning message
                String warning = regPage.getPasswordMismatchWarning().trim();
                Assert.assertEquals(warning,
                        "Password confirmation does not match password!",
                        "Password mismatch warning not displayed correctly");

                logger.info("Test Passed: Password mismatch warning displayed as expected.");
            }
            catch (Exception e) {
                logger.error("Test failed: " + e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_RF_007_ValidateRegisterPasswordMismatchTest *****");
        }
    }





