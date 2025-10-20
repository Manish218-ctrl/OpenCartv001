package testCases.TS_022_CheckOut;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CO_004_ValidateCheckoutFromCartBlockTest extends BaseClass {

    @Test(groups = {"Regression", "Checkout"})
    public void verifyCheckoutFromCartBlock() {
        logger.info("========== Starting TC_CO_004_VerifyCheckoutFromCartBlock ==========");

        try {
            HomePage home = new HomePage(driver);

            // Step 1: Search and add product to cart
            logger.info("[STEP 1] Searching product: " + productName);
            home.enterSearchText(productName);
            logger.info("Entered product name in search box: " + productName);

            home.clickSearchButton();
            logger.info("Clicked on Search button.");

            home.addProductToCart(productName);
            Thread.sleep(2000);
            home.clickaddtocarthpbtn();
            logger.info("Successfully added product to cart: " + productName);

            // Step 2: Expand Cart block
            logger.info("[STEP 2] Expanding cart dropdown block.");
            home.clickCartBlock();
            logger.info("Cart dropdown expanded successfully.");

            // Step 3: Click Checkout option inside cart block
            logger.info("[STEP 3] Clicking Checkout option inside Cart block.");
            home.clickCheckout();
            logger.info("Clicked Checkout option, waiting for Checkout page to load...");

            // Step 4: Validate breadcrumb
            String breadcrumb = home.getBreadcrumb();
            logger.info("[STEP 4] Captured breadcrumb text: " + breadcrumb);

            Assert.assertTrue(
                    breadcrumb.contains("Checkout"),
                    " User is NOT navigated to Checkout page! Expected breadcrumb to contain 'Shopping Cart'."
            );

            logger.info(" Assertion passed: User successfully navigated to Checkout page.");
            logger.info("========== Finished TC_CO_004_VerifyCheckoutFromCartBlock SUCCESSFULLY ==========");

        } catch (Exception e) {
            logger.error(" Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test Case Failed due to Exception: " + e.getMessage());
        }
    }
}
