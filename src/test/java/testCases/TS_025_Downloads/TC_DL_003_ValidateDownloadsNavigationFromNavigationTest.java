package testCases.TS_025_Downloads;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_003_ValidateDownloadsNavigationFromNavigationTest extends BaseClass {

        @Test
        public void validateDownloadsNavigation() {
            logger.info("========= Starting TC_DL_003 - Downloads Navigation Test =========");

            // Step 1: Perform Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to 'Downloads' from Right Column
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickDownloadsFromRightColumn();

            // Step 3: Validate navigation by page title
            String actualTitle = myAccountPage.getPageTitle();
            String expectedTitle = "Account Downloads"; // replace with exact title from your application
            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Navigation to Downloads page failed! Expected: " + expectedTitle + " but found: " + actualTitle);
            logger.info("Navigation to 'Account Downloads' page validated successfully.");

            // Optional: Validate breadcrumb
            String breadcrumb = myAccountPage.getBreadcrumbText();
            Assert.assertTrue(breadcrumb.contains("Downloads"), "Breadcrumb validation failed! Found: " + breadcrumb);
            logger.info("Breadcrumb validation passed: " + breadcrumb);

            logger.info("========= TC_DL_003 Completed =========");
        }
    }

