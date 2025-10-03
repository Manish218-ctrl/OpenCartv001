package testCases.TS_025_Downloads;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_005_ValidateNavigateToAccountDownloadsBeforeLoginTest extends BaseClass {

        @Test
        public void navigateToAccountDownloads() {

            logger.info("=== Starting TC_DL_005: Navigate to Account Downloads ===");

            // Initialize Page Objects
            Homepage home = new Homepage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);
            LoginPage loginPage = new LoginPage(driver);

            // Step 1: Click 'My Account' dropdown
            home.clickMyAccount();
            logger.info("Clicked on 'My Account' dropdown");

            // Step 2: Click 'Register' option
            home.clickRegister();
            logger.info("Clicked on 'Register' link");

            // Step 3: Click 'Downloads' from Right Column (before login)
            myAccount.clickDownloadsFromRightColumn();
            logger.info("Clicked on 'Downloads' link from Right Column");

            // Step 4: Enter credentials & click login
            loginPage.login(username, password);
            logger.info("Entered credentials and clicked login");

            // Validation 1: User should be logged in
            Assert.assertTrue(myAccount.isUserLoggedIn(), "User login failed");

            // Validation 2: Verify user is navigated to 'Account Downloads' page
            String expectedTitle = "Account Downloads";  // Replace with actual page title
            Assert.assertTrue(myAccount.getPageTitle().contains(expectedTitle),
                    "Failed to navigate to Account Downloads page");

            logger.info("=== TC_DL_005 Completed Successfully ===");
        }
    }
