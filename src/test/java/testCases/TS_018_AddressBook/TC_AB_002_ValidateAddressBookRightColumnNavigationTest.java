package testCases.TS_018_AddressBook;


import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AddressBookPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_AB_002_ValidateAddressBookRightColumnNavigationTest extends BaseClass {

    // Declare Page Objects at the class level
    private HomePage homePage;
    private MyAccountPage myAccountPage;
    private AddressBookPage addressBookPage;

    @Test(description = "Validate navigating to 'Address Book Entries' page from Right Column options")
    public void verifyAddressBookNavigation() {
        logger.info("Starting TC_AB_002: Validate Address Book Right Column Navigation");

        // Step 1: Login to the application
        performLogin();
        logger.info("Login successful.");

        // FIX: Initialize Page Objects after the driver is established by performLogin()

        homePage = new HomePage(driver);
        myAccountPage = new MyAccountPage(driver);
        addressBookPage = new AddressBookPage(driver);

        // Step 2: Click on 'Address Book' link from the My Account Right Column

        try {
            myAccountPage.clickAddressBookLink();
            logger.info("Clicked on 'Modify your address book entries' link.");
        } catch (Exception e) {
            logger.error("Failed to click Address Book link.", e);
            Assert.fail("Navigation failed: " + e.getMessage());
        }


        // Step 3: Verify Address Book page is displayed
        try {
            // Note: Assuming verifyAddressBookPage() performs an assertion or status check
            addressBookPage.verifyAddressBookPage();
            logger.info("Address Book page is displayed successfully via page object check.");
        } catch (Exception e) {
            logger.error("Failed to verify Address Book page.", e);
            Assert.fail("Page verification failed: " + e.getMessage());
        }


        String actualTitle = driver.getTitle();
        String expectedTitlePart = "Address Book";

        Assert.assertTrue(actualTitle.contains(expectedTitlePart),
                "Page Title Assertion Failed! Expected title to contain '" + expectedTitlePart + "', but found: " + actualTitle);

        logger.info("Test TC_AB_002 passed successfully! Page Title: " + actualTitle);
    }
}