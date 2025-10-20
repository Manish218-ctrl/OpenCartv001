package testCases.TS_013_ContactUs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
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

        HomePage home = new HomePage(driver);


        home.enterSearchText(productName);
        home.clickSearchButton();
        home.addProductToCart(productName);
        home.clickaddtocart();
        home.clickshoppingcartbtnmsg();
        home.clickcheckoutfromcart();



        // Step 4: Checkout process
        CheckoutPage checkout = new CheckoutPage(driver);

        checkout.continueBillingDetails();
        checkout.continueDeliveryDetails();
        checkout.continueDeliveryMethod();
        checkout.acceptTermsAndConditions();
        checkout.continuePaymentMethod();
        checkout.confirmOrder();
        logger.info("Order confirmed successfully");

        // Step 5: Click on the 'store owner' link in the Order Success page
        logger.info("Clicking on 'store owner' link in the Order Success page.");
        OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
        orderSuccessPage.clickStoreOwnerLink();

        // Step 6: Verify that the user is taken to the 'Contact Us' page
        logger.info("Verifying if the user is navigated to the 'Contact Us' page.");
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String pageTitle = contactUsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Contact Us"), "User is not navigated to the 'Contact Us' page.");

        logger.info("Test completed successfully.");
    }
}
