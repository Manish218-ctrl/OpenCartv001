package testCases.TS_012_HeaderMenuFooterOptions;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_HMF_003_ValidateRemoveButtonInShoppingCartTest extends BaseClass {

    @Test
    public void verifyRemoveButtonInShoppingCart() {
        logger.info("***** Starting TC_HMF_003_ValidateRemoveButtonInShoppingCartTest *****");

        try {
            Homepage homepage = new Homepage(driver);

            // Step 1: Add MacBook
            homepage.enterSearchText("MacBook");
            homepage.clickSearchButton();
            homepage.clickAddToCart("MacBook");
            homepage.clickViewCartFromSuccessAlert();   //  ensures navigation to cart page

            // Step 2: Add HP LP3065
            homepage.enterSearchText("HP LP3065");
            homepage.clickSearchButton();
            homepage.clickAddToCart("HP LP3065");
            homepage.clickViewCartFromSuccessAlert();   // ensures navigation to cart page

            // Step 3: Validate items in cart
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

            Assert.assertTrue(shoppingCartPage.isProductInCart("MacBook"),
                    "MacBook is not in the cart.");
            logger.info("MacBook is present in the cart.");

            Assert.assertTrue(shoppingCartPage.isProductInCart("HP LP3065"),
                    "HP LP3065 is not in the cart.");
            logger.info("HP LP3065 is present in the cart.");

            // Step 4: Remove MacBook from the cart
            shoppingCartPage.removeProduct("HP LP3065");

            // Step 5: Validate MacBook is removed
            Assert.assertFalse(shoppingCartPage.isProductInCart("HP LP3065"),
                    "MacBook was not removed from the cart.");
            logger.info("MacBook removed successfully from the cart.");

            logger.info("***** Finished TC_HMF_003_ValidateRemoveButtonInShoppingCartTest *****");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
