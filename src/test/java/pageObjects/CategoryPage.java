package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CategoryPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CategoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Top Menu ---
    @FindBy(xpath = "//a[normalize-space()='Desktops']")
    public WebElement menuDesktops;

    @FindBy(xpath = "/html/body/div[1]/nav/div[2]/ul/li[1]/div/a")
    public WebElement linkShowAllDesktops;

    // --- Left Sidebar Subcategories ---
    @FindBy(xpath = "/html/body/div[2]/div/aside/div[1]/a[3]")
    public WebElement linkMacSubCategory;

    // --- Products ---
    @FindBy(xpath = "//div[contains(@class,'product-layout')]")
    public List<WebElement> productCards;

    private By addToCartButton(String productName) {
        return By.xpath("//a[normalize-space()='" + productName + "']/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'cart.add')]");
    }

    // --- Success Message ---
    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    public WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[normalize-space()='shopping cart']")
    public WebElement linkShoppingCart;

    // --- Actions ---

    /** Hover over Desktops menu */
    public void hoverOnDesktopsMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(menuDesktops).perform();
        wait.until(ExpectedConditions.visibilityOf(linkShowAllDesktops));
    }

    /** Click on Show All Desktops */
    public void clickShowAllDesktops() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(linkShowAllDesktops)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", linkShowAllDesktops);
        }
        wait.until(ExpectedConditions.urlContains("path=20"));
    }

    /** Click Mac subcategory */
    public void clickMacSubCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(linkMacSubCategory)).click();
        wait.until(ExpectedConditions.urlContains("path=20_27"));
    }

    /** Add product to cart by product name */
    public void addProductToCart(String productName) {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton(productName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    /** Get success message text */
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
    }

    /** Click Shopping Cart link inside success message */
    public ShoppingCartPage clickShoppingCartLinkInSuccessMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkShoppingCart)).click();
        return new ShoppingCartPage(driver);
    }

    /** Get list of displayed products */
    public List<WebElement> getDisplayedProducts() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
    }

    // --- Continue Button on empty category page ---
    @FindBy(xpath = "//a[normalize-space()='Continue']")
    public  WebElement btnContinue;

    /** Click the Continue button (used when category is empty) */
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

}
