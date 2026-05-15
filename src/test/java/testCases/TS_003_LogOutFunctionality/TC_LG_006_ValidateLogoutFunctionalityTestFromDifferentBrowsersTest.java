package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void test_logout_from_multiple_places() {

        logger.info(
                "Starting TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest: Validating logout functionality."
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();

            logger.info(
                    "Navigating to My Account dropdown."
            );

            hp.clickLogin();

            logger.info(
                    "Clicked Login link to proceed to login page."
            );

            LoginPage lp = new LoginPage(getDriver());

            lp.setEmail(p.getProperty("email"));

            logger.info(
                    "Entering Email for login: "
                            + p.getProperty("email")
            );

            lp.setPassword(p.getProperty("password"));

            logger.info(
                    "Entering Password for login."
            );

            lp.clickLogin();

            logger.info(
                    "Attempting to log in."
            );

            MyAccountPage macc =
                    new MyAccountPage(getDriver());

            Assert.assertTrue(
                    macc.isMyAccountPageExists(),
                    "Login failed: My Account page not displayed."
            );

            logger.info(
                    "Login successful. User is on My Account page."
            );

            hp.clickMyAccount();

            logger.info(
                    "Re-opening My Account dropdown to access logout option."
            );

            hp.clickLogoutFromDropdown();

            logger.info(
                    "Selected Logout from the dropdown menu."
            );

            AccountSuccessPage accSuccess =
                    new AccountSuccessPage(getDriver());

            String confirmationMessage =
                    accSuccess.getConfirmationMsg();

            Assert.assertTrue(
                    confirmationMessage.contains("Account Logout"),
                    "Logout failed: Account Logout confirmation message not displayed."
            );

            logger.info(
                    "Successfully received Account Logout confirmation."
            );

            accSuccess.clickContinueOnSuccessPage();

            logger.info(
                    "Clicked Continue button on the logout success page."
            );

            Assert.assertTrue(
                    hp.isMyAccountDisplayed(),
                    "Redirection failed after logout. My Account link not visible."
            );

            logger.info(
                    "Successfully redirected to the homepage/login state after logout."
            );

            hp.clickMyAccount();

            hp.clickLogin();

            Assert.assertTrue(
                    lp.isLoginPageDisplayed(),
                    "Logout verification failed: User was not redirected to Login page after attempting to re-access account."
            );

            logger.info(
                    "Confirmed user is logged out by verifying redirection to the login page when attempting to access a protected area."
            );

        } catch (Exception e) {

            logger.error(
                    "Test execution failed for TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an exception: "
                            + e.getMessage()
            );

        } finally {

            logger.info(
                    "Finished TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest."
            );
        }
    }
}