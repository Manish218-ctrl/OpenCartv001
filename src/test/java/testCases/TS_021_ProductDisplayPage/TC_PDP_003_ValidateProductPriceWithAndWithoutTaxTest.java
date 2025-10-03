package testCases.TS_021_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_003_ValidateProductPriceWithAndWithoutTaxTest extends BaseClass {

    @Test(groups = {"Regression", "Product"})
    public void verifyProductPriceWithAndWithoutTax() {
        logger.info("***** Starting TC_PDP_004_ValidatePrice *****");

        try {
            // Step 1: Search for Product
            Homepage home = new Homepage(driver);
            home.enterSearchText(productName);
            home.clickSearchButton();
            logger.info("Searched for product: " + productName);

            // Step 2: Click on Product from Search Results
            driver.findElement(org.openqa.selenium.By.linkText(productName)).click();
            logger.info("Opened Product Display Page for: " + productName);

            // Step 3: Initialize PDP
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            // Step 4: Get prices
            String priceWithTax = pdp.getPriceWithTax();
            String priceExTax = pdp.getPriceExTax();

            logger.info("Price With Tax: " + priceWithTax);
            logger.info("Price Ex Tax: " + priceExTax);

            // Step 5: Assertions
            Assert.assertTrue(priceWithTax.contains("$"),
                    "Price with tax is not displayed correctly!");
            Assert.assertTrue(priceExTax.toLowerCase().contains("ex tax"),
                    "Price without tax (Ex Tax) is not displayed!");

            logger.info("Price validation successful for product: " + productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage());
            Assert.fail("Test Case failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_PDP_004_ValidatePrice *****");
    }
}
