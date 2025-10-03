package testCases.TS_004_ProductCompare;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PC_002_ValidateProductCompareFromListViewTest extends BaseClass {

    @Test(groups = {"regression","sanity"})
    public void verifyProductCompareFromListView() {
        logger.info("***** Starting TC_PC_002_ValidateProductCompareFromListViewTest *****");

        try {
            String productName = "HP LP3065";
            SearchPage sp = new SearchPage(driver);

            // Step 1 & 2: Search for product
            sp.enterSearchKeyword(productName);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(productName),
                    "ERROR: Product '" + productName + "' not displayed in search results.");

            // Step 3: Switch to List View
            sp.clickListView();
            Assert.assertTrue(sp.isListViewActive(),
                    "ERROR: Search results are not in List View.");

            // Step 4: Validate tooltip on Compare button
            String tooltip = sp.getFirstProductCard()
                    .findElement(org.openqa.selenium.By.xpath(".//button[@data-original-title='Compare this Product']"))
                    .getAttribute("data-original-title");
            Assert.assertEquals(tooltip, "Compare this Product",
                    "Tooltip text mismatch for 'Compare this Product'.");

            // Step 5: Add product to Compare
            sp.addFirstProductToCompare();

            // Step 6: Validate success message & navigate to comparison page
            String successMsg = driver.findElement(
                            By.xpath("//div[contains(@class,'alert-success')]"))
                    .getText().trim();
            String expectedMsg = "Success: You have added " + productName + " to your product comparison!";
            Assert.assertTrue(successMsg.contains(expectedMsg),
                    "Success message mismatch. Expected: " + expectedMsg + " | Actual: " + successMsg);

// Optionally wait for message to appear (not disappear)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'alert-success')]")));

// Don't wait for it to disappear – just proceed
            sp.clickProductCompareLink();


            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            cmp.waitForComparisonTableToLoad();
            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page.");
            Assert.assertTrue(cmp.waitForProductToBeListed(productName),
                    "ERROR: Product '" + productName + "' not found in comparison table.");


        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare List View test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_002_ValidateProductCompareFromListViewTest *****");
    }

}
