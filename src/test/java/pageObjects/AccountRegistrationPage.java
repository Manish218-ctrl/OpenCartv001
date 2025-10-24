package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }


    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtLasttname;

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    @FindBy(xpath="//input[@name='newsletter'][@value='1']")
    WebElement optNewsletterYes;

    @FindBy(xpath="//a[normalize-space()='Continue']")
    WebElement btnContinueSuccess;

    // Warning messages
    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    WebElement warnPrivacyPolicy;

    @FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
    WebElement warnFirstName;

    @FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
    WebElement warnLastName;

    @FindBy(xpath="//input[@id='input-email']/following-sibling::div")
    WebElement warnEmail;

   // Telephone warning message
    @FindBy(xpath = "//input[@id='input-telephone']/following::div[@class='text-danger'][1]")
    WebElement txtTelephoneWarning;

    @FindBy(xpath="//input[@id='input-password']/following-sibling::div")
    WebElement warnPassword;

    @FindBy(xpath="//input[@name='newsletter'][@value='0']")
    WebElement optNewsletterNo;

    @FindBy(xpath="//input[@id='input-confirm']/following-sibling::div")
    WebElement warnPasswordMismatch;

    public String getPasswordMismatchWarning() {
        return warnPasswordMismatch.getText();
    }

    // Warning for invalid email
    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    WebElement emailInvalidWarning;

    public String getEmailInvalidWarning() {
        try {
            return emailInvalidWarning.getText();
        } catch (Exception e) {
            return "";
        }
    }




    public void setNewsletterNo() {
        optNewsletterNo.click();
    }


    // Getter methods
    public String getPrivacyPolicyWarning() {
        return warnPrivacyPolicy.getText();
    }

    public String getFirstNameWarning() {
        return warnFirstName.getText();
    }

    public String getLastNameWarning() {
        return warnLastName.getText();
    }

    public String getEmailWarning() {
        return warnEmail.getText();
    }

    public String getTelephoneWarning() {
        try {
            return txtTelephoneWarning.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPasswordWarning() {
        return warnPassword.getText();
    }


    public void setNewsletterYes() {
        optNewsletterYes.click();
    }

    public void clickContinueOnSuccessPage() {
        btnContinueSuccess.click();
    }


    public void setFirstName(String fname) {
        txtFirstname.sendKeys(fname);

    }

    public void setLastName(String lname) {
        txtLasttname.sendKeys(lname);

    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);

    }

    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);

    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);

    }

    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd);

    }

    public void setPrivacyPolicy() {
        chkdPolicy.click();

    }

    public void clickContinue() {

        btnContinue.click();


    }



    public String getConfirmationMsg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));
            return successElement.getText().trim();
        } catch (Exception e1) {
            try {
                WebElement pwdError = driver.findElement(
                        By.xpath("//input[@id='input-password']/following-sibling::div[contains(@class,'text-danger')]"));
                if (pwdError.isDisplayed()) {
                    return pwdError.getText().trim();
                }
            } catch (Exception e2) {}

            try {
                WebElement confirmError = driver.findElement(
                        By.xpath("//input[@id='input-confirm']/following-sibling::div[contains(@class,'text-danger')]"));
                if (confirmError.isDisplayed()) {
                    return confirmError.getText().trim();
                }
            } catch (Exception e3) {}

            try {
                WebElement alert = driver.findElement(
                        By.xpath("//div[contains(@class,'alert') and contains(@class,'danger')]"));
                if (alert.isDisplayed()) {
                    return alert.getText().trim();
                }
            } catch (Exception e4) {}

            return "No validation message found";
        }
    }

}
