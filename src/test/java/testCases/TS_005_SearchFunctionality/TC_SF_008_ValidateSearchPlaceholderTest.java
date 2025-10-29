package testCases.TS_005_SearchFunctionality;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_SF_008_ValidateSearchPlaceholderTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_SF_008_ValidateSearchPlaceholderTest.class);

    @Test(groups = {"sanity", "regression", "master"})
    public void verifySearchPlaceholders() {
        logger.info("=== Starting TC_SF_008_ValidateSearchPlaceholderTest ===");
        try {
            SearchPage searchPage = new SearchPage(driver);

            //️  Verify Global Search Placeholder
            logger.info("Checking placeholder text in Global Search box on homepage...");
            String globalPlaceholder = searchPage.getGlobalSearchInputPlaceholder();
            logger.info("Global Search Placeholder: '{}'", globalPlaceholder);
            Assert.assertEquals(globalPlaceholder, "Search", "Global search placeholder mismatch!");
            logger.info("Global Search placeholder verified successfully.");

            //  Navigate to Advanced Search Page
            logger.info("Navigating to the Advanced Search page...");
            driver.get( "https://tutorialsninja.com/demo/index.php?route=product/search"); // adjust if needed
            searchPage = new SearchPage(driver); // re-init PageFactory for new page

            //  Verify Advanced Search Criteria Placeholder
            logger.info("Checking placeholder text in Advanced Search criteria field...");
            String advSearchPlaceholder = searchPage.getSearchCriteriaPlaceholder();
            logger.info("Advanced Search Placeholder: '{}'", advSearchPlaceholder);
            Assert.assertEquals(advSearchPlaceholder, "Keywords", "Advanced search placeholder mismatch!");
            logger.info("Advanced Search placeholder verified successfully.");

        } catch (Exception e) {
            logger.error("Test execution failed: {}", e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            logger.info("=== Finished TC_SF_008_ValidateSearchPlaceholderTest ===");
        }
    }
}
