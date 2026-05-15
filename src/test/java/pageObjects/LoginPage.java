package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        
    }

    //LOCATORS

    @FindBy(id = "input-email")
    WebElement txtEmailAddress;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(xpath = "//form[contains(@action,'account/login')]//input[@value='Login']")
    WebElement btnLogin;

    @FindBy(xpath = "//div[@id='content']//a[contains(@class,'btn-primary') and normalize-space()='Continue']")
    public WebElement btnContinueNewCustomer;

    @FindBy(xpath = "//div[contains(@class,'alert-danger') or contains(@class,'alert-dismissible')]")
    WebElement warningMessage;

    @FindBy(xpath = "//form[contains(@action,'account/login')]//a[contains(normalize-space(),'Forgotten Password')]")
    WebElement lnkForgotPassword;

    @FindBy(xpath = "//input[@value='Login' and contains(@class,'btn-primary')]")
    private WebElement loginButton;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Login']")
    WebElement rightColumnLogin;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]")
    WebElement breadcrumb;

    @FindBy(xpath = "//div[@id='content']//h2[normalize-space()='Returning Customer']")
    WebElement pageHeading;

    @FindBy(xpath = "//h1[normalize-space()='Forgot Your Password?']")
    WebElement forgotPasswordHeading;

    //ACTION METHODS

    public void login(String email, String password) {

        setEmail(email);

        setPassword(password);

        clickLogin();
    }

    public void clickRightColumnLogin() {

        clickElement(rightColumnLogin);
    }

    public String getBreadcrumb() {

        try {

            return getElementText(breadcrumb)
                    .replaceAll("\\s+", " ");

        } catch (Exception e) {

            return "";
        }
    }

    public String getPageHeading() {

        try {

            return getElementText(pageHeading);

        } catch (Exception e) {

            return "";
        }
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getEmailField() {
        return txtEmailAddress;
    }

    public WebElement getPasswordField() {
        return txtPassword;
    }

    public RegisterPage clickContinueButtonNewCustomer() {

        clickElement(btnContinueNewCustomer);

        return new RegisterPage(driver);
    }

    public void clickForgotPassword() {

        clickElement(lnkForgotPassword);
    }

    public boolean isLoginPageDisplayed() {

        try {

            return txtEmailAddress.isDisplayed()
                    && txtPassword.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void setEmail(String email) {

        typeText(txtEmailAddress, email);
    }

    public void setPassword(String pwd) {

        typeText(txtPassword, pwd);
    }

    public void clickLogin() {

        clickElement(btnLogin);
    }

    public String getWarningMessage() {

        try {

            return getElementText(warningMessage);

        } catch (Exception e) {

            return "";
        }
    }

    public boolean isForgotPasswordPageDisplayed() {

        return isElementDisplayed(forgotPasswordHeading);
    }

}