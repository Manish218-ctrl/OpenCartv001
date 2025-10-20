package testCases.TS_003_LogOutFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import pageObjects.AccountSuccessPage;

    public class TC_LG_002_ValidateLogoutFunctionalityRightColumnTest extends BaseClass {

        @Test(groups = {"regression"}) // Assigning regression group for this test
        public void test_logout_from_right_column() {
            logger.info("Starting TC_LG_002 Logout Functionality (Right Column) Test");

            try {
                // Pre-requisite: User is logged in and is on Account page

                // Step 1: Navigate to Login Page and Login
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount(); // Click on 'My Account' dropdown in the header
                logger.info("Clicked on My Account dropdown to expose Login link");
                hp.clickLogin(); // Click on 'Login' link from the dropdown
                logger.info("Clicked on Login link to navigate to Login page");

                LoginPage lp = new LoginPage(driver);
                lp.setEmail(rb.getString("email")); // Using email from config.properties
                logger.info("Provided Email Address: " + rb.getString("email"));
                lp.setPassword(rb.getString("password")); // Using password from config.properties
                logger.info("Provided Password");
                lp.clickLogin(); // Click on Login button to sign in
                logger.info("Clicked on Login button, user should now be on My Account page.");

                MyAccountPage macc = new MyAccountPage(driver);
                Assert.assertTrue(macc.isMyAccountPageExists(), "My Account page not displayed after login.");
                logger.info("User successfully logged in and My Account page is displayed.");

                // 1. Click on Logout option from the Right Column (Verify ER-1)
                macc.clickLogout(); // This method in MyAccountPage should target the right column logout link
                logger.info("Clicked on Logout option from the Right Column.");

                // Expected Result 1: User should be taken to the 'Account Logout' page and

                HomePage hpAfterLogout = new HomePage(driver);
                hpAfterLogout.clickMyAccount(); // Click My Account to verify login option is back
                boolean loginOptionVisible = hpAfterLogout.linkLogin.isDisplayed();
                Assert.assertTrue(loginOptionVisible, "Login option is not visible after logout, implying logout failed.");
                logger.info("Verified ER-1: Login option visible after logout.");


                AccountSuccessPage asp = new AccountSuccessPage(driver);
                // Verify if the confirmation message/page is displayed first
                Assert.assertTrue(asp.getConfirmationMsg().contains("Account Logout"), "Account Logout page confirmation message not found.");
                logger.info("Verified 'Account Logout' page is displayed.");


                asp.clickContinueOnSuccessPage(); // Clicks the continue button
                logger.info("Clicked on 'Continue' button.");

                // Expected Result 2: User should be taken to the Home page

                String currentUrl = driver.getCurrentUrl();
                String expectedHomeUrl = rb.getString("appURL"); // Get app URL from config.properties
                Assert.assertTrue(currentUrl.contains(expectedHomeUrl), "User is not taken to the Home page after logout.");
                logger.info("Verified ER-2: User is taken to the Home page.");

                logger.info("TC_LG_002 Logout Functionality (Right Column) Test Passed.");

            } catch (Exception e) {
                logger.error("TC_LG_002 Logout Functionality (Right Column) Test Failed: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }
        }
    }



