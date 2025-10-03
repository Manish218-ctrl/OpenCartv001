package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

    public class EditAddressPage extends BasePage {

        public EditAddressPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(name = "firstname")
        private WebElement txtFirstName;

        @FindBy(name = "lastname")
        private WebElement txtLastName;

        @FindBy(name = "company")
        private WebElement txtCompany;

        @FindBy(name = "address_1")
        private WebElement txtAddress1;

        @FindBy(name = "address_2")
        private WebElement txtAddress2;

        @FindBy(name = "city")
        private WebElement txtCity;

        @FindBy(name = "postcode")
        private WebElement txtPostcode;

        @FindBy(name = "country_id")
        private WebElement drpCountry;

        @FindBy(name = "zone_id")
        private WebElement drpRegion;

        @FindBy(xpath = "//input[@value='Continue']")
        private WebElement btnContinue;

        @FindBy(xpath = "//div[contains(@class,'alert-success')]")
        private WebElement msgSuccess;

        // Fill the Edit Address form
        public void updateAddress(String firstName, String lastName, String company,
                                  String address1, String address2, String city,
                                  String postcode, String country, String region) {

            txtFirstName.clear();
            txtFirstName.sendKeys(firstName);

            txtLastName.clear();
            txtLastName.sendKeys(lastName);

            txtCompany.clear();
            txtCompany.sendKeys(company);

            txtAddress1.clear();
            txtAddress1.sendKeys(address1);

            txtAddress2.clear();
            txtAddress2.sendKeys(address2);

            txtCity.clear();
            txtCity.sendKeys(city);

            txtPostcode.clear();
            txtPostcode.sendKeys(postcode);

            // Select Country
            org.openqa.selenium.support.ui.Select selectCountry = new org.openqa.selenium.support.ui.Select(drpCountry);
            selectCountry.selectByVisibleText(country);

            // Select Region
            org.openqa.selenium.support.ui.Select selectRegion = new org.openqa.selenium.support.ui.Select(drpRegion);
            selectRegion.selectByVisibleText(region);
        }

        // Click Continue
        public void clickContinue() {
            btnContinue.click();
        }

        // Verify success message
        public void verifySuccessMessage() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(msgSuccess));
            Assert.assertTrue(msgSuccess.getText().contains("Your address has been successfully updated"),
                    "Success message not displayed!");
        }



            @FindBy(xpath = "//input[@name='default'][@value='0']")
            public WebElement radioDefaultNo;

            @FindBy(xpath = "//input[@name='default'][@value='1']")
            public WebElement radioDefaultYes;


            // Uncheck default (select "No")
            public void uncheckDefaultAddress() {
                radioDefaultNo.click();
            }


        }





