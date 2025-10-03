package testCases.TS_023_CheckOut;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_CO_002_ValidateCheckoutNavigationTest extends BaseClass {

        @Test
        public void validateCheckoutNavigation() {
            try {
                logger.info("***** Starting TC_CO_002 - Validate Checkout Navigation *****");

                // Step 1: Initialize Homepage
                Homepage home = new Homepage(driver);

                // Step 2: Enter product name in search box
                home.enterSearchText(productName);
                logger.info("Entered product name: " + productName);

                // Step 3: Click Search button
                home.clickSearchButton();
                logger.info("Clicked search button");

                // Step 4: Add product to cart
               /// home.clickAddToCart(productName);
               // home.clickAddToCart1(productName);
                home.clickaddtocarthpbtn();
                logger.info("Clicked Add to Cart for product: " + productName);

                // Step 5: Click on Shopping Cart link (from success message)
                home.clickViewCartOption();
                logger.info("Navigated to Shopping Cart page");

                // Step 6: Click Checkout button
                home.clickCheckout();
                logger.info("Clicked Checkout button");

                // Validation - Verify Breadcrumb or Page Title
                String breadcrumb = home.getBreadcrumb();
                Assert.assertTrue(breadcrumb.contains("Checkout"),
                        "Expected to be on Checkout page but breadcrumb was: " + breadcrumb);

                logger.info("Successfully navigated to Checkout page.");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }
        }
    }

