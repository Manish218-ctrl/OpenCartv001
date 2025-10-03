package testCases.TS_003_LogOutFunctionality;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.NoSuchElementException; // Import this for catching the exception

import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_LG_010_ValidateValidateLogoutNotDisplayedBeforeLoginTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_logout_option_not_displayed_before_login() {
            logger.info("Starting TC_LG_010_ValidateValidateLogoutNotDisplayedBeforeLoginTest: Validate Logout option is not displayed before logging in.");

            try {
                // 1. Open the Application URL (handled by BaseClass setup)
                // The browser is already opened and navigated to the app URL by BaseClass setup.

                Homepage hp = new Homepage(driver);

                // 1. Click on 'My Account' Dropmenu
                hp.clickMyAccount();
                logger.info("Clicked 'My Account' dropdown to check for logout link.");

                // 2. Select 'Register' option (Verify FR-1)
                // The test case specifies checking for the 'Register' option, but the core objective
                // is to verify the ABSENCE of the Logout option before login.
                // We will attempt to find the logout link in the dropdown.

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

                // Optionally, you can also verify the Register link is present as per the step "Select 'Register' option (Verify FR-1)"
                // This part of the step seems to imply navigating there, but the core test is about logout visibility.
                // If you need to click Register to proceed with other tests in the same flow, uncomment and use it.
                // hp.clickRegister();
                // logger.info("Clicked 'Register' option as per test case step.");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_LG_010_ValidateValidateLogoutNotDisplayedBeforeLoginTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_LG_010_ValidateValidateLogoutNotDisplayedBeforeLoginTest.");
            }
        }
    }



