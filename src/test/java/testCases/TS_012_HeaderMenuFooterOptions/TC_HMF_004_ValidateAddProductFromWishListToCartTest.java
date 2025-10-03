package testCases.TS_012_HeaderMenuFooterOptions;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.WishListPage;
import pageObjects.ShoppingCartPage; // Assuming you have a ShoppingCartPage class
import testBase.BaseClass;

public class TC_HMF_004_ValidateAddProductFromWishListToCartTest extends BaseClass {

        // Define the product to use for the test.
        // This should ideally be read from a properties file or DataProvider,
        // but for this script, we'll use a constant.
        private final String PRODUCT_NAME = "MacBook"; // Using a common product for demonstration

        @Test(groups = {"master", "regression"})
        public void test_addProduct_from_wishlist_to_cart() {
            logger.info("Starting TC_WL_001: Validate adding a product from Wish List to Shopping Cart.");

            try {
                Homepage hp = new Homepage(driver);

                // Step 1: Login to the application
                logger.info("Performing user login.");
                hp.clickMyAccount();
                hp.clickLogin();

                // Perform login using the credentials from BaseClass properties
                // Assuming performLogin() method is available in BaseClass or LoginPage
                performLogin();
                logger.info("Login successful. Navigating to Homepage.");

                // --- Pre-requisite: Add product to Wish List (if not already there) ---
                // A robust test should ensure the product is in the Wish List first.
                // For simplicity, we will search and add it to the Wish List.

                // 2. Search for the product
                logger.info("Searching for product: " + PRODUCT_NAME);
                hp.enterSearchText(PRODUCT_NAME);
                hp.clickSearchButton();

                // Assuming SearchPage or Homepage has a method to add to Wish List from search results
                // Since the code for this is not provided, we will assume the test starts from the Wish List Page.
                // For a complete flow, you would need: searchPage.clickAddToWishListForProduct(PRODUCT_NAME);
                // Since we don't have a SearchPage, we'll navigate directly to Wish List (assuming the product is already there)

                // 3. Navigate to Wish List Page via Footer link (or My Account -> Wish List)
                hp.clickFooterLink("Wish List");

                WishListPage wlp = new WishListPage(driver);
                Assert.assertTrue(wlp.isOnWishListPage(), "Failed to navigate to the Wish List page.");
                logger.info("Successfully navigated to My Wish List page.");

                // 4. Click 'Add to Cart' for the specific product on the Wish List page
                logger.info("Attempting to move product '" + PRODUCT_NAME + "' to Shopping Cart.");
                wlp.clickAddToCartIcon(PRODUCT_NAME); // Assumes this method exists and clicks the correct button

                // 5. Verify success message for adding the product to the cart
                String successMessage = wlp.getSuccessMessage(); // Assumes this method returns the success alert text
                Assert.assertTrue(successMessage.contains("Success: You have added " + PRODUCT_NAME + " to your shopping cart!"),
                        "Add to Cart success message for " + PRODUCT_NAME + " not displayed.");
                logger.info("Verified ER-1: Success message displayed: " + successMessage);

                // 6. Navigate to Shopping Cart
                // The success message usually contains a link to the shopping cart,
                // but we'll use the header link for reliability.
                wlp.clickShoppingCartHeader(); // Assumes this method clicks the header Shopping Cart link

                // 7. Verify product is in the Shopping Cart
                ShoppingCartPage scp = new ShoppingCartPage(driver); // Assuming this class exists

                // Assuming ShoppingCartPage has a method to check if a product is in the cart
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


