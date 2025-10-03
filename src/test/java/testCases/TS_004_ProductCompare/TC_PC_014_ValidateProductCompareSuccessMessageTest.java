package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

public class TC_PC_014_ValidateProductCompareSuccessMessageTest extends BaseClass {

    @Test(groups = {"regression","smoke"})
    public void verifySuccessMessageAndNavigationFromPDP() {
        logger.info("***** Starting TC_PC_014_ValidateProductCompareSuccessMessageTest *****");

        try {
            String productName = "iMac";

            // Step 0: Cleanup any leftover products in comparison
            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            cmp.clearAllComparedProducts();

            // Step 1 & 2: Search for product
            SearchPage sp = new SearchPage(driver);
            sp.enterSearchKeyword(productName);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(productName),
                    "ERROR: Product '" + productName + "' not displayed in search results.");
            logger.info("Product '{}' found in search results.", productName);

            // Step 3: Click on Product → Navigate to PDP
            sp.clickFirstProductName();
            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            Assert.assertTrue(pdp.isOnProductDisplayPage(),
                    "ERROR: Did not navigate to Product Display Page.");

            // Step 4: Add to Compare
            pdp.clickCompareThisProduct();

            // Step 5: Validate Success Message
            String successMsg = pdp.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have added"),
                    "ERROR: Success message not displayed.");
            Assert.assertTrue(successMsg.contains(productName),
                    "ERROR: Success message does not contain product name.");
            logger.info("Success message validated: {}", successMsg);

            // Step 6: Click Product Name link inside success message
            pdp.clickProductNameLinkInSuccessMessage();
            Assert.assertTrue(pdp.isOnProductDisplayPage(),
                    "ERROR: Clicking Product Name link did not navigate to PDP.");
            logger.info("Successfully navigated to PDP via product link in success message.");

            // Step 7: Navigate to Product Comparison Page
            pdp.clickCompareThisProduct();  // Add again if needed
            pdp.clickProductComparisonLink();

            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page.");
            Assert.assertTrue(cmp.waitForProductToBeListed(productName),
                    "ERROR: Product not listed in comparison table.");
            Assert.assertEquals(cmp.getComparedProductCount(), 1,
                    "ERROR: Unexpected number of products in comparison.");
            logger.info("Product '{}' successfully listed in Product Comparison page.", productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare Success Message test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_014_ValidateProductCompareSuccessMessageTest *****");
    }
}
