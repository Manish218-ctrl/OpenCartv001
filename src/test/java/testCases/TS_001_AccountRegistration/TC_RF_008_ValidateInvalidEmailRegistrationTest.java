package testCases.TS_001_AccountRegistration;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_RF_008_ValidateInvalidEmailRegistrationTest extends BaseClass {

        @Test
        public void verify_registration_with_invalid_emails() {
            logger.info("***** Starting TC_RF_008_ValidateInvalidEmailRegistrationTest *****");

            try {
                // Navigate to application
                driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                hp.clickRegister();

                AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

                // Invalid email test data
                String[] invalidEmails = {"test@com", "testauto@com", "testauto@gmail-com", "testauto@gmailcom"};

                for (String invalidEmail : invalidEmails) {
                    logger.info("Testing with invalid email: " + invalidEmail);

                    // Fill in details with invalid email
                    regPage.setFirstName("John");
                    regPage.setLastName("Doe");
                    regPage.setEmail(invalidEmail);
                    regPage.setTelephone("1234567890");
                    regPage.setPassword("Test@123");
                    regPage.setConfirmPassword("Test@123");
                    regPage.setPrivacyPolicy();

                    regPage.clickContinue();

                    // Assertion
                    String warning = regPage.getEmailInvalidWarning();
                    logger.info("Warning for email '" + invalidEmail + "': " + warning);

                    Assert.assertEquals(
                            warning,
                            "E-Mail Address does not appear to be valid!",
                            "Invalid email warning not displayed correctly for: " + invalidEmail
                    );

                    // Reset form for next email test
                    driver.navigate().refresh();
                    hp.clickMyAccount();
                    hp.clickRegister();
                }

            } catch (Exception e) {
                logger.error("Test failed: " + e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_RF_008_ValidateInvalidEmailRegistrationTest *****");
        }
    }



