package testCases.TS_005_SearchFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage; // Import the new SearchPage
import testBase.BaseClass;


public class TC_SP_001_ValidateSearchExistingProductTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void test_search_existing_product_name() {
        logger.info("Starting TC_SP_001_ValidateSearchExistingProductTest: Validate searching with an existing Product Name.");

        try {
            // 1. Open the Application URL in any supported browser (handled by BaseClass setup)

            HomePage hp = new HomePage(driver);

            SearchPage searchPage = new SearchPage(driver);

            // 1. Enter any existing product name into the 'Search' text box field - <Refer Test Data>
            String productName = p.getProperty("searchProduct");

            searchPage.enterSearchKeyword(productName); // Use method from SearchPage
            logger.info("Entered product name into search box: " + productName);

            // 2. Click on the button having search icon (Validate ER-1)
            searchPage.clickSearchButton(); // Use method from SearchPage
            logger.info("Clicked search icon button.");


            // Verify the search results heading contains the product name (e.g., "Search - MacBook")
            String searchResultsHeading = searchPage.getSearchResultsHeading();
            Assert.assertTrue(searchResultsHeading.contains(productName), "Search results heading does not contain the searched product name.");
            logger.info("Verified search results heading: '" + searchResultsHeading + "'");

            // Verify the product itself is displayed in the search results
            Assert.assertTrue(searchPage.isProductDisplayed(productName), "Searched product '" + productName + "' is not displayed in the search results.");
            logger.info("Verified that the product '" + productName + "' is displayed in the search results.");

        } catch (Exception e) {
            logger.error("Test execution failed for TC_SP_001_ValidateSearchExistingProductTest: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
            logger.info("Finished TC_SP_001_ValidateSearchExistingProductTest.");
        }
    }
}
