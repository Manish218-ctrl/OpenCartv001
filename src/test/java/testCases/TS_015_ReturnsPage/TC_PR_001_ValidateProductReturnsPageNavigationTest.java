package testCases.TS_015_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_PR_001_ValidateProductReturnsPageNavigationTest extends BaseClass {

    @Test
    public void validateNavigatingToProductReturnsPage() {
        logger.info("Starting the test: TC_PR_001_ValidateProductReturnsPageNavigationTest");

        // Initializing necessary page objects
        HomePage homepage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        ProductReturnsPage productReturnsPage = new ProductReturnsPage(driver);

        // Step 1: Open the application URL and navigate to the 'Login' page
        logger.info("Step 1: Opening the application and navigating to the Login page.");
        driver.get(appURL);

        // Step 2: Click on 'My Account' to open the account menu and then click on 'Login'
        logger.info("Step 2: Clicking on 'My Account' and then clicking 'Login' from the dropdown.");
        homepage.clickMyAccount();
        // Click My Account

        performLogin();


        // Step 3: Navigate to 'My Account' page and click 'View your return requests'
        logger.info("Step 3: Navigating to 'My Account' page and clicking 'View your return requests'.");
        homepage.clickMyAccount();  // Click My Account
        homepage.clickMyAccountFromDropdown();
        Assert.assertTrue(myAccountPage.isMyAccountPageExists(), "My Account page not displayed after login.");

        try {
            myAccountPage.clickViewYourReturnRequests();  // Click View Your Return Requests
            logger.info("Successfully clicked 'View your return requests'.");
        } catch (Exception e) {
            logger.error("Failed to click 'View your return requests'.", e);
            Assert.fail("Failed to navigate to Product Returns page: " + e.getMessage());
        }

        // Step 4: Validate navigation to 'Product Returns' page
        logger.info("Step 4: Validating navigation to the 'Product Returns' page.");
        String currentURL = driver.getCurrentUrl();
        try {
            Assert.assertTrue(currentURL.contains("return"), "URL is not as expected.");
            logger.info("Navigated to the Product Returns page. Current URL: " + currentURL);
        } catch (AssertionError e) {
            logger.error("Product Returns page navigation failed. Expected URL to contain 'return', but got: " + currentURL, e);
            Assert.fail("Product Returns page navigation failed: " + e.getMessage());
        }

        // Step 5: Validate the page title
        logger.info("Step 5: Validating the Product Returns page title.");
        String pageTitle = productReturnsPage.getPageTitle();
        try {
            Assert.assertEquals(pageTitle, "Product Returns", "Page title is not correct.");
            logger.info("Product Returns page title validated successfully: " + pageTitle);
        } catch (AssertionError e) {
            logger.error("Product Returns page title validation failed. Expected 'Product Returns', but got: " + pageTitle, e);
            Assert.fail("Product Returns page title validation failed: " + e.getMessage());
        }

        logger.info("Test TC_PR_001_ValidateProductReturnsPageNavigationTest completed successfully.");
    }
}
