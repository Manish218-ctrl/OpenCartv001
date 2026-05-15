package pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderHistoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Order History']")
    private WebElement orderHistoryTitle;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody/tr[1]//a[contains(@title,'View')]")
    public WebElement firstOrderViewIcon;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody/tr[1]/td[1]")
    public WebElement firstOrderIdCell;

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Order History']")
    public WebElement pageTitleElement;

    @FindBy(xpath = "//div[@id='content']//h1")
    public WebElement pageHeading;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement btnContinue;

    // ACTION METHODS

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTitle() {

        wait.until(
                ExpectedConditions.visibilityOf(orderHistoryTitle)
        );

        return orderHistoryTitle.getText();
    }

    public boolean isFirstOrderViewIconVisible() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(firstOrderViewIcon)
            );

            return firstOrderViewIcon.isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }

    public void clickFirstOrderViewIcon() {

        wait.until(
                ExpectedConditions.elementToBeClickable(firstOrderViewIcon)
        ).click();
    }

    public String getOrderId() {

        wait.until(
                ExpectedConditions.visibilityOf(firstOrderIdCell)
        );

        return firstOrderIdCell.getText().trim();
    }

    public WebElement getPageTitleElement() {
        return pageTitleElement;
    }

    public String getPageHeading() {
        wait.until(ExpectedConditions.visibilityOf(pageHeading));
        return pageHeading.getText().trim();
    }

    public void clickContinueButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(btnContinue)
        ).click();
    }
}