package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

    public class DownloadsPage extends BasePage {

        WebDriver driver;
        WebDriverWait wait;

        public DownloadsPage(WebDriver driver) {
            super(driver);
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(driver, this);
        }

        // List of downloadable orders (assuming each row has a View link)
        @FindBy(xpath = "//table[@class='table table-bordered table-hover']//a[text()='View']")
        private List<WebElement> viewOrderLinks;



       // Validate breadcrumb or title
        @FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")
        private WebElement breadcrumbElement;

        public String getBreadcrumbText() {
            return wait.until(ExpectedConditions.visibilityOf(breadcrumbElement)).getText();
        }

        public void clickViewOrder() {
            List<WebElement> buttons = driver.findElements(By.cssSelector(".btn-view-order"));
            if (buttons.isEmpty()) {
                throw new RuntimeException("No 'View Order' buttons found on the Downloads page!");
            }
            buttons.get(0).click();
        }

    }
