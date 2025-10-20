package testCases.TS_012_SpecialOffers;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By; // New Import
import org.openqa.selenium.support.ui.ExpectedConditions; // New Import
import org.openqa.selenium.support.ui.WebDriverWait; // New Import
import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

import java.time.Duration; // New Import

public class TC_SPO_004_ValidateSpecialOffersPageDisplayedProductsinListViewTest extends BaseClass {

    private final By LIST_VIEW_PRODUCT_LOCATOR = By.xpath("//div[@id='content']//div[contains(@class,'product-layout')]");

    @Test
    public void validateProductsInListView() {
        logger.info("Starting TC_SPO_004_ValidateSpecialOffersPageDisplayedProductsinListViewTest...");

        // Step 1: Navigation to Site Map
        logger.info("Navigating to Site Map page via footer link...");
        HomePage homepage = new HomePage(driver);
        homepage.clickFooterLink("Site Map");
        logger.info("Clicked on 'Site Map' footer link.");

        SiteMapPage siteMapPage = new SiteMapPage(driver);
        Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "User is not on Site Map page");
        logger.info("Site Map page is loaded successfully.");

        // Step 2: Navigation to Special Offers page
        logger.info("Clicking on 'Specials' footer link...");
        siteMapPage.clickFooterLink("Specials");
        logger.info("Clicked on 'Specials' footer link.");

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
        Assert.assertTrue(specialOffersPage.getPageTitle().contains("Special Offers"), "User is not on Special Offers page");
        logger.info("Special Offers page is loaded successfully.");

        // Step 3: Select the 'List' view
        logger.info("Selecting 'List' view option...");
        specialOffersPage.selectListView();
        logger.info("List view selected successfully.");

        logger.info("Waiting for products to load in List view...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(LIST_VIEW_PRODUCT_LOCATOR));

        logger.info("Products are present in List view. Proceeding with validation.");

        logger.info("Validating that offer products are displayed in List view by counting elements...");

        int productCount = driver.findElements(LIST_VIEW_PRODUCT_LOCATOR).size();

        Assert.assertTrue(productCount > 0, "Offer products are not displayed in List view. Found " + productCount + " products.");

        logger.info("Offer products are displayed successfully in List view. Found " + productCount + " products.");

        logger.info("Test TC_SPO_004_ValidateSpecialOffersPageDisplayedProductsinListViewTest completed successfully.");
    }
}