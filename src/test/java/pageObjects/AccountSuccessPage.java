package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage extends BasePage {

    public AccountSuccessPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//h1[contains(text(),Your Account Has Been Created!)] | //h1[normalize-space()='Account Logout']")
    WebElement msgConfirmation;

    @FindBy(xpath = "//a[text()=Logout]")
    WebElement linkLogout;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement btnContinueSuccess;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement successHeading;

    // ACTION METHODS

    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText();

        } catch (Exception e) {
            return "";
        }
    }

    public void clickLogout() {
        try {
            linkLogout.click();

        } catch (Exception e) {

            System.out.println(
                    "Logout link not found: " + e.getMessage()
            );
        }
    }

    public boolean isAccountCreated() {
        try {
            return successHeading.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueOnSuccessPage() {
        try {
            btnContinueSuccess.click();

        } catch (Exception e) {

            System.out.println(
                    "Continue button not found on success page: "
                            + e.getMessage()
            );
        }
    }
}