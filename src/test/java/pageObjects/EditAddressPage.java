package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EditAddressPage extends BasePage {

    public EditAddressPage(WebDriver driver) {

        super(driver);

        
    }

    //LOCATORS

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

    @FindBy(xpath = "//input[@name='default' and @value='0']")
    public WebElement radioDefaultNo;

    @FindBy(xpath = "//input[@name='default' and @value='1']")
    public WebElement radioDefaultYes;

    @FindBy(xpath = "//h1[normalize-space()='Edit Address']")
    private WebElement headingEditAddress;

    //ACTION METHODS

    public void updateAddress(
            String firstName,
            String lastName,
            String company,
            String address1,
            String address2,
            String city,
            String postcode,
            String country,
            String region
    ) {

        typeText(txtFirstName, firstName);
        typeText(txtLastName, lastName);
        typeText(txtCompany, company);
        typeText(txtAddress1, address1);
        typeText(txtAddress2, address2);
        typeText(txtCity, city);
        typeText(txtPostcode, postcode);

        Select selectCountry = new Select(drpCountry);
        selectCountry.selectByVisibleText(country);

        Select selectRegion = new Select(drpRegion);
        selectRegion.selectByVisibleText(region);

        logger.info("Updated address details successfully.");
    }

    public void clickContinue() {

        clickElement(btnContinue);

        logger.info("Clicked Continue button.");
    }

    public void verifySuccessMessage() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOf(msgSuccess)
                ).getText().contains(
                        "Your address has been successfully updated"
                ),
                "Success message not displayed!"
        );

        logger.info("Address update success message verified.");
    }

    public void uncheckDefaultAddress() {

        clickElement(radioDefaultNo);
    }

    public boolean isEditAddressPageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(headingEditAddress)
        ).isDisplayed();
    }

}