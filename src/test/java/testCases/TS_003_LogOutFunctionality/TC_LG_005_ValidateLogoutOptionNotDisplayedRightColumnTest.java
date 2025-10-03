package testCases.TS_003_LogOutFunctionality;

    import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage; // MyAccountPage contains the right column logout link element
import testBase.BaseClass;

    public class TC_LG_005_ValidateLogoutOptionNotDisplayedRightColumnTest extends BaseClass {

        @Test(groups = {"sanity"}) // Assigning sanity group as it tests a fundamental UI state
        public void test_logout_option_not_displayed_in_right_column_before_login() {
            logger.info("Starting TC_LG_005 Validate logout option is not displayed under 'Right Column' options before logging in.");

            try {
                // Step 1: Open the Application URL (handled by BaseClass setup)
                // The application is already opened by the @BeforeClass setup method in BaseClass.
                logger.info("Application URL is open and user is not logged in.");

                // Expected Result 1: Logout option should not be displayed in the 'Right Column'
                // We'll use the MyAccountPage to check for the 'lnkLogout' which typically resides
                // in the right column list group. Before login, this should not be displayed.
                MyAccountPage macc = new MyAccountPage(driver);

                // The isUserLoggedIn() method in MyAccountPage checks if lnkLogout is displayed.
                // We expect it to be false before logging in.
                boolean isLogoutOptionDisplayedInRightColumn = macc.isUserLoggedIn();

                Assert.assertFalse(isLogoutOptionDisplayedInRightColumn, "Logout option IS displayed in the Right Column before login.");
                logger.info("Verified ER-1: Logout option is NOT displayed in the 'Right Column' before login.");

                logger.info("TC_LG_005 Validate logout option is not displayed in Right Column test passed.");

            } catch (Exception e) {
                logger.error("TC_LG_005 Validate logout option is not displayed in Right Column test Failed: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }
        }
    }


