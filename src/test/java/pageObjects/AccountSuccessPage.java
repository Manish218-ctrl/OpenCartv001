package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class AccountSuccessPage {

        WebDriver driver;

        public AccountSuccessPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        // Success message heading
        @FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')] | //h1[normalize-space()='Account Logout']")
        WebElement msgConfirmation;

        // Logout link (top-right menu)
        @FindBy(xpath = "//a[text()='Logout']")
        WebElement linkLogout;

        @FindBy(xpath="//a[normalize-space()='Continue']")
        WebElement btnContinueSuccess;

        // Method to get confirmation message
        public String getConfirmationMsg() {
            try {
                return msgConfirmation.getText();
            } catch (Exception e) {
                return "";
            }
        }

        // Click Logout link
        public void clickLogout() {
            try {
                linkLogout.click();
            } catch (Exception e) {
                System.out.println("Logout link not found: " + e.getMessage());
            }
        }

        // Success heading on account creation page
        @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
        WebElement successHeading;

        public boolean isAccountCreated() {
            try {
                return successHeading.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        // Method to click the 'Continue' button on the success/logout page
        public void clickContinueOnSuccessPage() {
            try {
                btnContinueSuccess.click();
            } catch (Exception e) {
                System.out.println("Continue button not found on success page: " + e.getMessage());
            }
        }
    }


