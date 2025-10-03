package testCases.TS_001_AccountRegistration;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

    public class TC_RF_004_ValidateRegisterNewsletterYesTest extends BaseClass {

        @Test
        public void verify_register_with_newsletter_yes() {
            logger.info("***** Starting TC_RF_004_ValidateRegisterNewsletterYesTest *****");

            try {
                // Step 1: Navigate to Register Page
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickRegister();

                // Step 2: Fill Registration form with Newsletter = Yes
                AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
                String email = randomString() + "@gmail.com";
                String password = randomAlphaNumeric();

                regPage.setFirstName(randomString());
                regPage.setLastName(randomString());
                regPage.setEmail(email);
                regPage.setTelephone(randomNumber());
                regPage.setPassword(password);
                regPage.setConfirmPassword(password);

                regPage.setNewsletterYes();   // Newsletter subscription = Yes
                regPage.setPrivacyPolicy();
                regPage.clickContinue();      // ER-1

                // Step 3: Click Continue on Account Success Page (ER-2)
                regPage.clickContinueOnSuccessPage();

                // Step 4: Validate user is on My Account page
                MyAccountPage myAcc = new MyAccountPage(driver);
                Assert.assertTrue(myAcc.isMyAccountPageExists(), "User is not on My Account page!");

                // Step 5: Go to Subscribe/Unsubscribe Newsletter page (ER-3)
                driver.navigate().to("https://tutorialsninja.com/demo/index.php?route=account/newsletter");

                NewsletterPage newsPage = new NewsletterPage(driver);

                // Step 6: Validate "Yes" is selected by default
                Assert.assertTrue(newsPage.isYesOptionSelected(),
                        "'Yes' option was not selected by default in Newsletter page!");

                logger.info("Test Passed: Newsletter 'Yes' option correctly retained after registration.");
            }
            catch (Exception e) {
                logger.error("Test failed: " + e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_RF_004_ValidateRegisterNewsletterYesTest *****");
        }
    }



