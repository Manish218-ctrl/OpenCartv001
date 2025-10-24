package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SiteMapPage extends BasePage {

        private static WebDriver driver;

        public SiteMapPage(WebDriver driver) {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }


            public By headingSiteMap = By.xpath("/html/body/div[2]/div/div/h1");
            private By linkShoppingCart = By.xpath("/html/body/div[2]/div/div/div/div[2]/ul/li[3]/a");
            private By linkOrderHistory = By.xpath("/html/body/div[2]/div/div/div/div[2]/ul/li[2]/ul/li[4]/a");


            public boolean isOnSiteMapPage () {
                try {
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(headingSiteMap)).isDisplayed();
                } catch (Exception e) {
                    return false;
                }
            }

            public void clickShoppingCartLink () {
                WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(linkShoppingCart));
                cartLink.click();
            }

            public static void clickFooterLink (String linkText){
                WebElement footerLink = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/ul/li[1]/a"));
                wait.until(ExpectedConditions.elementToBeClickable(footerLink)).click();
            }


            public void clickOrderHistoryLink () {
                wait.until(ExpectedConditions.elementToBeClickable(linkOrderHistory)).click();
            }


            @FindBy(xpath = "//a[normalize-space()='Address Book']")
            public WebElement lnkAddressBook;

            public void clickAddressBook () {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                try {
                    WebElement addressBookLink = wait.until(ExpectedConditions.elementToBeClickable(lnkAddressBook));

                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressBookLink);

                    addressBookLink.click();
                } catch (Exception e) {
                    Assert.fail("Unable to click 'Address Book' link: " + e.getMessage());
                }
            }


            private By linkAccountInformation = By.xpath("//div[@id='content']//a[normalize-space()='Account Information']");

            public void clickAccountInformation () {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(linkAccountInformation));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

                element.click();
            }

            private By lnkPassword = By.xpath("//div[@id='content']//a[normalize-space()='Password']");


            public void clickPasswordLink () {
                WebElement passwordLink = driver.findElement(lnkPassword);
                passwordLink.click();
            }

}


