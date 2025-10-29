package testCases.TS_018_AddressBook;

import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import testBase.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_AB_003_ValidateAddressBookNavigationSiteMapTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_AB_003_ValidateAddressBookNavigationSiteMapTest.class);

    @Test
    public void validateAddressBookNavigation() {
        try {
            logger.info("=== TC_AB_003: Validate Address Book Navigation via Footer Link START ===");

            // Step 1: Login to application
            logger.info("Step 1: Performing login");
            performLogin();
            logger.info("Login successful");

            // Step 2: Initialize HomePage object
            logger.info("Step 2: Initializing HomePage page object");
            HomePage homePage = new HomePage(driver);

            // Step 3: Click 'Site Map' footer link
            logger.info("Step 3: Clicking 'Site Map' footer link");
            homePage.clickFooterLink("Site Map");
            logger.info("'Site Map' footer link clicked successfully");

            // Step 4: Click 'Address Book' link from Site Map page
            logger.info("Step 4: Clicking 'Address Book' link from Site Map page");
            SiteMapPage siteMapPage = new SiteMapPage(driver);
            siteMapPage.clickAddressBook();
            logger.info("'Address Book' link clicked successfully");

            // Step 5: Verify Address Book Page
            logger.info("Step 5: Verifying Address Book page is displayed");
            AddressBookPage addressBookPage = new AddressBookPage(driver);
            addressBookPage.verifyAddressBookPage();
            logger.info("Address Book page is displayed successfully");

            logger.info("=== TC_AB_003: Validate Address Book Navigation via Footer Link END ===");
        } catch (Exception e) {
            logger.error("Test case TC_AB_003 failed due to exception: ", e);
            throw e;  // Re-throw exception to mark test as failed
        }
    }
}
