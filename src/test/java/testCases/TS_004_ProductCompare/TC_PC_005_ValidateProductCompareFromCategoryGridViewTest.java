package testCases.TS_004_ProductCompare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; // Required for WebElement declaration
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PC_005_ValidateProductCompareFromCategoryGridViewTest extends BaseClass {

    @Test(groups = {"regression", "sanity"})
    public void verifyProductCompareFromCategoryGridView() {
        logger.info("***** Starting TC_PC_005_ValidateProductCompareFromCategoryGridViewTest *****");

        try {
            // Step 1: Navigate to "Show All Desktops" via CategoryPage
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();
            logger.info("Navigated to Desktops category page.");

            SearchPage sp = new SearchPage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Step 2: Verify that the current view is the intended Grid View
            Assert.assertTrue(sp.isGridViewActive(), "ERROR: Category results are not in Grid View.");
            logger.info("Verified that the Category results are in Grid View (default view).");


            // Step 3: Get first product name dynamically

            // 1. Get the first product card, which includes a wait for visibility.
            WebElement firstProductCard = sp.getFirstProductCard();
            logger.info("First Product Card is visible.");

            // 2. SCROLL TO THE TARGET CARD: Ensure the card is in the viewport.
            sp.scrollHover(firstProductCard);
            logger.info("Scrolled to make the first product card visible.");

            // 3. Wait for the nested product name link to be visible within the card.
            WebElement productNameLink = wait.until(ExpectedConditions.visibilityOf(
                    firstProductCard.findElement(By.xpath(".//h4/a")) // Use the correctly initialized variable
            ));

            String productName = productNameLink.getText().trim();
            logger.info("First product in category grid view: " + productName);

            // Step 4: Validate tooltip on Compare button
            WebElement compareButton = wait.until(ExpectedConditions.visibilityOf(
                    firstProductCard.findElement(By.xpath(".//button[@data-original-title='Compare this Product']"))
            ));

            String tooltip = compareButton.getAttribute("data-original-title");
            Assert.assertEquals(tooltip, "Compare this Product",
                    "Tooltip text mismatch for 'Compare this Product'.");
            logger.info("Validated tooltip: " + tooltip);

            // Step 5: Add product to Compare
            sp.addFirstProductToCompare();
            logger.info("Added product to compare.");


            // Step 6: Validate success message (use explicit wait for the success alert)
            By successAlertLocator = By.xpath("//div[contains(@class,'alert-success')]");
            String successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successAlertLocator))
                    .getText().trim();
            logger.info("Success message received: " + successMsg);


            Assert.assertTrue(successMsg.contains("Success: You have added"),
                    "Success message not displayed correctly.");
            Assert.assertTrue(successMsg.contains(productName),
                    "Success message does not contain correct product name.");
            Assert.assertTrue(successMsg.contains("to your product comparison!"),
                    "Success message does not confirm product comparison addition.");

            // Step 7: Click on Product Comparison link
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("product comparison"))).click();
            logger.info("Clicked on Product Comparison link from success alert.");


            // Step 8: Verify Product Comparison page
            ProductComparisonPage cmp = new ProductComparisonPage(driver);
            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Not navigated to Product Comparison page.");
            Assert.assertTrue(cmp.isProductPresent(productName),
                    "ERROR: Product '" + productName + "' not found in comparison table.");

            logger.info("Product Comparison page successfully shows product: " + productName);

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare from Category Grid View test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_005_ValidateProductCompareFromCategoryGridViewTest *****");
    }
}