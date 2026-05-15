package testCases.TS_001_AccountRegistration;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_RF_002_ValidateRegisterAllFieldsTest extends BaseClass {

        @Test
        public void verify_register_with_all_fields() {
            logger.info("***** Starting  TC_RF_002_ValidateRegisterAllFieldsTest *****");

            try {

                HomePage hp = new HomePage(getDriver());
                hp.clickMyAccount();
                hp.clickRegister();

                AccountRegistrationPage regPage = new AccountRegistrationPage(getDriver());
                String email = randomString() + "@gmail.com";
                String password = randomAlphaNumeric();

                regPage.setFirstName(randomString());
                regPage.setLastName(randomString());
                regPage.setEmail(email);
                regPage.setTelephone(randomNumber());
                regPage.setPassword(password);
                regPage.setConfirmPassword(password);
                regPage.setNewsletterYes();
                regPage.setPrivacyPolicy();

                regPage.clickContinue();  // ER-1

                regPage.clickContinueOnSuccessPage();

                MyAccountPage myAcc = new MyAccountPage(getDriver());
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


