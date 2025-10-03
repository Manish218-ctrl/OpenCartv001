package testCases.TS_003_LogOutFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_LG_007_ValidateLogoutThenLoginTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_logout_and_immediate_login() {
            logger.info("Starting TC_LG_007_ValidateLogoutThenLoginTest: Validate logging out and immediate login functionality.");

            try {
                // 1. Open the Application URL (handled by BaseClass setup)

                // Step 2: User is logged in - Initial Login
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                logger.info("Clicked 'My Account' dropdown.");
                hp.clickLogin();
                logger.info("Clicked 'Login' link to navigate to login page.");

                LoginPage lp = new LoginPage(driver);
                lp.setEmail(p.getProperty("email")); // Using email from config.properties
                logger.info("Entering initial login email: " + p.getProperty("email"));
                lp.setPassword(p.getProperty("password")); // Using password from config.properties
                logger.info("Entering initial login password.");
                lp.clickLogin();
                logger.info("Attempting initial login.");

                MyAccountPage macc = new MyAccountPage(driver);
                Assert.assertTrue(macc.isMyAccountPageExists(), "Initial login failed: My Account page not displayed.");
                logger.info("Initial login successful. User is on My Account page.");

                // Step 1 (of test case steps): Click on My Account Dropmenu
                hp.clickMyAccount();
                logger.info("Re-clicked 'My Account' to open dropdown for logout.");

                // Step 2 (of test case steps): Select Logout option
                hp.clickLogoutFromDropdown();
                logger.info("Selected 'Logout' from the dropdown menu.");

                // Verify Account Logout confirmation
                AccountSuccessPage accSuccess = new AccountSuccessPage(driver);
                String confirmationMessage = accSuccess.getConfirmationMsg();
                Assert.assertTrue(confirmationMessage.contains("Account Logout"), "Logout failed: 'Account Logout' confirmation message not displayed.");
                logger.info("Successfully received 'Account Logout' confirmation.");

                // Click 'Continue' on the logout success page to go to the homepage or login page
                accSuccess.clickContinueOnSuccessPage();
                logger.info("Clicked 'Continue' button on the logout success page.");

                // Step 3 (of test case steps): Login immediately again with same or different account
                // For simplicity, we'll try to log in with the same account.
                hp.clickMyAccount();
                logger.info("Clicked 'My Account' dropdown for re-login attempt.");
                hp.clickLogin();
                logger.info("Clicked 'Login' link to proceed to login page for re-login.");

                // Verify that we are on the login page before attempting to re-login
                Assert.assertTrue(lp.isLoginPageDisplayed(), "Failed to navigate to Login page after logout for re-login.");
                logger.info("Confirmed user is on the Login page for re-login.");

                // Re-login with the same account
                lp.setEmail(p.getProperty("email"));
                logger.info("Entering email for immediate re-login: " + p.getProperty("email"));
                lp.setPassword(p.getProperty("password"));
                logger.info("Entering password for immediate re-login.");
                lp.clickLogin();
                logger.info("Attempting immediate re-login.");

                // Verify successful re-login
                Assert.assertTrue(macc.isMyAccountPageExists(), "Immediate re-login failed: My Account page not displayed after re-login.");
                logger.info("Immediate re-login successful. User is back on My Account page.");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_LG_007_ValidateLogoutThenLoginTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_LG_007_ValidateLogoutThenLoginTest.");
            }
        }
    }



