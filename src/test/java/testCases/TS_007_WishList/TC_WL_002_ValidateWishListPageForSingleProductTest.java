
package testCases.TS_007_WishList;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_WL_002_ValidateWishListPageForSingleProductTest extends BaseClass {

    @Test(description = "Validate the 'My Wish List' page when only one product is added to it (TS_009)")
    public void test_ValidateWishListPageForSingleProduct() {
        logger.info("***** Starting TC_WL_014: Validate Wish List page with a single product *****");

        final String PRODUCT_NAME = "HP LP3065"; // Define the product

       // WishListPage wl;
        try {
            // 1. Login
            HomePage hp = new HomePage(driver);
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

            wl.clickRemoveButtonForProduct("iPhone");
            wl.waitForModificationSuccessMessage();
            logger.info("Clicked remove and success message appeared for 'iPhone'");

            //  Remove all products from the wishlist
            wl.removeAllProductsIndividually();
            wl.waitForModificationSuccessMessage();
            logger.info("Removed all products from wishlist");



            String successMsg = wl.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have modified your wish list!"),
                    "Success message not shown after removing product.");
            logger.info("Validation Passed: Correct success message displayed");

            // 8) Validate wishlist is empty
            Assert.assertTrue(wl.isWishListEmptyMessageDisplayed(),
                    "Expected 'Your wish list is empty.' message not displayed.");


            logger.info("Validation Passed: Wish List is empty after removal");

            // --- MODIFICATION END ---


            // 2. Add a product to Wish List
            driver.navigate().to(rb.getString("appURL")); // Go back home
            SearchPage sp = new SearchPage(driver);

            HomePage home = new HomePage(driver);



            home.enterSearchText(PRODUCT_NAME);
            home.clickSearchButton();


            home.clickAddToWishListIconForProduct();
            logger.info("Added product '" + PRODUCT_NAME + "' to Wish List.");


            sp.clickWishListLinkInSuccessMessage();



            // Validate ER-1: My Wish List page is displayed correctly
            Assert.assertTrue(wl.isOnWishListPage(), "My Wish List page did not open after navigation.");
            Assert.assertEquals(wl.getWishListPageHeading(), "My Wish List", "Wish List page heading is incorrect.");
            logger.info("Validation Passed: My Wish List page is displayed correctly.");

            // Validate ER-2: The single added product is present
            Assert.assertTrue(wl.isProductInWishList(PRODUCT_NAME), PRODUCT_NAME + " is not displayed in the Wish List.");
            logger.info("Validation Passed: The added product '" + PRODUCT_NAME + "' is present in the Wish List.");


            logger.info("***** TC_WL_014 PASSED *****");

        } catch (Exception e) {
            logger.error("Test Failed for TC_WL_014: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
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