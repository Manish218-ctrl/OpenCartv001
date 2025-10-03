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
            Homepage hp = new Homepage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.login(rb.getString("email"), rb.getString("password"));
            logger.info("Login successful");

            // 2. Hover "Desktops" → Click "Show All Desktops" (merged CategoryPage)
            CategoryPage cp = new CategoryPage(driver);
            cp.hoverOnDesktopsMenu();
            cp.clickShowAllDesktops();
            logger.info("Navigated to 'Show All Desktops'");

            // 3. Select "Mac" Subcategory
            cp.clickMacSubCategory();
            logger.info("Navigated to 'Mac' Subcategory page");

            // 4. Add product to Wish List (choose first product dynamically)

            String productName = cp.getDisplayedProducts().get(0)
                    .findElement(org.openqa.selenium.By.cssSelector(".caption a"))
                    .getText().trim();


            logger.info("Product found in Mac category: " + productName);




            // Dynamic Add to Wishlist
            driver.findElement(org.openqa.selenium.By.xpath(
                    "//a[normalize-space()='" + productName + "']/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'wishlist.add')]"
            )).click();

            // Validate ER-1 (Success Message)
            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            String successMsg = pdp.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have added"),
                    "ER-1 Failed: Success message not displayed");
            logger.info("Success message displayed: " + successMsg);

            // 5. Click on "wish list!" link
            pdp.clickWishListLink();

            // Validate ER-2 (Product present in My Wish List)
            WishListPage wlp = new WishListPage(driver);
            Assert.assertTrue(wlp.isProductInWishList(productName),
                    "ER-2 Failed: Product not found in Wish List");
            logger.info("Validation Passed: Product '" + productName + "' present in Wish List");

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage(), e);
            Assert.fail("Test Failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_WL_004 Add Product from Category/Subcategory to Wish List *****");
    }
}
