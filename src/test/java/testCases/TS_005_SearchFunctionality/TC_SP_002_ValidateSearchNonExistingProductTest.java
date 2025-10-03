package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.SearchPage; // Import the SearchPage
import testBase.BaseClass;


    public class TC_SP_002_ValidateSearchNonExistingProductTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_search_non_existing_product_name() {
            logger.info("Starting TC_SP_002_ValidateSearchNonExistingProductTest: Validate searching with a non-existing Product Name.");

            try {
                // 1. Open the Application URL in any supported browser (handled by BaseClass setup)

                // Instantiate HomePage (if needed for initial navigation or common header elements)
                Homepage hp = new Homepage(driver);

                // Instantiate SearchPage to use its methods for search actions and validations
                SearchPage searchPage = new SearchPage(driver);

                // 1. Enter non-existing product name into the 'Search' text box field - <Refer Test Data>
                // Assuming 'nonExistingSearchProduct' in config.properties
                String nonExistingProductName = p.getProperty("nonExistingSearchProduct");

                searchPage.enterSearchKeyword(nonExistingProductName); // Use method from SearchPage
                logger.info("Entered non-existing product name into search box: " + nonExistingProductName);

                // 2. Click on the button having search icon (Validate ER-1)
                searchPage.clickSearchButton(); // Use method from SearchPage
                logger.info("Clicked search icon button.");

                // 1. There is no product that matches the search criteria should be displayed in the Search Results page
                // Verify that the "No product" message is displayed
                Assert.assertTrue(searchPage.isNoProductMessageDisplayed(), "The 'No product' message is not displayed for a non-existing product search.");
                logger.info("Verified: 'No product' message is displayed for non-existing product search.");

                // Optionally, verify that no products are actually displayed (i.e., productResultCards list is empty or size 0)
                // This requires adding a method to SearchPage, e.g., getProductResultCardsCount() or isEmptyResults().
                // For now, relying on isNoProductMessageDisplayed() as per the test case.

            } catch (Exception e) {
                logger.error("Test execution failed for TC_SP_002_ValidateSearchNonExistingProductTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SP_002_ValidateSearchNonExistingProductTest.");
            }
        }
    }



