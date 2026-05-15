package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//td[contains(@class,'text-left')]//a")
    public WebElement cartProductName;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody//td[contains(@class,'text-left') and normalize-space()='Product 21']")
    public WebElement cartProductModel;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody//td[contains(@class,'text-right')][1]")
    public WebElement productUnitPrice;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody//td[contains(@class,'text-right')][2]")
    public WebElement productTotalPrice;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody//img[contains(@class,'img-thumbnail')]")
    public WebElement productImage;

    @FindBy(xpath = "//a[@title='Shopping Cart']")
    private WebElement shoppingCartHeaderLink;

    @FindBy(xpath = "//div[@id='cart']//p[contains(@class,'text-right')]//a[contains(@href,'route=checkout/cart')]")
    private WebElement linkViewCartFromDropdown;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]//a[normalize-space()='Shopping Cart']")
    private WebElement breadcrumbShoppingCart;

    private By headingShoppingCart = By.xpath("//div[@id='content']//h1[contains(normalize-space(),'Shopping Cart')]");

    private By cartProductNames = By.xpath("//div[@id='content']//table[contains(@class,'table-bordered')]//td[contains(@class,'text-left')]/a");

    private final By removeProductButtonLocator = By.xpath("//button[@title='Remove' or contains(@class,'btn-danger')]");

    private final By emptyCartMessage = By.xpath("//div[@id='content']//p[contains(normalize-space(),'Your shopping cart is empty')]");

    private By productQuantityLocator(String productName) {

        return By.xpath(
                "//div[@id='content']//table//tbody//tr[td/a[normalize-space()='"
                        + productName +
                        "']]//input[contains(@name,'quantity')]"
        );
    }

    // ACTION METHODS

    public WebElement getShoppingCartHeaderLink() {

        return wait.until(
                ExpectedConditions.visibilityOf(shoppingCartHeaderLink)
        );
    }

    public void clickViewCartFromCartDropdown() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        linkViewCartFromDropdown
                )
        ).click();
    }

    public boolean isShoppingCartBreadcrumbDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(
                            breadcrumbShoppingCart
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isOnShoppingCartPage() {

        try {

            WebElement heading = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            headingShoppingCart
                    )
            );

            return heading.isDisplayed();

        } catch (Exception e) {

            String url = driver.getCurrentUrl();

            return url.contains("checkout/cart");
        }
    }

    public void goToCart() {

        driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");
    }

    public void clickShoppingCartLinkInSuccessMessage() {

        By cartLinkLocator =
                By.xpath("//div[contains(@class,'alert-success')]//a[contains(normalize-space(),'shopping cart')]");

        wait.until(
                ExpectedConditions.elementToBeClickable(cartLinkLocator)
        ).click();
    }

    public int getCartProductCount() {

        try {

            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            cartProductNames
                    )
            );

            return products.size();

        } catch (Exception e) {

            return 0;
        }
    }

    public boolean isProductDisplayedInCart(String productName) {

        return isProductInCart(productName);
    }

    public boolean isProductInCart(String productName) {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement productRow = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//div[@id='content']//table//tbody//tr//a[normalize-space()='"
                                            + productName +
                                            "']"
                            )
                    )
            );

            return productRow.isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }

    public String getProductName() {

        return wait.until(
                ExpectedConditions.visibilityOf(cartProductName)
        ).getText().trim();
    }

    public String getProductModel() {

        return wait.until(
                ExpectedConditions.visibilityOf(cartProductModel)
        ).getText().trim();
    }

    public String getProductQuantity() {

        WebElement quantityInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productQuantityLocator("HP LP3065")
                )
        );

        return quantityInput.getAttribute("value");
    }

    public String getUnitPrice() {

        return wait.until(
                ExpectedConditions.visibilityOf(productUnitPrice)
        ).getText().trim();
    }

    public String getTotalPrice() {

        return wait.until(
                ExpectedConditions.visibilityOf(productTotalPrice)
        ).getText().trim();
    }

    public boolean isProductImageDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(productImage)
            );

            return productImage.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void removeProductFromCart(String productName) {

        try {

            By removeBtn = By.xpath(
                    "//div[@id='content']//table//tbody//tr[td/a[normalize-space()='"
                            + productName +
                            "']]//button[@title='Remove']"
            );

            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(removeBtn)
            );

            btn.click();

            wait.until(ExpectedConditions.stalenessOf(btn));

        } catch (Exception e) {

            throw new NoSuchElementException(
                    "Could not find remove button for product: " + productName,
                    e
            );
        }
    }

    public void clearCart() {

        shoppingCartHeaderLink.click();

        logger.info("Starting cart cleanup...");

        try {

            List<WebElement> removeButtons =
                    driver.findElements(removeProductButtonLocator);

            while (!removeButtons.isEmpty()) {

                WebElement firstButton = removeButtons.get(0);

                firstButton.click();

                waitShort().until(
                        ExpectedConditions.stalenessOf(firstButton)
                );

                removeButtons =
                        driver.findElements(removeProductButtonLocator);
            }

            waitShort().until(
                    ExpectedConditions.visibilityOfElementLocated(
                            emptyCartMessage
                    )
            );

            logger.info("Cart successfully cleared.");

        } catch (Exception e) {

            logger.warn(
                    "Cart cleanup encountered an exception: "
                            + e.getMessage()
            );
        }
    }

    public void removeProduct(String productName) {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement productRow = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//div[@id='content']//table//tbody//tr[td/a[normalize-space()='"
                                            + productName +
                                            "']]"
                            )
                    )
            );

            WebElement removeBtn = productRow.findElement(
                    By.xpath(".//button[@title='Remove']")
            );

            removeBtn.click();

            wait.until(
                    ExpectedConditions.invisibilityOf(productRow)
            );

            logger.info(
                    productName + " removed from the cart successfully."
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to remove "
                            + productName +
                            " from the cart: " +
                            e.getMessage()
            );

            throw e;
        }
    }
}