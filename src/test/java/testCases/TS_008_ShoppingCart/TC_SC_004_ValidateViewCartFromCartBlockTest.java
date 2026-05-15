package testCases.TS_008_ShoppingCart;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_SC_004_ValidateViewCartFromCartBlockTest extends BaseClass {

    @Test
    public void verifyViewCartFromCartBlock() {

        try {

            logger.info("***** Starting TC_SC_004_ValidateViewCartFromCartBlockTest *****");

            // Login to the application
            performLogin();
            logger.info("User logged in successfully.");

            ShoppingCartPage shoppingCartPage =
                    new ShoppingCartPage(getDriver());

            // Hover over Cart icon
            Actions action = new Actions(getDriver());

            action.moveToElement(
                    shoppingCartPage.getShoppingCartHeaderLink()
            ).perform();

            logger.info("Hovered over the Cart menu.");

            // Click View Cart
            shoppingCartPage.clickViewCartFromCartDropdown();

            logger.info("View Cart button clicked successfully.");

            // Verify Shopping Cart page
            Assert.assertTrue(
                    shoppingCartPage.isShoppingCartBreadcrumbDisplayed(),
                    "Shopping Cart page is not displayed."
            );

            logger.info("Shopping Cart page is verified successfully.");

        } catch (Exception e) {

            logger.error("Test Failed: " + e.getMessage());

            Assert.fail("Test Failed: " + e.getMessage());
        }
    }
}