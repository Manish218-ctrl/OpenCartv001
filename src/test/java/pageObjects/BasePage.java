package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BaseClass;

import java.time.Duration;

public class BasePage extends BaseClass {

    protected static WebDriver driver;
    public static WebDriverWait wait;

    // Constructor with WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Optional helper for dynamic waits
    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Add common methods for all pages here
    public  String getPageTitle() {
        return driver.getTitle();
    }
}
