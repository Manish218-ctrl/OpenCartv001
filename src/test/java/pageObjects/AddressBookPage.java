package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

    public class AddressBookPage extends BasePage {

        public AddressBookPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[4]")
        public WebElement headingAddressBook;

        @FindBy(xpath = "//*[@id=\"content\"]/form/fieldset/div[10]/div/label[1]/input")
        public WebElement chkDefaultAddress;

        @FindBy(xpath = "//button[@type='submit']")
        public WebElement btnContinue;

        @FindBy(xpath = "/html/body/div[2]/div[1]")
        public WebElement warningMessage;

        @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/tbody/tr[2]/td[2]/a[1]")
          public WebElement editaddressbook;

                public void clickeditaddressbook(){
            editaddressbook.click();
                }
        // Verify if Address Book page is displayed
        public void verifyAddressBookPage() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement heading = wait.until(ExpectedConditions.visibilityOf(headingAddressBook));
            Assert.assertTrue(heading.isDisplayed(), "Address Book page is not displayed!");
        }

        // Attempt to uncheck Default Address
        public void uncheckDefaultAddress() {
            if (chkDefaultAddress.isSelected()) {
                chkDefaultAddress.click();
            }
            btnContinue.click();
        }

        // Verify warning message
        public void verifyWarningMessage() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement warning = wait.until(ExpectedConditions.visibilityOf(warningMessage));
            String expected = "Warning: You cannot update the Default Address status as there is only one address in your address book";
            Assert.assertEquals(warning.getText().trim(), expected, "Warning message mismatch!");
        }

        public boolean isDefaultAddressSelected() {
            return chkDefaultAddress.isSelected();
        }


        // AddressBookPage.java
        public void addNewAddress(String fname, String lname, String company,
                                  String address1, String address2, String city,
                                  String postcode, String country, String region) {

            // Click "New Address" button
            driver.findElement(By.xpath("//a[text()='New Address']")).click();

            // Fill out the address form
            EditAddressPage editAddress = new EditAddressPage(driver);
            editAddress.updateAddress(fname, lname, company, address1, address2, city, postcode, country, region);

            // Click Continue
            editAddress.clickContinue();

            // Verify success
            editAddress.verifySuccessMessage();
        }


            By defaultAddressNo = By.xpath("//input[@name='default'][@value='0']");
            By continueButton = By.xpath("//input[@value='Continue']");



            // Click Continue
            public void clickContinue() {
                driver.findElement(continueButton).click();
            }
        }







