package testCases.TS_018_OrderHistory;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_OH_005_ValidateOrderHistoryNavigationBeforeLoginTest extends BaseClass {

        @Test
        public void verifyOrderHistoryNavigationBeforeLogin() {
            try {
                logger.info("********* Starting  TC_OH_005_ValidateOrderHistoryNavigationBeforeLoginTest *********");

                // Step 1: Open Application (already done in BaseClass setup)

                Homepage home = new Homepage(driver);

                // Step 2: Click on My Account → Register
                home.clickMyAccount();
                home.clickRegister();
                logger.info("Clicked My Account > Register");

                // Step 3: Click on Order History from Right Column options
                home.clickorderhistory0();
                logger.info("Clicked 'Order History' link from Right Column");

                // Step 4: Enter credentials on Login Page
                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(username, password);
                logger.info("Entered credentials and clicked Login");

                // Validate user logged in
                MyAccountPage myAcc = new MyAccountPage(driver);
                Assert.assertTrue(myAcc.isUserLoggedIn(), "Login failed. User not logged in.");

                // Validate navigation to Order History page
                String pageTitle = driver.getTitle();
                Assert.assertTrue(pageTitle.contains("Order History"),
                        "Navigation failed! Expected 'Order History' page but got: " + pageTitle);

                logger.info("Successfully navigated to 'Order History' page after login.");
                logger.info("********* Finished TC_OH_005_OrderHistoryNavigationTest *********");

            } catch (Exception e) {
                logger.error("Test Case TC_OH_005_OrderHistoryNavigationTest failed.", e);
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
    }



