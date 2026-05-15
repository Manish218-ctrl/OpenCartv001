package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TransactionsPage extends BasePage {

    public TransactionsPage(WebDriver driver) {
        super(driver);
        
    }

    //Locators

    @FindBy(xpath = "//div[@id='content']/h1")
    public WebElement breadcrumbElementT;

    @FindBy(xpath = "//div[@id='content']/h1")
    public WebElement headingYourTransactions;

    @FindBy(xpath = "//div[@id='content']/p[contains(.,'balance')]")
    private WebElement txtBalance;

    @FindBy(xpath = "//table[contains(@class,'table-bordered') and contains(@class,'table-hover')]//thead/tr/td")
    private List<WebElement> tableHeaders;

    @FindBy(xpath = "//table[contains(@class,'table-bordered') and contains(@class,'table-hover')]//tbody/tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//div[@class='buttons clearfix']//a[contains(@class,'btn-primary')]")
    public WebElement btnContinue;

    //Action Methods

    public String getBreadcrumbTextt() {
        try {
            wait.until(ExpectedConditions.visibilityOf(breadcrumbElementT));
            return breadcrumbElementT.getText().trim();
        } catch (TimeoutException e) {
            return "Breadcrumb not found";
        }
    }

    public boolean isTransactionsHeadingDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(headingYourTransactions)).isDisplayed();
    }

    public String getBalanceText() {
        wait.until(ExpectedConditions.visibilityOf(txtBalance));
        return txtBalance.getText().trim();
    }

    public String[] getTableHeaders() {
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
        return tableHeaders.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toArray(String[]::new);
    }

    public int getTableRowCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
        return tableRows.size();
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

    public String getBreadcrumbText() {
        return driver.findElement(By.cssSelector("ul.breadcrumb")).getText();
    }
}