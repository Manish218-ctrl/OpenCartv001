package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {

    WebDriver driver;

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Password fields
    @FindBy(xpath = "//*[@id=\"input-password\"]")
    public WebElement newPasswordField;

    @FindBy(xpath = "//*[@id=\"input-confirm\"]") // Update if your actual ID is different
    public WebElement confirmPasswordField;

    // Buttons
    @FindBy(xpath = "/html/body/div[2]/div/div/form/div/div[2]/input") // Continue button
    public WebElement continueButton;

    // Messages
    @FindBy(xpath = "/html/body/div[2]/div/div/form/fieldset/div[2]/div/div") // Warning message locator (adjust if needed)
    public WebElement warningMessage;

    @FindBy(css = ".alert.alert-success") // Success message locator (adjust if needed)
    public WebElement successMessage;


    public WebElement getNewPasswordField() {
        return newPasswordField;
    }

    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public WebElement getWarningMessageElement() {
        return warningMessage;
    }

    public WebElement getSuccessMessageElement() {
        return successMessage;
    }


    public void setNewPassword(String password) {
        newPasswordField.clear();
        newPasswordField.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void clickContinue() {
        continueButton.click();
    }

    // Convenience methods to get message text directly
    public  String getWarningMessageText() {
        return warningMessage.getText();
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}
