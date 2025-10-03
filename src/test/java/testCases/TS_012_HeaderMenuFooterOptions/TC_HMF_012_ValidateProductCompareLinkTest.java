package testCases.TS_012_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_HMF_012_ValidateProductCompareLinkTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_012_ValidateProductCompareLinkTest.class);

        @Test
        public void validateProductCompareLink() {
            logger.info("Test Case TC_HMF_012 - Validate 'Product Compare' link in the 'Brand' page Started");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Timeout of 20 seconds
            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize Homepage object
                Homepage homepage = new Homepage(driver);

                // Step 1: Click on the 'Brands' footer link
                logger.info("Clicking on the 'Brands' footer link");
                homepage.clickBrandsFooterLink();

                // Step 2: Click on a brand (e.g., 'Apple')
                logger.info("Clicking on the 'Apple' brand");
                homepage.clickBrandByName("Apple");

                // Step 3: Click on the 'Product Compare' link in the brand page
                logger.info("Clicking on 'Product Compare' link");
                WebElement productCompareLink = driver.findElement(By.xpath("//*[@id=\"compare-total\"]"));
                wait.until(ExpectedConditions.elementToBeClickable(productCompareLink));
                productCompareLink.click();

                // Step 4: Validate that user is redirected to the 'Product Comparison' page
                logger.info("Validating that the user is redirected to 'Product Comparison' page");
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("product/compare"), "User was not redirected to the 'Product Comparison' page.");

                logger.info("Test Case TC_HMF_012 - Validate 'Product Compare' link in the 'Brand' page Completed Successfully");

            } catch (Exception e) {
                logger.error("Test Case TC_HMF_012 - Error occurred during 'Product Compare' link validation: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



