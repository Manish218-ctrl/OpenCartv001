package testCases.TS_021_ProductDisplayPage;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

    public class TC_PDP_002_ValidateProductAvailabilityStatusTest extends BaseClass {

        @Test
        public void verifyProductAvailabilityStatus() {
            logger.info("***** Starting TC_PDP_003_ProductAvailabilityTest *****");

            try {
                // Step 1: Navigate to homepage is already handled by BaseClass setup()

                Homepage home = new Homepage(driver);

                // Step 2: Enter Product name in search box
                home.enterSearchText(productName); // productName comes from config.properties (iMac)
                logger.info("Entered product name: " + productName);

                // Step 3: Click search button
                home.clickSearchButton();
                logger.info("Clicked on search button.");

                // Step 4: Click on the product in results
                driver.findElement(org.openqa.selenium.By.linkText(productName)).click();
                logger.info("Clicked on product link: " + productName);

                // Step 5: On Product Display Page, check availability status
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                String availabilityStatus = pdp.getProductAvailability();
                logger.info("Availability status displayed: " + availabilityStatus);

                // Step 6: Validate status is one of the expected values
                boolean isValidStatus = availabilityStatus.equalsIgnoreCase("In Stock")
                        || availabilityStatus.equalsIgnoreCase("Out Of Stock")
                        || availabilityStatus.equalsIgnoreCase("Limited Stock");

                Assert.assertTrue(isValidStatus,

                        "Invalid availability status! Found: " + availabilityStatus);

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test case failed due to exception.");
            }

            logger.info("***** Finished TC_PDP_003_ProductAvailabilityTest *****");
        }
    }


