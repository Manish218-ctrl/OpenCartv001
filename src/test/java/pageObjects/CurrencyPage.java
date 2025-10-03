package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class CurrencyPage extends BasePage {

        public CurrencyPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this); // Initialize elements
        }

        // Web elements for the Currency dropdown and Euro selection
        @FindBy(xpath = "/html/body/nav/div/div[1]/form/div/button/i")
        WebElement currencyDropdown;

        @FindBy(xpath = "/html/body/nav/div/div[1]/form/div/ul/li[1]/button")
        WebElement euroOption;

        @FindBy(xpath = "/html/body/nav/div/div[1]/form/div/ul/li[2]/button")  // pound Sterling
        WebElement poundSterlingOption;

        @FindBy(xpath = "/html/body/nav/div/div[1]/form/div/ul/li[3]/button")  // US Dollar option
        WebElement usDollarOption;

        public void clickCurrencyDropdown() {
            currencyDropdown.click();
        }

        public void selectEuroCurrency() {
            euroOption.click();
        }

        public void selectPoundSterlingCurrency() {
            poundSterlingOption.click();
        }

        public void selectUSDCurrency() {
            usDollarOption.click();  // Click on US Dollar option
        }
    }



