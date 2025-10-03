package testCases.TS_021_ProductDisplayPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PDP_012_ValidateProductDisplayPageOptionsTest extends BaseClass {

    @Test
    public void validateProductDisplayPageOptions() {
        logger.info("=== TC_PDP_034: Product Display Page Test Started ===");

        try {
            // Step 1: Login
            performLogin();
            logger.info("Login successful");

            // Step 2: Search for the product
            Homepage home = new Homepage(driver);
            home.enterSearchText(productName); // From config.properties
            home.clickSearchButton();
            logger.info("Searched for product: " + productName);

            // Step 3: Click the product from search results
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            By productLinkLocator = By.xpath("//a[normalize-space(text())='" + productName + "']");
            WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(productLinkLocator));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
                logger.info("Clicked product link using Selenium click: " + productName);
            } catch (Exception e) {
                logger.warn("Standard click failed, attempting JavaScript click for: " + productName);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);
                logger.info("Clicked product link using JavaScript click: " + productName);
            }

            // Step 4: Validate Product Display Page
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            // Verify Product Name
            String actualProductName = pdp.getProductName();
            Assert.assertEquals(actualProductName, productName, "Product Name does not match!");
            logger.info("Product Name validated: " + actualProductName);

            // Verify Product Brand
            String productBrand = pdp.getProductBrand();
            logger.info("Product Brand: " + productBrand);

            // Verify Product Code
            String productCode = pdp.getProductCode();
            logger.info("Product Code: " + productCode);

            // Verify Availability
            String availability = pdp.getProductAvailability();
            logger.info("Availability: " + availability);
            Assert.assertTrue(
                    availability.equalsIgnoreCase("In Stock") || availability.equalsIgnoreCase("Out Of Stock"),
                    "Product availability not displayed correctly!"
            );

            // Verify Price
            String priceWithTax = pdp.getPriceWithTax();
            String priceExTax = pdp.getPriceExTax();
            logger.info("Price With Tax: " + priceWithTax);
            logger.info("Price Ex Tax: " + priceExTax);

            // Verify Description tab
            pdp.clickDescriptionTab();
            String description = pdp.getProductDescription();
            Assert.assertFalse(description.isEmpty(), "Product description is empty!");
            logger.info("Product Description validated");

            // Verify Add to Cart button presence
            pdp.clickAddToCartButton();
            logger.info("Clicked Add to Cart button successfully");

            // Verify Add to Wish List
            pdp.clickAddToWishListButton();
            logger.info("Clicked Add to Wish List button successfully");

            logger.info("=== TC_PDP_034: Product Display Page Test Completed ===");

        } catch (Exception e) {
            logger.error("Test case failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
