package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;


public class ShoppingCartPage extends BasePage {

    private WebDriver driver;
   // private WebDriverWait wait;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    // --- Locators ---
    private By headingShoppingCart = By.xpath("//*[@id='content']/h1");
    private By cartProductNames = By.cssSelector("table.table.table-bordered td.text-left a");
    private final By removeProductButtonLocator = By.xpath("//button[@data-original-title='Remove' or contains(@class, 'btn-danger')]");
    private final By shoppingCartHeaderLink = By.xpath("//a[@title='Shopping Cart']");
    private final By emptyCartMessage = By.xpath("//div[@id='content']/p[text()='Your shopping cart is empty!']");

    // --- Methods ---

    // Verify we are on Shopping Cart page (by heading or fallback URL check)
    public boolean isOnShoppingCartPage() {
        try {
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingShoppingCart));
            System.out.println("DEBUG >>> Found Shopping Cart heading: " + heading.getText());
            return heading.isDisplayed();
        } catch (Exception e) {
            System.out.println("DEBUG >>> Heading not found, checking URL instead...");
            String url = driver.getCurrentUrl();
            System.out.println("DEBUG >>> Current URL: " + url);
            return url.contains("checkout/cart");
        }
    }

    // Get count of products in cart
    public int getCartProductCount() {
        try {
            List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartProductNames));
            return products.size();
        } catch (Exception e) {
            return 0;
        }
    }


    // Remove product by name
    public void removeProductFromCart(String productName) {
        try {
            By removeBtn = By.xpath("//a[text()='" + productName + "']/ancestor::tr//button[@data-original-title='Remove']");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
            btn.click();
            wait.until(ExpectedConditions.stalenessOf(btn));
        } catch (Exception e) {
            throw new NoSuchElementException("Could not find remove button for product: " + productName, e);
        }
    }

    public boolean isProductDisplayedInCart(String productName) {
        return isProductInCart(productName);
    }

    public void goToCart() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");
    }


    @FindBy(xpath = "//table[@class='table table-bordered']//td[@class='text-left']//a")
    public WebElement cartProductName;

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(cartProductName)).getText().trim();
    }

    @FindBy(xpath = "//table[@class='table table-bordered']//td[contains(text(),'Model')]")
    public WebElement cartProductModel;

    public String getProductModel() {
        return wait.until(ExpectedConditions.visibilityOf(cartProductModel)).getText().trim();
    }

    // Locator for quantity input for a specific product
    private By productQuantityLocator(String productName) {
        return By.xpath("//tr[td[text()='" + productName + "']]//td[@class='quantity']//input");
    }

    public String getProductQuantity() {
        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(productQuantityLocator("HP LP3065")));
        return quantityInput.getAttribute("value");
    }


    @FindBy(xpath = "//table[@class='table table-bordered']//td[@class='text-right']")
    public WebElement productUnitPrice;

    public String getUnitPrice() {
        return wait.until(ExpectedConditions.visibilityOf(productUnitPrice)).getText().trim();
    }


    @FindBy(xpath = "//table[@class='table table-bordered']//td[@class='text-right']//strong")
    public WebElement productTotalPrice;

    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOf(productTotalPrice)).getText().trim();
    }

    public void clickShoppingCartLinkInSuccessMessage() {
        By cartLinkLocator = By.xpath("/html/body/div[2]/div[1]/a[2]");
        // Code to click the shopping cart link.
    }

    @FindBy(xpath = "//table[@class='table table-bordered']//tr//td//img")
    public WebElement productImage;

    public boolean isProductImageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productImage));  // Wait for image to be visible
            return productImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void removeProduct(String productName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Locate the row containing the product
            WebElement productRow = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[@id='content']//a[normalize-space(text())='" + productName + "']/ancestor::tr")
                    )
            );

            // Find the remove button inside that row
            WebElement removeBtn = productRow.findElement(By.xpath(".//button[@data-original-title='Remove']"));
            removeBtn.click();

            // Wait for the row to disappear after removal
            wait.until(ExpectedConditions.invisibilityOf(productRow));

            logger.info(productName + " removed from the cart successfully.");
        } catch (Exception e) {
            logger.error("Failed to remove " + productName + " from the cart: " + e.getMessage());
            throw e;
        }
    }


    public boolean isProductInCart(String productName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement productRow = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']//a[normalize-space(text())='" + productName + "']")
                    )
            );
            return productRow.isDisplayed();
        } catch (TimeoutException e) {
            return false; // product not found
        }
    }


    public void clearCart() {
        driver.findElement(shoppingCartHeaderLink).click();

        logger.info("Starting cart cleanup...");

        try {
            // Find all 'Remove' buttons
            List<WebElement> removeButtons = driver.findElements(removeProductButtonLocator);

            // Loop as long as remove buttons are visible
            while (!removeButtons.isEmpty()) {
                WebElement firstButton = removeButtons.get(0);
                firstButton.click();
                logger.info("Removed an item. Waiting for cart table refresh.");

                waitShort().until(ExpectedConditions.stalenessOf(firstButton));

                // Re-find the buttons
                removeButtons = driver.findElements(removeProductButtonLocator);
            }

            // Final check: Wait for the "Your shopping cart is empty!" message
            waitShort().until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
            logger.info("Cart successfully cleared.");

        } catch (Exception e) {
            logger.warn("Cart cleanup encountered an exception or failed to remove all items: " + e.getMessage());


        }

    }

}

