package testCases.TS_006_AddtoCart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_ATC_004_ValidateAddProductToCartFromCategoryPageTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void testAddToCartFromCategoryPage() {
        logger.info("Starting TC_ATC_004_ValidateAddProductToCartFromCategoryPageTest - Add product to cart from category page.");

        String productName = "iMac"; // Product to add to cart

        try {
            HomePage hp = new HomePage(driver);
            CategoryPage categoryPage = new CategoryPage(driver);

            // Step 1: Open Application URL
            logger.info("Application URL opened.");

            // Step 2: Hover on 'Desktops' menu
            logger.info("Hovering over 'Desktops' menu.");
            categoryPage.hoverOnDesktopsMenu();

            // Step 3: Click 'Show All Desktops'
            logger.info("Clicking 'Show All Desktops'.");
            categoryPage.clickShowAllDesktops();

            // Step 4: Select 'Mac' subcategory
            logger.info("Clicking 'Mac' subcategory.");
            categoryPage.clickMacSubCategory();

            // Step 5: Add product (iMac) to cart
            logger.info("Adding product '" + productName + "' to cart.");
            categoryPage.addProductToCart(productName);

            String expectedSuccessMessagePart = "Success: You have added " + productName + " to your shopping cart!";
            String actualSuccessMessage = categoryPage.getSuccessMessage();
            logger.info("Actual success message: " + actualSuccessMessage);

            Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessagePart),
                    "ER-1: Success message not displayed or incorrect.");
            logger.info("ER-1: Success message for '" + productName + "' displayed correctly.");

            // Step 6: Click 'shopping cart' link in success message
            logger.info("Clicking 'shopping cart' link.");
            ShoppingCartPage scp = categoryPage.clickShoppingCartLinkInSuccessMessage();

            logger.info("Verifying Shopping Cart page.");
            Assert.assertTrue(scp.isOnShoppingCartPage(), "User is not on Shopping Cart page.");

            logger.info("Verifying product '" + productName + "' is in Shopping Cart.");
            Assert.assertTrue(scp.isProductDisplayedInCart(productName),
                    "ER-2: Product not displayed in Shopping Cart.");
            logger.info("ER-2: Product '" + productName + "' displayed in Shopping Cart.");

        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Exception: " + e.getMessage());
        }

        logger.info("Finished TC_ATC_004_ValidateAddProductToCartFromCategoryPageTest.");
    }
}
