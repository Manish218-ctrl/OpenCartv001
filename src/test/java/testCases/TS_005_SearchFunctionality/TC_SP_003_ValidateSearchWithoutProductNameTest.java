package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.SearchPage; // Import the SearchPage
import testBase.BaseClass;


    public class TC_SP_003_ValidateSearchWithoutProductNameTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_search_without_product_name() {
            logger.info("Starting TC_SP_003_ValidateSearchWithoutProductNameTest: Validate searching without providing any Product Name.");

            try {
                // 1. Open the Application URL in any supported browser (handled by BaseClass setup)

                // Instantiate HomePage (if needed for initial navigation or common header elements)
                Homepage hp = new Homepage(driver);

                // Instantiate SearchPage to use its methods for search actions and validations
                SearchPage searchPage = new SearchPage(driver);

                // 1. Don't enter anything into the 'Search' text box field
                // The search input field should be empty by default or cleared explicitly if needed.
                // Since we're not sending keys, we assume it's empty. If not, add searchPage.clearSearchKeyword();
                logger.info("Ensuring search box is empty (no product name entered).");

                // 2. Click on the button having search icon (Validate ER-1)
                searchPage.clickSearchButton(); // Use method from SearchPage to click the search button
                logger.info("Clicked search icon button without entering a product name.");

                // Expected Result: 1. There is no product that matches the search criteria should be displayed in the Search Results page
                // Verify that the "No product" message is displayed
                Assert.assertTrue(searchPage.isNoProductMessageDisplayed(), "The 'No product' message is not displayed when searching with an empty product name.");
                logger.info("Verified: 'No product' message is correctly displayed for an empty search.");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_SP_003_ValidateSearchWithoutProductNameTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SP_003_ValidateSearchWithoutProductNameTest.");
            }
        }
    }



