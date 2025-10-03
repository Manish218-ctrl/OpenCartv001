package testCases.TS_012_HeaderMenuFooterOptions;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.List;

    public class TC_HMF_009_ValidateNumberOfProductsInBrandPageUsingShowFieldTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_009_ValidateNumberOfProductsInBrandPageUsingShowFieldTest.class);

        @Test
        public void validateNumberOfProductsInBrandPageUsingShowField() {
            logger.info("Test Case TC_HMF_014 - Validate the number of Products displayed in the 'Brand' page using 'Show' field Started");

            // Creating an instance of WebDriverWait to wait for page load and elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Timeout of 20 seconds

            try {
                // Step 1: Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize Homepage object
                Homepage homepage = new Homepage(driver);

                // Step 2: Click on the 'Brands' footer link
                logger.info("Clicking on the 'Brands' footer link");
                homepage.clickBrandsFooterLink();

                // Step 3: Click on a brand (e.g., 'Apple')
                logger.info("Clicking on the 'Apple' brand");
                homepage.clickBrandByName("Apple");

                // Step 4: Select a value from the 'Show' dropdown (e.g., '12' products per page)
                logger.info("Selecting '12' from the 'Show' dropdown");
                WebElement showDropdown = driver.findElement(By.id("input-limit")); // The 'Show' dropdown
                Select showSelect = new Select(showDropdown);
                showSelect.selectByVisibleText("20");

                // Step 5: Wait until the products are displayed after changing the 'Show' value
                logger.info("Waiting for products to be displayed...");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-layout"))); // Waiting for products to be visible

                // Step 6: Get all displayed products
                logger.info("Getting the list of displayed products");
                List<WebElement> products = driver.findElements(By.cssSelector(".product-layout"));

                // Step 7: Validate that the number of displayed products matches the selected 'Show' value
                int expectedProductCount = 10; // You can change this value based on the 'Show' dropdown selection
                Assert.assertEquals(products.size(), expectedProductCount, "The number of displayed products does not match the selected 'Show' value.");

                // Step 8: Log completion of the test case
                logger.info("Test Case TC HMF 009 Validate Number Of Products In Brand Page Using Show Field Test Completed Successfully");

            } catch (Exception e) {
                // If any error occurs, log the error and fail the test
                logger.error("Test Case TC_HMF_014 - Error occurred during validation of 'Show' field: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }




