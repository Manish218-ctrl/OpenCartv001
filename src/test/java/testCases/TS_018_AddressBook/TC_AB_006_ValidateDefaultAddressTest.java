package testCases.TS_018_AddressBook;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.EditAddressPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_AB_006_ValidateDefaultAddressTest extends BaseClass {

    HomePage home;
    MyAccountPage myAccount;
    AddressBookPage addressBook;

    @BeforeClass
    public void setupTest() {

        performLogin();

        home = new HomePage(getDriver());
        myAccount = new MyAccountPage(getDriver());
        addressBook = new AddressBookPage(getDriver());
    }

    @Test
    public void validateDefaultAddressWithSingleAddress() {

        myAccount.clickAddressBookLink();

        addressBook.verifyAddressBookPage();

        addressBook.clickFirstEditAddress();

        EditAddressPage editAddress = new EditAddressPage(getDriver());

        Assert.assertTrue(editAddress.isEditAddressPageDisplayed(),
                "Edit Address page is not displayed.");

        editAddress.uncheckDefaultAddress();

        editAddress.clickContinue();

        addressBook.verifyWarningMessage();

        Assert.assertTrue(addressBook.isDefaultAddressSelected(),
                "Default Address should not be updated!");
    }
}