package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FeaturedSectionPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

public class TC_PC_007_ValidateProductCompareFromFeaturedTest extends BaseClass {

    @Test(groups = {"regression", "sanity"})
    public void verifyProductCompareFromFeaturedSection() {
        logger.info("***** Starting TC_PC_007_ValidateProductCompareFromFeaturedTest *****");

        try {
            FeaturedSectionPage featured = new FeaturedSectionPage(driver);

            // Step 1: Get featured product name
            String productName = featured.getFirstFeaturedProductName();
            logger.info("Featured product selected: " + productName);

            // Step 2: Hover on Compare button and validate tooltip
            featured.hoverOnCompareButton();
            Assert.assertTrue(featured.isCompareTooltipDisplayed(),
                    "ERROR: Tooltip 'Compare this Product' not displayed.");

            // Step 3: Click Compare button
            featured.clickCompareButton();

            // Step 4: Validate success message
            String successMsg = featured.getSuccessMessage();
            String expectedMsg = "Success: You have added " + productName + " to your product comparison!";
            Assert.assertTrue(successMsg.contains(expectedMsg),
                    "ERROR: Success message mismatch. Expected: " + expectedMsg + " | Actual: " + successMsg);

            // Step 5: Click Product Comparison link
            featured.clickProductComparisonLink();

            // Step 6: Verify Product Comparison page
            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page.");
            Assert.assertTrue(cmp.isProductPresent(productName),
                    "ERROR: Product '" + productName + "' not found in comparison table.");

            logger.info("Product Comparison page successfully shows featured product: " + productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare from Featured section test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_007_ValidateProductCompareFromFeaturedTest *****");
    }
}
