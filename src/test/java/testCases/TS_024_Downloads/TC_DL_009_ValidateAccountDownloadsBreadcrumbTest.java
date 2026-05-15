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

            //Perform login
            performLogin();
            logger.info("User logged in successfully.");

            //Navigate to My Account -> Downloads (Right Column)
            MyAccountPage myAccountPage = new MyAccountPage(getDriver());
            myAccountPage.clickDownloadsFromRightColumn();
            logger.info("Clicked on Downloads link from Right Column in My Account page.");

            //Initialize DownloadsPage
            DownloadsPage downloadsPage = new DownloadsPage(getDriver());

            //Validate breadcrumb
            String breadcrumbText = downloadsPage.getBreadcrumbText();
            logger.info("Breadcrumb displayed: " + breadcrumbText);

            //Assertion (expected breadcrumb text may vary; update as per your app)
            String expectedBreadcrumb = "Downloads";
            Assert.assertEquals(breadcrumbText, expectedBreadcrumb, "Breadcrumb text validation failed!");

            logger.info("=== TC_DL_010 Completed Successfully ===");
        }
    }
