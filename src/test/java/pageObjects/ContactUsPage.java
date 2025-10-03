package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends BasePage {

    // Constructor
    public ContactUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    // Page title
    @FindBy(xpath = "//h1[normalize-space()='Contact Us']")
    public WebElement pageTitle;

    // Input fields
    @FindBy(id = "input-name")
    public WebElement nameField;

    @FindBy(id = "input-email")
    public WebElement emailField;

    @FindBy(id = "input-enquiry")
    public WebElement enquiryField;

    // Submit button
    @FindBy(xpath = "//input[@value='Submit']")
    public WebElement submitButton;

    // Email error message
    @FindBy(css = "div.text-danger")
    public WebElement emailErrorMessage;

    // ------------------- METHODS -------------------

    // Get page title text
    public String getPageTitle() {
        return pageTitle.getText();
    }

    // Get value of "Your Name" field
    public String getNameFieldValue() {
        return nameField.getAttribute("value");
    }

    // Get value of "E-Mail Address" field
    public String getEmailFieldValue() {
        return emailField.getAttribute("value");
    }

    // Enter enquiry text
    public void enterEnquiry(String message) {
        enquiryField.clear();
        enquiryField.sendKeys(message);
    }

    // Click Submit button
    public void clickSubmitButton() {
        submitButton.click();
    }

    // Check if email error message is displayed
    public boolean isEmailErrorMessageDisplayed() {
        return emailErrorMessage.isDisplayed();
    }
}
