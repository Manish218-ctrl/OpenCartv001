package testCases.TS_004_ProductCompare;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

public class TC_PC_004_ValidateProductCompareFromCategoryListViewTest extends BaseClass {

    @Test(groups = {"regression", "sanity"})
    public void verifyProductCompareFromCategoryListView() {
        logger.info("***** Starting TC_PC_004_ValidateProductCompareFromCategoryListViewTest *****");

        try {
            // Step 1: Navigate to "Show All Desktops" via CategoryPage
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();
            logger.info("Navigated to Desktops category page.");

            SearchPage sp = new SearchPage(driver);

            // Step 2: Switch to List View
            sp.clickListView();
            Assert.assertTrue(sp.isListViewActive(), "ERROR: Category results are not in List View.");

            // Step 3: Get first product name dynamically
            String productName = sp.getFirstProductCard()
                    .findElement(By.xpath(".//h4/a"))
                    .getText().trim();
            logger.info("First product in category list: " + productName);

            // Step 4: Validate tooltip on Compare button for the first product
            String tooltip = sp.getFirstProductCard()
                    .findElement(By.xpath(".//button[@data-original-title='Compare this Product']"))
                    .getAttribute("data-original-title");
            Assert.assertEquals(tooltip, "Compare this Product",
                    "Tooltip text mismatch for 'Compare this Product'.");

            // Step 5: Add product to Compare
            sp.addFirstProductToCompare();

            // Step 6: Validate success message & navigate to comparison page
            String successMsg = driver.findElement(
                            By.xpath("//div[contains(@class,'alert-success')]"))
                    .getText().trim();

            Assert.assertTrue(successMsg.contains("Success: You have added"),
                    "Success message not displayed correctly.");
            Assert.assertTrue(successMsg.contains(productName),
                    "Success message does not contain correct product name.");
            Assert.assertTrue(successMsg.contains("to your product comparison!"),
                    "Success message does not confirm product comparison addition.");

            sp.clickProductCompareLink();

            // Step 7: Verify Product Comparison page
            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page.");
            Assert.assertTrue(cmp.isProductPresent(productName),
                    "ERROR: Product '" + productName + "' not found in comparison table.");

            logger.info("Product Comparison page successfully shows product: " + productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare from Category List View test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_004_ValidateProductCompareFromCategoryListViewTest *****");
    }
}
