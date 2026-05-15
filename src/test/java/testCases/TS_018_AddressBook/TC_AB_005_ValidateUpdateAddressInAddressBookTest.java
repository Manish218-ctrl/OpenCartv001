package testCases.TS_018_AddressBook;

import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.EditAddressPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_AB_005_ValidateUpdateAddressInAddressBookTest extends BaseClass {

    @Test
    public void updateAddressInAddressBook() {

        performLogin();

        MyAccountPage myAccount = new MyAccountPage(getDriver());
        AddressBookPage addressBook = new AddressBookPage(getDriver());
        EditAddressPage editAddress = new EditAddressPage(getDriver());

        myAccount.clickAddressBookLink();

        addressBook.verifyAddressBookPage();

        if (!addressBook.hasEditableAddress()) {

            addressBook.addNewAddress(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString() + " St.",
                    "Suite " + randomNumber(),
                    "City" + randomString(),
                    randomNumber(),
                    "United States",
                    "California"
            );
        }

        addressBook.clickFirstEditAddress();

        editAddress.updateAddress(
                randomString(),
                randomString(),
                randomString(),
                randomString() + " St.",
                "Suite " + randomNumber(),
                "City" + randomString(),
                randomNumber(),
                "United States",
                "California"
        );

        editAddress.clickContinue();

        editAddress.verifySuccessMessage();
    }
}