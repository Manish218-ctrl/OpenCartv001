package testCases.TS_022_CheckOut;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_CO_002_ValidateCheckoutNavigationTest extends BaseClass {

        @Test
        public void validateCheckoutNavigation() {
            try {
                logger.info("***** Starting TC_CO_002 - Validate Checkout Navigation *****");

                HomePage home = new HomePage(driver);

                home.enterSearchText(productName);
                home.clickSearchButton();
                home.addProductToCart(productName);
                home.clickaddtocart();
                home.clickshoppingcartbtnmsg();
                home.clickcheckoutfromcart();

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

