package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage; // Import the SearchPage
import testBase.BaseClass;


    public class TC_SF_002_ValidateSearchNonExistingProductTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"})
        public void test_search_non_existing_product_name() {
            logger.info("Starting TC_SF_002_ValidateSearchNonExistingProductTest: Validate searching with a non-existing Product Name.");

            try {

                HomePage hp = new HomePage(driver);
                SearchPage searchPage = new SearchPage(driver);

                // 1. Enter non-existing product name into the 'Search' text box field - <Refer Test Data>
                String nonExistingProductName = p.getProperty("nonExistingSearchProduct");

                searchPage.enterSearchKeyword(nonExistingProductName); // Use method from SearchPage
                logger.info("Entered non-existing product name into search box: " + nonExistingProductName);

                // 2. Click on the button having search icon (Validate ER-1)
                searchPage.clickSearchButton(); // Use method from SearchPage
                logger.info("Clicked search icon button.");

                // 1. There is no product that matches the search criteria should be displayed in the Search Results page
                Assert.assertTrue(searchPage.isNoProductMessageDisplayed(), "The 'No product' message is not displayed for a non-existing product search.");
                logger.info("Verified: 'No product' message is displayed for non-existing product search.");


            } catch (Exception e) {
                logger.error("Test execution failed for TC_SF_002_ValidateSearchNonExistingProductTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SF_002_ValidateSearchNonExistingProductTest.");
            }
        }
    }



