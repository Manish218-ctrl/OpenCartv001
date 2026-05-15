package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class DownloadsPage extends BasePage {

    public DownloadsPage(WebDriver driver) {
        super(driver);
        
        this.wait = new org.openqa.selenium.support.ui.WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    // LOCATORS

    @FindBy(xpath = "//table[contains(@class,'table-bordered')]//a[normalize-space()='View']")
    private List<WebElement> viewOrderLinks;

    @FindBy(xpath = "//ul[@class=breadcrumb]//li[last()]")
    private WebElement breadcrumbElement;

    @FindBy(xpath = "//div[@id='content']//p[contains(normalize-space(),'You have not made any previous downloadable orders!')]")
    private WebElement noDownloadsMessage;

    // ACTION METHODS

    public String getBreadcrumbText() {
        return wait.until(
                ExpectedConditions.visibilityOf(breadcrumbElement)
        ).getText();
    }

    public void clickViewOrder() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(viewOrderLinks)
        );

        if (viewOrderLinks.isEmpty()) {

            throw new RuntimeException(
                    "No View Order buttons found on the Downloads page!"
            );
        }

        wait.until(
                ExpectedConditions.elementToBeClickable(viewOrderLinks.get(0))
        ).click();
    }

    public String getNoDownloadsMessage() {

        return wait.until(
                ExpectedConditions.visibilityOf(noDownloadsMessage)
        ).getText().trim();
    }


}