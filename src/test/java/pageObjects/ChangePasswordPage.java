package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ChangePasswordPage extends BasePage {

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(id = "input-password")
    public WebElement newPasswordField;

    @FindBy(id = "input-confirm")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//div[contains(@class,'buttons')]//input[@value='Continue' and contains(@class,'btn-primary')]")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'text-danger')]")
    public WebElement warningMessage;

    @FindBy(xpath = "//div[contains(@class,'alert') and contains(@class,'alert-success')]")
    public WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'buttons')]//a[normalize-space()='Back']")
    public WebElement backButton;

    @FindBy(xpath = "//input[@id='input-password']/ancestor::div[contains(@class,'form-group')]")
    public WebElement passwordFormGroup;

    @FindBy(xpath = "//input[@id='input-confirm']/ancestor::div[contains(@class,'form-group')]")
    public WebElement confirmPasswordFormGroup;

    @FindBy(css = ".breadcrumb")
    private WebElement breadcrumbContainer;

    @FindBy(css = ".breadcrumb a")
    private java.util.List<WebElement> breadcrumbLinks;

    @FindBy(css = "div#content h1")
    private WebElement pageHeading;

    // ACTION METHODS

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

    public String getWarningMessageText() {

        return warningMessage.getText();
    }

    public String getSuccessMessageText() {

        return successMessage.getText();
    }

    public void clickBackButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(backButton)
        ).click();
    }

    public boolean isNewPasswordFieldMandatory() {

        return passwordFormGroup
                .getAttribute("class")
                .contains("required");
    }

    public boolean isConfirmPasswordFieldMandatory() {

        return confirmPasswordFormGroup
                .getAttribute("class")
                .contains("required");
    }

    public void openChangePasswordPage(String appURL) {

        driver.get(appURL + "/index.php?route=account/password");

        logger.info("Navigated to Change Password page.");
    }

    public boolean isBreadcrumbDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(breadcrumbContainer)
        ).isDisplayed();
    }

    public List<WebElement> getBreadcrumbLinks() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(breadcrumbLinks)
        );

        return breadcrumbLinks;
    }

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getCurrentPageURL() {

        return driver.getCurrentUrl();
    }

    public String getPageHeading() {

        return wait.until(
                ExpectedConditions.visibilityOf(pageHeading)
        ).getText().trim();
    }
}
