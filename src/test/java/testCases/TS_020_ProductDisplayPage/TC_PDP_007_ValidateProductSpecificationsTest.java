package testCases.TS_020_ProductDisplayPage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_PDP_007_ValidateProductSpecificationsTest extends BaseClass {

        @Test
        public void validateProductSpecifications() {
            logger.info("======= Starting TC_PDP_009 =======");

            // Step 1: Navigate to HomePage
            HomePage home = new HomePage(driver);

            // Step 2: Enter existing product name in search box
            home.enterSearchText(productName);
            logger.info("Entered product name in search box: " + productName);

            // Step 3: Click Search button
            home.clickSearchButton();
            logger.info("Clicked on Search button");

            // Step 4: Click on product displayed in search results
            WebElement productLink = driver.findElement(By.linkText(productName));
            productLink.click();
            logger.info("Clicked on product link: " + productName);

            // Initialize Product Display Page
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            // Step 5: Click on the Specification tab (assuming it's same as Description for this example)
            pdp.clickDescriptionTab(); // Modify if you have a separate 'Specifications' tab
            logger.info("Clicked on Product Specification tab");

            // Step 6: Validate specifications
            String actualProductName = pdp.getProductName();
            String actualBrand = pdp.getProductBrand();
            String actualProductCode = pdp.getProductCode();
            String actualAvailability = pdp.getProductAvailability();
            String actualPriceWithTax = pdp.getPriceWithTax();
            String actualPriceExTax = pdp.getPriceExTax();

            // Assertions
            Assert.assertEquals(actualProductName, productName, "Product Name mismatch!");
            Assert.assertNotNull(actualBrand, "Brand should not be null");
            Assert.assertNotNull(actualProductCode, "Product Code should not be null");
            Assert.assertTrue(actualAvailability.equalsIgnoreCase("In Stock") || actualAvailability.equalsIgnoreCase("Out Of Stock"),
                    "Invalid Availability value");
            Assert.assertNotNull(actualPriceWithTax, "Price with tax should be displayed");
            Assert.assertNotNull(actualPriceExTax, "Price ex-tax should be displayed");

            logger.info("Product specifications validated successfully for: " + productName);
            logger.info("======= TC_PDP_009 Completed =======");
        }
    }

