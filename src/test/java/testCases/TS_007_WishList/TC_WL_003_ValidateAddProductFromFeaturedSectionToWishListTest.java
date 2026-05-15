package testCases.TS_007_WishList;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_WL_003_ValidateAddProductFromFeaturedSectionToWishListTest extends BaseClass {

    @Test
    public void test_AddProductFromFeaturedSection_ToWishList() {
        logger.info("***** Starting TC_WL_003 Add Product from Featured Section to Wish List *****");

        try {
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(getDriver());
            lp.login(p.getProperty("email"), p.getProperty("password"));
            logger.info("Login successful");

            hp.clickLogo();
            logger.info("Clicked on Store logo navigated to Home page");
            Assert.assertTrue(getDriver().getTitle().contains("Your Store"),
                    "ER-1 Failed: Not navigated to Home page");

            FeaturedSectionPage fsp = new FeaturedSectionPage(getDriver());
            String featuredProduct = fsp.getFirstFeaturedProductName();
            logger.info("First Featured Product: " + featuredProduct);

            hp.clickFirstFeaturedProductWishListBtn();

            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());
            String successMsg = pdp.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have added"),
                    "ER-2 Failed: Success message not displayed");
            logger.info("Success message displayed: " + successMsg);

            pdp.clickWishListLink();

            WishListPage wlp = new WishListPage(getDriver());
            Assert.assertTrue(wlp.isProductInWishList(featuredProduct),
                    "ER-3 Failed: Product not found in Wish List");
            logger.info("Validation Passed: Product " + featuredProduct + " present in Wish List");

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage());
            Assert.fail();
        }

        logger.info("***** Finished TC_WL_003 Add Product from Featured Section to Wish List *****");
    }
}