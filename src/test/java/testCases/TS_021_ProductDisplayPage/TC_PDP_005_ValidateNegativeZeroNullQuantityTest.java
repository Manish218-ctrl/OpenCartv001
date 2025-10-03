package testCases.TS_021_ProductDisplayPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PDP_005_ValidateNegativeZeroNullQuantityTest extends BaseClass {

    private final String productName = "HP LP3065"; // Product to search

    @Test
    public void TC_PDP_006_ValidateNegativeZeroNullQuantity() {
        logger.info("==== TC_PDP_006: Validate negative, zero, or null quantity on PDP ====");

        try {
            // Step 1: Navigate to OpenCart Demo
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            logger.info("Navigated to OpenCart demo site");

            // Step 2: Search for product
            Homepage homePage = new Homepage(driver);
            homePage.enterSearchText(productName);
            homePage.clickSearchButton();
            logger.info("Searched for product: " + productName);

            // Step 3: Click on product from search results
            driver.findElement(By.linkText(productName)).click();
            logger.info("Clicked on product link: " + productName);

            // Step 4: Verify Product Display Page
            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            Assert.assertTrue(pdp.isOnProductDisplayPage(), "Not on Product Display Page");
            logger.info("On Product Display Page successfully");

            // Step 5: Locate quantity field
            WebElement qtyField = driver.findElement(By.id("input-quantity"));
            logger.info("Quantity field located");

            // Step 6: Validate quantities dynamically
            validateQuantity(qtyField, pdp, "-5", "negative");
            validateQuantity(qtyField, pdp, "0", "zero");
            validateQuantity(qtyField, pdp, "", "null/empty");

            logger.info("==== TC_PDP_006 Test Completed Successfully ====");

        } catch (Exception e) {
            logger.error("Test case failed", e);
            Assert.fail("Test case execution failed: " + e.getMessage());
        }
    }

    private void validateQuantity(WebElement qtyField, ProductDisplayPage pdp, String qty, String type) {
        logger.info("Validating " + type + " quantity: " + (qty.isEmpty() ? "empty" : qty));

        try {
            // Clear and enter quantity
            qtyField.clear();
            logger.info("Cleared quantity field");
            if (!qty.isEmpty()) {
                qtyField.sendKeys(qty);
                logger.info("Entered quantity: " + qty);
            } else {
                logger.info("Quantity is left empty");
            }

            // Click Add to Cart
            pdp.clickAddToCartButton();
            logger.info("Clicked 'Add to Cart' button");

            // Wait for error message dynamically
            By[] errorLocators = new By[]{
                    By.xpath("//div[contains(@class,'text-danger')]"),
                    By.xpath("//span[contains(@class,'text-danger')]"),
                    By.xpath("//p[contains(@class,'text-danger')]"),
                    By.cssSelector(".alert.alert-danger"),          // OpenCart alert box
                    By.cssSelector(".modal-dialog .text-danger")   // If error inside modal
            };

            WebElement msgElement = null;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            for (By locator : errorLocators) {
                try {
                    msgElement = wait
                            .pollingEvery(Duration.ofMillis(500))
                            .ignoring(org.openqa.selenium.NoSuchElementException.class)
                            .until(ExpectedConditions.visibilityOfElementLocated(locator));
                    if (msgElement != null) {
                        logger.info("Validation message found using locator: " + locator);
                        // Scroll into view
                        ((org.openqa.selenium.JavascriptExecutor) driver)
                                .executeScript("arguments[0].scrollIntoView(true);", msgElement);
                        break;
                    }
                } catch (Exception ignored) {
                    logger.info("Validation message not found with locator: " + locator);
                }
            }

            if (msgElement == null) {
                logger.error("No validation message displayed for " + type + " quantity");
                Assert.fail("No validation message displayed for " + type + " quantity");
            }

            String msg = msgElement.getText().trim();
            logger.info("Validation message displayed: " + msg);

            Assert.assertTrue(
                    msg.contains("Quantity should be a positive number") ||
                            msg.contains("Quantity cannot be zero, null or negative") ||
                            msg.contains("Warning:"),
                    "Validation message not displayed correctly for " + type + " quantity"
            );

            logger.info(type.substring(0, 1).toUpperCase() + type.substring(1) +
                    " quantity validation passed");

        } catch (Exception e) {
            logger.error("Failed validation for " + type + " quantity", e);
            Assert.fail("Failed validation for " + type + " quantity: " + e.getMessage());
        }
    }
}
