package testCases.TS_003_LogOutFunctionality;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void test_logout_option_not_displayed_before_login() {

        logger.info(
                "Starting TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest: Validate Logout option is not displayed before logging in."
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();

            logger.info(
                    "Clicked My Account dropdown to check for logout link."
            );

            boolean isLogoutLinkDisplayed = false;

            try {

                isLogoutLinkDisplayed =
                        hp.lnkLogoutFromDropdown.isDisplayed();

                logger.info(
                        "Logout link was found in the dropdown, which is unexpected."
                );

            } catch (NoSuchElementException e) {

                logger.info(
                        "Logout link is NOT displayed in the dropdown (as expected)."
                );

                isLogoutLinkDisplayed = false;
            }

            Assert.assertFalse(
                    isLogoutLinkDisplayed,
                    "Logout link is displayed in the My Account dropdown before logging in, which is incorrect."
            );

            logger.info(
                    "Assertion Passed: Logout link is correctly not displayed in the My Account dropdown before login."
            );

        } catch (Exception e) {

            logger.error(
                    "Test execution failed for TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an exception: "
                            + e.getMessage()
            );

        } finally {

            logger.info(
                    "Finished TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest."
            );
        }
    }
}