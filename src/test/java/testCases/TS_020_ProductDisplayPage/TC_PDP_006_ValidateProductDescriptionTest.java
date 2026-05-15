package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_006_ValidateProductDescriptionTest extends BaseClass {

    @Test
    public void validateProductDescription() {

        try {

            logger.info("==== TC_PDP_008: Product Description Validation Started ====");

            HomePage homePage = new HomePage(getDriver());
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            String productName = "iMac";

            homePage.enterSearchText(productName);
            homePage.clickSearchButton();

            logger.info("Searched for product: {}", productName);

            homePage.clickProductByName(productName);

            logger.info("Clicked on product link: {}", productName);

            Assert.assertTrue(pdp.isOnProductDisplayPage(),
                    "Not on Product Display Page");

            logger.info("On Product Display Page successfully");

            pdp.clickDescriptionTab();

            logger.info("Clicked on Description tab");

            String description = pdp.getProductDescription();

            logger.info("Product Description displayed: {}", description);

            Assert.assertFalse(description.isEmpty(),
                    "Product description is empty!");

            logger.info("Product description validation passed");

            logger.info("==== TC_PDP_008: Product Description Validation Completed ====");

        } catch (Exception e) {

            logger.error("Error in TC_PDP_008: {}", e.getMessage(), e);

            Assert.fail("Test case TC_PDP_008 failed: " + e.getMessage());
        }
    }
}