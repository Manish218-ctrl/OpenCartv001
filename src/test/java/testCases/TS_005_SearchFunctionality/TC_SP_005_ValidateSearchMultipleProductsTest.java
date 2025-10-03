package testCases.TS_005_SearchFunctionality;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.SearchPage; // Import the SearchPage
import testBase.BaseClass;
import java.util.List; // Import for List

    public class TC_SP_005_ValidateSearchMultipleProductsTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_search_multiple_products() {
            logger.info("Starting TC_SP_005_ValidateSearchMultipleProductsTest: Validate searching with a search criteria resulting in multiple products.");

            try {
                // 1. Open the Application URL in any supported browser (handled by BaseClass setup)

                // Instantiate HomePage (if needed for initial navigation or common header elements)
                Homepage hp = new Homepage(driver);

                // Instantiate SearchPage to use its methods for search actions and validations
                SearchPage searchPage = new SearchPage(driver);

                // 1. Enter the search criteria in the 'Search' text box field which can result in multiple products - <Refer Test Data>
                // Assuming 'multiProductSearchKeyword' in config.properties, e.g., "Mac" or "Apple"
                String searchKeyword = p.getProperty("multiProductSearchKeyword");

                searchPage.enterSearchKeyword(searchKeyword);
                logger.info("Entered search keyword for multiple products: " + searchKeyword);

                // 2. Click on the button having search icon (Validate ER-1)
                searchPage.clickSearchButton();
                logger.info("Clicked search icon button.");

                // Expected Result: 1. More than one products should be displayed in the search results page
                // Get the list of product result cards
                List<WebElement> productResults = searchPage.productResultCards; // Directly accessing the @FindBy list
                // from SearchPage.

                Assert.assertTrue(productResults.size() > 1, "Less than two products are displayed for the search keyword '" + searchKeyword + "'. Actual count: " + productResults.size());
                logger.info("Verified: More than one product (" + productResults.size() + ") is displayed in the search results for '" + searchKeyword + "'.");

                // Optional: You might also want to verify the search results heading for consistency
                String searchResultsHeading = searchPage.getSearchResultsHeading();
                Assert.assertTrue(searchResultsHeading.contains(searchKeyword), "Search results heading does not contain the search keyword.");
                logger.info("Verified search results heading: '" + searchResultsHeading + "'");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_SP_005_ValidateSearchMultipleProductsTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SP_005_ValidateSearchMultipleProductsTest.");
            }
        }
    }



