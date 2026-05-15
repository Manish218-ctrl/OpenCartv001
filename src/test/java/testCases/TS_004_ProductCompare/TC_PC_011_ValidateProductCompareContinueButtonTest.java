package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.ProductComparisonPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_PC_011_ValidateProductCompareContinueButtonTest extends BaseClass {

    @Test(groups = {"regression","sanity"})
    public void verifyContinueButtonNavigatesToHomePage() {
        logger.info("***** Starting TC_PC_011_ValidateProductCompareContinueButtonTest *****");

        try {
            //Navigate to "Show All Desktops"
            CategoryPage categoryPage = new CategoryPage(getDriver());
            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();
            logger.info("Navigated to Desktops category page.");

            //Click "Product Compare (0)" link
            SearchPage sp = new SearchPage(getDriver());
            sp.clickProductCompareLink();
            logger.info("Clicked on Product Compare link without adding any products.");

            //Click Continue button
            ProductComparisonPage cmp = new ProductComparisonPage(getDriver());
            cmp.clickContinue();
            logger.info("Clicked Continue button on Product Compare page.");

            //Verify navigation to Home Page
            Assert.assertTrue(cmp.isOnHomePage(),
                    "ERROR: User was not navigated to Home Page after clicking Continue button.");

            logger.info("Verified Continue button redirects user to Home Page successfully.");

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Product Compare Continue Button test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_011_ValidateProductCompareContinueButtonTest *****");
    }
}
