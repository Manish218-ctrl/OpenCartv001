package testCases.TS_001_AccountRegistration;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_RF_002_ValidateRegisterAllFieldsTest extends BaseClass {

        @Test
        public void verify_register_with_all_fields() {
            logger.info("***** Starting  TC_RF_002_ValidateRegisterAllFieldsTest *****");

            try {
                // Step 1: Navigate to Register page
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickRegister();

                // Step 2: Fill Registration Form (all fields)
                AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
                String email = randomString() + "@gmail.com";
                String password = randomAlphaNumeric();

                regPage.setFirstName(randomString());
                regPage.setLastName(randomString());
                regPage.setEmail(email);
                regPage.setTelephone(randomNumber());
                regPage.setPassword(password);
                regPage.setConfirmPassword(password);
                regPage.setNewsletterYes();  // Newsletter subscription
                regPage.setPrivacyPolicy();

                regPage.clickContinue();  // ER-1

                // Step 3: Continue on Success page (ER-2)
                regPage.clickContinueOnSuccessPage();

                // Step 4: Validate MyAccount page
                MyAccountPage myAcc = new MyAccountPage(driver);
                boolean targetPage = myAcc.isMyAccountPageExists();
                Assert.assertTrue(targetPage, "User is not on My Account page!");

                logger.info("Test Passed: User registered successfully with all fields.");
            }
            catch (Exception e) {
                logger.error("Test failed: " + e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished  TC_RF_002_ValidateRegisterAllFieldsTest *****");
        }
    }


