package testCases.TS_006_AddtoCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_ATC_005_ValidateAddToCartFromSearchPageTest extends BaseClass {

    @Test
    public void verifyAddToCartFromCategoryPage() {

        try {

            logger.info("***** Starting TC_ATC_005_ValidateAddToCartFromSearchPageTest *****");

            HomePage home = new HomePage(getDriver());

            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());

            String expectedProduct = "HP LP3065";

            // Navigate directly to PDP
            getDriver().navigate().to(
                    appURL + "/index.php?route=product/product&path=18&product_id=47"
            );

            logger.info("Navigated to HP LP3065 Product Display Page.");

            // Add product to cart
            pdp.addToCart();

            logger.info("Clicked Add To Cart button.");

            // Open Shopping Cart from success message
            pdp.clickShoppingCartLinkInSuccessMessage();

            logger.info("Clicked Shopping Cart link from success message.");

            // Validate Shopping Cart page
            Assert.assertTrue(
                    cartPage.isOnShoppingCartPage(),
                    "Not on Shopping Cart page!"
            );

            // Validate product exists in cart
            Assert.assertTrue(
                    cartPage.isProductDisplayedInCart(expectedProduct),
                    "Product not found in cart!"
            );

            logger.info("Product successfully added and verified in Shopping Cart.");

            logger.info("***** Finished TC_ATC_005_ValidateAddToCartFromSearchPageTest *****");

        } catch (Exception e) {

            logger.error(
                    "Test failed due to exception: " + e.getMessage(),
                    e
            );

            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}