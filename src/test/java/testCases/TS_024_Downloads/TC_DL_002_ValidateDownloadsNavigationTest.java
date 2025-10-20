package testCases.TS_024_Downloads;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_002_ValidateDownloadsNavigationTest extends BaseClass {

        @Test
        public void validateDownloadsNavigation() {
            logger.info("========= Starting TC_DL_002 - Downloads Navigation Test =========");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to 'My Account' drop-down (already in performLogin) and click Downloads
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickDownloads();
            logger.info("Clicked on 'Downloads' link.");

            // Step 3: Validate navigation by checking page title or breadcrumb
            String actualTitle = myAccountPage.getPageTitle();
            logger.info("Current page title: " + actualTitle);

            String expectedTitle = "Account Downloads"; // Replace with the actual page title
            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Navigation to Downloads page failed! Expected title: " + expectedTitle + " but found: " + actualTitle);

            logger.info("Navigation to 'Account Downloads' page validated successfully.");
            logger.info("========= TC_DL_002 Completed =========");
        }
    }

