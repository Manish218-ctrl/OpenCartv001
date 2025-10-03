package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import testBase.BaseClass;

    public class TC_SP_009_ValidateNavigateToCompareFromSearchTest extends BaseClass {

        private static final String PRODUCT_NAME = "iMac";

        @Test(description = "TC_SF_013: Validate navigating to Product Compare Page from Search Results page")
        public void navigateToCompareFromSearch() {

            SearchPage sp = new SearchPage(driver);

            // Step 1-2: Search for product
            sp.enterSearchKeyword(PRODUCT_NAME);
            sp.clickSearchButton();
            Assert.assertFalse(sp.isNoProductMessageDisplayed(),
                    "Search returned no products for: " + PRODUCT_NAME);

            // Step 3: Click on 'Product Compare' link (Validate ER-1)
            sp.clickProductCompareLink();

            // ER-1: User should be navigated to the Product Compare Page
            Assert.assertTrue(sp.isOnProductComparePage(),
                    "Did not navigate to the Product Compare page.");
        }
    }


