package testCases.TS_003_LogOutFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_LG_001_ValidateLogoutFunctionalityTest extends BaseClass {

        @Test(groups = {"sanity", "regression"}) // Assigning groups for test execution
        public void test_logout() {
            logger.info("Starting TC_LG_001 Logout Functionality Test");

            try {

                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                logger.info("Clicked on My Account");
                hp.clickLogin();
                logger.info("Clicked on Login link");

                LoginPage lp = new LoginPage(driver);
                // Replace with actual credentials from config.properties if available
                lp.setEmail(rb.getString("email")); // Using email from config.properties
                logger.info("Provided Email Address");
                lp.setPassword(rb.getString("password")); // Using password from config.properties
                logger.info("Provided Password");
                lp.clickLogin();
                logger.info("Clicked on Login button");

                MyAccountPage macc = new MyAccountPage(driver);
                Assert.assertTrue(macc.isMyAccountPageExists(), "My Account page not displayed after login.");
                logger.info("User successfully logged in and My Account page is displayed.");


                hp.clickMyAccount(); // Re-clicking My Account to show dropdown
                logger.info("Clicked on My Account Dropmenu again.");

                // Select 'Logout' option (Verify ER-1)

                macc.clickLogout(); // Clicks the logout link
                logger.info("Selected Logout option.");

                // Expected Result 1: User should be taken to the 'Account Logout' page and User should see Login option in place of Logout under the 'My Account' dropmenu

                HomePage hpAfterLogout = new HomePage(driver);
                hpAfterLogout.clickMyAccount(); // Click My Account to verify login option
                boolean loginOptionVisible = hpAfterLogout.linkLogin.isDisplayed();
                Assert.assertTrue(loginOptionVisible, "Login option is not visible after logout.");
                logger.info("Verified ER-1: Login option visible after logout.");

                // Expected Result 2: User should be taken to the Home page

                String currentUrl = driver.getCurrentUrl();
                String expectedHomeUrl = rb.getString("appURL"); // Get app URL from config.properties
                Assert.assertTrue(currentUrl.contains(expectedHomeUrl), "User is not taken to the Home page.");
                logger.info("Verified ER-2: User is taken to the Home page.");

                logger.info("TC_LG_001 Logout Functionality Test Passed.");

            } catch (Exception e) {
                logger.error("TC_LG_001 Logout Functionality Test Failed: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }
        }
    }



