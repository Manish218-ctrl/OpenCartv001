package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_003_ValidateProductPriceWithAndWithoutTaxTest extends BaseClass {

    @Test(groups = {"Regression", "Product"})
    public void verifyProductPriceWithAndWithoutTax() {

        logger.info("***** Starting TC_PDP_004_ValidatePrice *****");

        try {

            HomePage home = new HomePage(getDriver());
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            home.enterSearchText(productName);
            home.clickSearchButton();

            logger.info("Searched for product: {}", productName);

            home.clickProductByName(productName);

            logger.info("Opened Product Display Page for: {}", productName);

            String priceWithTax = pdp.getPriceWithTax();
            String priceExTax = pdp.getPriceExTax();

            logger.info("Price With Tax: {}", priceWithTax);
            logger.info("Price Ex Tax: {}", priceExTax);

            Assert.assertTrue(priceWithTax.contains("$"),
                    "Price with tax is not displayed correctly!");

            Assert.assertTrue(priceExTax.toLowerCase().contains("ex tax"),
                    "Price without tax (Ex Tax) is not displayed!");

            logger.info("Price validation successful for product: {}", productName);

        } catch (Exception e) {

            logger.error("Test Failed due to Exception: {}", e.getMessage(), e);

            Assert.fail("Test Case failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_PDP_004_ValidatePrice *****");
    }
}