package testCases.TS_017_OrderHistory;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import testBase.BaseClass;

public class TC_OH_004_ValidateOrderHistoryPageSiteMapNavigationTest extends BaseClass {

    @Test
    public void verifyOrderHistoryNavigationFromSiteMap() {
        logger.info("******** Starting TC_OH_004_ValidateOrderHistoryPageSiteMapNavigationTest ********");

        try {
            // Step 1: Perform Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to Site Map (via footer link)
            HomePage home = new HomePage(driver);
            home.clickFooterLink("Site Map");
            logger.info("Clicked on Site Map footer link.");

            // Step 3: Validate Site Map page is displayed
            SiteMapPage siteMapPage = new SiteMapPage(driver);
            Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "Site Map page is NOT displayed!");
            logger.info("Site Map page displayed successfully.");

            // Step 4: Click on 'Order History' from Site Map page
            siteMapPage.clickOrderHistoryLink();
            logger.info("Clicked on 'Order History' link inside Site Map page.");

            // Step 5: Validate navigation to 'Order History' page
            String expectedTitle = "Order History";
            String actualTitle = driver.getTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Expected title: " + expectedTitle + " but found: " + actualTitle);

            logger.info("User successfully navigated to Order History page from Site Map.");

        } catch (Exception e) {
            logger.error("Test Case failed due to exception: " + e.getMessage());
            Assert.fail("Test Case failed due to exception.");
        }

        logger.info("******** Finished TC_TS_017_OrderHistoryTest ********");
    }
}
