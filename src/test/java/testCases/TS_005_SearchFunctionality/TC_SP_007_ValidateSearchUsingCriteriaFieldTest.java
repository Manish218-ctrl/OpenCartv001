package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;


    public class TC_SP_007_ValidateSearchUsingCriteriaFieldTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"})
        public void test_search_using_criteria_field() {
            logger.info("Starting TC_SP_007_ValidateSearchUsingCriteriaFieldTest: Validate searching using the Search Criteria field.");

            try {

                HomePage hp = new HomePage(driver); // Changed to HomePage
                SearchPage searchPage = new SearchPage(driver);


                //Do not enter anything into the Search text box field.
                searchPage.enterSearchKeyword("");
                logger.info("Ensured the search box is empty.");

                //Click on the button having the search icon.
                searchPage.clickSearchButton();
                logger.info("Clicked search icon button with an empty search query.");

                //Test Data: An existing product name (e.g., "iPhone").
                String existingProductName = p.getProperty("singleProductSearchKeyword");
                logger.info("Using existing product name for search criteria: " + existingProductName);

                //Enter any existing product name into the Search Criteria text box field (Refer Test Data).
                searchPage.enterSearchKeyword(existingProductName);
                logger.info("Entered '" + existingProductName + "' into the Search Criteria text box field.");

                //Click on the Search button.
                searchPage.clickSearchButton();
                logger.info("Clicked Search button again with the valid product name.");

                // Expected Result (ER-1): The searched product should be displayed in the search results.
                Assert.assertTrue(searchPage.isProductDisplayed(existingProductName),
                        "The searched product '" + existingProductName + "' is not displayed in the search results after using the Search Criteria field.");
                logger.info("Verified: The searched product '" + existingProductName + "' is displayed in the search results.");

                String searchResultsHeading = searchPage.getSearchResultsHeading();
                Assert.assertTrue(searchResultsHeading.contains(existingProductName),
                        "Search results heading does not contain the searched product name. Actual: " + searchResultsHeading);
                logger.info("Verified search results heading contains: '" + existingProductName + "'.");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_SP_007_ValidateSearchUsingCriteriaFieldTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SP_007_ValidateSearchUsingCriteriaFieldTest.");
            }
        }
    }



