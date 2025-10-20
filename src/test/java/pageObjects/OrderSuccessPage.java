package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageObjects.HomePage.logger;

public class OrderSuccessPage extends BasePage {

        WebDriver driver;

        // Constructor
        public OrderSuccessPage(WebDriver driver) {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this); // Initialize WebElements
        }

        // Locator for the 'store owner' link
        @FindBy(xpath = "//a[normalize-space()='store owner']")
        private WebElement storeOwnerLink;

        // Method to click on 'store owner' link
        public void clickStoreOwnerLink() {
            storeOwnerLink.click();
        }

        // Locator for the 'My Account' link on the success page
        @FindBy(xpath = "//div[@class='buttons']//a[normalize-space()='My Account']")
        private WebElement lnkMyAccount;

        // Locator for the success message heading
        @FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
        private WebElement msgSuccessHeading;


        // Method to check if the Order Success page is displayed
        public boolean isOrderSuccessPageDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                return wait.until(ExpectedConditions.visibilityOf(msgSuccessHeading)).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        // Method to click the 'My account' link
        public void clickMyAccountLink() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(lnkMyAccount)).click();
            logger.info("Clicked on 'My account' link from Order Success page.");
        }
    }



