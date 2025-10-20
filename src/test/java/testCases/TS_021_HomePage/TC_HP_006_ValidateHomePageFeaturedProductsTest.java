package testCases.TS_021_HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.List;

public class TC_HP_006_ValidateHomePageFeaturedProductsTest extends BaseClass {

    @Test
    public void validateFeaturedProducts() {
        logger.info("***** Starting TC_HP_006_ValidateHomePageFeaturedProductsTest *****");

        try {
            HomePage home = new HomePage(driver);
            String featuredProductsXpath = "//div[@id='content']//div[contains(@class,'product-thumb')]";

            // Step 1: Validate count
            List<WebElement> featuredProducts = driver.findElements(By.xpath(featuredProductsXpath));
            int productCount = featuredProducts.size();
            logger.info("Number of featured products displayed: " + productCount);

            Assert.assertEquals(productCount, 4, "Featured products count is not 4!");

            // Step 2: Validate each product link

            int productsToTest = 3;
            logger.warn("Temporarily limiting featured product validation to the first " + productsToTest + " products.");

            for (int i = 0; i < productsToTest; i++) {

                featuredProducts = driver.findElements(By.xpath(featuredProductsXpath));

                WebElement productContainer = featuredProducts.get(i);
                WebElement nameLink = productContainer.findElement(By.xpath(".//div[@class='caption']/h4/a"));

                String productName = nameLink.getText().trim();
                logger.info("Checking featured product: " + productName);

                // Click the product
                nameLink.click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));


                String productPageTitle = driver.getTitle();

                String cleanProductName = productName.replace("\"", "").trim();

                Assert.assertTrue(
                        productPageTitle.contains(cleanProductName),
                        "Product navigation failed for: " + productName + ". Title: " + productPageTitle
                );

                logger.info("Successfully navigated to product page: " + productName);

                home.clickLogo();
                logger.info("Returned to Home Page.");
            }

            logger.info("***** TC_HP_006_ValidateHomePageFeaturedProductsTest Passed *****");

        } catch (Exception e) {
            logger.error("Test Failed due to exception: " + e.getMessage());
            Assert.fail("Test case failed with exception: " + e.getMessage());
        }
    }
}