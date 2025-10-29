package testCases.TS_005_SearchFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;
import utilities.DataProviders;

/**
 * Data-driven test to validate that searching for existing products
 * displays the correct product in search results.
 *
 * Test data is read from: src/test/resources/SearchData.xlsx -> ExistingProducts sheet
 */
public class TC_SF_010_ValidateSearchExistingProductDataDrivenTest extends BaseClass {

    @Test(dataProvider = "SearchExistingProductData", dataProviderClass = DataProviders.class,
            groups = {"Sanity", "Regression"})
    public void verify_search_existing_product(String searchQuery, String expectedProductTitle)
    {
        logger.info("***** Starting Data-Driven Search Test ****");
        logger.info("Search Query: '{}', Expected Product: '{}'", searchQuery, expectedProductTitle);

        try
        {
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct(searchQuery);
            logger.info("Executed search for: '{}'", searchQuery);

            SearchPage searchPage = new SearchPage(driver);
            boolean isProductVisible = searchPage.isProductDisplayed(expectedProductTitle);

            if (isProductVisible) {
                String actualProductTitle = searchPage.getActualProductTitleFromResults(expectedProductTitle);
                logger.info("Product found: '{}'", actualProductTitle);

                Assert.assertTrue(
                        actualProductTitle.contains(expectedProductTitle) ||
                                expectedProductTitle.contains(actualProductTitle),
                        String.format(
                                "Product title mismatch. Expected (partial): '%s', Actual: '%s'",
                                expectedProductTitle, actualProductTitle
                        )
                );

                logger.info("Test PASSED for query: '{}'", searchQuery);
            } else {
                logger.error("Test FAILED: Product '{}' not displayed for query '{}'",
                        expectedProductTitle, searchQuery);
                Assert.fail(String.format(
                        "Product '%s' was not found in search results for query '%s'",
                        expectedProductTitle, searchQuery
                ));
            }
        }
        catch (AssertionError ae) {
            throw ae;
        }
        catch (Exception e)
        {
            logger.error("Test failed for query: '{}'. Error: {}", searchQuery, e.getMessage());
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        finally
        {
            logger.info("***** Finished Search Test for: '{}' *****\n", searchQuery);
        }
    }
}
