package testCases.TS_007_WishList;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects .*;
import testBase.BaseClass;

    public class TC_WL_006_ValidateNavigateToWishListFromSuccessMessageTest extends BaseClass {

        @Test(description = "Validate navigating to 'My Wish List' via the 'wish list!' link in success message")
        public void test_NavigateToWishList_FromSuccessMessage() {
            final String productName = "iMac";
            logger.info("***** Starting TC_WL_006 Navigate to My Wish List via success link *****");

            // 1) Login
            Homepage hp = new Homepage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(rb.getString("email"));        // ensure config.properties has email=...
            lp.setPassword(rb.getString("password"));  // ensure config.properties has password=...
            lp.clickLogin();
            logger.info("Logged in successfully.");

            // 2) Search and open the product display page
            SearchPage sp = new SearchPage(driver);
            sp.enterSearchKeyword(productName);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(productName),
                    "Expected product not shown in search results: " + productName);

            // You can click the exact product or just the first result.
            // Since we searched "iMac", open the PDP by clicking the first product name:
            sp.clickFirstProductName();

            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            Assert.assertTrue(pdp.isOnProductDisplayPage(), "Not on Product Display Page as expected.");

            // 3) Add to Wish List (from PDP)
            pdp.clickAddToWishListButton();
            String successMsg = pdp.getSuccessMessage();
            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Success message not shown after adding to Wish List. Actual: " + successMsg);

            // 4) Click the "wish list!" link in the success message
            pdp.clickWishListLink();
            logger.info("Clicked 'wish list!' link from success message.");

            // 5) Verify we are on My Wish List page and the product exists in the list
            WishListPage wlp = new WishListPage(driver);

            // Title check
            String title = wlp.getPageTitle();
            Assert.assertTrue(title.toLowerCase().contains("wish list"),
                    "Expected 'Wish List' page. Actual title: " + title);

            // Product present check
            Assert.assertTrue(wlp.isProductInWishList(productName),
                    "Product not present in My Wish List: " + productName);

            // (Optional) cleanup so future runs start clean
            try {
                wlp.clickRemoveButtonForProduct(productName);
                logger.info("Cleanup: removed '{}' from My Wish List.", productName);
            } catch (Exception ignored) {
            }

            logger.info("***** Completed TC_WL_006 successfully *****");
        }
    }







