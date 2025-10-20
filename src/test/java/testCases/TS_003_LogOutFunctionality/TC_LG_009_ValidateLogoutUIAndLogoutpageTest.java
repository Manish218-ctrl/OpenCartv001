package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.NoSuchElementException;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_009_ValidateLogoutUIAndLogoutpageTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
    public void test_validate_logout_ui_and_logout_page() {
        logger.info("Starting TC_LG_009_ValidateLogoutUIAndLogoutpageTest: Validate UI of Logout option and Account Logout page.");

        try {

            // Step 1: User is logged in
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked 'My Account' dropdown.");
            hp.clickLogin();
            logger.info("Clicked 'Login' link to navigate to login page.");

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email")); // Using email from config.properties
            logger.info("Entering login email: " + p.getProperty("email"));
            lp.setPassword(p.getProperty("password")); // Using password from config.properties
            logger.info("Entering login password.");
            lp.clickLogin();
            logger.info("Attempting login.");

            MyAccountPage macc = new MyAccountPage(driver);
            Assert.assertTrue(macc.isMyAccountPageExists(), "Login failed: My Account page not displayed.");
            logger.info("Login successful. User is on My Account page.");

            // Part 1: Validate UI of the Logout option (My Account DropMenu and Right Column)

            // Verify Logout option in My Account Dropmenu after login
            hp.clickMyAccount(); // Re-click to ensure dropdown is open
            logger.info("Re-opened 'My Account' dropdown to check for Logout link UI.");
            boolean isDropdownLogoutDisplayed = false;
            try {
                isDropdownLogoutDisplayed = hp.lnkLogoutFromDropdown.isDisplayed();
                Assert.assertTrue(isDropdownLogoutDisplayed, "Logout link in 'My Account' dropdown is not displayed after login.");
                logger.info("Verified: Logout link is displayed in 'My Account' dropdown.");
            } catch (NoSuchElementException e) {
                Assert.fail("Logout link in 'My Account' dropdown was not found after login.");
            }

            // Verify Logout option in the Right Column (assuming lnkLogout in MyAccountPage refers to this)
            boolean isRightColumnLogoutDisplayed = false;
            try {
                isRightColumnLogoutDisplayed = macc.lnkLogout.isDisplayed();
                Assert.assertTrue(isRightColumnLogoutDisplayed, "Logout link in Right Column is not displayed after login.");
                logger.info("Verified: Logout link is displayed in the Right Column.");
            } catch (NoSuchElementException e) {
                Assert.fail("Logout link in Right Column was not found after login.");
            }


            //  Part 2: Perform Logout and Validate UI of the Account Logout page

            // Click Logout option (from dropdown for consistency, could also use right column)
            hp.clickLogoutFromDropdown(); // Or macc.clickLogout();
            logger.info("Selected 'Logout' from the dropdown menu to navigate to logout page.");

            AccountSuccessPage accSuccess = new AccountSuccessPage(driver);

            // Check the Page Heading
            String pageHeading = accSuccess.getConfirmationMsg(); // This method returns the heading which can be "Account Logout"
            Assert.assertTrue(pageHeading.contains("Account Logout"), "Logout Page Heading 'Account Logout' is not displayed or incorrect. Actual: " + pageHeading);
            logger.info("Verified Logout Page Heading: '" + pageHeading + "'");

            // Check the Page Title
            String pageTitle = driver.getTitle();
            Assert.assertTrue(pageTitle.contains("Account Logout"), "Logout Page Title is not 'Account Logout'. Actual: " + pageTitle);
            logger.info("Verified Logout Page Title: '" + pageTitle + "'");

            // Check the Page URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("account/logout"), "Logout Page URL does not contain 'account/logout'. Actual: " + currentUrl);
            logger.info("Verified Logout Page URL: '" + currentUrl + "'");

            // Check the Breadcrumb
            LoginPage logoutPage = new LoginPage(driver); // Reusing LoginPage for breadcrumb check, if applicable.
            String breadcrumbText = logoutPage.getBreadcrumb();
            Assert.assertTrue(breadcrumbText.contains("Logout"), "Logout Page Breadcrumb does not contain 'Logout'. Actual: " + breadcrumbText);
            logger.info("Verified Logout Page Breadcrumb: '" + breadcrumbText + "'");


        } catch (Exception e) {
            logger.error("Test execution failed for TC_LG_009_ValidateLogoutUIAndLogoutpageTest: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
            logger.info("Finished TC_LG_009_ValidateLogoutUIAndLogoutpageTest.");
        }
    }
}
