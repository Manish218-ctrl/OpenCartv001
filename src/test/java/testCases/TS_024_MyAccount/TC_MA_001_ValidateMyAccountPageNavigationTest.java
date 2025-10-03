package testCases.TS_024_MyAccount;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import pageObjects.OrderSuccessPage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_MA_001_ValidateMyAccountPageNavigationTest extends BaseClass {

    // Test Data from the table
    private final String PRODUCT_NAME = "iMac";

    @Test(groups = {"Regression", "TS_024"})
    public void validateMyAccountNavigationFromOrderSuccessPage() {
        logger.info("Starting TC_MA_001: Validate navigation to My Account page from Order Success page.");

        try {
            // **Pre-requisite: 1. Open the Application URL and login**
            performLogin();

            // Initialize Page Objects
            Homepage homePage = new Homepage(driver);
            ProductDisplayPage productPage = new ProductDisplayPage(driver);
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            // **Pre-requisite: Place an order for product: iMac**
            logger.info("Attempting to place an order for product: " + PRODUCT_NAME);

            // Search for the product
            homePage.searchProduct(PRODUCT_NAME);

            // Click on the search result to go to the Product Page
            // Assumes product search results page displays a link with the exact product name
            homePage.clickProductByName(PRODUCT_NAME);

            // Add the product to the cart
            productPage.addToCart();

            // Navigate to Checkout
            productPage.goToCheckout();

            // Complete the Checkout process
            // NOTE: This assumes 'completeCheckout()' method in CheckoutPage.java handles all steps
            // (Shipping, Payment, Confirmation) to land on the Order Success page.
            checkoutPage.completeCheckout();

            // Validation of Pre-requisite: Check if Order Success Page is displayed (Implicit ER-1)
            Assert.assertTrue(orderSuccessPage.isOrderSuccessPageDisplayed(),
                    "Pre-requisite Failed: User was not navigated to the Order Success page after checkout.");
            logger.info("Order successfully placed. Now on Order Success page.");

            // **Action Step: 1. Click on 'My account' page link**
            orderSuccessPage.clickMyAccountLink();

            // **Expected Result: 1. User should be taken to 'My Account' page**
            // The isMyAccountPageDisplayed() method should check for the "My Account" heading.
            boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageDisplayed();

            Assert.assertTrue(isMyAccountPageDisplayed, "Test Failed: User was NOT navigated to the 'My Account' page.");

            logger.info("Test Passed: Successfully validated navigation to the My Account page from Order Success page.");
            if(test != null) test.pass("Navigation to My Account page from Order Success page validated successfully.");

        } catch (Exception e) {
            logger.error("Test Failed: TC_MA_001 failed due to an exception: " + e.getMessage());
            if(test != null) test.fail("Test Failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}