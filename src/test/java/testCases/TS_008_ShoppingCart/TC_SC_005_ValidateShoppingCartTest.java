package testCases.TS_008_ShoppingCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_SC_005_ValidateShoppingCartTest extends BaseClass {

    @Test
    public void validateProductDetailsInShoppingCart() {
        logger.info("***** Starting TC_SC_007 - Validate Product Details in Shopping Cart *****");

        try {
            // Step 1: Perform login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Search for the product (e.g., "HP LP3065")
            HomePage homepage = new HomePage(driver);
            homepage.enterSearchText("HP LP3065");
            homepage.clickSearchButton();
            logger.info("Searched for 'HP LP3065' product.");

            // Step 3: Select the product from search results
            ProductDisplayPage productPage = new ProductDisplayPage(driver);
            productPage.clickProductFromSearchResults("HP LP3065");
            logger.info("Clicked on 'HP LP3065' product.");

            // Step 4: Add the product to the cart
            productPage.clickAddToCartButton();  // No need to pass the product name here
            logger.info("Added 'HP LP3065' to the cart.");

            // Step 5: Click the 'shopping cart!' link in the success message
            productPage.clickShoppingCartLinkInSuccessMessage();
            logger.info("Navigated to the shopping cart.");

            // Step 6: Validate the product details on the Shopping Cart page
            ShoppingCartPage cartPage = new ShoppingCartPage(driver);

            // Validate Image
            Assert.assertTrue(cartPage.isProductImageDisplayed(), "Product image is not displayed correctly.");
            logger.info("Product image is displayed correctly.");

            // Validate Name
            String productName = cartPage.getProductName();
            Assert.assertEquals(productName, "HP LP3065", "Product name is incorrect. Expected: 'iMac', Found: " + productName);
            logger.info("Product name is correct: " + productName);

            // Validate Model
            String productModel = cartPage.getProductModel();
            Assert.assertEquals(productModel, "Model", "Product model is incorrect. Expected: 'iMac Model', Found: " + productModel);
            logger.info("Product model is correct: " + productModel);



        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_SC_007 - Validate Product Details in Shopping Cart *****");
    }
}
