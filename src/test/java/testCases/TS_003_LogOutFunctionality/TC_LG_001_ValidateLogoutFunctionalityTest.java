package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_001_ValidateLogoutFunctionalityTest extends BaseClass {

    @Test(groups = {"sanity", "regression"})
    public void test_logout() {

        logger.info(
                "Starting TC_LG_001 Logout Functionality Test"
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();

            logger.info(
                    "Clicked on My Account"
            );

            hp.clickLogin();

            logger.info(
                    "Clicked on Login link"
            );

            LoginPage lp = new LoginPage(getDriver());

            lp.setEmail(p.getProperty("email"));

            logger.info(
                    "Provided Email Address"
            );

            lp.setPassword(p.getProperty("password"));

            logger.info(
                    "Provided Password"
            );

            lp.clickLogin();

            logger.info(
                    "Clicked on Login button"
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

            hp.clickMyAccount();

            logger.info(
                    "Clicked on My Account Dropmenu again."
            );

            macc.clickLogout();

            logger.info(
                    "Selected Logout option."
            );

            HomePage hpAfterLogout =
                    new HomePage(getDriver());

            hpAfterLogout.clickMyAccount();

            boolean loginOptionVisible =
                    hpAfterLogout.linkLogin.isDisplayed();

            Assert.assertTrue(
                    loginOptionVisible,
                    "Login option is not visible after logout."
            );

            logger.info(
                    "Verified ER-1: Login option visible after logout."
            );

            String currentUrl =
                    getDriver().getCurrentUrl();

            String expectedHomeUrl =
                    p.getProperty("appURL");

            Assert.assertTrue(
                    currentUrl.contains(expectedHomeUrl),
                    "User is not taken to the Home page."
            );

            logger.info(
                    "Verified ER-2: User is taken to the Home page."
            );

            logger.info(
                    "TC_LG_001 Logout Functionality Test Passed."
            );

        } catch (Exception e) {

            logger.error(
                    "TC_LG_001 Logout Functionality Test Failed: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}