package testCases.TS_014_ContactUs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.OrderSuccessPage;
import pageObjects.ContactUsPage;
import pageObjects.CheckoutPage;
import testBase.BaseClass;

public class TC_CU_003_ValidateContactUsPageNavigationFromOrderSuccessTest extends BaseClass {

    @Test
    public void validateNavigationToContactUsPageFromOrderSuccess() {
        logger.info("Starting the test: validateNavigationToContactUsPageFromOrderSuccess");

        // Step 1: Open the application URL
        logger.info("Opening application URL: " + rb.getString("appURL"));
        driver.get(rb.getString("appURL"));

        // Step 2: Login before searching product
        logger.info("Performing login with valid credentials.");
        performLogin();

        // Step 3: Search for the product 'HP LP3065' and add to cart
        logger.info("Searching for product: HP LP3065");
        Homepage homepage = new Homepage(driver);
        homepage.enterSearchText("HP LP3065");
        logger.info("Clicking on search button.");
        homepage.clickSearchButton();
        logger.info("Adding 'HP LP3065' to the cart.");
        homepage.clickAddToCart("HP LP3065");

        // Step 4: Go to the Shopping Cart
        logger.info("Navigating to the shopping cart.");
        homepage.clickCartBlock();
        homepage.clickViewCartOption();
        logger.info("Proceeding to Checkout.");
        homepage.clickCheckout();

        // Step 5: Checkout process (QAFox flow)
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        logger.info("Filling billing details.");
        checkoutPage.fillBillingDetails("John", "Doe", "123 Street", "London", "12345", "United Kingdom", "Greater London");

        logger.info("Continuing Delivery Details.");
        checkoutPage.continueDeliveryDetails();

        logger.info("Continuing Delivery Method.");
        checkoutPage.continueDeliveryMethod();

        logger.info("Accepting terms and continuing Payment Method.");
        checkoutPage.acceptTermsAndContinuePayment();

        logger.info("Confirming the order.");
        checkoutPage.confirmOrder();

        // Step 6: Click on the 'store owner' link in the Order Success page
        logger.info("Clicking on 'store owner' link in the Order Success page.");
        OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
        orderSuccessPage.clickStoreOwnerLink();

        // Step 7: Verify that the user is taken to the 'Contact Us' page
        logger.info("Verifying if the user is navigated to the 'Contact Us' page.");
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String pageTitle = contactUsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Contact Us"), "User is not navigated to the 'Contact Us' page.");

        logger.info("Test completed successfully.");
    }
}
