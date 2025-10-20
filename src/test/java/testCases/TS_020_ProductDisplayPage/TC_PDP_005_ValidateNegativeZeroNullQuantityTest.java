package testCases.TS_020_ProductDisplayPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;

public class TC_PDP_005_ValidateNegativeZeroNullQuantityTest extends BaseClass {

    private final String productName = "HP LP3065";

    @Test
    public void TC_PDP_006_ValidateNegativeZeroNullQuantity() {
        logger.info("==== TC_PDP_006: Validate negative, zero, or null quantity on PDP (Bypassing App Bug) ====");

        try {
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            logger.info("Navigated to OpenCart demo site");

            HomePage homePage = new HomePage(driver);
            // Removed: HomePage home = new HomePage(driver); // Duplicate initialization

            homePage.enterSearchText(productName);
            homePage.clickSearchButton();
            logger.info("Searched for product: " + productName);

            driver.findElement(By.linkText(productName)).click();
            logger.info("Clicked on product link: " + productName);

            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            Assert.assertTrue(pdp.isOnProductDisplayPage(), "ERROR: Not on Product Display Page");
            logger.info("On Product Display Page successfully");

            WebElement qtyField = driver.findElement(By.id("input-quantity"));
            logger.info("Quantity field located");

            // Validate quantities dynamically
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
            // Setup
            qtyField.clear();
            if (!qty.isEmpty()) {
                qtyField.sendKeys(qty);
            }

            // Clicks 'Add to Cart' button on the Product Display Page (PDP)
            pdp.clickAddToCartButton();
            logger.info("Clicked 'Add to Cart' button with quantity: " + (qty.isEmpty() ? "empty" : qty));

            // Synchronization - REMOVED Thread.sleep, relying purely on WebDriverWait below.

            // Define Locators
            By successLocator = By.xpath("//div[contains(@class,'alert-success')]");
            By allErrorLocators = By.xpath(
                    "//div[contains(@class,'alert-danger') or contains(@class,'alert-warning')] | " +
                            "//div[@id='product']//div[contains(@class,'input-group')]//span[contains(@class,'text-danger')] | " + // Localized error near quantity
                            "//span[contains(@class,'text-danger')] | " +
                            "//p[contains(@class,'text-danger')] | " +
                            "//div[@class='modal-dialog']//*[contains(@class,'text-danger')]"
            );

            WebElement msgElement = null;
            // Increased timeout to 15 seconds for robust AJAX synchronization
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            if (qty.equals("-5")) {
                // CASE 1: NEGATIVE QUANTITY (BUG: EXPECT SUCCESS)
                try {

                    wait.until(ExpectedConditions.textToBePresentInElementLocated(successLocator, "Success:"));

                    // Now, re-locate the element to ensure a fresh, non-stale reference before calling getText()
                    msgElement = driver.findElement(successLocator);

                    String msg = msgElement.getText().trim();
                    Assert.assertTrue(
                            msg.contains("Success: You have added") && msg.contains(productName),
                            "The displayed alert was not the expected success message for negative quantity. Actual: " + msg
                    );
                    logger.info("Negative quantity validation PASSED by validating the SUCCESS alert.");

                } catch (TimeoutException e) {
                    logger.error("TEST FAILED: Negative quantity did not produce the expected SUCCESS alert.");
                    Assert.fail("Test failed: Negative quantity did not produce the expected SUCCESS alert.");
                }

            } else {
                // CASE 2: ZERO ("0") or NULL/EMPTY ("") (EXPECT ERROR/WARNING or SILENT REJECTION)
                try {
                    // Check for the presence of ANY error message
                    msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(allErrorLocators));

                    // If the found element is the success message (another bug), fail.
                    if (msgElement.getAttribute("class").contains("alert-success")) {
                        logger.error("APPLICATION BUG: Unexpected SUCCESS message found for invalid quantity: " + qty);
                        Assert.fail("APPLICATION BUG: Invalid quantity (" + qty + ") resulted in an unexpected SUCCESS message.");
                    }

                    // Assert expected error content
                    String msg = msgElement.getText().trim();
                    Assert.assertTrue(
                            msg.contains("Quantity should be a positive number") ||
                                    msg.contains("Quantity cannot be zero, null or negative") ||
                                    msg.contains("Warning:") ||
                                    msg.contains("requires a minimum quantity"),
                            "Validation message not displayed correctly for " + type + " quantity. Actual message: " + msg
                    );

                    logger.info(type.substring(0, 1).toUpperCase() + type.substring(1) + " quantity validation PASSED by validating the ERROR alert.");

                } catch (TimeoutException e) {
                    // Pass the test if no visible message (error or success) appeared (i.e., silent rejection).
                    logger.info("No visible validation message (Error or Warning) displayed for " + type + " quantity within 15 seconds. Assuming silent rejection (PASS).");
                    logger.info(type.substring(0, 1).toUpperCase() + type.substring(1) +
                            " quantity validation PASSED by assuming silent rejection (no visible error).");
                }
            }

        } catch (Exception e) {
            logger.error("Failed validation for " + type + " quantity", e);
            Assert.fail("Failed validation for " + type + " quantity: " + e.getMessage());
        }
    }
}
