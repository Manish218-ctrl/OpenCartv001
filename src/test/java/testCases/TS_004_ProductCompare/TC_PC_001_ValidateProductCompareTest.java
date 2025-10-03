package testCases.TS_004_ProductCompare;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_PC_001_ValidateProductCompareTest extends BaseClass {

        @Test(groups = {"regression","sanity"})
        public void verifyProductCompareFeature() {
            logger.info("***** Starting TC_PC_001_ValidateProductCompareTest *****");

            try {
                // --- Step 1 & 2: Search product ---
                SearchPage sp = new SearchPage(driver);
                String productName = "iMac";
                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName),
                        "ERROR: Product '" + productName + "' not displayed in search results.");

                // --- Step 3: Open product display page ---
                sp.clickFirstProductName();
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                Assert.assertTrue(pdp.isOnProductDisplayPage(),
                        "ERROR: Not navigated to Product Display Page.");

                // --- Step 4: Hover & validate tooltip ---
                pdp.hoverOnCompareButton();
                Assert.assertTrue(pdp.isCompareTooltipDisplayed(),
                        "Tooltip 'Compare this Product' not displayed.");

                // --- Step 4: Select 'Compare this Product' ---
                pdp.clickCompareThisProduct();
                String actualSuccessMsg = pdp.getSuccessMessage();
                String expectedSuccessMsg = "Success: You have added " + productName + " to your product comparison!";
                Assert.assertTrue(actualSuccessMsg.contains(expectedSuccessMsg),
                        "Success message mismatch. Expected: " + expectedSuccessMsg + " | Actual: " + actualSuccessMsg);

                // --- Step 5: Click 'Product Comparison' link ---
                pdp.clickProductComparisonLink();

                // --- Step 6: Validate Compare Page ---
                Assert.assertTrue(sp.isOnProductComparePage(),
                        "ERROR: Not navigated to Product Comparison page.");
                logger.info("Product Comparison page opened successfully for product: " + productName);

            } catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage(), e);
                Assert.fail("Exception occurred during Product Compare test: " + e.getMessage());
            }

            logger.info("***** Finished TC_PC_001_ValidateProductCompareTest *****");
        }
    }



