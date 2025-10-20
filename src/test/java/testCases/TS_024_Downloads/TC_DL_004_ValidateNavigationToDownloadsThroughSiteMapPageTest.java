package testCases.TS_024_Downloads;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_004_ValidateNavigationToDownloadsThroughSiteMapPageTest extends BaseClass {

        @Test
        public void validateNavigationToDownloads() {
            logger.info("=== TC_DL_004: Validate navigation to Account Downloads page from Site Map ===");

            // Initialize Page Objects
            HomePage home = new HomePage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            // Step 1: Login
            performLogin();
            Assert.assertTrue(myAccount.isMyAccountPageExists(), "Login failed or My Account page not displayed.");
            logger.info("Login successful and My Account page is displayed.");

            // Step 2: Click 'Site Map' link in footer
            logger.info("Clicking 'Site Map' footer link.");
            home.clickFooterLink("Site Map"); // Reuse method from HomePage.java

            // Step 3: Click 'Downloads' link from Site Map page
            logger.info("Clicking 'Downloads' link from Site Map page.");
            myAccount.clickDownloads(); // Using existing method in MyAccountPage.java

            // Step 4: Validate navigation to Downloads page
            String expectedTitle = "Account Downloads"; // Adjust if your site title differs
            String actualTitle = myAccount.getPageTitle();
            logger.info("Expected page title: " + expectedTitle);
            logger.info("Actual page title: " + actualTitle);

            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Navigation failed: Expected page title '" + expectedTitle + "', but found '" + actualTitle + "'.");

            logger.info("Successfully navigated to Account Downloads page.");
        }
    }
