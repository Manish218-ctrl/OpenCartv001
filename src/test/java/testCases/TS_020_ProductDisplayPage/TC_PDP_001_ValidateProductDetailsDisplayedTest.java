package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_001_ValidateProductDetailsDisplayedTest extends BaseClass {

    @Test
    public void verifyProductDetailsDisplayed() {

        logger.info("***** Starting TC_PDP_002_ProductDisplayPageTest *****");

        try {

            HomePage home = new HomePage(getDriver());
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            home.enterSearchText(productName);
            home.clickSearchButton();

            logger.info("Searched for product: {}", productName);

            home.clickProductByName(productName);

            logger.info("Clicked on product: {}", productName);

            String actualName = pdp.getProductName();

            Assert.assertEquals(actualName, productName,
                    "Product Name mismatch!");

            String actualBrand = pdp.getProductBrand();

            Assert.assertFalse(actualBrand.isEmpty(),
                    "Product Brand not displayed!");

            String actualCode = pdp.getProductCode();

            Assert.assertFalse(actualCode.isEmpty(),
                    "Product Code not displayed!");

            logger.info("Verified Product Details successfully");
            logger.info("Name: {} | Brand: {} | Code: {}",
                    actualName, actualBrand, actualCode);

        } catch (Exception e) {

            logger.error("Test failed due to exception: {}", e.getMessage(), e);

            Assert.fail("Test case failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_PDP_002_ProductDisplayPageTest *****");
    }
}