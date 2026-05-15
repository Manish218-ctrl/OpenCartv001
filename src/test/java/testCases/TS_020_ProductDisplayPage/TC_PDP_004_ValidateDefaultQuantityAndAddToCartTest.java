package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_004_ValidateDefaultQuantityAndAddToCartTest extends BaseClass {

    @Test(groups = {"Regression", "Product"})
    public void validateDefaultQuantityAndAddToCart() {

        logger.info(
                "***** Starting TC_PDP_004_ValidateDefaultQuantityAndAddToCartTest *****"
        );

        try {

            // Search for product
            HomePage home =
                    new HomePage(getDriver());

            home.enterSearchText("iMac");

            home.clickSearchButton();

            logger.info("Searched for product: iMac");

            // Product Display Page
            ProductDisplayPage pdp =
                    new ProductDisplayPage(getDriver());

            // Open product from search results
            pdp.clickProductFromSearchResults("iMac");

            logger.info(
                    "Opened Product Display Page for: iMac"
            );

            // Validate default quantity
            String defaultQty =
                    pdp.getProductQuantity();

            logger.info(
                    "Default quantity displayed: "
                            + defaultQty
            );

            Assert.assertEquals(
                    defaultQty,
                    "1",
                    "Default quantity should be 1"
            );

            // Update quantity
            pdp.updateProductQuantity("2");

            logger.info(
                    "Updated quantity to 2"
            );

            // Add to cart
            pdp.clickAddToCartButton();

            logger.info(
                    "Clicked Add to Cart button"
            );

            // Validate success message
            Assert.assertTrue(
                    pdp.isSuccessMessageDisplayed(),
                    "Success message is not displayed."
            );

            Assert.assertTrue(
                    pdp.getSuccessMessage()
                            .contains("Success: You have added iMac to your shopping cart!"),
                    "Incorrect success message: "
                            + pdp.getSuccessMessage()
            );

            logger.info(
                    "Product added to cart successfully."
            );

        } catch (Exception e) {

            logger.error(
                    "Test Failed due to Exception: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test case failed: "
                            + e.getMessage()
            );
        }

        logger.info(
                "***** Finished TC_PDP_004_ValidateDefaultQuantityAndAddToCartTest *****"
        );
    }
}