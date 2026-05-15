package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_007_ValidateLogoutThenLoginTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void test_logout_and_immediate_login() {

        logger.info(
                "Starting TC_LG_007_ValidateLogoutThenLoginTest: Validate logging out and immediate login functionality."
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();

            logger.info(
                    "Clicked My Account dropdown."
            );

            hp.clickLogin();

            logger.info(
                    "Clicked Login link to navigate to login page."
            );

            LoginPage lp = new LoginPage(getDriver());

            lp.setEmail(p.getProperty("email"));

            logger.info(
                    "Entering initial login email: "
                            + p.getProperty("email")
            );

            lp.setPassword(p.getProperty("password"));

            logger.info(
                    "Entering initial login password."
            );

            lp.clickLogin();

            logger.info(
                    "Attempting initial login."
            );

            MyAccountPage macc =
                    new MyAccountPage(getDriver());

            Assert.assertTrue(
                    macc.isMyAccountPageExists(),
                    "Initial login failed: My Account page not displayed."
            );

            logger.info(
                    "Initial login successful. User is on My Account page."
            );

            hp.clickMyAccount();

            logger.info(
                    "Re-clicked My Account to open dropdown for logout."
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

            hp.clickMyAccount();

            logger.info(
                    "Clicked My Account dropdown for re-login attempt."
            );

            hp.clickLogin();

            logger.info(
                    "Clicked Login link to proceed to login page for re-login."
            );

            Assert.assertTrue(
                    lp.isLoginPageDisplayed(),
                    "Failed to navigate to Login page after logout for re-login."
            );

            logger.info(
                    "Confirmed user is on the Login page for re-login."
            );

            lp.setEmail(p.getProperty("email"));

            logger.info(
                    "Entering email for immediate re-login: "
                            + p.getProperty("email")
            );

            lp.setPassword(p.getProperty("password"));

            logger.info(
                    "Entering password for immediate re-login."
            );

            lp.clickLogin();

            logger.info(
                    "Attempting immediate re-login."
            );

            Assert.assertTrue(
                    macc.isMyAccountPageExists(),
                    "Immediate re-login failed: My Account page not displayed after re-login."
            );

            logger.info(
                    "Immediate re-login successful. User is back on My Account page."
            );

        } catch (Exception e) {

            logger.error(
                    "Test execution failed for TC_LG_007_ValidateLogoutThenLoginTest: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an exception: "
                            + e.getMessage()
            );

        } finally {

            logger.info(
                    "Finished TC_LG_007_ValidateLogoutThenLoginTest."
            );
        }
    }
}