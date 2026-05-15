package testCases.TS_022_CheckOut;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_CO_005_ValidateCheckoutAsSignedInUserTest extends BaseClass {

    private final String PRODUCT_TO_BUY = "MacBook";

    @BeforeMethod
    public void setupCartAndLogin() {
        logger.info("--- STARTING @BeforeMethod: setupCartAndLogin ---");

        HomePage home = new HomePage(getDriver());
        LoginPage login = new LoginPage(getDriver());
        ShoppingCartPage cart = new ShoppingCartPage(getDriver());

        home.clickMyAccount();
        home.clickLogin();
        login.login("dekew75582@lorkex.com", "?.7LfLfn).}PCge");
        logger.info("@BeforeMethod: User logged in successfully.");

        cart.clearCart();
        logger.info("@BeforeMethod: Ensured cart is empty.");

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
    public void verifyCheckoutAsSignedInUser(@Optional("false") boolean useRandomAddress) {
        logger.info("=== TC_CO_005: Verify Checkout as Signed-In User. Use Random Address: " + useRandomAddress + " ===");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        CheckoutPage checkout = new CheckoutPage(getDriver());

        if (useRandomAddress) {
            logger.info("Using New/Random address flow.");
            checkout.fillExistingBillingDetails();
            logger.info("Selected/Filled New Billing Details.");
            checkout.selectRandomRegion();
        } else {
            logger.info("Using default/static EXISTING address flow.");
        }

        logger.info("Continuing from Billing/Payment Address section.");
        checkout.continueBillingDetails();
        logger.info("Billing/Payment Address step completed.");

        logger.info("Continuing from Delivery Address section.");
        checkout.continueDeliveryDetails();
        logger.info("Delivery Address step completed.");

        logger.info("Continuing from Delivery Method section.");
        checkout.continueDeliveryMethod();
        logger.info("Delivery Method step completed.");

        logger.info("Accepting Terms & Conditions.");
        checkout.acceptTermsAndConditions();

        logger.info("Continuing from Payment Method section.");
        checkout.continuePaymentMethod();
        logger.info("Payment Method step completed.");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Confirm Order']")));

        logger.info("Confirming the order.");
        checkout.confirmOrder();
        logger.info("Order submitted.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='content']/h1")));

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
        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        cartPage.clearCart();
        logger.info("--- @AfterMethod COMPLETED: Cart cleared ---");
    }
}