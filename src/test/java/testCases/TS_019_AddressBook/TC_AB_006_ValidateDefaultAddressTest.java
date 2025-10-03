package testCases.TS_019_AddressBook;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.EditAddressPage;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import org.testng.annotations.BeforeClass;

    public class TC_AB_006_ValidateDefaultAddressTest extends BaseClass {

        Homepage home;
        MyAccountPage myAccount;
        AddressBookPage addressBook;

        @BeforeClass
        public void setupTest() {
            performLogin(); // Login using BaseClass method
            home = new Homepage(driver);
            myAccount = new MyAccountPage(driver);
            addressBook = new AddressBookPage(driver);
        }

        @Test
        public void validateDefaultAddressWithSingleAddress() throws InterruptedException {
            // Navigate to My Account -> Address Book
            myAccount.clickAddressBookLink();

            // Verify Address Book page
            addressBook.verifyAddressBookPage();

            //  Click Edit on an existing address
            driver.findElement(By.xpath("//a[text()='Edit']")).click();

            //  Now work with EditAddressPage
            EditAddressPage editAddress = new EditAddressPage(driver);
            Thread.sleep(20000);
            editAddress.uncheckDefaultAddress();
            editAddress.clickContinue();

            // Verify warning message is displayed
            addressBook.verifyWarningMessage();

            // Verify Default Address is still selected
            assert addressBook.isDefaultAddressSelected() : "Default Address should not be updated!";
        }
    }



