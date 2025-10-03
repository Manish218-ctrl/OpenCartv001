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





            // --- Locators ---
            public By headingSiteMap = By.xpath("/html/body/div[2]/div/div/h1");
            private By linkShoppingCart = By.xpath("//div[@id='content']//a[normalize-space()='Shopping Cart']");
            // Locator for Order History link inside Site Map
            private By linkOrderHistory = By.xpath("//div[@id='content']//a[normalize-space()='Order History']");


            // --- Methods ---
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
                WebElement footerLink = driver.findElement(By.xpath("/html/body/footer/div/div/div[2]/ul/li[3]/a"));
                wait.until(ExpectedConditions.elementToBeClickable(footerLink)).click();
            }


            // Method to click on Order History
            public void clickOrderHistoryLink () {
                wait.until(ExpectedConditions.elementToBeClickable(linkOrderHistory)).click();
            }


            // --- Locator for 'Address Book' link in Site Map page ---
            @FindBy(xpath = "//a[normalize-space()='Address Book']")
            public WebElement lnkAddressBook;

            // --- Method to click 'Address Book' link ---
            public void clickAddressBook () {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                try {
                    WebElement addressBookLink = wait.until(ExpectedConditions.elementToBeClickable(lnkAddressBook));

                    // Scroll into view in case it's hidden
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressBookLink);

                    addressBookLink.click();
                } catch (Exception e) {
                    Assert.fail("Unable to click 'Address Book' link: " + e.getMessage());
                }
            }


            // --- Locator for 'Account Information' link ---
            private By linkAccountInformation = By.xpath("//div[@id='content']//a[normalize-space()='Account Information']");

            // --- Method to click 'Account Information' ---
            public void clickAccountInformation () {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(linkAccountInformation));

                // Scroll into view in case it is not visible
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

                element.click();
            }

            // Locator for "Password" link
            private By lnkPassword = By.xpath("//div[@id='content']//a[normalize-space()='Password']");


            // Method to click "Password" link
            public void clickPasswordLink () {
                WebElement passwordLink = driver.findElement(lnkPassword);
                passwordLink.click();
            }

   /* public boolean isOnSiteMapPage() {
        return driver.findElement(By.xpath("//h2[text()='Site Map']")).isDisplayed();
    }*/


}


