package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.ProductComparisonPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_PC_010_ValidateProductCompareEmptyStateTest extends BaseClass {

    @Test(groups = {"regression","sanity"})
    public void verifyEmptyProductComparePage() {

        logger.info("***** Starting TC_PC_010_ValidateProductCompareEmptyStateTest *****");

        try {

            //Navigate to Show All Desktops
            CategoryPage categoryPage =
                    new CategoryPage(getDriver());

            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();

            logger.info(
                    "Navigated to Desktops category page."
            );

            //Click Product Compare link
            SearchPage sp =
                    new SearchPage(getDriver());

            sp.clickProductCompareLink();

            logger.info(
                    "Clicked on Product Compare link without adding any products."
            );

            //Verify empty comparison page message
            ProductComparisonPage cmp =
                    new ProductComparisonPage(getDriver());

            String actualMessage =
                    cmp.getEmptyComparisonMessage();

            String expectedMessage =
                    "You have not chosen any products to compare.";

            Assert.assertEquals(
                    actualMessage,
                    expectedMessage,
                    "Empty Product Compare page message mismatch!"
            );

            logger.info(
                    "Verified empty Product Compare page message successfully."
            );

        } catch (Exception e) {

            logger.error(
                    "Test Failed due to Exception: {}",
                    e.getMessage(),
                    e
            );

            Assert.fail(
                    "Exception occurred during Empty Product Compare Page test: "
                            + e.getMessage()
            );
        }

        logger.info("***** Finished TC_PC_010_ValidateProductCompareEmptyStateTest *****");
    }
}