package testCases.TS_006_AddtoCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_ATC_003_ValidateAddToCartFromRelatedProductsTest extends BaseClass {


    @Test(description = "Validate adding the product to Cart from the Related Products section of the PDP")
    public void test_AddToCart_From_RelatedProducts() {
        logger.info("TC_ATC_004: Start");

        try {

            //Search for product
            HomePage home = new HomePage(getDriver());
            String searchTerm = (searchProductName != null && !searchProductName.isBlank())
                    ? searchProductName
                    : "iPhone"; // fallback safety

            logger.info("Searching for product: " + searchTerm);
            home.enterSearchText(searchTerm);
            home.clickSearchButton();

            //Open the Product Display Page
            SearchPage results = new SearchPage(getDriver());
            Assert.assertTrue(results.hasResults(), "No search results found for: " + searchTerm);
            results.openProductByName(searchTerm);
            logger.info("Opened PDP for: " + searchTerm);

            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());
            String pdpTitle = pdp.getPdpTitle();
            logger.info("On PDP titled: " + pdpTitle);

            //Wait + Scroll to Related Products
            logger.info("Scrolling to Related Products section...");

            pdp.clickfirstRelatedAddToCartBtn();

            //Validate success message
            String successMsg = pdp.getSuccessMessageText();
            logger.info("Success message: " + successMsg);

            //Click shopping cart! link inside the success toast
            pdp.clickShoppingCartLinkInSuccess();

            //Validate Shopping Cart Page
            ShoppingCartPage cart = new ShoppingCartPage(getDriver());
            Assert.assertTrue(cart.isOnShoppingCartPage(),
                    "Not on Shopping Cart page after clicking link from success message.");
            Assert.assertTrue(cart.isProductDisplayedInCart("iPhone"),
                    "Product not found in cart: " + "iPhone");

            logger.info("Verified product present in Shopping Cart: " + "iPhone");

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage(), e);
            Assert.fail("Test Failed due to exception: " + e.getMessage());
        }

        logger.info("TC_ATC_004:End");
    }
}
