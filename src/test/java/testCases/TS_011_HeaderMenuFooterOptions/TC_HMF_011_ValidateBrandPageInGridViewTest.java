package testCases.TS_011_HeaderMenuFooterOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HMF_011_ValidateBrandPageInGridViewTest
        extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_HMF_011_ValidateBrandPageInGridViewTest.class
            );

    @Test
    public void validateViewingProductsInGridView() {

        logger.info(
                "Test Case TC_HMF_011 Started"
        );

        try {

            // Open application
            logger.info(
                    "Opening application URL: "
                            + p.getProperty("appURL")
            );

            getDriver().get(
                    p.getProperty("appURL")
            );

            // Initialize HomePage
            HomePage homepage =
                    new HomePage(getDriver());

            // Click Brands footer link
            logger.info(
                    "Clicking Brands footer link"
            );

            homepage.clickBrandsFooterLink();

            // Click Apple brand
            logger.info(
                    "Clicking Apple brand"
            );

            homepage.clickBrandByName("Apple");

            // Select Grid view
            logger.info(
                    "Selecting Grid View"
            );

            homepage.selectGridView();

            // Validate products displayed
            logger.info(
                    "Validating products are displayed in Grid View"
            );

            Assert.assertTrue(
                    homepage.isBrandProductDisplayed(),
                    "Products are not displayed in Grid View."
            );

            logger.info(
                    "Products displayed successfully in Grid View."
            );

            logger.info(
                    "Test Case TC_HMF_011 Completed Successfully"
            );

        } catch (Exception e) {

            logger.error(
                    "Error during Grid View validation: "
                            + e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}