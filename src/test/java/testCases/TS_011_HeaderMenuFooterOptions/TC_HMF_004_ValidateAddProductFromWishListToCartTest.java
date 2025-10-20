package testCases.TS_011_HeaderMenuFooterOptions;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.WishListPage;
import pageObjects.ShoppingCartPage; // Assuming you have a ShoppingCartPage class
import testBase.BaseClass;

public class TC_HMF_004_ValidateAddProductFromWishListToCartTest extends BaseClass {

        private final String PRODUCT_NAME = "HP LP3065"; // Using a common product for demonstration

        @Test(groups = {"master", "regression"})
        public void test_addProduct_from_wishlist_to_cart() {
            logger.info("Starting :TC_HMF_004_ValidateAddProductFromWishListToCartTest Validate adding a product from Wish List to Shopping Cart.");

            try {
                HomePage hp = new HomePage(driver);
                HomePage home = new HomePage(driver);
                // Step 1: Login to the application
                logger.info("Performing user login.");
                hp.clickMyAccount();
                hp.clickLogin();


                performLogin();
                logger.info("Login successful. Navigating to HomePage.");

                // --- Pre-requisite: Add product to Wish List (if not already there) ---


                // 2. Search for the product
                logger.info("Searching for product: " + PRODUCT_NAME);
                hp.enterSearchText(PRODUCT_NAME);
                hp.clickSearchButton();
                home.clickAddToWishListIconForProduct();

Thread.sleep(3000);

                // 3. Navigate to Wish List Page via Footer link (or My Account -> Wish List)
                hp.clickFooterLink("Wish List");

                WishListPage wlp = new WishListPage(driver);
                Assert.assertTrue(wlp.isOnWishListPage(), "Failed to navigate to the Wish List page.");
                logger.info("Successfully navigated to My Wish List page.");

                Thread.sleep(3000);

                // 4. Click 'Add to Cart' for the specific product on the Wish List page
                logger.info("Attempting to move product '" + PRODUCT_NAME + "' to Shopping Cart.");
                wlp.clickaddtocartbtnfromwishlist();
                home.clickaddtocart();

                Thread.sleep(2900);

                // 6. Navigate to Shopping Cart
                wlp.clickShoppingCartHeader();

                // 7. Verify product is in the Shopping Cart
                ShoppingCartPage scp = new ShoppingCartPage(driver); // Assuming this class exists


                Assert.assertTrue(scp.isProductInCart(PRODUCT_NAME),
                        "Verified ER-2: Product '" + PRODUCT_NAME + "' is NOT found in the Shopping Cart.");
                logger.info("Verified FR-2: Product '" + PRODUCT_NAME + "' is successfully added to the Shopping Cart.");

                // 8. Clean up (Optional but good practice) - Remove the item from the cart after test
                scp.removeProductFromCart(PRODUCT_NAME); // Assuming this method exists
                logger.info("Clean up: Removed product from cart.");

                logger.info("TC_WL_001 Add Product from Wish List to Cart test Passed.");

            } catch (Exception e) {
                logger.error("TC_WL_001 Add Product from Wish List to Cart test Failed: " + e.getMessage());
                captureScreenshot("TC_WL_001_Failed");
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }
        }
    }


