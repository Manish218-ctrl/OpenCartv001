package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

    public class ForgotPasswordPage extends BasePage {

        public ForgotPasswordPage(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = "//h1[text()='Forgot Your Password?']")
        WebElement forgotPasswordHeading;

        public boolean isForgotPasswordPageDisplayed() {
            try {
                return forgotPasswordHeading.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }
    }



