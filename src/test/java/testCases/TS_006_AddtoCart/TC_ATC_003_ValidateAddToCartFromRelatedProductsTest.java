package testCases.TS_006_AddtoCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_ATC_003_ValidateAddToCartFromRelatedProductsTest extends BaseClass {


    @Test(description = "Validate adding the product to Cart from the Related Products section of the PDP")
    public void test_AddToCart_From_RelatedProducts() {
        logger.info("=== TC_ATC_004: Start ===");

        try {

            // Step 1: Search for product
            Homepage home = new Homepage(driver);
            String searchTerm = (searchProductName != null && !searchProductName.isBlank())
                    ? searchProductName
                    : "Apple Cinema 30\""; // fallback safety

            logger.info("Searching for product: " + searchTerm);
            home.enterSearchText(searchTerm);
            home.clickSearchButton();

            // Step 2: Open the Product Display Page
            SearchPage results = new SearchPage(driver);
            Assert.assertTrue(results.hasResults(), "No search results found for: " + searchTerm);
            results.openProductByName(searchTerm);
            logger.info("Opened PDP for: " + searchTerm);

            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            String pdpTitle = pdp.getPdpTitle();
            logger.info("On PDP titled: " + pdpTitle);

            // Step 3: Wait + Scroll to Related Products
            logger.info("Scrolling to Related Products section...");
            Assert.assertTrue(pdp.hasRelatedProducts(), "Related products section not found on PDP.");


            int indexToAdd = 0; // pick first related product
            String relatedProductName = pdp.getRelatedProductName(indexToAdd);
            logger.info("Adding related product to cart: " + relatedProductName);

            pdp.addRelatedProductToCartByIndex(indexToAdd);

            // Step 4: Validate success message
            String successMsg = pdp.getSuccessMessageText();
            logger.info("Success message: " + successMsg);

            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Success keyword not found in message: " + successMsg);
            Assert.assertTrue(successMsg.contains(relatedProductName),
                    "Related product name not found in success message. Msg: " + successMsg);

            // Step 5: Click 'shopping cart!' link inside the success toast
            pdp.clickShoppingCartLinkInSuccess();

            // Step 6: Validate Shopping Cart Page
            ShoppingCartPage cart = new ShoppingCartPage(driver);
            Assert.assertTrue(cart.isOnShoppingCartPage(),
                    "Not on Shopping Cart page after clicking link from success message.");
            Assert.assertTrue(cart.isProductDisplayedInCart(relatedProductName),
                    "Product not found in cart: " + relatedProductName);

            logger.info("Verified product present in Shopping Cart: " + relatedProductName);

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage(), e);
            Assert.fail("Test Failed due to exception: " + e.getMessage());
        }

        logger.info("=== TC_ATC_004: End ===");
    }
}
