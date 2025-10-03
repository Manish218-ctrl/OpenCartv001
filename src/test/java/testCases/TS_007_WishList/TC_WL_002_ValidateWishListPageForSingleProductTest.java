
package testCases.TS_007_WishList;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_WL_002_ValidateWishListPageForSingleProductTest extends BaseClass {

    @Test(description = "Validate the 'My Wish List' page when only one product is added to it (TS_009)")
    public void test_ValidateWishListPageForSingleProduct() {
        logger.info("***** Starting TC_WL_014: Validate Wish List page with a single product *****");

        final String PRODUCT_NAME = "iPhone"; // Define the product

        try {
            // 1. Login
            Homepage hp = new Homepage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.login(rb.getString("username"), rb.getString("password"));
            logger.info("Login successful.");

            MyAccountPage my = new MyAccountPage(driver);
            Assert.assertTrue(my.isMyAccountPageExists(), "My Account page not displayed after login.");

            // Cleanup: Ensure the Wish List is empty before starting the test
            my.clickWishListFromMyAccount();
            WishListPage wl = new WishListPage(driver);

            // --- MODIFICATION START ---
            // The issue is likely here: remove the explicit wait after removing products
            if (wl.getTotalProductsInWishList() > 0) {
                wl.removeAllProductsIndividually();
                // wl.waitForModificationSuccessMessage(); <-- REMOVED THIS LINE
                // Instead, rely on the assertion below to confirm cleanup success
                Assert.assertTrue(wl.isWishListEmptyMessageDisplayed(), "Cleanup failed: Wish List is not empty after removal.");
                logger.info("Cleaned up existing products from the Wish List.");
            }
            // --- MODIFICATION END ---


            // 2. Add a product to Wish List
            driver.navigate().to(rb.getString("appURL")); // Go back home
            SearchPage sp = new SearchPage(driver);

            sp.enterSearchKeyword(PRODUCT_NAME);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(PRODUCT_NAME), PRODUCT_NAME + " not found in search results. Cannot proceed.");

            sp.clickAddToWishListIconForProduct(PRODUCT_NAME);
            logger.info("Added product '" + PRODUCT_NAME + "' to Wish List.");

            // Navigate to Wish List page via the success message link
            sp.clickWishListLinkInSuccessMessage();

            // --- Validation Steps ---

            // Validate ER-1: My Wish List page is displayed correctly
            Assert.assertTrue(wl.isOnWishListPage(), "My Wish List page did not open after navigation.");
            Assert.assertEquals(wl.getWishListPageHeading(), "My Wish List", "Wish List page heading is incorrect.");
            logger.info("Validation Passed: My Wish List page is displayed correctly.");

            // Validate ER-2: The single added product is present
            Assert.assertTrue(wl.isProductInWishList(PRODUCT_NAME), PRODUCT_NAME + " is not displayed in the Wish List.");
            logger.info("Validation Passed: The added product '" + PRODUCT_NAME + "' is present in the Wish List.");

            // 3. Optional: Validate Continue button functionality (if supported)
            // If you need to validate the 'Continue' button, add the necessary page object methods here.

            logger.info("***** TC_WL_014 PASSED *****");

        } catch (Exception e) {
            logger.error("Test Failed for TC_WL_014: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
            // Clean up in the finally block for reliability
            try {
                WishListPage wl = new WishListPage(driver);
                // Ensure we are on the wishlist page before attempting cleanup
                wl.clickWishListHeader();
                if (wl.getTotalProductsInWishList() > 0) {
                    wl.removeAllProductsIndividually();
                }
                logger.info("Cleanup successful: Wish List is empty.");
            } catch (Exception cleanupException) {
                logger.warn("Cleanup in finally block failed: " + cleanupException.getMessage());
            }
        }
    }
}