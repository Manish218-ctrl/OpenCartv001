package testCases.TS_008_ShoppingCart;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_SC_001_ValidateNavigateToShoppingCartFromSuccessMsgTest extends BaseClass {

        @Test
        public void test_NavigateToShoppingCartFromSuccessMsg() {
            logger.info("***** Starting TC_SC_001 Navigate to Shopping Cart from Success Message *****");

            try {
                // 1. Open application is handled in BaseClass (setup)

                // 2. Enter search keyword (iMac)
                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword("iMac");
                sp.clickSearchButton();
                logger.info("Searched for 'iMac'");

                // 3. Click on the product from results
                Assert.assertTrue(sp.isProductDisplayed("iMac"), "iMac not found in search results");
                sp.clickFirstProductName();
                logger.info("Clicked on 'iMac' product link");

                // 4. Click "Add to Cart" on product display page
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                Assert.assertTrue(pdp.isOnProductDisplayPage(), "Not on Product Display Page");
                pdp.clickAddToCartButton();
                logger.info("Clicked 'Add to Cart' button");

                // 5. Click "shopping cart!" link in success message
                pdp.clickShoppingCartLinkInSuccessMessage();
                logger.info("Clicked 'shopping cart!' link from success message");

                // 6. Validate user is on Shopping Cart page
                String actualTitle = driver.getTitle();
                Assert.assertTrue(actualTitle.contains("Shopping Cart"),
                        "Not navigated to Shopping Cart page. Title is: " + actualTitle);
                logger.info("Validation Passed: User is on Shopping Cart page");

            } catch (Exception e) {
                logger.error("Test Failed due to exception: " + e.getMessage());
                Assert.fail("Test Failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_SC_001 Navigate to Shopping Cart from Success Message *****");
        }
    }



