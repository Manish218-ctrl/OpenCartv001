package testCases.TS_022_HomePage;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_HP_001_ValidateNavigateFromCartToHomeTest extends BaseClass {

        @Test(groups = {"Regression", "HomePage"})
        public void navigateFromCartToHome() {
            logger.info("***** Starting TC_HP_001: Navigate from Shopping Cart to Home Page *****");

            try {
                Homepage home = new Homepage(driver);

                // Step 1: Search for product
                String product = productName; // "iMac" from config
                home.enterSearchText(product);
                home.clickSearchButton();
                logger.info("Searched for product: " + product);

                // Step 2: Click 'Add to Cart' button
                home.clickAddToCart(product);
                home.clickAddToCart1(productName);


                logger.info("Clicked 'Add to Cart' button for: " + product);

                // Step 3: Click 'shopping cart!' link in success message
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                pdp.clickShoppingCartLinkInSuccessMessage();
                logger.info("Clicked 'shopping cart!' link in success message");

                // Step 4: Click 'Continue Shopping' button to go back to home page
                home.clickContinueShopping(); // You can create this method in Homepage if missing
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


