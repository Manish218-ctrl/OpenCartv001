package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize elements
    }

    @FindBy(xpath = "//*[@id=\"input-email\"]")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//*[@id=\"input-password\"]")
    WebElement txtPassword;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div/form/input")
    WebElement btnLogin;

    @FindBy(xpath="//a[normalize-space()='Continue']")
    public WebElement btnContinueNewCustomer;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    WebElement warningMessage;

    @FindBy(linkText = "Forgotten Password")
    WebElement lnkForgotPassword;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    // Right Column -> Login link
    @FindBy(xpath = "//aside//a[normalize-space()='Login']")
    WebElement rightColumnLogin;

    public void clickRightColumnLogin() {
        rightColumnLogin.click();
    }

    // Breadcrumb
    @FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")
    WebElement breadcrumb;

    // Page Heading
    @FindBy(xpath = "//h2[normalize-space()='Returning Customer']")
    WebElement pageHeading;

    // --- Combined Login Method ---
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogin();
    }


    // Methods
    // Return breadcrumb text in a normalized form
    public String getBreadcrumb() {
        try {
            WebElement breadcrumbElement = driver.findElement(By.xpath("//ul[@class='breadcrumb']//li[last()]"));
            return breadcrumbElement.getText().trim().replaceAll("\\s+", " "); // normalize spaces
        } catch (Exception e) {
            return "";
        }
    }


    public String getPageHeading() {
        try {
            return pageHeading.getText();
        } catch (Exception e) {
            return "";
        }
    }




    // --- Getters ---
    public WebElement getLoginButton() {
        return loginButton;
    }
    public WebElement getEmailField() { return txtEmailAddress; }
    public WebElement getPasswordField() { return txtPassword; }

    // --- Actions ---
    public RegisterPage clickContinueButtonNewCustomer() {
        btnContinueNewCustomer.click();
        return new RegisterPage(driver);
    }

    public void clickForgotPassword() {
        lnkForgotPassword.click();
    }

    public boolean isLoginPageDisplayed() {
        try {
            return (txtEmailAddress.isDisplayed() && txtPassword.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public void setEmail(String email) {
        txtEmailAddress.sendKeys(email);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public String getWarningMessage() {
        try {
            return warningMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }



}
