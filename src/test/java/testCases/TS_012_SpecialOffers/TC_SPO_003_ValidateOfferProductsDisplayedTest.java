package testCases.TS_012_SpecialOffers;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

public class TC_SPO_003_ValidateOfferProductsDisplayedTest extends BaseClass {

    @Test
    public void validateOfferProductsDisplayed() {

        logger.info("Starting TC_SPO_003_ValidateOfferProductsDisplayedTest...");

        HomePage homepage = new HomePage(getDriver());

        SiteMapPage siteMapPage = new SiteMapPage(getDriver());

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(getDriver());

        // Navigate to Site Map page
        logger.info("Navigating to Site Map page via footer link...");

        homepage.clickFooterLink("Site Map");

        logger.info("Clicked on Site Map footer link.");

        // Validate Site Map page
        Assert.assertTrue(
                siteMapPage.isOnSiteMapPage(),
                "User is not on Site Map page"
        );

        logger.info("Site Map page is loaded successfully.");

        // Navigate to Special Offers page
        logger.info("Clicking on Specials footer link...");

        siteMapPage.clickFooterLink("Special Offer");

        logger.info("Clicked on Specials footer link.");

        // Validate Special Offers page
        Assert.assertTrue(
                specialOffersPage.getPageTitle().contains("Special Offers"),
                "User is not on Special Offers page"
        );

        logger.info("Special Offers page is loaded successfully.");

        // Validate offer products are displayed
        int productCount = specialOffersPage.getOfferProductsCount();

        Assert.assertTrue(
                productCount > 0,
                "Offer products are not displayed on the page. Found "
                        + productCount +
                        " products."
        );

        logger.info(
                "Offer products are displayed successfully. Found "
                        + productCount +
                        " products."
        );

        logger.info("Test TC_SPO_003_ValidateOfferProductsDisplayedTest completed successfully.");
    }
}