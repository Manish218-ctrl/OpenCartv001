package testCases.TS_012_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_HMF_010_ValidateBrandPageInListViewTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_010_ValidateBrandPageInListViewTest.class);

        @Test
        public void validateViewingProductsInListView() {
            logger.info("Test Case TC_HMF_010 - Validate viewing products in 'Brand' page in List view Started");

            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize the Homepage object
                Homepage homepage = new Homepage(driver);

                // Step 1: Click on the 'Brands' footer link
                logger.info("Clicking on the 'Brands' footer link");
                homepage.clickBrandsFooterLink();

                // Step 2: Click on a brand (example brand: 'Apple')
                logger.info("Clicking on the 'Apple' brand");
                homepage.clickBrandByName("Apple");

                // Step 3: Select the 'List' view for the brand page
                logger.info("Selecting 'List' view option");
                homepage.selectListView();

                // Step 4: Validate that the products are displayed in List view
                // This can be done by checking for elements that are only visible in the list view
                logger.info("Validating that products are displayed in List view");
                WebElement firstProduct = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div/div[2]/div[1]/h4/a"));
                Assert.assertTrue(firstProduct.isDisplayed(), "The product is not displayed in List view.");

                logger.info("Test Case TC_HMF_010 - Validate viewing products in 'Brand' page in List view Completed Successfully");

            } catch (Exception e) {
                logger.error("Test Case TC_HMF_010 - Error occurred during validation of 'Brand' page in List view: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



