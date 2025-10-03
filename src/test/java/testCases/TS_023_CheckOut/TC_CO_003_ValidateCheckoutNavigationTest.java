package testCases.TS_023_CheckOut;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_CO_003_ValidateCheckoutNavigationTest extends BaseClass {

        @Test
        public void validateCheckoutNavigation() {
            logger.info("***** Starting TC_CO_003 - Checkout Test *****");

            try {
                Homepage home = new Homepage(driver);

                // Step 1: Search for product
                home.enterSearchText(productName);   // iMac from config.properties
                home.clickSearchButton();
                logger.info("Searched for product: " + productName);

                // Step 2: Add product to cart
                home.addProductToCart(productName);
                Thread.sleep(20000);
                home.clickaddtocarthpbtn();
                logger.info("Added product to cart: " + productName);

                // Step 3: Navigate to Shopping Cart page
                home.clickViewCartOption();  // from success alert OR dropdown

                // Step 4: Click Checkout option
                home.clickcheckoutfromcart();

                // Step 5: Validate user is on Checkout Page
                String actualBreadcrumb = home.getBreadcrumb();
                logger.info("Breadcrumb after Checkout navigation: " + actualBreadcrumb);

                Assert.assertTrue(
                        actualBreadcrumb.contains("Checkout"),
                        " User is not on the Checkout page. Actual breadcrumb: " + actualBreadcrumb
                );

                logger.info(" User successfully navigated to Checkout page.");

            } catch (Exception e) {
                logger.error(" Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_CO_003 - Checkout Test *****");
        }
    }
