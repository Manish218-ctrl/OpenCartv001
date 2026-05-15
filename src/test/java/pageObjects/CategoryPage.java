package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {

        super(driver);
        
    }

    // LOCATORS
    @FindBy(xpath = "//nav[@id='menu']//a[normalize-space()='Desktops']")
    private WebElement menuDesktops;

    @FindBy(xpath = "//nav[@id='menu']//a[contains(@class,'see-all') and contains(normalize-space(),'Desktops')]")
    private WebElement linkShowAllDesktops;

    @FindBy(xpath = "//a[contains(@href,'path=20_27') and contains(normalize-space(),'Mac')]")
    private WebElement linkMacSubCategory;

    @FindBy(xpath = "//div[contains(@class,'product-layout')]")
    private List<WebElement> productCards;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(normalize-space(),'shopping cart')]")
    private WebElement linkShoppingCart;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement btnContinue;

    // DYNAMIC LOCATORS

    private By addToCartButton(String productName) {

        return By.xpath(
                "//div[contains(@class,'product-thumb')]" +
                        "[.//div[contains(@class,'caption')]//a[normalize-space()='"
                        + productName +
                        "']]//button[contains(@onclick,'cart.add')]"
        );
    }

    private By addToWishListButton(String productName) {

        return By.xpath(
                "//div[contains(@class,'product-thumb')]" +
                        "[.//div[contains(@class,'caption')]//a[normalize-space()='"
                        + productName +
                        "']]//button[contains(@onclick,'wishlist.add')]"
        );
    }

    // ACTION METHODS

    public void hoverOnDesktopsMenu() {

        wait.until(
                ExpectedConditions.visibilityOf(menuDesktops)
        );

        Actions actions = new Actions(driver);

        actions.moveToElement(menuDesktops)
                .perform();

        wait.until(
                ExpectedConditions.visibilityOf(linkShowAllDesktops)
        );

        logger.info("Hovered on Desktops menu");
    }

    public void clickShowAllDesktops() {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(linkShowAllDesktops)
            ).click();

        } catch (Exception e) {

            logger.warn("Normal click failed for Show All Desktops. Using JavaScript fallback.");

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    linkShowAllDesktops
            );
        }

        wait.until(
                ExpectedConditions.urlContains("path=20")
        );

        logger.info("Navigated to Show All Desktops page");
    }

    public void clickMacSubCategory() {

        wait.until(
                ExpectedConditions.elementToBeClickable(linkMacSubCategory)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("path=20_27")
        );

        logger.info("Navigated to Mac subcategory page");
    }

    public void addProductToCart(String productName) {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton(productName)
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                btn
        );

        logger.info("Added product to cart: {}", productName);
    }

    public void addProductToWishList(String productName) {

        try {

            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            addToWishListButton(productName)
                    )
            );

            btn.click();

        } catch (Exception e) {

            logger.warn("Normal click failed for wishlist button. Using JavaScript fallback.");

            WebElement btn = driver.findElement(
                    addToWishListButton(productName)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    btn
            );
        }

        logger.info("Added product to wishlist: {}", productName);
    }

    public String getSuccessMessage() {

        return wait.until(
                ExpectedConditions.visibilityOf(successMessage)
        ).getText().trim();
    }

    public ShoppingCartPage clickShoppingCartLinkInSuccessMessage() {

        wait.until(
                ExpectedConditions.elementToBeClickable(linkShoppingCart)
        ).click();

        logger.info("Clicked Shopping Cart link from success message");

        return new ShoppingCartPage(driver);
    }

    public List<WebElement> getDisplayedProducts() {

        return wait.until(
                ExpectedConditions.visibilityOfAllElements(productCards)
        );
    }

    public String getFirstDisplayedProductName() {

        String productName = getDisplayedProducts()
                .get(0)
                .findElement(By.cssSelector(".caption a"))
                .getText()
                .trim();

        logger.info("First displayed product: {}", productName);

        return productName;
    }

    public void clickContinue() {

        wait.until(
                ExpectedConditions.elementToBeClickable(btnContinue)
        ).click();

        logger.info("Clicked Continue button");
    }
}