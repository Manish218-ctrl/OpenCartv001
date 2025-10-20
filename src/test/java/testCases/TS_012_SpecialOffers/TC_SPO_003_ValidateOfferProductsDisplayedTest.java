package testCases.TS_012_SpecialOffers;

import pageObjects.HomePage;
import testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.SiteMapPage;
import pageObjects.SpecialOffersPage;

import java.time.Duration; // Import for WebDriverWait

public class TC_SPO_003_ValidateOfferProductsDisplayedTest extends BaseClass {

    // Define the robust product locator once
    private final By PRODUCT_THUMBNAIL_LOCATOR = By.xpath("//div[@id='content']//div[contains(@class,'product-thumb')]");

    @Test
    public void validateOfferProductsDisplayed() {
        logger.info("Starting TC_SPO_003_ValidateOfferProductsDisplayedTest...");

        // Steps 1-4: Navigation and Page Load validation (No changes)

        logger.info("Navigating to Site Map page via footer link...");
        HomePage homepage = new HomePage(driver);
        homepage.clickFooterLink("Site Map");
        logger.info("Clicked on 'Site Map' footer link.");

        SiteMapPage siteMapPage = new SiteMapPage(driver);
        Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "User is not on Site Map page");
        logger.info("Site Map page is loaded successfully.");

        logger.info("Clicking on 'Specials' footer link...");
        siteMapPage.clickFooterLink("Special Offer");
        logger.info("Clicked on 'Specials' footer link.");

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);
        Assert.assertTrue(specialOffersPage.getPageTitle().contains("Special Offers"), "User is not on Special Offers page");
        logger.info("Special Offers page is loaded successfully.");


        logger.info("Waiting for offer products to be displayed...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(PRODUCT_THUMBNAIL_LOCATOR));

        logger.info("Product thumbnails are present. Proceeding with validation.");


        logger.info("Validating that offer products are displayed by counting elements...");

        int productCount = driver.findElements(PRODUCT_THUMBNAIL_LOCATOR).size();

        Assert.assertTrue(productCount > 0, "Offer products are not displayed on the page. Found " + productCount + " products.");

        logger.info("Offer products are displayed successfully. Found " + productCount + " products.");

        logger.info("Test TC_SPO_003_ValidateOfferProductsDisplayedTest completed successfully.");
    }
}