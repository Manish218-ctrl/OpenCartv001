package testCases.TS_021_ProductDisplayPage;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_PDP_001_ValidateProductDetailsDisplayedTest extends BaseClass {

        @Test
        public void verifyProductDetailsDisplayed() {
            logger.info("***** Starting TC_PDP_002_ProductDisplayPageTest *****");

            try {
                // Step 1: Navigate to Homepage
                Homepage home = new Homepage(driver);

                // Step 2: Enter product name into search box
                home.enterSearchText(productName);   // productName comes from config.properties
                home.clickSearchButton();
                logger.info("Searched for product: " + productName);

                // Step 3: Click on the product from search results
                driver.findElement(
                        org.openqa.selenium.By.linkText(productName)
                ).click();
                logger.info("Clicked on product: " + productName);

                // Step 4: Initialize ProductDisplayPage
                ProductDisplayPage pdp = new ProductDisplayPage(driver);

                // Step 5: Verify Product Name
                String actualName = pdp.getProductName();
                Assert.assertEquals(actualName, productName, "Product Name mismatch!");

                // Step 6: Verify Product Brand is displayed
                String actualBrand = pdp.getProductBrand();
                Assert.assertFalse(actualBrand.isEmpty(), "Product Brand not displayed!");

                // Step 7: Verify Product Code is displayed
                String actualCode = pdp.getProductCode();
                Assert.assertFalse(actualCode.isEmpty(), "Product Code not displayed!");

                logger.info("Verified Product Details successfully:");
                logger.info("Name: " + actualName + " | Brand: " + actualBrand + " | Code: " + actualCode);

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test case failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_PDP_002_ProductDisplayPageTest *****");
        }
    }



