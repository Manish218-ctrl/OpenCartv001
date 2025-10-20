package testCases.TS_023_MyAccount;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.OrderSuccessPage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_MA_001_ValidateMyAccountPageNavigationTest extends BaseClass {

    private final String PRODUCT_NAME = "HP LP3065";

    @Test(groups = {"Regression", "TS_024"})
    public void validateMyAccountNavigationFromOrderSuccessPage() {
        logger.info("Starting TC_MA_001: Validate navigation to My Account page from Order Success page.");

        try {
            performLogin();

            HomePage homePage = new HomePage(driver);
            ProductDisplayPage productPage = new ProductDisplayPage(driver);
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            // **Pre-requisite: Place an order for product: HP LP3065**
            logger.info("Attempting to place an order for product: " + PRODUCT_NAME);



            HomePage home = new HomePage(driver);
            home.enterSearchText(productName);
            home.clickSearchButton();
            home.addProductToCart(productName);
            home.clickaddtocart();
            home.clickshoppingcartbtnmsg();
            home.clickcheckoutfromcart();
            logger.info("Navigated to checkout page");

            // Navigate to Checkout
            CheckoutPage checkout = new CheckoutPage(driver);


            checkout.continueBillingDetails();
            checkout.continueDeliveryDetails();
            checkout.continueDeliveryMethod();
            checkout.acceptTermsAndConditions();
            checkout.continuePaymentMethod();
            checkout.confirmOrder();



            // Step 6: Navigate to My Account -> Address Book
            logger.info("Step 6: Navigating to My Account -> Address Book");
            Thread.sleep(3000);
            home.clickMyAccount();
            logger.info("Clicked My Account menu");
            Thread.sleep(3000);
            home.clickMyAccountFromDropdown();
            logger.info("Clicked 'My Account' from dropdown");
            MyAccountPage myAccount = new MyAccountPage(driver);
            Thread.sleep(3000);

            // **Expected Result: 1. User should be taken to 'My Account' page**
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