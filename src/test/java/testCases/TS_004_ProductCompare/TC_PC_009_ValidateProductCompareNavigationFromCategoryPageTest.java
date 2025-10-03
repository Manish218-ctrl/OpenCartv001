package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

public class TC_PC_009_ValidateProductCompareNavigationFromCategoryPageTest extends BaseClass {

    @Test(groups = {"regression","sanity"})
    public void verifyNavigationToProductCompareFromCategoryPage() {
        logger.info("***** Starting TC_PC_009_ValidateProductCompareNavigationFromCategoryPageTest *****");

        try {
            // Step 1: Navigate to "Show All Desktops" via CategoryPage
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();
            logger.info("Navigated to Desktops category page.");

            // Step 2: Click Product Compare link from Category/Search results page
            SearchPage sp = new SearchPage(driver);
            sp.clickProductCompareLink();

            // Step 3: Verify navigation to Product Comparison page
            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page from Category page.");

            logger.info("Successfully navigated to Product Comparison page from Category page.");

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare navigation from Category Page test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_009_ValidateProductCompareNavigationFromCategoryPageTest *****");
    }
}
