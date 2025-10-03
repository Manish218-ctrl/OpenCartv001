package testCases.TS_021_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_013_ValidateBulkPricesTest extends BaseClass {

    @Test
    public void validateBulkPrices() {
        logger.info("Starting TC_PDP_015: Validate Bulk Prices");

        // 1. Login
        performLogin();
        logger.info("User logged in successfully.");

        // 2. Go to Homepage and search product
        Homepage home = new Homepage(driver);
        home.enterSearchText(productName);
        home.clickSearchButton();
        logger.info("Searched for product: " + productName);

        // 3. Click product from search results (instead of success message link)
        ProductDisplayPage pdp = new ProductDisplayPage(driver);
        pdp.clickProductFromSearchResults(productName);
        logger.info("Navigated to Product Display Page: " + productName);

        // 4. Verify we are on the PDP
        Assert.assertTrue(pdp.isOnProductDisplayPage(), "Product Display Page not opened!");
        logger.info("Confirmed Product Display Page is opened.");

        // 5. Validate bulk prices for different quantities
        int[] quantities = {10, 20, 30};
        for (int qty : quantities) {
            String price = pdp.getBulkPrice(qty);
            logger.info("Bulk Price for " + qty + " units: " + price);
            Assert.assertNotNull(price, "Bulk Price not displayed for quantity: " + qty);
        }

        logger.info("Bulk prices validated successfully for all quantities.");
    }
}
