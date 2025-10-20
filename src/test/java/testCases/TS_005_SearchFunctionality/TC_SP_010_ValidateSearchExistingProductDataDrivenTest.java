package testCases.TS_005_SearchFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_SP_010_ValidateSearchExistingProductDataDrivenTest extends BaseClass {


    @Test(dataProvider = "SearchExistingProductData", dataProviderClass = DataProviders.class)
    public void verify_search_existing_product(String searchQuery, String expectedProductTitle)
    {
        logger.info("***** Starting Data-Driven TC_SF_001_SearchExistingProductTest ****");
        logger.debug("Testing search query: " + searchQuery);

        try
        {
            HomePage hp = new HomePage(driver);

            hp.searchProduct(searchQuery);
            logger.info("Executed search for: " + searchQuery);

            SearchPage searchPage = new SearchPage(driver);

            boolean isProductVisible = searchPage.isProductDisplayed(expectedProductTitle);

            String actualProductTitle = searchPage.getProductTitleFromResult();

            if (isProductVisible) {
                logger.info("Product found: " + actualProductTitle);

                // Assert that the product is visible AND the displayed title matches the expected title
                Assert.assertTrue(isProductVisible, "Product link was not visible in search results.");
                Assert.assertEquals(actualProductTitle, expectedProductTitle,
                        "Product title mismatch. Expected: " + expectedProductTitle + ", Actual: " + actualProductTitle);

                logger.info("Test Passed for: " + searchQuery);

            } else {
                // Should only happen if the search mechanism failed, not if the data is incorrect.
                logger.error("Test Failed: Product '" + expectedProductTitle + "' not displayed for query '" + searchQuery + "'.");
                Assert.fail("Product '" + expectedProductTitle + "' was not found in the search results.");
            }
        }
        catch (Exception e)
        {
            logger.error("Test failed for query: " + searchQuery + ". Error: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
        finally
        {
            logger.info("***** Finished a run of TC_SF_001_SearchExistingProductTest *****");
        }
    }
}
