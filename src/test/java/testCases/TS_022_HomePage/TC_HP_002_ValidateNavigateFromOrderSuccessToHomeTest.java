package testCases.TS_022_HomePage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC_HP_002_ValidateNavigateFromOrderSuccessToHomeTest extends BaseClass {

    @Test(groups = {"Regression", "HomePage"})
    public void navigateFromOrderSuccessToHome() throws InterruptedException {
        logger.info("***** Starting TC_HP_002 *****");

        Homepage home = new Homepage(driver);

        // Step 0: Login before placing order
        performLogin();
        logger.info("[Step 0] User logged in successfully.");

        // Step 1: Place an order
        logger.info("[Step 1] Searching and adding product to cart: " + productName);
        home.enterSearchText(productName);
        home.clickSearchButton();
        home.addProductToCart(productName); // Consolidated method

        // Navigate to cart and checkout
        home.clickCartBlock();
        home.clickViewCartOption();
        home.clickCheckout();

        Thread.sleep(2000);
        
        home.clickbillingdetails();
        home.clickContinueDeliveryDetails();
        home.clickContinueDeliveryMethod();
        home.selectTermsAndConditions();
        home.clickContinuePaymentMethod();
        home.clickConfirmOrder();

        logger.info("Order placed successfully");

        // Step 2: Continue from Order Success page
        home.clickContinue();
        logger.info("[Step 2] Clicked 'Continue' on Order Success page");

        // Step 3: Validate Home Page
        String pageTitle = home.getPageTitle();
        logger.info("[Step 3] Current Page Title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("Your Store"), "User is NOT on Home Page");

        logger.info("***** Finished TC_HP_002 *****");
    }
}
