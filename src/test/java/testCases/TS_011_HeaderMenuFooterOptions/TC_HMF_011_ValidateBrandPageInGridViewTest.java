package testCases.TS_011_HeaderMenuFooterOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HMF_011_ValidateBrandPageInGridViewTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_HMF_011_ValidateBrandPageInGridViewTest.class);

    @Test
    public void validateViewingProductsInGridView() {
        logger.info("Test Case TC_HMF_011 - Validate viewing products in 'Brand' page in Grid view Started");

        try {
            // Open the application URL
            logger.info("Opening the application URL: " + rb.getString("appURL"));
            driver.get(rb.getString("appURL"));


            HomePage homepage = new HomePage(driver);

            // Step 1: Click on the 'Brands' footer link
            logger.info("Clicking on the 'Brands' footer link");
            homepage.clickBrandsFooterLink();

            // Step 2: Click on a brand (example brand: 'Apple')
            logger.info("Clicking on the 'Apple' brand");
            homepage.clickBrandByName("Apple");

            // Step 3: Select the 'Grid' view for the brand page
            logger.info("Selecting 'Grid' view option");
            homepage.selectGridView();

            // Step 4: Validate that the products are displayed in Grid view

            logger.info("Validating that products are displayed in Grid view");
            WebElement firstProduct = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div/div[2]/div[1]/h4/a"));
            Assert.assertTrue(firstProduct.isDisplayed(), "The product is not displayed in Grid view.");

            logger.info("Test Case TC_HMF_011 - Validate viewing products in 'Brand' page in Grid view Completed Successfully");

        } catch (Exception e) {
            logger.error("Test Case TC_HMF_011 - Error occurred during validation of 'Brand' page in Grid view: " + e.getMessage());
            Assert.fail("Test failed due to an error: " + e.getMessage());
        }
    }
}
