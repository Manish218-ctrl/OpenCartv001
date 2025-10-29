package testCases.TS_006_AddtoCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_ATC_001_ValidateAddToCartFromProductDisplayPageTest extends BaseClass {

    @Test(groups = {"regression","smoke"})
    public void verifyAddToCartFromPDP() {
        logger.info("***** Starting TC_ATC_001_ValidateAddToCartFromProductDisplayPageTest *****");

        try {
            String productName = "MacBook";

            // Step 1: Search for product
            SearchPage sp = new SearchPage(driver);
            sp.enterSearchKeyword(productName);
            sp.clickSearchButton();

            Assert.assertTrue(sp.isProductDisplayed(productName),
                    "ERROR: Product '" + productName + "' not displayed in search results.");
            logger.info("Product '{}' found in search results.", productName);

            // Step 2: Navigate to Product Display Page
            sp.clickFirstProductName();
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            Assert.assertTrue(pdp.isOnProductDisplayPage(),
                    "ERROR: Did not navigate to Product Display Page.");

            // Step 3: Click 'Add to Cart'
            pdp.clickAddToCartButton();

            // Step 4: Validate success message
            String successMsg = pdp.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have added"),
                    "ERROR: Success message not displayed.");
            Assert.assertTrue(successMsg.contains(productName),
                    "ERROR: Success message does not contain product name.");
            logger.info("Success message validated: {}", successMsg);

            // Step 5: Click 'shopping cart!' link
            pdp.clickShoppingCartLinkInSuccessMessage();

            // Step 6: Validate product is in Shopping Cart
            ShoppingCartPage cart = new ShoppingCartPage(driver);
            Assert.assertTrue(cart.isProductInCart(productName),
                    "ERROR: Product not found in Shopping Cart.");
            logger.info("Product '{}' successfully added to Shopping Cart.", productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Add to Cart test: " + e.getMessage());
        }

        logger.info("***** Finished TC_ATC_001_ValidateAddToCartFromProductDisplayPageTest *****");
    }
}
