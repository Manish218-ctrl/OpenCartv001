package testCases.TS_001_AccountRegistration;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_RF_003_ValidateRegisterMandatoryFieldsTest extends BaseClass {

        @Test
        public void verify_mandatory_field_warnings() {
            logger.info("***** Starting TC_RF_003_ValidateRegisterMandatoryFieldsTest *****");

            try {
                // Step 1: Navigate to Register Page
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                hp.clickRegister();

                AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

                // Step 2: Don't enter anything, just click Continue
                regPage.clickContinue();

                // Step 3: Validate Warning messages
                Assert.assertEquals(regPage.getPrivacyPolicyWarning().trim(),
                        "Warning: You must agree to the Privacy Policy!", "Privacy Policy warning mismatch");

                Assert.assertEquals(regPage.getFirstNameWarning().trim(),
                        "First Name must be between 1 and 32 characters!", "First Name warning mismatch");

                Assert.assertEquals(regPage.getLastNameWarning().trim(),
                        "Last Name must be between 1 and 32 characters!", "Last Name warning mismatch");

                Assert.assertEquals(regPage.getEmailWarning().trim(),
                        "E-Mail Address does not appear to be valid!", "Email warning mismatch");

                Assert.assertEquals(regPage.getTelephoneWarning().trim(),
                        "Telephone must be between 3 and 32 characters!", "Telephone warning mismatch");

                Assert.assertEquals(regPage.getPasswordWarning().trim(),
                        "Password must be between 4 and 20 characters!", "Password warning mismatch");

                logger.info("Test Passed: All mandatory field warnings displayed correctly.");

            } catch (Exception e) {
                logger.error("Test failed: " + e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_RF_003_ValidateRegisterMandatoryFieldsTest *****");
        }
    }


