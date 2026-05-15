package testCases.TS_011_HeaderMenuFooterOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HMF_010_ValidateBrandPageInListViewTest extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_HMF_010_ValidateBrandPageInListViewTest.class
            );

    @Test
    public void validateViewingProductsInListView() {

        logger.info(
                "Test Case TC_HMF_010 Started"
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

            // Select List View
            logger.info(
                    "Selecting List View"
            );

            homepage.selectListView();

            // Validate product displayed
            logger.info(
                    "Validating products displayed in List View"
            );

            Assert.assertTrue(
                    homepage.isBrandProductDisplayed(),
                    "Product is not displayed in List View."
            );

            logger.info(
                    "Products displayed successfully in List View."
            );

            logger.info(
                    "Test Case TC_HMF_010 Completed Successfully"
            );

        } catch (Exception e) {

            logger.error(
                    "Error during test execution: "
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