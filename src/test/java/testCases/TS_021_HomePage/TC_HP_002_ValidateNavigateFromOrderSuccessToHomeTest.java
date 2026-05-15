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

        HomePage home = new HomePage(getDriver());

        try {

            //Login before placing order
            performLogin();
            logger.info(" User logged in successfully and landed on HomePage.");

            //Place an order
            logger.info(" START ORDER PLACEMENT ");
            logger.info(" Searching and adding product to cart: " + productName);



            home.enterSearchText(productName);
            home.clickSearchButton();
            home.selectListView();
            home.addProductToCart(productName);
            home.clickaddtocart();
            home.clickshoppingcartbtnmsg();
            home.clickcheckoutfromcart();
            logger.info("Navigated to checkout page");

            logger.info(" Product " + productName + " added to cart.");

            // Navigate to cart and checkout

            //home.clickCartBlock();
            logger.info(" Clicked the Cart dropdown block.");

            //home.clickViewCartOption();
            logger.info(" Navigated to Shopping Cart page.");

           // home.clickcheckoutfromcart();

           // home.clickCheckout();
            logger.info(" Started the Checkout process.");

            // Checkout Steps
            logger.info(" Handling Billing Details...");
            home.clickbillingdetails(); // Clicks Continue on Billing Details (Assuming logged-in user uses existing address)
            logger.info(" Clicked Continue on Billing Details step.");

            logger.info(" Handling Delivery Details...");
            home.clickContinueDeliveryDetails(); // Clicks Continue on Delivery Details
            logger.info(" Clicked Continue on Delivery Details step.");

            logger.info(" Handling Delivery Method...");
            home.clickContinueDeliveryMethod(); // Clicks Continue on Delivery Method
            logger.info(" Clicked Continue on Delivery Method step.");

            logger.info(" Handling Payment Method...");
            home.selectTermsAndConditions(); // Selects checkbox
            logger.info(" Accepted Terms & Conditions.");

            home.clickContinuePaymentMethod(); // Clicks Continue on Payment Method
            logger.info(" Clicked Continue on Payment Method step.");

            logger.info(" Confirming Order...");
            CheckoutPage checkout =new CheckoutPage(getDriver());
            checkout.confirmOrder();

            //home.clickConfirmOrder();
            logger.info("--- ORDER PLACED SUCCESSFULLY ---");

            //Continue from Order Success page

            // Should also wait for the success page elements to be visible before clicking continue
            checkout.clickOrderSuccessContinueButton();

            //home.clickContinue();
            logger.info(" Clicked Continue button on Order Success page.");

            //Validate Home Page
            String pageTitle = home.getPageTitle();
            logger.info(" Current Page Title after navigation: " + pageTitle);

            Assert.assertTrue(pageTitle.contains("Your Store"), "Assertion Failed: User is NOT on Home Page. Title: " + pageTitle);
            logger.info(" Assertion Passed: User successfully navigated back to the Home Page.");

        } catch (Exception e) {
            logger.error("Test case failed due to exception during order or validation: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** FINISHED TC_HP_002_ValidateNavigateFromOrderSuccessToHomeTest *****");
    }
}