package testCases.TS_006_AddtoCart;



import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

    public class TC_ATC_006_ValidateAddToCartFromCategoryPageTest extends BaseClass {

        @Test
        public void verifyAddToCartFromCategoryPage() {
            try {
                logger.info("***** Starting TC_ATC_006_ValidateAddToCartFromCategoryPageTest *****");

                // Step 1: Initialize HomePage
                HomePage home = new HomePage(driver);

                // Step 2: Hover on 'Desktops' -> Click 'Show All Desktops'
                home.clickLogo();

                // Navigate to Mac subcategory
                driver.navigate().to(appURL + "/index.php?route=product/product&path=18&product_id=47"); // Direct URL for "Mac"
                logger.info("Navigated to HP LP3065 subcategory page.");

                // Step 3: Add Product to Cart
                String expectedProduct = "HP LP3065";
                //home.clickAddToCart(expectedProduct);
                driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();

                // Step 4: Validate success message and click Shopping Cart link
                home.clickViewCartFromSuccessAlert();
                logger.info("Clicked 'shopping cart' link from success alert.");

                // Step 5: Verify product is displayed in Shopping Cart
                ShoppingCartPage cartPage = new ShoppingCartPage(driver);
                Assert.assertTrue(cartPage.isOnShoppingCartPage(), "Not on Shopping Cart page!");

                boolean productExists = cartPage.isProductDisplayedInCart(expectedProduct);
                Assert.assertTrue(productExists, "Product not found in cart!");

                logger.info("Product successfully added and verified in Shopping Cart.");

                logger.info("***** Finished TC_ATC_006_ValidateAddToCartFromCategoryPageTest  *****");

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test failed: " + e.getMessage());
            }
        }
    }
