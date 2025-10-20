package testCases.TS_018_AddressBook;



import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.EditAddressPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_AB_005_ValidateUpdateAddressInAddressBookTest extends BaseClass {

        @Test
        public void updateAddressInAddressBook() throws InterruptedException {


            performLogin();

            //  Navigate to My Account → Address Book
            MyAccountPage myAccount = new MyAccountPage(driver);
            myAccount.clickAddressBookLink();

            //  Verify Address Book Page
            AddressBookPage addressBook = new AddressBookPage(driver);
            addressBook.verifyAddressBookPage();

            // 🔹 Ensure there is at least one address
            if (driver.findElements(By.xpath("//a[text()='Edit']")).isEmpty()) {
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

            //  Click Edit on an existing address
            driver.findElement(By.xpath("//a[text()='Edit']")).click(); // Edit first address

            //  Update all fields with random data
            EditAddressPage editAddress = new EditAddressPage(driver);
            editAddress.updateAddress(
                    randomString(),  // First Name
                    randomString(),  // Last Name
                    randomString(),  // Company
                    randomString() + " St.", // Address1
                    "Suite " + randomNumber(), // Address2
                    "City" + randomString(),   // City
                    randomNumber(),          // Postcode
                    "United States",         // Country
                    "California"             // Region/State
            );

            // Click Continue
            editAddress.clickContinue();

            //  Verify success message
            editAddress.verifySuccessMessage();
        }
    }


