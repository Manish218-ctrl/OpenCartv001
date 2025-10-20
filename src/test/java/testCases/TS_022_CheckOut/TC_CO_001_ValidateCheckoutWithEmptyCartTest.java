package testCases.TS_022_CheckOut;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_CO_001_ValidateCheckoutWithEmptyCartTest extends BaseClass {

        @Test
        public void verifyCheckoutWithEmptyCart() throws InterruptedException {
            logger.info("***** Starting TC_CO_001_CheckoutEmptyCart *****");
            performLogin();
            HomePage home = new HomePage(driver);

            // Step 1: Navigate directly to Shopping Cart
            home.clickdircartbtn();



            String breadcrumb = home.getBreadcrumb();
            logger.info("Breadcrumb displayed: " + breadcrumb);

            // Assert that we are in Shopping Cart page
            Assert.assertTrue(breadcrumb.contains("Shopping Cart"),
                    "User is not on Shopping Cart page as expected.");

            // Step 2: Try to navigate to Checkout
            home.clickCheckoutemptycart();

            // Validate user is still on "Shopping Cart" (not actual checkout)
            String pageTitle = home.getPageTitle();
            logger.info("Page Title after Checkout click: " + pageTitle);

            // Assertion: Page should still show "Shopping Cart"
            Assert.assertTrue(pageTitle.contains("Your Store"),
                    "User was incorrectly taken to Checkout instead of Shopping Cart when cart is empty.");

            logger.info("***** Finished TC_CO_001_CheckoutEmptyCart *****");
        }
    }