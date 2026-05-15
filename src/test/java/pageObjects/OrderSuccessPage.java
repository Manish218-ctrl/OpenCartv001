package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderSuccessPage extends BasePage {

        WebDriver driver;

        public OrderSuccessPage(WebDriver driver) {
            super(driver);
            this.driver = driver;
            
        }

        //LOCATORS

        @FindBy(xpath = "//a[normalize-space()='store owner']")
        private WebElement storeOwnerLink;

        @FindBy(xpath = "//div[@class=buttons]//a[normalize-space()='My Account']")
        private WebElement lnkMyAccount;

        @FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
        private WebElement msgSuccessHeading;


    //ACTION METHODS

    public void clickStoreOwnerLink() {
        storeOwnerLink.click();
    }


    public boolean isOrderSuccessPageDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                return wait.until(ExpectedConditions.visibilityOf(msgSuccessHeading)).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        public void clickMyAccountLink() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(lnkMyAccount)).click();
            logger.info("Clicked on My account link from Order Success page.");
        }
    }



