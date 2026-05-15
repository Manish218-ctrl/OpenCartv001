package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.ProductComparisonPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_PC_004_ValidateProductCompareFromCategoryListViewTest extends BaseClass {

    @Test(groups = {"regression", "sanity"})
    public void verifyProductCompareFromCategoryListView() {

        logger.info("***** Starting TC_PC_004_ValidateProductCompareFromCategoryListViewTest *****");

        try {

            //Navigate to Show All Desktops
            CategoryPage categoryPage =
                    new CategoryPage(getDriver());

            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();

            logger.info(
                    "Navigated to Desktops category page"
            );

            SearchPage sp =
                    new SearchPage(getDriver());

            //Switch to List View
            sp.clickListView();

            Assert.assertTrue(
                    sp.isListViewActive(),
                    "ERROR: Category results are not in List View."
            );

            //Get first product name dynamically
            String productName =
                    sp.getFirstProductName();

            logger.info(
                    "First product in category list: {}",
                    productName
            );

            //Validate compare tooltip
            String tooltip =
                    sp.getCompareTooltipForProduct(productName);

            Assert.assertEquals(
                    tooltip,
                    "Compare this Product",
                    "Tooltip text mismatch for Compare this Product."
            );

            //Add product to compare
            sp.addProductToCompare(productName);

            //Validate success message
            String successMsg =
                    sp.getSuccessMessage();

            Assert.assertTrue(
                    successMsg.contains("Success: You have added"),
                    "Success message not displayed correctly."
            );

            Assert.assertTrue(
                    successMsg.contains(productName),
                    "Success message does not contain correct product name."
            );

            Assert.assertTrue(
                    successMsg.contains("to your product comparison!"),
                    "Success message does not confirm product comparison addition."
            );

            //Navigate to comparison page
            sp.clickProductCompareLink();

            //Verify Product Comparison page
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
                    "Exception occurred during Product Compare from Category List View test: "
                            + e.getMessage()
            );
        }

        logger.info("***** Finished TC_PC_004_ValidateProductCompareFromCategoryListViewTest *****");
    }
}