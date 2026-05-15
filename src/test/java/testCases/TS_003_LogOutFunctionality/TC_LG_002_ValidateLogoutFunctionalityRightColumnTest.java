package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_002_ValidateLogoutFunctionalityRightColumnTest extends BaseClass {

    @Test(groups = {"regression"})
    public void test_logout_from_right_column() {

        logger.info(
                "Starting TC_LG_002 Logout Functionality (Right Column) Test"
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();

            logger.info(
                    "Clicked on My Account dropdown to expose Login link"
            );

            hp.clickLogin();

            logger.info(
                    "Clicked on Login link to navigate to Login page"
            );

            LoginPage lp = new LoginPage(getDriver());

            lp.setEmail(p.getProperty("email"));

            logger.info(
                    "Provided Email Address: "
                            + p.getProperty("email")
            );

            lp.setPassword(p.getProperty("password"));

            logger.info(
                    "Provided Password"
            );

            lp.clickLogin();

            logger.info(
                    "Clicked on Login button, user should now be on My Account page."
            );

            MyAccountPage macc =
                    new MyAccountPage(getDriver());

            Assert.assertTrue(
                    macc.isMyAccountPageExists(),
                    "My Account page not displayed after login."
            );

            logger.info(
                    "User successfully logged in and My Account page is displayed."
            );

            macc.clickLogout();

            logger.info(
                    "Clicked on Logout option from the Right Column."
            );

            HomePage hpAfterLogout =
                    new HomePage(getDriver());

            hpAfterLogout.clickMyAccount();

            boolean loginOptionVisible =
                    hpAfterLogout.linkLogin.isDisplayed();

            Assert.assertTrue(
                    loginOptionVisible,
                    "Login option is not visible after logout, implying logout failed."
            );

            logger.info(
                    "Verified ER-1: Login option visible after logout."
            );

            AccountSuccessPage asp =
                    new AccountSuccessPage(getDriver());

            Assert.assertTrue(
                    asp.getConfirmationMsg().contains("Account Logout"),
                    "Account Logout page confirmation message not found."
            );

            logger.info(
                    "Verified Account Logout page is displayed."
            );

            asp.clickContinueOnSuccessPage();

            logger.info(
                    "Clicked on Continue button."
            );

            String currentUrl =
                    getDriver().getCurrentUrl();

            String expectedHomeUrl =
                    p.getProperty("appURL");

            Assert.assertTrue(
                    currentUrl.contains(expectedHomeUrl),
                    "User is not taken to the Home page after logout."
            );

            logger.info(
                    "Verified ER-2: User is taken to the Home page."
            );

            logger.info(
                    "TC_LG_002 Logout Functionality (Right Column) Test Passed."
            );

        } catch (Exception e) {

            logger.error(
                    "TC_LG_002 Logout Functionality (Right Column) Test Failed: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}