package testCases.TS_012_SpecialOffers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SpecialOffersPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

public class TC_SPO_009_ValidateAddingProductForComparisonTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_SPO_009_ValidateAddingProductForComparisonTest.class);

    // Define the product names as constants for clarity and easy maintenance
    private final String PRODUCT_TO_COMPARE = "Canon EOS 5D";
    private final String EXPECTED_SUCCESS_MESSAGE_PART = "Success: You have added " + PRODUCT_TO_COMPARE + " to your product comparison!";

    @Test
    public void validateAddingProductForComparison() {
        logger.info("Starting the test: validateAddingProductForComparison");

        // Step 1: Open the application URL
        String applicationUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";
        logger.info("Opening application URL: " + applicationUrl);

        driver.get(applicationUrl);


        // Step 2: Navigate to the Special Offers page and select product
        HomePage homepage = new HomePage(driver);
        logger.info("Navigating to the Special Offers page...");
        homepage.clickFooterSpecialsLink();  // Click on 'Specials' footer link

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
        logger.info("Selecting product for comparison: " + "Canon EOS 5D");


             specialOffersPage.clickproductcomparebtn();


        // Step 3: Verify the success message
        specialOffersPage.clickproductcomparisonmsg();


        // Step 4: Open the Product Comparison page
        ProductComparisonPage productComparisonPage = new ProductComparisonPage(driver);

        logger.info("Opening Product Comparison page...");


        // Step 5: Verify that the product is added to the comparison list
        boolean isProductInComparisonList = productComparisonPage.isProductPresent("Canon EOS 5D");
        logger.info("Product found in comparison list: " + isProductInComparisonList);
        Assert.assertTrue(isProductInComparisonList, "Product '" + "Canon EOS 5D" + "' not found in the comparison list.");

        logger.info("Test completed successfully.");
    }
}