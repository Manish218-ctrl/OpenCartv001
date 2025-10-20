package testCases.TS_003_LogOutFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_LG_004_ValidateLogoutOptionNotDisplayedBeforeLoginTest extends BaseClass {

        @Test(groups = {"sanity"}) // Assigning sanity group for this test
        public void test_logout_option_not_displayed_before_login() {
            logger.info("Starting TC_LG_004 Validate logout option is not displayed under 'My Account' menu before logging in.");

            try {
                // Step 1: Open the Application URL (handled by BaseClass setup)

                // Step 2: Click on 'My Account' Dropmenu (Verify ER-1)
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount(); // This action opens the 'My Account' dropdown

                logger.info("Clicked on My Account dropdown.");

                boolean isLogoutOptionDisplayed = false;
                try {

                    isLogoutOptionDisplayed = hp.lnkLogoutFromDropdown.isDisplayed();
                } catch (Exception e) {

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



