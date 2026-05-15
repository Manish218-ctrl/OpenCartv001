package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_005_ValidateLogoutOptionNotDisplayedRightColumnTest extends BaseClass {

    @Test(groups = {"sanity"})
    public void test_logout_option_not_displayed_in_right_column_before_login() {

        logger.info(
                "Starting TC_LG_005 Validate logout option is not displayed under Right Column options before logging in."
        );

        try {

            logger.info(
                    "Application URL is open and user is not logged in."
            );

            MyAccountPage macc =
                    new MyAccountPage(getDriver());

            boolean isLogoutOptionDisplayedInRightColumn =
                    macc.isUserLoggedIn();

            Assert.assertFalse(
                    isLogoutOptionDisplayedInRightColumn,
                    "Logout option IS displayed in the Right Column before login."
            );

            logger.info(
                    "Verified ER-1: Logout option is NOT displayed in the Right Column before login."
            );

            logger.info(
                    "TC_LG_005 Validate logout option is not displayed in Right Column test passed."
            );

        } catch (Exception e) {

            logger.error(
                    "TC_LG_005 Validate logout option is not displayed in Right Column test Failed: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}