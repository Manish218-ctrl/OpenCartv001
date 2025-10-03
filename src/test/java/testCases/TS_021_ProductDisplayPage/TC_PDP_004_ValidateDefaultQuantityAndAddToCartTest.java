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

public class TC_PDP_004_ValidateDefaultQuantityAndAddToCartTest extends BaseClass {

    @Test(groups = {"Regression", "Product"})
    public void validateDefaultQuantityAndAddToCart() {
        logger.info("***** Starting TC_PDP_005_ValidateDefaultQuantity *****");

        try {
            // Step 1: Search for the product
            Homepage home = new Homepage(driver);
            home.enterSearchText("iMac");
            home.clickSearchButton();
            logger.info("Searched for product: iMac");

            // Step 2: Click on the product in search results
            WebElement productLink = driver.findElement(By.linkText("iMac"));
            productLink.click();
            logger.info("Opened Product Display Page for: iMac");

            // Step 3: Initialize Product Display Page
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            // Step 4: Validate default quantity
            WebElement qtyField = driver.findElement(By.id("input-quantity"));
            String defaultQty = qtyField.getAttribute("value");
            logger.info("Default quantity displayed: " + defaultQty);
            Assert.assertEquals(defaultQty, "1", "Default quantity should be 1");

            // Step 5: Update quantity to 2
            qtyField.clear();
            qtyField.sendKeys("2");
            logger.info("Updated quantity to 2");

            // Step 6: Click 'Add to Cart'
            pdp.clickAddToCartButton();
            logger.info("Clicked 'Add to Cart' button");

            // Step 7: Wait for and validate success message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success"))
            );

            String successMsg = successAlert.getText();
            logger.info("Success message displayed: " + successMsg);
            Assert.assertTrue(successMsg.contains("Success"), "Product was not added to cart successfully");

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage());
            Assert.fail("Test case failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_PDP_005_ValidateDefaultQuantity *****");
    }
}
