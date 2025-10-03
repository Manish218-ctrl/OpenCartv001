package testCases.TS_007_WishList;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_WL_010_ValidateRemoveProductFromWishListTest extends BaseClass {

        @Test(description = "Validate removing a product from 'My Wish List' page")
        public void test_RemoveProductFromWishList() {
            logger.info("***** Starting TC_WL_015: Remove Product from My Wish List *****");

            try {
                // 1) Login
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(driver);
                lp.login(rb.getString("email"), rb.getString("password"));
                logger.info("Login successful");

                // 2) Navigate to My Account
                MyAccountPage my = new MyAccountPage(driver);
                Assert.assertTrue(my.isMyAccountPageExists(), "My Account page not displayed after login.");

                // 3) Navigate to My Wish List page
                my.clickWishListFromMyAccount();
                WishListPage wl = new WishListPage(driver);
                Assert.assertTrue(wl.isOnWishListPage(), "My Wish List page not opened.");
                logger.info("Navigated to My Wish List page");

                // 4) Ensure wishlist is clean
                wl.clearWishList();

                // 5) Add a product into wishlist (from homepage 'Featured' for simplicity)
                hp = new Homepage(driver);
                hp.clickFooterWishList(); // optional, just to navigate if needed
                driver.navigate().to(rb.getString("appURL")); // Go back home

                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword("iPhone");
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed("iPhone"), "Test product not found in search results.");
                sp.clickAddToWishListIconForProduct("iPhone");
                logger.info("Added product 'iPhone' to wishlist");

                // Go to wishlist via success message link
                sp.clickWishListLinkInSuccessMessage();
                Assert.assertTrue(wl.isOnWishListPage(), "Did not navigate to wishlist page after adding.");

                // 6) Remove the product
                wl.clickRemoveButtonForProduct("iPhone");
                wl.waitForModificationSuccessMessage();
                logger.info("Clicked remove and success message appeared for 'iPhone'");

                //  Remove all products from the wishlist
                wl.removeAllProductsIndividually();
                wl.waitForModificationSuccessMessage();
                logger.info("Removed all products from wishlist");



                // 7) Validate success message
                String successMsg = wl.getSuccessMessage();
                Assert.assertTrue(successMsg.contains("Success: You have modified your wish list!"),
                        "Success message not shown after removing product.");
                logger.info("Validation Passed: Correct success message displayed");

                // 8) Validate wishlist is empty
                Assert.assertTrue(wl.isWishListEmptyMessageDisplayed(),
                        "Expected 'Your wish list is empty.' message not displayed.");


                logger.info("Validation Passed: Wish List is empty after removal");

                Thread.sleep(20000);

                logger.info("***** TC_WL_015 PASSED *****");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Exception occurred during test execution: " + e.getMessage());
            }
        }
    }



