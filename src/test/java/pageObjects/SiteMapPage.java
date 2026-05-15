package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SiteMapPage extends BasePage {

    public SiteMapPage(WebDriver driver) {
        super(driver);
        
    }

    //LOCATORS

    @FindBy(xpath = "//div[@id='content']//a[normalize-space()='Address Book']")
    private WebElement lnkAddressBook;

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Site Map']")
    private WebElement headingSiteMap;

    @FindBy(xpath = "//div[@id='content']//a[normalize-space()='Shopping Cart']")
    private WebElement linkShoppingCart;

    @FindBy(xpath = "//div[@id='content']//a[normalize-space()='My Account']/following-sibling::ul//a[normalize-space()='Order History']")
    private WebElement linkOrderHistory;

    @FindBy(xpath = "//div[@id='content']//a[normalize-space()='My Account']/following-sibling::ul//a[normalize-space()='Account Information']")
    private WebElement linkAccountInformation;

    @FindBy(xpath = "//div[@id='content']//a[normalize-space()='My Account']/following-sibling::ul//a[normalize-space()='Password']")
    private WebElement lnkPassword;

    //DYNAMIC LOCATORS

    private By footerLink(String linkText) {
        return By.xpath("//footer//a[normalize-space()='" + linkText + "']");
    }

    //ACTION METHODS

    public boolean isOnSiteMapPage() {

        try {
            return wait.until(ExpectedConditions.visibilityOf(headingSiteMap)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickShoppingCartLink() {
        clickElement(linkShoppingCart);
        logger.info("Clicked Shopping Cart link.");
    }

    public void clickFooterLink(String linkText) {

        WebElement footerLinkElement =
                wait.until(ExpectedConditions.elementToBeClickable(footerLink(linkText)));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                footerLinkElement
        );

        footerLinkElement.click();

        logger.info("Clicked footer link: {}", linkText);
    }

    public void clickOrderHistoryLink() {
        clickElement(linkOrderHistory);
        logger.info("Clicked Order History link.");
    }

    public void clickAddressBook() {

        try {
            clickElement(lnkAddressBook);
            logger.info("Clicked Address Book link.");
        } catch (Exception e) {
            Assert.fail("Unable to click Address Book link: " + e.getMessage());
        }
    }

    public void clickAccountInformation() {
        clickElement(linkAccountInformation);
        logger.info("Clicked Account Information link.");
    }

    public void clickPasswordLink() {
        clickElement(lnkPassword);
        logger.info("Clicked Password link.");
    }
}