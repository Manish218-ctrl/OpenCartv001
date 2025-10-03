package testCases.TS_013_SpecialOffers;

import testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.SiteMapPage;
import pageObjects.SpecialOffersPage;

public class TC_SPO_003_ValidateOfferProductsDisplayedTest extends BaseClass {

    @Test
    public void validateOfferProductsDisplayed() {
        logger.info("Starting TC_SPO_003_ValidateOfferProductsDisplayedTest...");

        // Step 1: Navigate to the Site Map page via the footer link
        logger.info("Navigating to Site Map page via footer link...");
        Homepage homepage = new Homepage(driver);
        homepage.clickFooterLink("Site Map");
        logger.info("Clicked on 'Site Map' footer link.");

        // Step 2: Validate that the Site Map page is loaded
        SiteMapPage siteMapPage = new SiteMapPage(driver);
        Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "User is not on Site Map page");
        logger.info("Site Map page is loaded successfully.");

        // Step 3: Click on the 'Specials' footer link to navigate to Special Offers page
        logger.info("Clicking on 'Specials' footer link...");
        //siteMapPage.clickFooterLink("Specials");
        logger.info("Clicked on 'Specials' footer link.");

        // Step 4: Validate that the Special Offers page is loaded correctly
        SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
        Assert.assertTrue(specialOffersPage.getPageTitle().contains("Special Offers"), "User is not on Special Offers page");
        logger.info("Special Offers page is loaded successfully.");

        // Step 5: Validate that offer products are displayed on the Special Offers page
        logger.info("Validating that offer products are displayed...");
        boolean areProductsDisplayed = specialOffersPage.areOfferProductsDisplayed();
        Assert.assertTrue(areProductsDisplayed, "Offer products are not displayed on the page");
        logger.info("Offer products are displayed successfully: " + areProductsDisplayed);

        logger.info("Test TC_SPO_003_ValidateOfferProductsDisplayedTest completed successfully.");
    }
}
