package testCases.TS_028_ChangePassword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_CP_001_ValidateChangePasswordPageNavigationValidationTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_CP_001_ValidateChangePasswordPageNavigationValidationTest.class);

    @Test(groups = {"Regression", "ChangePassword"})
    public void verifyChangePasswordPageNavigation() {
        logger.info("***** Starting TC_CP_001_ValidateChangePasswordPageNavigationValidationTest *****");

        try {
            // Step 1: Login using BaseClass helper
            performLogin();
            logger.info("Login successful.");

            // Step 2: Navigate to My Account page
            Homepage homePage = new Homepage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on 'My Account' dropdown.");
            homePage.selectMyAccountOption();
            logger.info("Selected 'My Account' option from dropdown.");

            // Step 3: Verify My Account page and click Change Password
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            Assert.assertTrue(myAccountPage.isMyAccountPageExists(),
                    "My Account page is not displayed.");
            logger.info("User is on My Account page.");

            myAccountPage.clickChangeYourPassword();
            logger.info("Clicked on 'Change your password' link.");

            // Step 4: Validate navigation (breadcrumb or URL)
            String breadcrumb = myAccountPage.getBreadcrumbText();
            logger.info("Breadcrumb text after navigation: " + breadcrumb);

            Assert.assertTrue(
                    breadcrumb.contains("Change Password") ||
                            driver.getCurrentUrl().contains("password"),
                    "Navigation to Change Password page failed."
            );
            logger.info("Successfully navigated to Change Password page.");

        } catch (Exception e) {
            logger.error("Test failed due to exception: ", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_CP_001_ValidateChangePasswordPageNavigationValidationTest *****");
    }
}
