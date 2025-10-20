package testCases.TS_012_SpecialOffers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SpecialOffersPage;
import pageObjects.WishListPage;
import testBase.BaseClass;

public class TC_SPO_008_ValidateSpecialOffersPageFromWishListNavigationTest extends BaseClass {

    // Initialize logger
    private static final Logger logger = LogManager.getLogger(TC_SPO_008_ValidateSpecialOffersPageFromWishListNavigationTest.class);

    @Test
    public void validateAddingProductToWishList() throws InterruptedException {
        logger.info("Starting the test: validateAddingProductToWishList");

        // Step 1: Perform login
        logger.info("Performing login...");
        performLogin();
        logger.info("User logged in successfully.");

        // Step 2: Navigate to 'Special Offers' page
        HomePage homepage = new HomePage(driver);
        logger.info("Clicking the 'Specials' footer link...");
        homepage.clickFooterWishList();  // Click the "Specials" footer link

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
        logger.info("Navigating to the 'Special Offers' page...");
        specialOffersPage.clickSpecialsLink();  // Navigate to the Special Offers page

        // Step 3: Add a product to the Wish List from 'Special Offers'
        logger.info("Verifying that special offers are displayed...");
        Assert.assertTrue(specialOffersPage.areSpecialOffersDisplayed(), "Special Offers are not displayed.");
        logger.info("Clicking the first special offer item...");
        specialOffersPage.clickFirstSpecialOffer();  // Click on the first special offer item
        logger.info("Adding product to cart...");
        specialOffersPage.clickAddToCart();  // Add product to cart
        Thread.sleep(2800);
        specialOffersPage.clickwishlisticon();

        // Step 4: Go to Wish List page and verify product is added
        WishListPage wishListPage = new WishListPage(driver);
        logger.info("Navigating to the Wish List page...");
        wishListPage.clickWishListHeader();  // Navigate to Wish List page

        // Step 5: Verify the heading on the Wish List page
        String wishListPageHeading = wishListPage.getWishListPageHeading();
        logger.info("Wish List Page Heading: " + wishListPageHeading);
        Assert.assertEquals(wishListPageHeading, "My Wish List", "Failed to navigate to the Wish List page.");





        // End of the test, clean up
        logger.info("Test completed. Tearing down...");
        tearDown();
        logger.info("Test execution finished.");
    }
}