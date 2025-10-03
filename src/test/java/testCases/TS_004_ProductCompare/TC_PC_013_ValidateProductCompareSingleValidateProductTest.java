package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

public class TC_PC_013_ValidateProductCompareSingleValidateProductTest extends BaseClass {

    @Test(groups = {"regression"})
    public void verifyProductComparisonPageWithSingleProduct() {
        logger.info("***** Starting TC_PC_013_ValidateProductCompareSingleValidateProductTest *****");

        try {
            String productName = "iMac";

            // Step 0: Cleanup any leftover products
            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            cmp.clearAllComparedProducts();

            // Step 1 & 2: Search for product
            SearchPage sp = new SearchPage(driver);
            sp.enterSearchKeyword(productName);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(productName),
                    "ERROR: Product '" + productName + "' not displayed in search results.");

            // Step 3: Click product → PDP
            sp.clickFirstProductName();
            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            Assert.assertTrue(pdp.isOnProductDisplayPage(),
                    "ERROR: Did not navigate to Product Display Page.");

            // Step 4: Add product to compare
            pdp.clickCompareThisProduct();

            // Step 5: Click "Product Comparison" link
            pdp.clickProductComparisonLink();

            // Step 6: Validate only one product listed
            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page.");
            Assert.assertTrue(cmp.waitForProductToBeListed(productName),
                    "ERROR: Expected product not listed in comparison table.");
            Assert.assertEquals(cmp.getComparedProductCount(), 1,
                    "ERROR: More than one product is displayed in comparison.");
            logger.info("Product '{}' displayed correctly in comparison page.", productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Single Product Compare test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_013_ValidateProductCompareSingleValidateProductTest *****");
    }
}
