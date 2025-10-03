package testCases.TS_004_ProductCompare;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_PC_010_ValidateProductCompareEmptyStateTest extends BaseClass {

    @Test(groups = {"regression","sanity"})
    public void verifyEmptyProductComparePage() {
        logger.info("***** Starting TC_PC_010_ValidateProductCompareEmptyStateTest *****");

        try {
            // Step 1: Navigate to "Show All Desktops" via CategoryPage
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryPage.hoverOnDesktopsMenu();
            categoryPage.clickShowAllDesktops();
            logger.info("Navigated to Desktops category page.");

            // Step 2: Click "Product Compare (0)" link
            SearchPage sp = new SearchPage(driver);
            sp.clickProductCompareLink();
            logger.info("Clicked on Product Compare link without adding any products.");

            // Step 3: Verify empty message
            String actualMessage = driver.findElement(By.xpath("//div[@id='content']/p"))
                    .getText().trim();
            String expectedMessage = "You have not chosen any products to compare.";
            Assert.assertEquals(actualMessage, expectedMessage,
                    "Empty Product Compare page message mismatch!");

            logger.info("Verified empty Product Compare page message successfully.");

        } catch (Exception e) {
            logger.error("Test Failed due to Exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during Empty Product Compare Page test: " + e.getMessage());
        }

        logger.info("***** Finished TC_PC_010_ValidateProductCompareEmptyStateTest *****");
    }
}
