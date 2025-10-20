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

            sp.enterSearchKeyword(PRODUCT_NAME);
            sp.clickSearchButton();
            Assert.assertFalse(sp.isNoProductMessageDisplayed(),
                    "Search returned no products for: " + PRODUCT_NAME);

            sp.clickProductCompareLink();

            // ER-1: User should be navigated to the Product Compare Page
            Assert.assertTrue(sp.isOnProductComparePage(),
                    "Did not navigate to the Product Compare page.");
        }
    }


