package testCases.TS_004_ProductCompare;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

    public class TC_PC_008_ValidateProductCompareNavigationFromSearchTest extends BaseClass {

        @Test(groups = {"regression","sanity"})
        public void verifyNavigationToComparePageFromSearchResults() {
            logger.info("***** Starting TC_PC_008_ValidateProductCompareNavigationFromSearchTest *****");

            try {
                String productName = "iMac";  // Test Data

                //Search for product
                SearchPage sp = new SearchPage(getDriver());
                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();

                Assert.assertTrue(sp.isProductDisplayed(productName),
                        "ERROR: Product " + productName + " not displayed in search results.");

                //Click Product Compare link
                sp.clickProductCompareLink();

                //Verify navigation to Product Comparison page
                ProductComparisonPage cmp = new ProductComparisonPage(getDriver());
                Assert.assertTrue(cmp.isOnComparisonPage(),
                        "ERROR: Not navigated to Product Comparison page.");

                logger.info("Successfully navigated to Product Comparison page from Search Results.");

            } catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage(), e);
                Assert.fail("Exception occurred during Product Compare Navigation from Search Results test: " + e.getMessage());
            }

            logger.info("***** Finished TC_PC_008_ValidateProductCompareNavigationFromSearchTest *****");
        }
    }




