package testCases.TS_011_HeaderMenuFooterOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HMF_009_ValidateNumberOfProductsInBrandPageUsingShowFieldTest extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC_HMF_009_ValidateNumberOfProductsInBrandPageUsingShowFieldTest.class);

    @Test
    public void validateNumberOfProductsInBrandPageUsingShowField() {

        logger.info("Test Case TC_HMF_014 - Validate the number of Products displayed in the Brand page using Show field Started");

        try {

            HomePage homepage = new HomePage(getDriver());

            logger.info("Opening the application URL: {}", p.getProperty("appURL"));

            homepage.clickBrandsFooterLink();

            logger.info("Clicking on the Apple brand");

            homepage.clickBrandByName("Apple");

            logger.info("Selecting 20 from the Show dropdown");

            homepage.selectShowLimit("20");

            logger.info("Getting displayed products count");

            int actualProductCount = homepage.getDisplayedProductsCount();

            int expectedProductCount = 10;

            Assert.assertEquals(actualProductCount, expectedProductCount,
                    "The number of displayed products does not match the selected Show value.");

            logger.info("Displayed Product Count: {}", actualProductCount);

            logger.info("Test Case TC_HMF_009 Validate Number Of Products In Brand Page Using Show Field Test Completed Successfully");

        } catch (Exception e) {

            logger.error("Test Case TC_HMF_014 - Error occurred during validation of Show field: {}",
                    e.getMessage(), e);

            Assert.fail("Test failed due to an error: " + e.getMessage());
        }
    }
}