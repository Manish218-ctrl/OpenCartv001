package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductComparisonPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_PC_003_ValidateProductCompareFromGridViewTest extends BaseClass {

    @Test(groups = {"regression","sanity"})
    public void verifyProductCompareFromGridView() {

        logger.info("***** Starting TC_PC_003_ValidateProductCompareFromGridViewTest *****");

        try {

            String productName = "HP LP3065";

            SearchPage sp = new SearchPage(getDriver());

            sp.enterSearchKeyword(productName);
            sp.clickSearchButton();

            Assert.assertTrue(
                    sp.isProductDisplayed(productName),
                    "ERROR: Product " + productName + " not displayed in search results."
            );

            sp.clickgridview();

            String tooltip = sp.getCompareTooltipText(productName);

            Assert.assertEquals(
                    tooltip,
                    "Compare this Product",
                    "Tooltip text mismatch for Compare this Product."
            );

            sp.addProductToCompare(productName);

            String successMsg = sp.getSuccessMessage();

            String expectedMsg =
                    "Success: You have added " +
                            productName +
                            " to your product comparison!";

            Assert.assertTrue(
                    successMsg.contains(expectedMsg),
                    "Success message mismatch. Expected: "
                            + expectedMsg +
                            " | Actual: "
                            + successMsg
            );

            sp.clickProductCompareLink();

            //Verify comparison page
            ProductComparisonPage cmp =
                    new ProductComparisonPage(getDriver());

            Assert.assertTrue(
                    cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page."
            );

            Assert.assertTrue(
                    cmp.isProductPresent(productName),
                    "ERROR: Product " + productName + " not found in comparison table."
            );

            logger.info(
                    "Product Comparison page successfully shows product: {}",
                    productName
            );

        } catch (Exception e) {

            logger.error(
                    "Test Failed due to Exception: {}",
                    e.getMessage(),
                    e
            );

            Assert.fail(
                    "Exception occurred during Product Compare Grid View test: "
                            + e.getMessage()
            );
        }

        logger.info("***** Finished TC_PC_003_ValidateProductCompareFromGridViewTest *****");
    }
}