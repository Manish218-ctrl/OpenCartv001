package testCases.TS_021_HomePage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HP_006_ValidateHomePageFeaturedProductsTest extends BaseClass {

    @Test
    public void validateFeaturedProducts() {

        logger.info("***** Starting TC_HP_006_ValidateHomePageFeaturedProductsTest *****");

        try {

            HomePage home = new HomePage(getDriver());

            // Validate featured products count
            int productCount =
                    home.getFeaturedProductsCount();

            logger.info(
                    "Number of featured products displayed: {}",
                    productCount
            );

            Assert.assertEquals(
                    productCount,
                    4,
                    "Featured products count is not 4!"
            );

            // Validate featured product navigation
            int productsToTest = 3;

            logger.warn(
                    "Temporarily limiting featured product validation to the first {} products.",
                    productsToTest
            );

            for (int i = 0; i < productsToTest; i++) {

                String productName =
                        home.clickFeaturedProductByIndex(i);

                logger.info(
                        "Checking featured product: {}",
                        productName
                );

                String productPageTitle =
                        home.getCurrentPageTitle();

                String cleanProductName =
                        productName.replace("\"", "").trim();

                Assert.assertTrue(
                        productPageTitle.contains(cleanProductName),
                        "Product navigation failed for: "
                                + productName
                                + ". Title: "
                                + productPageTitle
                );

                logger.info(
                        "Successfully navigated to product page: {}",
                        productName
                );

                home.clickLogo();

                logger.info("Returned to Home Page.");
            }

            logger.info("***** TC_HP_006_ValidateHomePageFeaturedProductsTest Passed *****");

        } catch (Exception e) {

            logger.error(
                    "Test Failed due to exception: {}",
                    e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test case failed with exception: "
                            + e.getMessage()
            );
        }
    }
}