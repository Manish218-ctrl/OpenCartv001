package testCases.TS_003_LogOutFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_LG_004_ValidateLogoutOptionNotDisplayedBeforeLoginTest extends BaseClass {

        @Test(groups = {"sanity"}) // Assigning sanity group for this test
        public void test_logout_option_not_displayed_before_login() {
            logger.info("Starting TC_LG_004 Validate logout option is not displayed under 'My Account' menu before logging in.");

            try {
                // Step 1: Open the Application URL (handled by BaseClass setup)
                // The application is already opened by the @BeforeClass setup method in BaseClass.

                // Step 2: Click on 'My Account' Dropmenu (Verify ER-1)
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount(); // This action opens the 'My Account' dropdown

                logger.info("Clicked on My Account dropdown.");

                // Expected Result 1: Logout option should not be displayed under 'My Account' dropmenu
                // We need to verify that the 'Logout' WebElement, which normally appears
                // when logged in, is NOT displayed.

                // To do this, we'll try to find the logout element and assert that it's not present.
                // MyAccountPage.java has a 'lnkLogout' WebElement. We can reuse it,
                // or directly check for the 'Logout' link on the Homepage if it's strictly
                // within the dropdown that Homepage controls.
                // Given 'lnkLogoutFromDropdown' in Homepage.java:

                // The 'lnkLogoutFromDropdown' is defined in Homepage.java.
                // Its existence implies it's part of the dropdown when 'My Account' is clicked.
                boolean isLogoutOptionDisplayed = false;
                try {
                    // Using Homepage's lnkLogoutFromDropdown to check its display status.
                    // This assumes the element is always present in the DOM but hidden,
                    // or throws NoSuchElementException if not present at all.
                    isLogoutOptionDisplayed = hp.lnkLogoutFromDropdown.isDisplayed();
                } catch (Exception e) {
                    // If the element is not found, isDisplayed() would throw an exception,
                    // which means it's not displayed as expected.
                    isLogoutOptionDisplayed = false;
                }

                Assert.assertFalse(isLogoutOptionDisplayed, "Logout option IS displayed under 'My Account' dropdown before login.");
                logger.info("Verified ER-1: Logout option is NOT displayed under 'My Account' dropmenu before login.");

                logger.info("TC_LG_004 Validate logout option is not displayed test passed.");

            } catch (Exception e) {
                logger.error("TC_LG_004 Validate logout option is not displayed test Failed: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }
        }
    }



