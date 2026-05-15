package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

public class TC_RF_005_ValidateRegisterNewsletterNoTest extends BaseClass {

    @Test
    public void verify_register_with_newsletter_no() {

        logger.info(
                "***** Starting TC_RF_005_ValidateRegisterNewsletterNoTest *****"
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();
            hp.clickRegister();

            AccountRegistrationPage regPage =
                    new AccountRegistrationPage(getDriver());

            String email =
                    randomString() + "@gmail.com";

            String password =
                    randomAlphaNumeric();

            regPage.setFirstName(randomString());
            regPage.setLastName(randomString());
            regPage.setEmail(email);
            regPage.setTelephone(randomNumber());
            regPage.setPassword(password);
            regPage.setConfirmPassword(password);

            regPage.setNewsletterNo();

            regPage.setPrivacyPolicy();

            regPage.clickContinue();

            regPage.clickContinueOnSuccessPage();

            MyAccountPage myAcc =
                    new MyAccountPage(getDriver());

            Assert.assertTrue(
                    myAcc.isMyAccountPageExists(),
                    "User is not on My Account page!"
            );

            getDriver().navigate().to(
                    "https://tutorialsninja.com/demo/index.php?route=account/newsletter"
            );

            NewsletterPage newsPage =
                    new NewsletterPage(getDriver());

            Assert.assertTrue(
                    newsPage.isNoOptionSelected(),
                    "No option was not selected by default in Newsletter page!"
            );

            logger.info(
                    "Test Passed: Newsletter No option correctly retained after registration."
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
                "***** Finished TC_RF_005_ValidateRegisterNewsletterNoTest *****"
        );
    }
}