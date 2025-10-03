package testCases.TS_025_Downloads;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_001_ValidateNavigationToAccountDownloadsTest extends BaseClass {

        @Test
        public void verifyNavigationToAccountDownloads() {
            logger.info("=== TC_DL_001: Validate navigation to 'Account Downloads' from 'My Account' page ===");

            // Page Objects
            Homepage home = new Homepage(driver);
            LoginPage login = new LoginPage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            //  Login to the application
            home.clickMyAccount();
            home.clickLogin();
            login.login(username, password);
            logger.info("User logged in successfully.");

            //  Click 'Downloads' in My Account page
            myAccount.clickDownloads();

            //  Validate landing on Account Downloads page
            String expectedTitle = "Account Downloads"; // Update based on actual title
            String actualTitle = myAccount.getPageTitle();
            logger.info("Expected page title: " + expectedTitle + " | Actual page title: " + actualTitle);
            Assert.assertTrue(actualTitle.contains(expectedTitle), "User is not navigated to Account Downloads page!");

            logger.info("=== TC_DL_001 completed successfully ===");
        }
    }

