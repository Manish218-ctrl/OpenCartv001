package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransactionsPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public TransactionsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Breadcrumb element (last breadcrumb item = current page name)
    @FindBy(xpath = "/html/body/div[2]/div/div/h1")
    public WebElement breadcrumbElementT;



    // Method to get breadcrumb text safely
    public String getBreadcrumbTextt() {
        try {
            wait.until(ExpectedConditions.visibilityOf(breadcrumbElementT));
            return breadcrumbElementT.getText().trim();
        } catch (TimeoutException e) {
            return "Breadcrumb not found";
        }
    }



    // TransactionsPage.java

    // Replace old locator
    @FindBy(xpath = "/html/body/div[2]/div/div/h1")
    public WebElement headingYourTransactions;


    // Balance text
    @FindBy(xpath = "/html/body/div[2]/div/div/p")
    private WebElement txtBalance;

    // Table Headers
    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//thead/tr/td")
    private List<WebElement> tableHeaders;

    // Table Rows
    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody/tr")
    private List<WebElement> tableRows;

    // --- METHODS ---

    public boolean isTransactionsHeadingDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(headingYourTransactions)).isDisplayed();
    }

    public String getBalanceText() {
        wait.until(ExpectedConditions.visibilityOf(txtBalance));
        return txtBalance.getText().trim();
    }

    public String[] getTableHeaders() {
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
        return tableHeaders.stream().map(WebElement::getText).map(String::trim).toArray(String[]::new);
    }

    public int getTableRowCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders)); // ensure table loaded
        return tableRows.size();
    }

    // Locator for Continue button on Transactions page
    @FindBy(xpath = "/html/body/div[2]/div/div/div[3]/div/a")
    public WebElement btnContinue;

    // Method to click Continue
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }


    public String getBreadcrumbText() {
        return driver.findElement(By.cssSelector(".breadcrumb")).getText();
    }

}
