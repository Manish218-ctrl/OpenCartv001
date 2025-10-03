// OrderHistoryPage.java
package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderHistoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "/html/body/div[2]/div/div/h1")
    private WebElement orderHistoryTitle;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/tbody/tr[1]/td[7]/a/i")
    public WebElement firstOrderViewIcon;

    @FindBy(xpath = "/html/body/div[2]/div/div/table[1]/tbody/tr/td[1]/b[1]")
    public WebElement firstOrderIdCell;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(orderHistoryTitle));
        return orderHistoryTitle.getText();
    }

    public boolean isFirstOrderViewIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstOrderViewIcon));
            return firstOrderViewIcon.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickFirstOrderViewIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(firstOrderViewIcon)).click();
    }

    public String getOrderId() {
        wait.until(ExpectedConditions.visibilityOf(firstOrderIdCell));
        return firstOrderIdCell.getText().trim();
    }

    @FindBy(xpath = "//h1[text()='Order History']")
    public WebElement pageTitleElement;

    public WebElement getPageTitleElement() {
        return pageTitleElement;
    }

}
