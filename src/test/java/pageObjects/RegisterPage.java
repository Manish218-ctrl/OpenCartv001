package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static pageObjects.HomePage.logger;

public class RegisterPage extends BasePage {

        public RegisterPage(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = "//h1[normalize-space()='Register Account']")
        WebElement headingRegister;

        public boolean isRegisterPageDisplayed() {
            try {
                return headingRegister.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        // First Name
        @FindBy(id = "input-firstname")
        WebElement txtFirstName;

        // Last Name
        @FindBy(id = "input-lastname")
        WebElement txtLastName;

        // Email
        @FindBy(id = "input-email")
        WebElement txtEmail;

        // Telephone
        @FindBy(id = "input-telephone")
        WebElement txtTelephone;

        // Password
        @FindBy(id = "input-password")
        WebElement txtPassword;

        // Confirm Password
        @FindBy(id = "input-confirm")
        WebElement txtConfirmPassword;

        // Privacy Policy Checkbox
        @FindBy(name = "agree")
        WebElement chkPolicy;

        // Continue Button
        @FindBy(xpath = "//input[@value='Continue']")
        WebElement btnContinue;

        // Warning messages
        @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
        WebElement warningTop;



        // Telephone warning message (below input)
        @FindBy(xpath = "//div[contains(text(),'Telephone must be between')]")
        WebElement telephoneWarning;

        @FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
        WebElement passwordConfirmWarning;

    // Login link in Register Page
    @FindBy(xpath = "//a[normalize-space()='login page']")
    WebElement linkLoginPage;

    public void clickLoginLink() {
        linkLoginPage.click();
    }


    public String getPasswordConfirmWarning() {
            try {
                return passwordConfirmWarning.getText();
            } catch (Exception e) {
                return null;
            }
        }


        // In RegisterPage.java
        @FindBy(name = "newsletter")
        List<WebElement> newsletterOptions;

    // Password confirm warning (field-level)
    @FindBy(xpath = "//div[contains(text(),'Password confirmation does not match password!')]")
    WebElement msgPasswordMismatch;

    public String getPasswordMismatchWarning() {
        try {
            return msgPasswordMismatch.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Privacy Policy checkbox
    @FindBy(name = "agree")
    WebElement chkPrivacyPolicy;

    // Accept Privacy Policy
    public void acceptPrivacyPolicy() {
        try {
            if (!chkPrivacyPolicy.isSelected()) {
                chkPrivacyPolicy.click();
            }
        } catch (Exception e) {
            System.out.println("Privacy Policy checkbox not found: " + e.getMessage());
        }
    }




    public void selectNewsletter(String option) {
            for (WebElement el : newsletterOptions) {
                if (el.getAttribute("value").equalsIgnoreCase(option)) {
                    el.click();
                    break;
                }
            }
        }


        public String getTelephoneWarning() {
            try {
                return telephoneWarning.getText();
            } catch (Exception e) {
                return "";
            }
        }


        //ACTION METHODS

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

        public void clickContinue() {
            btnContinue.click();
        }

        public String getWarningMessage() {
            try {
                return warningTop.getText();
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


    // --- Locator for 'Address Book' in Right Column ---
    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[5]")
    public WebElement lnkRightColumnAddressBook;

    // --- Method to click Address Book ---
    public void clickAddressBook() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lnkRightColumnAddressBook)).click();
            logger.info("Clicked on 'Address Book' link in Right Column.");
        } catch (Exception e) {
            logger.error("Failed to click 'Address Book' link in Right Column: " + e.getMessage());
            Assert.fail("Unable to click Address Book link in Right Column: " + e.getMessage());
        }
    }
    }




