package testCases.TS_020_ProductDisplayPage;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_PDP_006_ValidateProductDescriptionTest extends BaseClass {

        @Test
        public void validateProductDescription() {
            try {
                logger.info("==== TC_PDP_008: Product Description Validation Started ====");

                //  Navigate to Application
                driver.get(appURL);
                logger.info("Navigated to URL: " + appURL);

                //  Search for product
                HomePage homePage = new HomePage(driver);
                homePage.enterSearchText("iMac");   // or use productName variable
                homePage.clickSearchButton();
                logger.info("Searched for product: iMac");

                //  Click on product in search results
                driver.findElement(By.linkText("iMac")).click();
                logger.info("Clicked on product link: iMac");

                ProductDisplayPage pdp = new ProductDisplayPage(driver);

                // Validate we are on PDP
                Assert.assertTrue(pdp.isOnProductDisplayPage(), "Not on Product Display Page");
                logger.info("On Product Display Page successfully");

                //  Click Description tab
                pdp.clickDescriptionTab();
                logger.info("Clicked on Description tab");

                //  Validate product description
                String description = pdp.getProductDescription();
                logger.info("Product Description displayed: " + description);

                Assert.assertTrue(!description.isEmpty(), "Product description is empty!");
                logger.info("Product description validation passed");

                logger.info("==== TC_PDP_008: Product Description Validation Completed ====");

            } catch (Exception e) {
                logger.error("Error in TC_PDP_008: " + e.getMessage(), e);
                Assert.fail("Test case TC_PDP_008 failed: " + e.getMessage());
            }
        }
    }

