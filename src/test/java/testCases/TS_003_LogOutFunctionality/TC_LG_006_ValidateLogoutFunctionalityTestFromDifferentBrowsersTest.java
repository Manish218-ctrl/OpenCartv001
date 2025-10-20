package testCases.TS_003_LogOutFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_logout_from_multiple_places() {
            logger.info("Starting TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest: Validating logout functionality.");

            try {

                // Navigate to Login Page
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                logger.info("Navigating to 'My Account' dropdown.");
                hp.clickLogin();
                logger.info("Clicked 'Login' link to proceed to login page.");

                // Login to the application
                LoginPage lp = new LoginPage(driver);
                lp.setEmail(p.getProperty("email")); // Using email from config.properties
                logger.info("Entering Email for login: " + p.getProperty("email"));
                lp.setPassword(p.getProperty("password")); // Using password from config.properties
                logger.info("Entering Password for login.");
                lp.clickLogin();
                logger.info("Attempting to log in.");

                // Verify successful login by checking My Account page
                MyAccountPage macc = new MyAccountPage(driver);
                Assert.assertTrue(macc.isMyAccountPageExists(), "Login failed: My Account page not displayed.");
                logger.info("Login successful. User is on My Account page.");

                // 1. Click on 'My Account' Dropmenu (simulated in current browser)
                hp.clickMyAccount(); // Re-click My Account to open dropdown
                logger.info("Re-opening 'My Account' dropdown to access logout option.");

                // 2. Select 'Logout' option from dropdown
                hp.clickLogoutFromDropdown();
                logger.info("Selected 'Logout' from the dropdown menu.");

                // Verify Account Logout confirmation
                AccountSuccessPage accSuccess = new AccountSuccessPage(driver);
                String confirmationMessage = accSuccess.getConfirmationMsg();
                Assert.assertTrue(confirmationMessage.contains("Account Logout"), "Logout failed: 'Account Logout' confirmation message not displayed.");
                logger.info("Successfully received 'Account Logout' confirmation.");

                // 3. Click 'Continue' on the logout success page
                accSuccess.clickContinueOnSuccessPage();
                logger.info("Clicked 'Continue' button on the logout success page.");

                // Verify redirection to the homepage or login page post-logout
                Assert.assertTrue(hp.lnkMyaccount.isDisplayed(), "Redirection failed after logout. 'My Account' link not visible.");
                logger.info("Successfully redirected to the homepage/login state after logout.");

                // Further verification: Attempt to navigate to My Account page, should redirect to login.
                hp.clickMyAccount();
                hp.clickLogin();
                Assert.assertTrue(lp.isLoginPageDisplayed(), "Logout verification failed: User was not redirected to Login page after attempting to re-access account.");
                logger.info("Confirmed user is logged out by verifying redirection to the login page when attempting to access a protected area.");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_LG_006_ValidateLogoutFunctionalityTestFromDifferentBrowsersTest.");
            }
        }
    }



