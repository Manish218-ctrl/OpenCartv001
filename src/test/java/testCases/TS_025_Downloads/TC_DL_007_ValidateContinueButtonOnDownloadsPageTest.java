package testCases.TS_025_Downloads;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_007_ValidateContinueButtonOnDownloadsPageTest extends BaseClass {

        @Test
        public void validateContinueButtonOnDownloadsPage() {

            // Step 1: Login to the application
            performLogin();
            logger.info("Login successful.");

            // Step 2: Navigate to 'My Account' page Downloads section
            MyAccountPage myAccount = new MyAccountPage(driver);
            myAccount.clickDownloadsFromRightColumn();
            logger.info("Clicked on 'Downloads' from right column.");

            // Step 3: Validate that 'Continue' button is visible
            Assert.assertTrue(myAccount.btnContinue0.isDisplayed(), "'Continue' button is not displayed on Downloads page.");
            logger.info("'Continue' button is displayed on Downloads page.");

            // Step 4: Click on 'Continue' button
            myAccount.clickbtnContinue0();
            logger.info("Clicked on 'Continue' button.");

            // Step 5: Validate user is redirected to 'My Account' page
            Assert.assertTrue(myAccount.isMyAccountPageExists(), "User is not redirected to My Account page after clicking 'Continue'.");
            logger.info("User successfully redirected to 'My Account' page.");
        }
    }

