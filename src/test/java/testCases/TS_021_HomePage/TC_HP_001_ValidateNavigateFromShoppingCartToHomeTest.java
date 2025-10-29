package testCases.TS_021_HomePage;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_HP_001_ValidateNavigateFromShoppingCartToHomeTest extends BaseClass {

        @Test(groups = {"Regression", "HomePage"})
        public void navigateFromCartToHome() {
            logger.info("***** Starting TC_HP_001: Navigate from Shopping Cart to Home Page *****");

            try {
                HomePage home = new HomePage(driver);

                // Step 1: Search for product
                home.enterSearchText(productName);
                home.clickSearchButton();
                home.selectListView();
                home.addProductToCart(productName);
                home.clickaddtocart();
                home.clickshoppingcartbtnmsg();
                logger.info("Navigated to checkout page");

                logger.info("Clicked 'Add to Cart' button for: " + productName);

                // Step 2: Click 'shopping cart!' link in success message
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                logger.info("Clicked 'shopping cart!' link in success message");

                // Step 4: Click 'Continue Shopping' button to go back to home page
                home.clickContinueShopping(); // You can create this method in HomePage if missing
                logger.info("Clicked 'Continue Shopping' button");

                // Step 5: Validate user is on Home Page
                String pageTitle = home.getPageTitle();
                logger.info("Current Page Title: " + pageTitle);
                Assert.assertTrue(pageTitle.contains("Your Store"), "User is not on Home Page");

            } catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage());
                Assert.fail("Test case failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_HP_001 *****");
        }
    }


