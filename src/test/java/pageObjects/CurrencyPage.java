package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CurrencyPage extends BasePage {

    public CurrencyPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//form[@id='form-currency']//button[contains(@class,'dropdown-toggle')]")
    WebElement currencyDropdown;

    @FindBy(xpath = "//form[@id='form-currency']//button[@name='EUR' and contains(@class,'currency-select')]")
    WebElement euroOption;

    @FindBy(xpath = "//form[@id='form-currency']//button[@name='GBP' and contains(@class,'currency-select')]")
    WebElement poundSterlingOption;

    @FindBy(xpath = "//form[@id='form-currency']//button[@name='USD' and contains(@class,'currency-select')]")
    WebElement usDollarOption;

    private final By currencySymbolDisplay =
            By.xpath(
                    "//form[@id='form-currency']" +
                            "//button[contains(@class,'dropdown-toggle')]" +
                            "//strong"
            );

    // ACTION METHODS

    public String getCurrentSelectedCurrencySymbol() {

        WebElement currencyElement =
                waitShort().until(
                        ExpectedConditions.visibilityOfElementLocated(
                                currencySymbolDisplay
                        )
                );

        return currencyElement.getText();
    }

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
        usDollarOption.click();
    }
}