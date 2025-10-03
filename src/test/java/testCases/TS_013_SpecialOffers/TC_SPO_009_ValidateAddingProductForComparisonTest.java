package testCases.TS_013_SpecialOffers;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.SpecialOffersPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

    public class TC_SPO_009_ValidateAddingProductForComparisonTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_SPO_009_ValidateAddingProductForComparisonTest.class);

        @Test
        public void validateAddingProductForComparison() {
            logger.info("Starting the test: validateAddingProductForComparison");

            // Step 1: Open the application URL
            String applicationUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";
            logger.info("Opening application URL: " + applicationUrl);
            driver.get(applicationUrl);

            // Step 2: Navigate to the Special Offers page
            Homepage homepage = new Homepage(driver);
            logger.info("Navigating to the Special Offers page...");
            homepage.clickFooterSpecialsLink();  // Click on 'Specials' footer link

            SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
            logger.info("Selecting product for comparison...");
            specialOffersPage.selectProductForComparison("Apple Cinema 30\""); // Select the product for comparison

            // Step 3: Verify the success message
            String successMessage = specialOffersPage.getSuccessMessage();
            logger.info("Success message received: " + successMessage);
            Assert.assertTrue(successMessage.contains("Success: You have added Apple Cinema 30\" to your product comparison!"),
                    "Success message not displayed correctly.");

            // Step 4: Open the Product Comparison page
            ProductComparisonPage productComparisonPage = new ProductComparisonPage(driver);
            logger.info("Opening Product Comparison page...");

            // Step 5: Verify that the product is added to the comparison list
            boolean isProductInComparisonList = productComparisonPage.isProductPresent("Apple Cinema 30\"");
            logger.info("Product found in comparison list: " + isProductInComparisonList);
            Assert.assertTrue(isProductInComparisonList, "Product not found in the comparison list.");

            logger.info("Test completed successfully.");
        }
    }