package testCases.TS_022_HomePage;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;
import pageObjects.Homepage;

import java.util.List;

    public class TC_HP_006_ValidateHomePageFeaturedProductsTest extends BaseClass {

        @Test
        public void validateFeaturedProducts() {
            logger.info("***** Starting TC_HP_006_ValidateHomePageFeaturedProductsTest *****");

            try {
                Homepage home = new Homepage(driver);

                // Validate that 4 products are displayed
                List<WebElement> featuredProducts = driver.findElements(By.xpath("//div[@id='content']//div[contains(@class,'product-thumb')]"));
                int productCount = featuredProducts.size();
                logger.info("Number of featured products displayed: " + productCount);
                Assert.assertEquals(productCount, 4, "Featured products count is not 4!");

                // Validate that each product link is clickable and working
                for (int i = 0; i < productCount; i++) {
                    // Re-locate products after each navigation to avoid stale elements
                    featuredProducts = driver.findElements(By.xpath("//div[@id='content']//div[contains(@class,'product-thumb')]"));

                    WebElement product = featuredProducts.get(i).findElement(By.tagName("a"));
                    String productName = product.getText().trim();

                    logger.info("Checking featured product: " + productName);

                    // Click the product
                    product.click();

                    // Validate navigation (breadcrumb or product title)
                    String breadcrumb = home.getBreadcrumb();
                    Assert.assertTrue(
                            breadcrumb.contains(productName) || driver.getTitle().contains(productName),
                            "Product navigation not working for: " + productName
                    );

                    logger.info("Successfully navigated to product page: " + productName);

                    // Go back to Home page for next product check
                    home.clickLogo();
                }

                logger.info("***** TC_HP_006_ValidateHomePageFeaturedProductsTest Passed *****");

            } catch (Exception e) {
                logger.error("Test Failed due to exception: " + e.getMessage());
                Assert.fail("Test case failed with exception: " + e.getMessage());
            }
        }
    }