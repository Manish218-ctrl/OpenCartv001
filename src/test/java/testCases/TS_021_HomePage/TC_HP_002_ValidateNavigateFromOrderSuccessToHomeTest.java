package testCases.TS_021_HomePage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HP_002_ValidateNavigateFromOrderSuccessToHomeTest extends BaseClass {

    @Test(groups = {"Regression", "HomePage"})
    public void navigateFromOrderSuccessToHome() throws InterruptedException {
        logger.info("***** STARTING TC_HP_002_ValidateNavigateFromOrderSuccessToHomeTest *****");

        HomePage home = new HomePage(driver);

        try {
            // Step 0: Login before placing order
            performLogin();
            logger.info("[Step 0] User logged in successfully and landed on HomePage.");

            // Step 1: Place an order
            logger.info(" START ORDER PLACEMENT ");
            logger.info("[Step 1] Searching and adding product to cart: " + productName);



            home.enterSearchText(productName);
            home.clickSearchButton();
            home.selectListView();
            home.addProductToCart(productName);
            home.clickaddtocart();
            home.clickshoppingcartbtnmsg();
            home.clickcheckoutfromcart();
            logger.info("Navigated to checkout page");

            logger.info("[Step 2] Product '" + productName + "' added to cart.");

            // Navigate to cart and checkout
            //home.clickCartBlock();
            logger.info("[Step 1.2] Clicked the Cart dropdown block.");

            //home.clickViewCartOption();
            logger.info("[Step 1.2] Navigated to Shopping Cart page.");

           // home.clickcheckoutfromcart();
           // home.clickCheckout();
            logger.info("[Step 1.2] Started the Checkout process.");

            // NOTE: Replace Thread.sleep(2000) with an explicit wait for the checkout page to load.
            // Thread.sleep(2000);

            // Checkout Steps
            logger.info("[Step 1.3] Handling Billing Details...");
            home.clickbillingdetails(); // Clicks 'Continue' on Billing Details (Assuming logged-in user uses existing address)
            logger.info("[Step 1.3] Clicked 'Continue' on Billing Details step.");

            logger.info("[Step 1.4] Handling Delivery Details...");
            home.clickContinueDeliveryDetails(); // Clicks 'Continue' on Delivery Details
            logger.info("[Step 1.4] Clicked 'Continue' on Delivery Details step.");

            logger.info("[Step 1.5] Handling Delivery Method...");
            home.clickContinueDeliveryMethod(); // Clicks 'Continue' on Delivery Method
            logger.info("[Step 1.5] Clicked 'Continue' on Delivery Method step.");

            logger.info("[Step 1.6] Handling Payment Method...");
            home.selectTermsAndConditions(); // Selects checkbox
            logger.info("[Step 1.6] Accepted Terms & Conditions.");

            home.clickContinuePaymentMethod(); // Clicks 'Continue' on Payment Method
            logger.info("[Step 1.6] Clicked 'Continue' on Payment Method step.");

            logger.info("[Step 1.7] Confirming Order...");
            CheckoutPage checkout =new CheckoutPage(driver);
            checkout.confirmOrder();

            //home.clickConfirmOrder();

            logger.info("--- ORDER PLACED SUCCESSFULLY ---");

            // Step 2: Continue from Order Success page
            // Should also wait for the success page elements to be visible before clicking continue
            Thread.sleep(3000);
            checkout.clickOrderSuccessContinueButton();

            //home.clickContinue();
            logger.info("[Step 2] Clicked 'Continue' button on Order Success page.");

            // Step 3: Validate Home Page
            String pageTitle = home.getPageTitle();
            logger.info("[Step 3] Current Page Title after navigation: " + pageTitle);

            Assert.assertTrue(pageTitle.contains("Your Store"), "Assertion Failed: User is NOT on Home Page. Title: " + pageTitle);
            logger.info("[Step 3] Assertion Passed: User successfully navigated back to the Home Page.");

        } catch (Exception e) {
            logger.error("Test case failed due to exception during order or validation: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** FINISHED TC_HP_002_ValidateNavigateFromOrderSuccessToHomeTest *****");
    }
}