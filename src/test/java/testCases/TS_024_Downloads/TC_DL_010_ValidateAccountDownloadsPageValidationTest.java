package testCases.TS_024_Downloads;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DownloadsPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_010_ValidateAccountDownloadsPageValidationTest extends BaseClass {

        @Test
        public void validateAccountDownloadsPage() {
            //Perform login
            performLogin();

            //Navigate to My Account -> Downloads (Right Column)
            MyAccountPage myAccountPage = new MyAccountPage(getDriver());
            myAccountPage.clickDownloadsFromRightColumn();

            //Initialize DownloadsPage
            DownloadsPage downloadsPage = new DownloadsPage(getDriver());

            //Validation

            // Expected values
            String expectedUrl = "https://tutorialsninja.com/demo/index.php?route=account/download"; // Replace with actual URL
            String expectedTitle = "Account Downloads"; // Replace with actual page title
            String expectedHeading = "Downloads"; // Typically the breadcrumb last item

            // Actual values
            String actualUrl = getDriver().getCurrentUrl();
            String actualTitle = downloadsPage.getPageTitle();
            String actualHeading = downloadsPage.getBreadcrumbText();

            // Assert URL
            Assert.assertEquals(actualUrl, expectedUrl, "Page URL validation failed!");

            // Assert Page Title
            Assert.assertEquals(actualTitle, expectedTitle, "Page Title validation failed!");

            // Assert Page Heading (Breadcrumb)
            Assert.assertEquals(actualHeading, expectedHeading, "Page Heading validation failed!");

            System.out.println("All validations passed for Account Downloads page!");
        }
    }
