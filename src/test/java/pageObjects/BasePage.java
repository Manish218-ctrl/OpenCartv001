package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.logger = LogManager.getLogger(this.getClass());

        PageFactory.initElements(driver, this);
    }

    //UTILITY METHODS

    protected void clickElement(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {

            element.click();

        } catch (Exception e) {

            jsClick(element);
        }
    }

    protected void typeText(WebElement element, String text) {

        wait.until(ExpectedConditions.visibilityOf(element));

        element.clear();

        element.sendKeys(text);
    }

    protected String getElementText(WebElement element) {

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        ).getText().trim();
    }

    protected void scrollIntoView(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    protected void jsClick(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    protected void hoverElement(WebElement element) {

        new Actions(driver)
                .moveToElement(element)
                .perform();
    }

    protected void selectDropdownByVisibleText(
            WebElement dropdown,
            String value
    ) {

        Select select = new Select(dropdown);

        select.selectByVisibleText(value);
    }

    protected boolean isElementDisplayed(WebElement element) {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(element)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    protected boolean isElementClickable(WebElement element) {

        try {

            return wait.until(
                    ExpectedConditions.elementToBeClickable(element)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    protected String getElementAttribute(
            WebElement element,
            String attributeName
    ) {

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        ).getAttribute(attributeName);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    protected WebDriverWait waitShort() {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected WebDriverWait waitLong() {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }
}