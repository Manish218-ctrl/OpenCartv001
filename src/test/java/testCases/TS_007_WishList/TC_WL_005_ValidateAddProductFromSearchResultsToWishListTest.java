package testCases.TS_007_WishList;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_WL_005_ValidateAddProductFromSearchResultsToWishListTest extends BaseClass {

        @Test
        public void test_AddProductFromSearchResults_ToWishList() {
            logger.info("***** Starting TC_WL_005 Add Product from Search Results to Wish List *****");

            try {
                // 1. Login
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(driver);
                lp.login(rb.getString("email"), rb.getString("password"));

                // 2. Search Product (iMac)
                SearchPage sp = new SearchPage(driver);
                String productName = "iMac";
                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName), productName + " not displayed in results");

                // 3. Add product to Wish List from results
                sp.clickAddToWishListIconForProduct(productName);

                // Validate success message
                String successMsg = sp.getSuccessMessage();
                Assert.assertTrue(successMsg.contains("Success: You have added"), "Success message not shown");
                logger.info("Validation Passed: Success message displayed -> " + successMsg);

                // 4. Click 'wish list!' link
                sp.clickWishListLinkInSuccessMessage();

                // 5. Verify product in My Wish List page
                WishListPage wlp = new WishListPage(driver);
                Assert.assertTrue(wlp.isProductInWishList(productName), "Product not found in wishlist");
                logger.info("Validation Passed: Product '" + productName + "' is in wishlist");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail(e.getMessage());
            }

            logger.info("***** Finished TC_WL_005 Add Product from Search Results to Wish List *****");
        }
    }



