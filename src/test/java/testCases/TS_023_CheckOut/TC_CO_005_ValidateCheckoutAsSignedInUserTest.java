package testCases.TS_023_CheckOut;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC_CO_005_ValidateCheckoutAsSignedInUserTest extends BaseClass {

    @Test
    @Parameters({"useRandomAddress"})
    public void verifyCheckoutAsSignedInUser(boolean useRandomAddress) throws InterruptedException {

        logger.info("=== TC_CO_005: Verify Checkout as Signed-In User ===");

        Homepage home = new Homepage(driver);
        LoginPage login = new LoginPage(driver);
        CheckoutPage checkout = new CheckoutPage(driver);

        // Navigate to login and sign in
        logger.info("Clicking 'My Account' link...");
        home.clickMyAccount();

        logger.info("Clicking 'Login' link...");
        home.clickLogin();

        logger.info("Logging in with test credentials...");
        login.login("jojol83635@besaies.com",
                "'nA#$%?w72=!b*7");
        logger.info("Login successful.");

        // Go to checkout page (assuming items already in cart)
        logger.info("Opening Cart block...");
        home.clickCartBlock();

        logger.info("Clicking 'Checkout' button...");
        //Thread.sleep(20000);
        home.clickbtnCheckout();

        // Fill billing & delivery details

        checkout.completeCheckout();

       /* if (useRandomAddress) {
            logger.info("Using random billing & delivery address.");
            checkout.fillExistingBillingDetails();
            checkout.continueBillingDetails();

            checkout.selectRandomRegion();
            checkout.continueBillingDetails();

            checkout.fillExistingDeliveryDetails();
            checkout.selectRandomRegion();
            checkout.continueDeliveryDetails();

        } else {
            logger.info("Using default/static billing & delivery address.");
            // Optional: fill default/static details
            // checkout.fillBillingDetails("John", "Doe", "123 Main St", "City", "12345", "1234567890");
            // checkout.fillDeliveryDetails(...);
        }

        logger.info("Continuing from Billing Details...");
        checkout.continueBillingDetails();

        logger.info("Continuing from Delivery Details...");
        checkout.continueDeliveryDetails();

        logger.info("Continuing from Delivery Method...");
        checkout.continueDeliveryMethod();

        logger.info("Accepting Terms & Conditions...");
        checkout.acceptTermsAndConditions();

        logger.info("Continuing from Payment Method...");
        checkout.continuePaymentMethod();

        logger.info("Confirming the order...");
        checkout.confirmOrder();*/

        // Validation
        Thread.sleep(20000);
        boolean orderSuccess = checkout.isOrderSuccessDisplayed();
        logger.info("Order success displayed: " + orderSuccess);
        Assert.assertTrue(orderSuccess, "Order success message not displayed!");

        String confirmationText = checkout.getOrderSuccessText();
        logger.info("Order Confirmation Text: " + confirmationText);
        System.out.println("Order Confirmation: " + confirmationText);

        logger.info("=== TC_CO_005 completed ===");
    }
}
