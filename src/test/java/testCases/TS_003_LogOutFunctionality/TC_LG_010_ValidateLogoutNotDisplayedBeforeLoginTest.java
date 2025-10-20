package testCases.TS_003_LogOutFunctionality;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.NoSuchElementException; // Import this for catching the exception

import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_logout_option_not_displayed_before_login() {
            logger.info("Starting TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest: Validate Logout option is not displayed before logging in.");

            try {

                HomePage hp = new HomePage(driver);

                // 1. Click on 'My Account' Dropmenu
                hp.clickMyAccount();
                logger.info("Clicked 'My Account' dropdown to check for logout link.");



                boolean isLogoutLinkDisplayed = false;
                try {
                    // Attempt to find the logout link element
                    isLogoutLinkDisplayed = hp.lnkLogoutFromDropdown.isDisplayed();
                    logger.info("Logout link was found in the dropdown, which is unexpected.");
                } catch (NoSuchElementException e) {
                    // This is the expected exception if the element is not found
                    logger.info("Logout link is NOT displayed in the dropdown (as expected).");
                    isLogoutLinkDisplayed = false;
                }

                // Assert that the logout link is NOT displayed
                Assert.assertFalse(isLogoutLinkDisplayed, "Logout link is displayed in the 'My Account' dropdown before logging in, which is incorrect.");
                logger.info("Assertion Passed: Logout link is correctly not displayed in the 'My Account' dropdown before login.");


            } catch (Exception e) {
                logger.error("Test execution failed for TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_LG_010_ValidateLogoutNotDisplayedBeforeLoginTest.");
            }
        }
    }



