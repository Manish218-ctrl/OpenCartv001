package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Register Account']")
    WebElement headingRegister;

    @FindBy(id = "input-firstname")
    WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    WebElement txtLastName;

    @FindBy(id = "input-email")
    WebElement txtEmail;

    @FindBy(id = "input-telephone")
    WebElement txtTelephone;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(id = "input-confirm")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPrivacyPolicy;

    @FindBy(xpath = "//div[contains(@class,'buttons')]//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//div[contains(@class,'alert') and contains(@class,'alert-danger')]")
    WebElement warningTop;

    @FindBy(xpath = "//input[@id='input-telephone']/parent::div/following-sibling::div[contains(@class,'text-danger')]")
    WebElement telephoneWarning;

    @FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div[contains(@class,'text-danger')]")
    WebElement passwordConfirmWarning;

    @FindBy(xpath = "//div[contains(normalize-space(),'Password confirmation does not match password')]")
    WebElement msgPasswordMismatch;

    @FindBy(xpath = "//div[@id='content']//a[contains(@href,'account/login') and normalize-space()='login page']")
    WebElement linkLoginPage;

    @FindBy(xpath = "//input[@name='newsletter']")
    List<WebElement> newsletterOptions;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'account/address') and normalize-space()='Address Book']")
    public WebElement lnkRightColumnAddressBook;

    // ACTION METHODS

    public boolean isRegisterPageDisplayed() {

        try {

            return headingRegister.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void setFirstName(String fname) {

        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {

        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {

        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tel) {

        txtTelephone.clear();
        txtTelephone.sendKeys(tel);
    }

    public void setPassword(String pwd) {

        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String pwd) {

        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(pwd);
    }

    public void setPrivacyPolicy() {

        if (!chkPolicy.isSelected()) {

            chkPolicy.click();
        }
    }

    public void acceptPrivacyPolicy() {

        try {

            if (!chkPrivacyPolicy.isSelected()) {

                chkPrivacyPolicy.click();
            }

        } catch (Exception e) {

            System.out.println(
                    "Privacy Policy checkbox not found: "
                            + e.getMessage()
            );
        }
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public void clickLoginLink() {
        linkLoginPage.click();
    }

    public void selectNewsletter(String option) {

        for (WebElement el : newsletterOptions) {

            if (el.getAttribute("value")
                    .equalsIgnoreCase(option)) {

                el.click();
                break;
            }
        }
    }

    public void clickAddressBook() {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            lnkRightColumnAddressBook
                    )
            ).click();

            logger.info(
                    "Clicked on Address Book link in Right Column."
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to click Address Book link in Right Column: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Unable to click Address Book link in Right Column: "
                            + e.getMessage()
            );
        }
    }

    public String getWarningMessage() {

        try {

            return warningTop.getText();

        } catch (Exception e) {

            return "";
        }
    }

    public String getTelephoneWarning() {

        try {

            return telephoneWarning.getText();

        } catch (Exception e) {

            return "";
        }
    }

    public String getTelephoneWarningMessage() {

        try {

            return telephoneWarning.getText();

        } catch (Exception e) {

            return "";
        }
    }

    public String getPasswordConfirmWarning() {

        try {

            return passwordConfirmWarning.getText();

        } catch (Exception e) {

            return null;
        }
    }

    public String getPasswordMismatchWarning() {

        try {

            return msgPasswordMismatch.getText();

        } catch (Exception e) {

            return "";
        }
    }
}