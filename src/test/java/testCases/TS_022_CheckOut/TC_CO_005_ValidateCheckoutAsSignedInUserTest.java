package testCases.TS_022_CheckOut;

import org.testng.Assert;
import org.testng.annotations.AfterMethod; // <-- New Import
import org.testng.annotations.BeforeMethod; // <-- New Import
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ShoppingCartPage; // <-- Assuming you have this
import testBase.BaseClass;

public class TC_CO_005_ValidateCheckoutAsSignedInUserTest extends BaseClass {

    // Define the product name once for easy maintenance
    private final String PRODUCT_TO_BUY = "MacBook"; // Example product name

    // --- SETUP: LOGIN AND ADD PRODUCT ---
    @BeforeMethod
    public void setupCartAndLogin() {
        logger.info("--- STARTING @BeforeMethod: setupCartAndLogin ---");

        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        ShoppingCartPage cart = new ShoppingCartPage(driver);


        home.clickMyAccount();
        home.clickLogin();
        login.login("dekew75582@lorkex.com", "?.7LfLfn).}PCge");
        logger.info("@BeforeMethod: User logged in successfully.");

        // Clear any existing cart items for a clean test state
        cart.clearCart();
        logger.info("@BeforeMethod: Ensured cart is empty.");

        // Add the required product to the cart
        home.enterSearchText(productName);
        home.clickSearchButton();
        home.addProductToCart(productName);
        home.clickaddtocart();
        home.clickshoppingcartbtnmsg();
        home.clickcheckoutfromcart();

        logger.info("--- @BeforeMethod COMPLETED ---");
    }


    @Test
    @Parameters({"useRandomAddress"})
    public void verifyCheckoutAsSignedInUser(@Optional("false") boolean useRandomAddress) throws InterruptedException {

        logger.info("=== TC_CO_005: Verify Checkout as Signed-In User. Use Random Address: " + useRandomAddress + " ===");



        // Handle Billing & Delivery Details

        CheckoutPage checkout = new CheckoutPage(driver);


        if (useRandomAddress) {
            logger.info("[Step 3] Using New/Random address flow.");
            checkout.fillExistingBillingDetails();
            logger.info("[Step 3] Selected/Filled New Billing Details.");
            checkout.selectRandomRegion();
        } else {
            logger.info("[Step 3] Using default/static EXISTING address flow.");
        }

        logger.info("[Step 4.1] Continuing from Billing/Payment Address section.");
        checkout.continueBillingDetails();
        logger.info("[Step 4.1] Billing/Payment Address step completed.");

        logger.info("[Step 4.2] Continuing from Delivery Address section.");
        checkout.continueDeliveryDetails();
        logger.info("[Step 4.2] Delivery Address step completed.");

        logger.info("[Step 4.3] Continuing from Delivery Method section.");
        checkout.continueDeliveryMethod();
        logger.info("[Step 4.3] Delivery Method step completed.");

        // --- 4. Payment and Confirmation ---

        logger.info("[Step 4.4] Accepting Terms & Conditions.");
        checkout.acceptTermsAndConditions();

        logger.info("[Step 4.4] Continuing from Payment Method section.");
        checkout.continuePaymentMethod();
        logger.info("[Step 4.4] Payment Method step completed.");

        logger.info("[Step 4.5] Confirming the order.");
        Thread.sleep(5000);
        checkout.confirmOrder();
        logger.info("[Step 4.5] Order submitted.");

        // Validation

        Thread.sleep(5000);

        boolean orderSuccess = checkout.isOrderSuccessDisplayed();
        logger.info("Order success displayed: " + orderSuccess);

        Assert.assertTrue(orderSuccess, "Assertion Failed: Order success message was not displayed!");

        String confirmationText = checkout.getOrderSuccessText();
        logger.info("Order Confirmation Text: " + confirmationText);
        System.out.println("Final Order Confirmation Text: " + confirmationText);

        logger.info("=== TC_CO_005 completed ===");
    }

    @AfterMethod
    public void cleanupCart() {
        logger.info("--- STARTING @AfterMethod: cleanupCart ---");
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        // We only clear the cart here, assuming the order placement handled everything else.
        // Note: For a reliable cleanup, you might need to handle the case where the order fails and the item is still in the cart.
        cartPage.clearCart();
        logger.info("--- @AfterMethod COMPLETED: Cart cleared ---");
    }
}