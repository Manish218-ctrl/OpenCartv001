package testCases.TS_012_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

import java.time.Duration;

    public class TC_HMF_002_ValidateNavigationToProductDisplayPageTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_002_ValidateNavigationToProductDisplayPageTest.class);

        @Test
        public void validateNavigationToProductDisplayPage() {
            logger.info("Test Case TC_HMF_002 - Validate User is navigating to Product Display Page from 'Brand' page Started");

            // Create WebDriverWait instance to manage waits
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

                // Step 4: Click on a product thumbnail or product name
                logger.info("Clicking on a product thumbnail or product name");
                WebElement productThumbnail = driver.findElement(By.xpath("//div[@class='product-thumb']//a")); // Assuming the first product thumbnail
                wait.until(ExpectedConditions.elementToBeClickable(productThumbnail));
                productThumbnail.click();

                // Step 5: Wait for the Product Display Page to load
                logger.info("Waiting for the Product Display Page to load");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div[1]/div[1]/ul[2]/li[1]/a")));

                // Step 6: Validate that the user is on the Product Display Page
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("product_id"), "User was not redirected to the 'Product Display Page'. The current URL is: " + currentUrl);
                logger.info("Test Case TC_HMF_002 - Validate User is navigating to Product Display Page from 'Brand' page Completed Successfully");

            } catch (Exception e) {
                // Log and fail the test case if there is an error
                logger.error("Test Case TC_HMF_002 - Error occurred during validation of navigation to Product Display Page: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



