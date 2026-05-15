package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;

public class AddressBookPage extends BasePage {

    public AddressBookPage(WebDriver driver) {

        super(driver);

        
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h2[normalize-space()='Address Book Entries']")
    private WebElement headingAddressBook;

    @FindBy(xpath = "//input[@name='default' and @value='1']")
    private WebElement chkDefaultAddress;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    private WebElement warningMessage;

    @FindBy(xpath =
            "//table[contains(@class,'table-bordered')]//tbody//tr[1]" +
                    "//a[normalize-space()='Edit']")
    private WebElement firstEditAddressButton;

    @FindBy(xpath =
            "//div[contains(@class,'buttons')]//a[contains(@class,'btn-primary')]")
    private WebElement btnNewAddress;

    @FindBy(xpath =
            "//div[contains(@class,'buttons')]//a[normalize-space()='Back']")
    private WebElement btnBack;

    @FindBy(xpath = "//a[normalize-space()='Edit']")
    private java.util.List<WebElement> editAddressButtons;

    // ACTION METHODS

    public void clickEditFirstAddress() {

        wait.until(
                ExpectedConditions.elementToBeClickable(firstEditAddressButton)
        ).click();
    }

    public void verifyAddressBookPage() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOf(headingAddressBook)
                ).isDisplayed(),
                "Address Book page is not displayed!"
        );
    }

    public void uncheckDefaultAddress() {

        if (chkDefaultAddress.isSelected()) {

            chkDefaultAddress.click();
        }
    }

    public void verifyWarningMessage() {

        String expected =
                "Warning: You cannot update the Default Address status as there is only one address in your address book";

        Assert.assertEquals(
                wait.until(
                        ExpectedConditions.visibilityOf(warningMessage)
                ).getText().trim(),
                expected,
                "Warning message mismatch!"
        );
    }

    public boolean isDefaultAddressSelected() {

        return wait.until(
                ExpectedConditions.visibilityOf(chkDefaultAddress)
        ).isSelected();
    }

    public void clickNewAddress() {

        wait.until(
                ExpectedConditions.elementToBeClickable(btnNewAddress)
        ).click();
    }

    public void clickBackButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(btnBack)
        ).click();
    }

    public void addNewAddress(
            String fname,
            String lname,
            String company,
            String address1,
            String address2,
            String city,
            String postcode,
            String country,
            String region
    ) {

        clickNewAddress();

        EditAddressPage editAddress =
                new EditAddressPage(driver);

        editAddress.updateAddress(
                fname,
                lname,
                company,
                address1,
                address2,
                city,
                postcode,
                country,
                region
        );

        editAddress.clickContinue();

        editAddress.verifySuccessMessage();
    }

    public boolean hasEditableAddress() {

        return !editAddressButtons.isEmpty();
    }

    public void clickFirstEditAddress() {

        wait.until(
                ExpectedConditions.elementToBeClickable(editAddressButtons.get(0))
        ).click();
    }
}