package testCases.TS_019_AddressBook;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.AddressBookPage;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_AB_002_ValidateAddressBookRightColumnNavigationTest extends BaseClass {

        private Homepage homePage;
        private LoginPage loginPage;
        private MyAccountPage myAccountPage;
        private AddressBookPage addressBookPage;

        @BeforeClass
        @Parameters({"os", "browser"})
        public void setupTest(String os, String browser) throws Exception {
            super.setup(os, browser);

            // Initialize page objects
            homePage = new Homepage(driver);
            loginPage = new LoginPage(driver);
            myAccountPage = new MyAccountPage(driver);
            addressBookPage = new AddressBookPage(driver);
        }

        @Test(description = "Validate navigating to 'Address Book Entries' page from Right Column options")
        public void verifyAddressBookNavigation() {
            // Step 1: Login to the application
            performLogin();
            logger.info("Login successful.");

            // Step 2: Click on 'My Account' -> 'Address Book'
            myAccountPage.clickAddressBookLink();
            logger.info("Clicked on 'Modify your address book entries' link.");

            // Step 3: Verify Address Book page is displayed
            addressBookPage.verifyAddressBookPage();
            logger.info("Address Book page is displayed successfully.");

            // Optional: Validate page title
            String expectedTitle = "Address Book Entries"; // Replace with actual title if different
            String actualTitle = driver.getTitle();
            Assert.assertTrue(actualTitle.contains("Address Book"),
                    "Expected title to contain 'Address Book', but found: " + actualTitle);

            logger.info("Test TS_016 passed successfully!");
        }
    }



