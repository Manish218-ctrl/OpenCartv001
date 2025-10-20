package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductComparisonPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

import java.util.Set;

public class TC_PC_002_ValidateProductCompareFromListViewTest extends BaseClass {

    // Test Data
    private static final String ProductName = "HP LP3065";
    private static final String EXPECTED_COMPARE_TOOLTIP = "Compare this Product";


    @Test
    public void verifyProductCompareFromListView() {
        logger.info("***** Starting TC_PC_002_ValidateProductCompareFromListViewTest *****");

        try {
            HomePage hp = new HomePage(driver);
            SearchPage sp = new SearchPage(driver);
            ProductComparisonPage cmp = new ProductComparisonPage(driver);


            cmp.clearAllComparedProducts();
            logger.info("Cleared all previously compared products.");
            hp.enterProductNameInSearch( ProductName);
            logger.info("Entered search keyword: '" +  ProductName + "'");
            hp.clickSearchButton();
            logger.info("Clicked global search icon.");
            sp.clickListView();
            logger.info("Clicked List View button.");
            String actualTooltip = sp.getCompareTooltipForFirstProduct();
            Assert.assertEquals(actualTooltip, EXPECTED_COMPARE_TOOLTIP, "ERROR: Tooltip mismatch for 'Compare this Product'.");
            logger.info("Verified tooltip: " + actualTooltip);
            sp.clickCompareProductForFirstProduct();
            logger.info("Clicked 'Compare this Product' for the first product.");
            String successMsg = sp.getSuccessMessage();
            Assert.assertTrue(successMsg.contains("Success: You have added " +  ProductName), "ERROR: Success message not displayed or product name missing.");
            logger.info("Success message verified: " + successMsg);
            String originalWindow = driver.getWindowHandle();
            sp.clickProductComparisonLinkFromSuccessMessage();
            logger.info("Clicked 'Product Compare' link from search results.");
            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String winHandle : allWindowHandles) {
                if (!originalWindow.contentEquals(winHandle)) {
                    driver.switchTo().window(winHandle);
                    break;
                }
            }
            logger.info("Switched to window/tab: " + driver.getTitle());

            cmp = new ProductComparisonPage(driver);

            Assert.assertTrue(cmp.isOnComparisonPage(), "ERROR: Did not land on Product Comparison page.");
            logger.info("Verified product comparison page loaded.");

            boolean isProductInTable = cmp.waitForProductToBeListed(ProductName);

            if (isProductInTable) {
                logger.info("Assertion Passed: Product '" +  ProductName + "' successfully found in comparison table.");
            } else {
                logger.error("ERROR: Product '" +  ProductName + "' not found in comparison table.");
                Assert.fail("ERROR: Product '" +  ProductName + "' not found in comparison table.");
            }

        } catch (Exception e) {
            logger.error("Test Failed: An exception occurred during execution: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
            logger.info("***** Finished TC_PC_002_ValidateProductCompareFromListViewTest *****");
        }
    }
}