package testCases.TS_004_ProductCompare;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

    public class TC_PC_003_ValidateProductCompareFromGridViewTest extends BaseClass {

        @Test(groups = {"regression","sanity"})
        public void verifyProductCompareFromGridView() {
            logger.info("***** Starting TC_PC_003_ValidateProductCompareFromGridViewTest *****");

            try {
                String productName = "HP LP3065";
                SearchPage sp = new SearchPage(driver);

                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName),
                        "ERROR: Product '" + productName + "' not displayed in search results.");

               sp.clickgridview();

                String tooltip = sp.getFirstProductCard()
                        .findElement(org.openqa.selenium.By.xpath(".//button[@data-original-title='Compare this Product']"))
                        .getAttribute("data-original-title");
                Assert.assertEquals(tooltip, "Compare this Product",
                        "Tooltip text mismatch for 'Compare this Product'.");

                sp.addFirstProductToCompare();

                String successMsg = driver.findElement(
                                org.openqa.selenium.By.xpath("//div[contains(@class,'alert-success')]"))
                        .getText().trim();
                String expectedMsg = "Success: You have added " + productName + " to your product comparison!";
                Assert.assertTrue(successMsg.contains(expectedMsg),
                        "Success message mismatch. Expected: " + expectedMsg + " | Actual: " + successMsg);

                sp.clickProductCompareLink();

                // Step 7: Verify comparison page
                ProductComparisonPage cmp = new ProductComparisonPage(driver);
                Assert.assertTrue(cmp.isOnComparisonPage(),
                        "ERROR: Not navigated to Product Comparison page.");
                Assert.assertTrue(cmp.isProductPresent(productName),
                        "ERROR: Product '" + productName + "' not found in comparison table.");

                logger.info("Product Comparison page successfully shows product: " + productName);

            } catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage(), e);
                Assert.fail("Exception occurred during Product Compare Grid View test: " + e.getMessage());
            }

            logger.info("***** Finished TC_PC_003_ValidateProductCompareFromGridViewTest *****");
        }
    }



