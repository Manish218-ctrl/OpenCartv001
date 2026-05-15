package testCases.TS_011_HeaderMenuFooterOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HMF_002_ValidateNavigationToProductDisplayPageTest
        extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_HMF_002_ValidateNavigationToProductDisplayPageTest.class
            );

    @Test
    public void validateNavigationToProductDisplayPage() {

        logger.info(
                "Test Case TC_HMF_002 Started"
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

            // Click first product
            logger.info(
                    "Clicking first product from brand page"
            );

            homepage.clickFirstBrandProduct();

            // Validate PDP navigation
            logger.info(
                    "Validating Product Display Page navigation"
            );

            Assert.assertTrue(
                    homepage.isUserNavigatedToProductDisplayPage(),
                    "User was not redirected to Product Display Page."
            );

            logger.info(
                    "Successfully navigated to Product Display Page."
            );

            logger.info(
                    "Test Case TC_HMF_002 Completed Successfully"
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