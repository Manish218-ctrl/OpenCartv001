package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage; // Import the SearchPage
import testBase.BaseClass;
import java.util.List; // Import for List
import org.openqa.selenium.WebElement; // Import for WebElement

    public class TC_SP_006_ValidateSearchSingleProductTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_search_single_product() {
            logger.info("Starting TC_SP_006_ValidateSearchSingleProductTest: Validate searching with a search criteria resulting in a single product.");

            try {

                HomePage hp = new HomePage(driver);

                SearchPage searchPage = new SearchPage(driver);

                // 1. Enter the search criteria in the 'Search' text box field which can result in single product - <Refer Test Data>
                String searchKeyword = p.getProperty("singleProductSearchKeyword");

                searchPage.enterSearchKeyword(searchKeyword);
                logger.info("Entered search keyword for a single product: " + searchKeyword);

                // 2. Click on the button having search icon (Validate ER-1)
                searchPage.clickSearchButton();
                logger.info("Clicked search icon button.");

                // Expected Result: 1. Only one product should be displayed in the search results page
                List<WebElement> productResults = searchPage.productResultCards; // Directly accessing the @FindBy list

                Assert.assertEquals(productResults.size(), 1, "Expected exactly one product, but found " + productResults.size() + " for the search keyword '" + searchKeyword + "'.");
                logger.info("Verified: Exactly one product (" + productResults.size() + ") is displayed in the search results for '" + searchKeyword + "'.");

                String searchResultsHeading = searchPage.getSearchResultsHeading();
                Assert.assertTrue(searchResultsHeading.contains(searchKeyword), "Search results heading does not contain the search keyword.");
                logger.info("Verified search results heading: '" + searchResultsHeading + "'");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_SP_006_ValidateSearchSingleProductTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SP_006_ValidateSearchSingleProductTest.");
            }
        }
    }



