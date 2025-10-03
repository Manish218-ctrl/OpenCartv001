package testCases.TS_019_AddressBook;


import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_AB_001_ValidateAddressBookNavigationTest extends BaseClass {

        @Test
        public void validateAddressBookNavigation() {
            // 1. Login using BaseClass method
            performLogin();

            // 2. Navigate to My Account
            Homepage home = new Homepage(driver);
            home.clickMyAccount(); // Click on My Account dropdown

            // 3. Click 'My Account' option
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            // Validate My Account page loaded
            assert myAccountPage.isMyAccountPageExists() : "My Account page is not displayed!";

            // 4. Click 'Modify your address book entries' link
            myAccountPage.clickAddressBookLink();

            // 5. Verify navigation to Address Book page
            AddressBookPage addressBookPage = new AddressBookPage(driver);
            addressBookPage.verifyAddressBookPage();

            logger.info("Navigation to Address Book page validated successfully.");
        }
    }




