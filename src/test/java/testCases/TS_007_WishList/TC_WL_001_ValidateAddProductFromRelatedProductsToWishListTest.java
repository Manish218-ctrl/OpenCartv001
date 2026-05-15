package testCases.TS_007_WishList;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_WL_001_ValidateAddProductFromRelatedProductsToWishListTest extends BaseClass {

    @Test
    public void test_AddProductFromRelatedProducts_ToWishList() {
        logger.info("***** Starting TC_WL_001 Add Product from Related Products to Wish List *****");

        try {
            // 1. Login
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(getDriver());
            lp.login(p.getProperty("email"), p.getProperty("password"));

            // 2. Search Product (iMac)
            SearchPage sp = new SearchPage(getDriver());
            sp.enterSearchKeyword("iMac");
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed("iMac"), "iMac not displayed in results");

            // 3. Open product page
            sp.clickFirstProductName();

            // 4. Add first related product to Wish List (dynamic)
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());
            String relatedProduct = pdp.getFirstRelatedProductName();
            logger.info("First Related Product found: " + relatedProduct);

            pdp.addFirstRelatedProductToWishList();

            String successMsg = pdp.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have added"), "Success message not shown");
            logger.info("Validation Passed: Success message displayed");

            // 5. Click Wish List link
            pdp.clickWishListLink();

            // 6. Verify in My Wish List page
            WishListPage wlp = new WishListPage(getDriver());
            Assert.assertTrue(wlp.isProductInWishList(relatedProduct), "Product not found in wishlist");
            logger.info("Validation Passed: Product " + relatedProduct + " is in wishlist");

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage());
            Assert.fail();
        }

        logger.info("***** Finished TC_WL_001 Add Product from Related Products to Wish List *****");
    }
}
