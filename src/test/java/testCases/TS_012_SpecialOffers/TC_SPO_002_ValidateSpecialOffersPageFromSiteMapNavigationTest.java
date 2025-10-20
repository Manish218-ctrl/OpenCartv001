package testCases.TS_012_SpecialOffers;

import pageObjects.HomePage;
import testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SiteMapPage;
import pageObjects.SpecialOffersPage;

    public class TC_SPO_002_ValidateSpecialOffersPageFromSiteMapNavigationTest extends BaseClass {

        @Test
        public void validateSpecialOffersPageNavigation() {
            // Step 1: Navigate to the Site Map page via the footer link
            HomePage homepage = new HomePage(driver);
            homepage.clickFooterLink("Site Map");

            // Step 2: Validate that the Site Map page is loaded
            SiteMapPage siteMapPage = new SiteMapPage(driver);
            Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "User is not on Site Map page");

            // Step 3: Click on the 'Specials' footer link to navigate to Special Offers page
            siteMapPage.clickFooterLink("Special Offers");

            // Step 4: Validate that the Special Offers page is loaded correctly
            SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
            Assert.assertTrue(specialOffersPage.getPageTitle().contains("Special Offers"), "User is not on Special Offers page");
            Assert.assertTrue(specialOffersPage.areSpecialOffersDisplayed(), "No special offers found on the page");
        }
    }



