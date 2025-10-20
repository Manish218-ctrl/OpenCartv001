package testCases.TS_024_Downloads;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DownloadsPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_009_ValidateAccountDownloadsBreadcrumbTest extends BaseClass {

        @Test
        public void validateAccountDownloadsBreadcrumb() {
            logger.info("=== Starting TC_DL_010: Validate Breadcrumb on Account Downloads page ===");

            // Step 1: Perform login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to My Account -> Downloads (Right Column)
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickDownloadsFromRightColumn();
            logger.info("Clicked on 'Downloads' link from Right Column in My Account page.");

            // Step 3: Initialize DownloadsPage
            DownloadsPage downloadsPage = new DownloadsPage(driver);

            // Step 4: Validate breadcrumb
            String breadcrumbText = downloadsPage.getBreadcrumbText();
            logger.info("Breadcrumb displayed: " + breadcrumbText);

            // Step 5: Assertion (expected breadcrumb text may vary; update as per your app)
            String expectedBreadcrumb = "Downloads";
            Assert.assertEquals(breadcrumbText, expectedBreadcrumb, "Breadcrumb text validation failed!");

            logger.info("=== TC_DL_010 Completed Successfully ===");
        }
    }
