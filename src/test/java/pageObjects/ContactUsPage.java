package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Contact Us']")
    public WebElement pageTitle;

    @FindBy(xpath = "//div[@id='content']//h3[normalize-space()='Our Location']")
    public WebElement locationHeading;

    @FindBy(xpath = "//div[contains(@class,'panel-body')]//div[contains(@class,'row')]//div[contains(@class,'col-sm-3')][1]//strong")
    public WebElement storeDetails;

    @FindBy(xpath = "//div[contains(@class,'panel-body')]//div[contains(@class,'row')]//div[contains(@class,'col-sm-3')][2]//strong")
    public WebElement phoneDetails;

    @FindBy(id = "input-name")
    public WebElement nameField;

    @FindBy(id = "input-email")
    public WebElement emailField;

    @FindBy(id = "input-enquiry")
    public WebElement enquiryField;

    @FindBy(xpath = "//input[@value='Submit']")
    public WebElement submitButton;

    @FindBy(css = "div.text-danger")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "//input[@id='input-name']/following-sibling::div[contains(@class,'text-danger')]")
    public WebElement nameError;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div[contains(@class,'text-danger')]")
    public WebElement emailError;

    @FindBy(xpath = "//textarea[@id='input-enquiry']/following-sibling::div[contains(@class,'text-danger')]")
    public WebElement enquiryError;

    @FindBy(css = "ul.breadcrumb")
    public WebElement breadcrumb;

    @FindBy(xpath = "//label[@for='input-name' and contains(normalize-space(),'Your Name')]")
    public WebElement lblNameMandatory;

    @FindBy(xpath = "//label[@for='input-email' and contains(normalize-space(),'E-Mail Address')]")
    public WebElement lblEmailMandatory;

    @FindBy(xpath = "//label[@for='input-enquiry' and contains(normalize-space(),'Enquiry')]")
    public WebElement lblEnquiryMandatory;

    @FindBy(xpath = "//a[contains(@class,'btn-primary') and normalize-space()='Continue']")
    private WebElement btnContinue;

    // ACTION METHODS

    public String getPageTitle() {

        return pageTitle.getText();
    }

    public WebElement getLocationHeading() {

        return locationHeading;
    }

    public WebElement getStoreDetails() {

        return storeDetails;
    }

    public WebElement getPhoneDetails() {

        return phoneDetails;
    }

    public String getNameFieldValue() {

        return nameField.getAttribute("value");
    }

    public String getEmailFieldValue() {

        return emailField.getAttribute("value");
    }

    public void enterEnquiry(String message) {

        enquiryField.clear();

        enquiryField.sendKeys(message);
    }

    public void clickSubmitButton() {

        submitButton.click();
    }

    public boolean isEmailErrorMessageDisplayed() {

        return emailErrorMessage.isDisplayed();
    }

    public boolean isNameErrorDisplayed() {

        return nameError.isDisplayed();
    }

    public boolean isEmailErrorDisplayed() {

        return emailError.isDisplayed();
    }

    public boolean isEnquiryErrorDisplayed() {

        return enquiryError.isDisplayed();
    }

    public boolean isBreadcrumbDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(breadcrumb)
        ).isDisplayed();
    }

    public boolean isNameMandatoryLabelDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(lblNameMandatory)
        ).isDisplayed();
    }

    public boolean isEmailMandatoryLabelDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(lblEmailMandatory)
        ).isDisplayed();
    }

    public boolean isEnquiryMandatoryLabelDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(lblEnquiryMandatory)
        ).isDisplayed();
    }

    public void submitEnquiry(String enquiryMessage) {

        enquiryField.clear();

        enquiryField.sendKeys(enquiryMessage);

        logger.info("Entered enquiry message.");

        clickSubmitButton();
    }

    public void clickContinueButton() {

        clickElement(btnContinue);

        logger.info("Clicked Continue button.");
    }

    public String getCurrentPageURL() {

        return driver.getCurrentUrl();
    }

}