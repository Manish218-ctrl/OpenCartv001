package testCases.TS_012_SpecialOffers;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

public class TC_SPO_004_ValidateSpecialOffersPageDisplayedProductsinListViewTest extends BaseClass {

    @Test
    public void validateProductsInListView() {

        logger.info("Starting TC_SPO_004_ValidateSpecialOffersPageDisplayedProductsinListViewTest...");

        HomePage homepage = new HomePage(getDriver());

        SiteMapPage siteMapPage = new SiteMapPage(getDriver());

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(getDriver());

        // Navigation to Site Map
        logger.info("Navigating to Site Map page via footer link...");

        homepage.clickFooterLink("Site Map");

        logger.info("Clicked on Site Map footer link.");

        Assert.assertTrue(
                siteMapPage.isOnSiteMapPage(),
                "User is not on Site Map page"
        );

        logger.info("Site Map page is loaded successfully.");

        // Navigation to Special Offers page
        logger.info("Clicking on Specials footer link...");

        siteMapPage.clickFooterLink("Specials");

        logger.info("Clicked on Specials footer link.");

        Assert.assertTrue(
                specialOffersPage.getPageTitle().contains("Special Offers"),
                "User is not on Special Offers page"
        );

        logger.info("Special Offers page is loaded successfully.");

        // Select List View
        logger.info("Selecting List view option...");

        specialOffersPage.selectListView();

        logger.info("List view selected successfully.");

        // Validate products count
        int productCount =
                specialOffersPage.getListViewProductsCount();

        Assert.assertTrue(
                productCount > 0,
                "Offer products are not displayed in List view. Found "
                        + productCount +
                        " products."
        );

        logger.info(
                "Offer products are displayed successfully in List view. Found "
                        + productCount +
                        " products."
        );

        logger.info("Test TC_SPO_004_ValidateSpecialOffersPageDisplayedProductsinListViewTest completed successfully.");
    }
}