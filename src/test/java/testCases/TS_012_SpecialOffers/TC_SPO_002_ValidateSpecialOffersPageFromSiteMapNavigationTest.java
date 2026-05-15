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
            //Navigate to the Site Map page via the footer link
            HomePage homepage = new HomePage(getDriver());
            homepage.clickFooterLink("Site Map");

            //Validate that the Site Map page is loaded
            SiteMapPage siteMapPage = new SiteMapPage(getDriver());
            Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "User is not on Site Map page");

            //Click on the Specials footer link to navigate to Special Offers page
            siteMapPage.clickFooterLink("Special Offers");

            //Validate that the Special Offers page is loaded correctly
            SpecialOffersPage specialOffersPage = new SpecialOffersPage(getDriver());
            Assert.assertTrue(specialOffersPage.getPageTitle().contains("Special Offers"), "User is not on Special Offers page");
            Assert.assertTrue(specialOffersPage.areSpecialOffersDisplayed(), "No special offers found on the page");
        }
    }



