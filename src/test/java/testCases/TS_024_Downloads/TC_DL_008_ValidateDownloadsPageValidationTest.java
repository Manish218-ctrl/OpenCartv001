package testCases.TS_024_Downloads;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_008_ValidateDownloadsPageValidationTest extends BaseClass {

        @Test
        public void validateDownloadsPage() {
            logger.info("Starting TC_DL_008: Validate Account Downloads page");

            //Perform login
            performLogin();
            logger.info("Login successful");

            //Navigate to My Account Page
            MyAccountPage myAccount = new MyAccountPage(getDriver());

            Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page is not displayed");

            //Click Downloads from right column
            myAccount.clickDownloadsFromRightColumn();
            logger.info("Clicked on Downloads link from Right Column");

            //Validate page title or breadcrumbs
            String pageTitle = myAccount.getPageTitle();
            logger.info("Downloads Page Title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("Downloads"), "Downloads page title is not correct");



            logger.info("TC_DL_008 executed successfully");
        }
    }
