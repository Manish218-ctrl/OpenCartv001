package testCases.TS_007_WishList;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_WL_010_ValidateRemoveProductFromWishListTest extends BaseClass {

        @Test(description = "Validate removing a product from My Wish List page")
        public void test_RemoveProductFromWishList() {
            logger.info("***** Starting TC_WL_015: Remove Product from My Wish List *****");

            try {
                // 1) Login
                HomePage hp = new HomePage(getDriver());
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(getDriver());
                lp.login(p.getProperty("email"), p.getProperty("password"));
                logger.info("Login successful");

                // 2) Navigate to My Account
                MyAccountPage my = new MyAccountPage(getDriver());
                Assert.assertTrue(my.isMyAccountPageExists(), "My Account page not displayed after login.");

                // 3) Navigate to My Wish List page
                my.clickWishListFromMyAccount();
                WishListPage wl = new WishListPage(getDriver());
                Assert.assertTrue(wl.isOnWishListPage(), "My Wish List page not opened.");
                logger.info("Navigated to My Wish List page");

                // 4) Ensure wishlist is clean
                wl.clearWishList();

                // 5) Add a product into wishlist (from homepage Featured for simplicity)
                hp = new HomePage(getDriver());
                HomePage home = new HomePage(getDriver());
                hp.clickFooterWishList(); // optional, just to navigate if needed
                getDriver().navigate().to(p.getProperty("appURL")); // Go back home

                SearchPage sp = new SearchPage(getDriver());
                sp.enterSearchKeyword("iPhone");
                home.clickSearchButton();

                Assert.assertTrue(sp.isProductDisplayed("iPhone"), "Test product not found in search results.");
                sp.clickAddToWishListIconForProduct("iPhone");
                logger.info("Added product iPhone to wishlist");

                // Go to wishlist via success message link
                sp.clickWishListLinkInSuccessMessage();

                Assert.assertTrue(wl.isOnWishListPage(), "Did not navigate to wishlist page after adding.");



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
                        "Expected Your wish list is empty. message not displayed.");


                logger.info("Validation Passed:Wish List is empty after removal");

                logger.info("***** TC_WL_015 PASSED *****");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Exception occurred during test execution: " + e.getMessage());
            }
        }
    }



