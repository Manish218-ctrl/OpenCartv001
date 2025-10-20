package testCases.TS_004_ProductCompare;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

    public class TC_PC_006_ValidateProductCompareFromRelatedProductsTest extends BaseClass {

        @Test(groups = {"regression", "sanity"})
        public void verifyProductCompareFromRelatedProducts() {
            logger.info("***** Starting TC_PC_006_ValidateProductCompareFromRelatedProductsTest *****");

            try {
                String productName = "iMac";

                // Step 1 & 2: Search for product
                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName),
                        "ERROR: Product '" + productName + "' not displayed in search results.");

                // Step 3: Click on product in search results -> navigate to PDP
                sp.clickFirstProductName();

                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                Assert.assertTrue(pdp.isOnProductDisplayPage(),
                        "ERROR: Not navigated to Product Display Page.");

                // Step 4: Hover on Compare this Product button (validate tooltip)
                pdp.hoverOnCompareButton();
                Assert.assertTrue(pdp.isCompareTooltipDisplayed(),
                        "ERROR: Tooltip 'Compare this Product' not displayed.");

                // Step 5: Click Compare this Product
                pdp.clickCompareThisProduct();

                // Step 6: Validate success message
                String successMsg = pdp.getSuccessMessage();
                String expectedMsg = "Success: You have added " + productName + " to your product comparison!";
                Assert.assertTrue(successMsg.contains(expectedMsg),
                        "ERROR: Success message mismatch. Expected: " + expectedMsg + " | Actual: " + successMsg);

                // Step 7: Click on Product Comparison link
                pdp.clickProductComparisonLink();

                // Step 8: Verify Product Comparison page
                ProductComparisonPage cmp = new ProductComparisonPage(driver);
                Assert.assertTrue(cmp.isOnComparisonPage(),
                        "ERROR: Not navigated to Product Comparison page.");
                Assert.assertTrue(cmp.isProductPresent(productName),
                        "ERROR: Product '" + productName + "' not found in comparison table.");

                logger.info("Product Comparison page successfully shows product: " + productName);

            } catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage(), e);
                Assert.fail("Exception occurred during Product Compare from Related Products test: " + e.getMessage());
            }

            logger.info("***** Finished TC_PC_006_ValidateProductCompareFromRelatedProductsTest *****");
        }
    }



