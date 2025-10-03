package testCases.TS_021_ProductDisplayPage;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_PDP_011_ValidateProductDisplayPageTest extends BaseClass {

        @Test
        public void validateProductDisplayPage() {

            logger.info("=== TC_PDP_035 Started ===");

            // 1️⃣ Navigate to Homepage
            Homepage home = new Homepage(driver);

            // 2️⃣ Enter product in search box
            String productToSearch = rb.getString("productName"); // e.g., "iMac"
            logger.info("Searching for product: " + productToSearch);
            home.enterSearchText(productToSearch);
            home.clickSearchButton();

            // 3️⃣ Click on the product link from search results
            try {
                driver.findElement(
                        org.openqa.selenium.By.linkText(productToSearch)
                ).click();
            } catch (Exception e) {
                logger.error("Product not found in search results: " + productToSearch);
                Assert.fail("Product not found in search results");
            }

            // 4️⃣ Switch to Product Display Page
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            // 5️⃣ Validate Page Title
            String actualTitle = driver.getTitle();
            logger.info("Page Title: " + actualTitle);
            Assert.assertTrue(actualTitle.contains(productToSearch), "Page title does not contain product name.");

            // 6️⃣ Validate Page Heading (Product Name)
            String actualHeading = pdp.getProductName();
            logger.info("Page Heading: " + actualHeading);
            Assert.assertEquals(actualHeading, productToSearch, "Product heading mismatch.");

            // 7️⃣ Validate Page URL
            String currentURL = driver.getCurrentUrl();
            logger.info("Current Page URL: " + currentURL);
            Assert.assertTrue(currentURL.contains("product_id"), "URL does not contain 'product_id'.");

            logger.info("=== TC_PDP_035 Completed Successfully ===");
        }
    }

