package testCases.TS_006_AddtoCart;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

    public class TC_ATC_005_ValidateAddToCartTest_DataDrivenTest extends BaseClass {

        // Data Provider to supply product names for the test
        @DataProvider(name = "productData")
        public Object[][] getProductData() {
            return new Object[][] {
                    {"iMac"},
                    {"MacBook"},
                    {"iPhone"},
                    {"Samsung Galaxy Tab 10.1"}
            };
        }

        @Test(dataProvider = "productData", groups = {"regression", "smoke"})
        public void verifyAddToCartFromPDP(String productName) {
            logger.info("***** Starting Data-Driven Add to Cart Test for product: " + productName + " *****");

            try {
                // Step 1: Search for product using the data-driven value
                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();

                // Assert that the product is displayed in search results
                Assert.assertTrue(sp.isProductDisplayed(productName),
                        "ERROR: Product '" + productName + "' not displayed in search results.");
                logger.info("Product '" + productName + "' found in search results.");

                // Step 2: Navigate to Product Display Page
                sp.clickFirstProductName();
                ProductDisplayPage pdp = new ProductDisplayPage(driver);

                // Assert that we are on the correct Product Display Page
                Assert.assertTrue(pdp.isOnProductDisplayPage(),
                        "ERROR: Did not navigate to Product Display Page for " + productName);

                // Step 3: Click 'Add to Cart'
                pdp.clickAddToCartButton();

                // Step 4: Validate success message
                String successMsg = pdp.getSuccessMessage();
                Assert.assertTrue(successMsg.contains("Success: You have added"),
                        "ERROR: Success message not displayed for " + productName);
                Assert.assertTrue(successMsg.contains(productName),
                        "ERROR: Success message does not contain product name for " + productName);
                logger.info("Success message validated: " + successMsg);

                // Step 5: Click 'shopping cart!' link
                pdp.clickShoppingCartLinkInSuccessMessage();

                // Step 6: Validate product is in Shopping Cart
                ShoppingCartPage cart = new ShoppingCartPage(driver);
                Assert.assertTrue(cart.isProductInCart(productName),
                        "ERROR: Product " + productName + " not found in Shopping Cart.");
                logger.info("Product '" + productName + "' successfully added to Shopping Cart.");

            } catch (Exception e) {
                logger.error("Test Failed for product " + productName + " due to an Exception: " + e.getMessage(), e);
                Assert.fail("Exception occurred during Add to Cart test for " + productName + ": " + e.getMessage());
            }

            logger.info("***** Finished Data-Driven Add to Cart Test for product: " + productName + " *****");
        }
    }


