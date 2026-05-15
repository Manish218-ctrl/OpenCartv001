package testCases.TS_007_WishList;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_WL_004_ValidateAddProductFromCategoryPageToWishListTest extends BaseClass {

    @Test
    public void test_AddProductFromCategory_ToWishList() {

        logger.info("***** Starting TC_WL_004 Add Product from Category/Subcategory to Wish List *****");

        try {

            // 1. Login
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(getDriver());
            lp.login(p.getProperty("email"), p.getProperty("password"));

            logger.info("Login successful");

            // 2. Navigate to Desktops -> Show All Desktops
            CategoryPage cp = new CategoryPage(getDriver());

            cp.hoverOnDesktopsMenu();
            cp.clickShowAllDesktops();

            logger.info("Navigated to Show All Desktops");

            // 3. Navigate to Mac subcategory
            cp.clickMacSubCategory();

            logger.info("Navigated to Mac Subcategory page");

            // 4. Get first displayed product dynamically
            String productName = cp.getFirstDisplayedProductName();

            logger.info("Product found in Mac category: {}", productName);

            // 5. Add product to wishlist
            cp.addProductToWishList(productName);

            // 6. Validate success message
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            String successMsg = pdp.getSuccessMessage();

            Assert.assertTrue(
                    successMsg.contains("Success: You have added"),
                    "ER-1 Failed: Success message not displayed"
            );

            logger.info("Success message displayed: {}", successMsg);

            // 7. Navigate to wishlist
            pdp.clickWishListLink();

            // 8. Validate product exists in wishlist
            WishListPage wlp = new WishListPage(getDriver());

            Assert.assertTrue(
                    wlp.isProductInWishList(productName),
                    "ER-2 Failed: Product not found in Wish List"
            );

            logger.info(
                    "Validation Passed: Product {} present in Wish List",
                    productName
            );

        } catch (Exception e) {

            logger.error("Test Failed: {}", e.getMessage(), e);

            Assert.fail("Test Failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_WL_004 Add Product from Category/Subcategory to Wish List *****");
    }
}