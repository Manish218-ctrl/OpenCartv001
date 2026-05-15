package testCases.TS_011_HeaderMenuFooterOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HMF_012_ValidateProductCompareLinkTest extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(TC_HMF_012_ValidateProductCompareLinkTest.class);

    @Test
    public void validateProductCompareLink() {

        logger.info("Test Case TC_HMF_012 - Validate Product Compare link in the Brand page Started");

        try {

            HomePage homepage = new HomePage(getDriver());

            logger.info("Opening the application URL: {}", p.getProperty("appURL"));

            homepage.clickBrandsFooterLink();

            logger.info("Clicking on the Apple brand");

            homepage.clickBrandByName("Apple");

            logger.info("Clicking on Product Compare link");

            homepage.clickProductCompareLink();

            logger.info("Validating that the user is redirected to Product Comparison page");

            String currentUrl = homepage.getCurrentPageURL();

            Assert.assertTrue(currentUrl.contains("product/compare"),
                    "User was not redirected to the Product Comparison page.");

            logger.info("Test Case TC_HMF_012 - Validate Product Compare link in the Brand page Completed Successfully");

        } catch (Exception e) {

            logger.error("Test Case TC_HMF_012 - Error occurred during Product Compare link validation: {}",
                    e.getMessage(), e);

            Assert.fail("Test failed due to an error: " + e.getMessage());
        }
    }
}