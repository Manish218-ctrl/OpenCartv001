package testCases.TS_006_AddtoCart;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.WishListPage;
import testBase.BaseClass;

public class TC_ATC_002_ValidateAddToCartFromWishListTest extends BaseClass {

    @BeforeMethod
    public void loginPrerequisite() {
        performLogin();
    }

    @Test
    public void verifyAddToCartFromWishList() {
        logger.info("***** Starting TC_ATC_002_ValidateAddToCartFromWishListTest *****");

        try {

Thread.sleep(3000);


            // Step 1 Add product to Wish List
            String expectedProduct ="MacBook" ;
            HomePage home = new HomePage(driver);

            // Search for product
            home.searchProduct(expectedProduct);
            logger.info("Searched for product: " + expectedProduct);

            // Add product to Wish List
            home.clickAddToWishList(expectedProduct);
            logger.info("Clicked Add to Wish List for product: " + expectedProduct);

Thread.sleep(3000);
            // Validate success message
            String wishListSuccessMsg = home.getSuccessMessage();
            Assert.assertTrue(wishListSuccessMsg.contains("Success: You have added"),
                    "Success message for adding to Wish List not displayed.");


            //  Step 2: Navigate to Wish List page via success message link
            home.clickWishListLinkFromSuccessMessage();
            logger.info("Navigated to Wish List page from success message.");

            Thread.sleep(3000);
            // Step 3: Validate product exists in Wish List
           WishListPage wishListPage = new WishListPage(driver);


            // Step 4: Add product to Cart from Wish List
            wishListPage.clickAddToCartIcon(expectedProduct);


            Thread.sleep(3000);


            // Step 6: Navigate to Shopping Cart
            wishListPage.clickShoppingCartHeader();

            // Step 7: Verify product is in Shopping Cart
            String pageTitle = new BasePage(driver).getPageTitle();
            Assert.assertTrue(pageTitle.contains("Shopping Cart"),
                    "Shopping Cart page not opened.");
            Assert.assertTrue(driver.getPageSource().contains(expectedProduct),
                    "Product '" + expectedProduct + "' not found in Shopping Cart.");

            logger.info("Product successfully moved from Wish List to Shopping Cart.");

        } catch (Exception e) {
            logger.error("Test case failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }

        logger.info("***** Finished TC_ATC_002_ValidateAddToCartFromWishListTest *****");
    }
}
