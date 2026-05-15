package testCases.TS_019_MyAccountInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_MAI_003_ValidateNavigateToMyAccountInformationFromSiteMapTest extends BaseClass {

    @Test
    public void TC_SI_001_NavigateToMyAccountInformationFromSiteMap() {
        try {
            logger.info("***** Starting Test: TC_SI_001_NavigateToMyAccountInformationFromSiteMap *****");

            //Perform login
            performLogin();
            logger.info("User logged in successfully.");

            //Navigate to Site Map page using HomePage footer
            HomePage home = new HomePage(getDriver());
            SiteMapPage siteMapPage = new SiteMapPage(getDriver());
            siteMapPage.clickFooterLink("Site Map");
            logger.info("Clicked on Site Map footer link.");

            //Validate Site Map page is displayed
            Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "Site Map page was not displayed.");
            logger.info("Site Map page is displayed successfully.");

            //Click on Account Information link
            siteMapPage.clickAccountInformation();
            logger.info("Clicked on Account Information link from Site Map page.");

            //Validate user is navigated to My Account Information page
            MyAccountPage myAccountPage = new MyAccountPage(getDriver());
            Assert.assertTrue(myAccountPage.isMyAccountInformationPageDisplayed(),
                    "Failed to navigate to My Account Information page.");
            logger.info("Successfully navigated to My Account Information page.");

            logger.info("***** Test Completed: TC_SI_001_NavigateToMyAccountInformationFromSiteMap *****");

        } catch (Exception e) {
            logger.error("Test Case Failed: " + e.getMessage(), e);
            Assert.fail("Exception during test execution: " + e.getMessage());
        }
    }
}
