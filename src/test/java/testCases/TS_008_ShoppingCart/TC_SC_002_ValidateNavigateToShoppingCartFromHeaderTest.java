package testCases.TS_008_ShoppingCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

    public class TC_SC_002_ValidateNavigateToShoppingCartFromHeaderTest extends BaseClass {

        @Test
        public void test_NavigateToShoppingCart_From_Header() {
            logger.info("***** Starting TC_SC_002: Navigate to Shopping Cart from Header *****");

            try {
                // Step 1: Search for "iMac"
                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword("iMac");
                sp.clickSearchButton();
                logger.info("Searched for 'iMac'");

                // Step 2: Validate iMac is displayed in results
                Assert.assertTrue(sp.isProductDisplayed("iMac"), "iMac not found in search results");

                // Step 3: Click Add to Cart directly from search results
                sp.clickAddToCartFromSearchResults("iMac");
                logger.info("Clicked 'Add to Cart' for iMac in search results");

                // Step 4: Click Shopping Cart header link
                sp.clickShoppingCartHeaderLink();
                logger.info("Clicked Shopping Cart header link");

                // Step 5: Validate user is on Shopping Cart page
                ShoppingCartPage scp = new ShoppingCartPage(driver);
                Assert.assertTrue(scp.isOnShoppingCartPage(), "User is not on Shopping Cart page");
                Assert.assertTrue(scp.isProductInCart("iMac"), "iMac is not present in Shopping Cart");

                logger.info("Validation Passed: Navigated to Shopping Cart page and iMac is in cart");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Test Failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_SC_002: Navigate to Shopping Cart from Header *****");
        }
    }



