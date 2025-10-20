package testCases.TS_012_SpecialOffers;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

public class TC_SPO_001_ValidateSpecialOffersTest extends BaseClass {

    @Test(priority = 1)
    public void validateNavigationToSpecialOffersPage() {

        // Step 1: Perform Login
        logger.info("Starting the login process...");
        performLogin();
        logger.info("Login successful.");

        // Step 2: Navigate to the homepage
        HomePage homepage = new HomePage(driver);
        logger.info("Navigating to the homepage.");

        // Step 3: Click on the 'Specials' footer link to navigate to Special Offers page
        logger.info("Clicking on 'Specials' footer link to navigate to the Special Offers page.");
        homepage.clickFooterLink();

        // Step 4: Wait for the Special Offers page to load and validate the title
        SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);

        // Initialize SpecialOffersPage class
        String pageTitle = specialOffersPage.getPageTitle();

        // Get the page title
        logger.info("Verifying page title. Expected: 'Special Offers', Found: '" + pageTitle + "'");
        Assert.assertEquals(pageTitle, "Special Offers", "Special Offers page did not load correctly!");

        // Step 5: Verify that special offers are displayed
        logger.info("Verifying if special offers are displayed on the page.");
        boolean areOffersDisplayed = specialOffersPage.areSpecialOffersDisplayed();
        Assert.assertTrue(areOffersDisplayed, "No special offers are displayed on the page.");

        logger.info("Navigated to Special Offers page successfully and special offers are displayed.");
    }
}
