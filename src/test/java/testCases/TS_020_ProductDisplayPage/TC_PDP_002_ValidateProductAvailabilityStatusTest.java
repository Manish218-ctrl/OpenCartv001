package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_002_ValidateProductAvailabilityStatusTest extends BaseClass {

    @Test
    public void verifyProductAvailabilityStatus() {

        logger.info("***** Starting TC_PDP_003_ProductAvailabilityTest *****");

        try {

            HomePage home =
                    new HomePage(getDriver());

            home.enterSearchText(productName);

            logger.info(
                    "Entered product name: {}",
                    productName
            );

            home.clickSearchButton();

            logger.info(
                    "Clicked on search button."
            );

            home.clickProductByName(productName);

            logger.info(
                    "Clicked on product link: {}",
                    productName
            );

            ProductDisplayPage pdp =
                    new ProductDisplayPage(getDriver());

            String availabilityStatus =
                    pdp.getProductAvailability();

            logger.info(
                    "Availability status displayed: {}",
                    availabilityStatus
            );

            boolean isValidStatus =
                    availabilityStatus.equalsIgnoreCase("In Stock")
                            || availabilityStatus.equalsIgnoreCase("Out Of Stock")
                            || availabilityStatus.equalsIgnoreCase("Limited Stock");

            Assert.assertTrue(
                    isValidStatus,
                    "Invalid availability status! Found: "
                            + availabilityStatus
            );

            logger.info(
                    "Product availability status validation passed."
            );

        } catch (Exception e) {

            logger.error(
                    "Test failed due to exception: {}",
                    e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test case failed due to exception."
            );
        }

        logger.info("***** Finished TC_PDP_003_ProductAvailabilityTest *****");
    }
}