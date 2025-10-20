package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static pageObjects.HomePage.logger;

public class ProductDisplayPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductDisplayPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locators ---
    @FindBy(xpath = "//button[@data-original-title='Compare this Product'] | //button[contains(@aria-label,'Compare')]")
    public WebElement btnCompareProduct;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    public WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'compare')]")
    public WebElement linkProductComparison;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[not(contains(@href,'compare'))]")
    public WebElement linkProductNameInSuccessMessage;

   // public final By relatedProductCards = By.xpath("//h3[normalize-space()='Related Products']/following-sibling::div//div[contains(@class,'product-layout')]");


    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(text(),'shopping cart')]")
    public WebElement linkShoppingCartInSuccessMessage;


    @FindBy(xpath = "(//div[@class='product-thumb'])[1]//button[contains(@onclick,'wishlist.add')]")
    public WebElement firstRelatedAddToWishListBtn;

    @FindBy (xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div[3]/button[1]")
    public WebElement firstRelatedAddToCartBtn;




    // Link inside success message → "wish list!"
    @FindBy(xpath = "//a[text()='wish list']")
    public WebElement linkWishList;

    public void clickWishListLink() {
        wait.until(ExpectedConditions.elementToBeClickable(linkWishList)).click();
    }

    // --- Methods ---

    public void clickProductNameLinkInSuccessMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkProductNameInSuccessMessage)).click();
    }

    public void hoverOnCompareButton() {
        wait.until(ExpectedConditions.visibilityOf(btnCompareProduct));
        new Actions(driver).moveToElement(btnCompareProduct).perform();
    }

    public boolean isCompareTooltipDisplayed() {
        try {
            String tooltipText = btnCompareProduct.getAttribute("data-original-title");
            return tooltipText != null && tooltipText.trim().equalsIgnoreCase("Compare this Product");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCompareThisProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCompareProduct)).click();
    }

    public String getSuccessMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickProductComparisonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(linkProductComparison)).click();
    }




    // Related products section heading
    private By relatedProductsSection = By.xpath("/html/body/div[2]/div/div/h3");

    // First related product name
    private By firstRelatedProductName = By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[2]/h4/a");

    // Add to wishlist button for first related product
    private By firstRelatedProductWishlistBtn = By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[3]/button[2]");



    // Inside ProductDisplayPage.java
    @FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
    private WebElement btnAddToWishList;

    // Method to click Add to Wish List button
    public void clickAddToWishListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAddToWishList)).click();
    }

    public void addRelatedProductToWishList(String productName) {
        // Wait until Related Products section is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[text()='Related Products']")
        ));

        // Build a safer locator for the button inside Related Product card
        By addToWishListBtn = By.xpath("//div[@class='caption']/h4/a[contains(text(),'"
                + productName + "')]/../..//button[contains(@onclick,'wishlist.add')]");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToWishListBtn));
        btn.click();
    }


    // Get name of first related product
    public String getFirstRelatedProductName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRelatedProductName)).getText().trim();
    }

    // Add first related product to wish list
    public void addFirstRelatedProductToWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(firstRelatedProductWishlistBtn)).click();
    }


    // --- Locators ---
    @FindBy(css = "div#content img.img-responsive")  // Main Product Image
    private WebElement mainThumbnail;

    @FindBy(css = "button.mfp-close")   // Close button in Lightbox
    private WebElement btnCloseLightbox;

    @FindBy(css = "button.mfp-arrow-left")   // Prev
    private WebElement btnPrevThumbnail;

    @FindBy(css = "button.mfp-arrow-right")  // Next
    private WebElement btnNextThumbnail;

    @FindBy(css = "ul.thumbnails img")   // List of thumbnails
    private java.util.List<WebElement> smallThumbnails;

    // --- Actions ---

    // Click main thumbnail to open Lightbox
    public void openLightboxFromMainThumbnail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(mainThumbnail)).click();

    }

    // Click next image
    public void clickNextThumbnail() {
        wait.until(ExpectedConditions.elementToBeClickable(btnNextThumbnail)).click();
    }

    // Click previous image
    public void clickPrevThumbnail() {
        wait.until(ExpectedConditions.elementToBeClickable(btnPrevThumbnail)).click();
    }

    // Close Lightbox
    public void closeLightbox() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCloseLightbox)).click();
    }

    // Click ESC key to close Lightbox
    public void pressEscapeKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    // Click on a small thumbnail by index
    public void clickSmallThumbnail(int index) {
        if (index < smallThumbnails.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(smallThumbnails.get(index))).click();
        } else {
            throw new IllegalArgumentException("Thumbnail index out of range!");
        }
    }

    // Validate if lightbox is displayed
    public boolean isLightboxDisplayed() {
        try {
            return btnCloseLightbox.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // --- Product Details ---
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement productName;   // Product Name

    @FindBy(xpath = "//div[@id='content']//ul[@class='list-unstyled']/li/a")
    private WebElement productBrand;  // Brand (first link in list)

    @FindBy(xpath = "//div[@id='content']//ul[@class='list-unstyled']/li[contains(text(),'Product Code')]")
    private WebElement productCode;   // Product Code

    // --- Getter Methods ---
    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText().trim();
    }

    public String getProductBrand() {
        return wait.until(ExpectedConditions.visibilityOf(productBrand)).getText().trim();
    }

    public String getProductCode() {
        return wait.until(ExpectedConditions.visibilityOf(productCode)).getText().replace("Product Code: ", "").trim();
    }

    public String getProductAvailability() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Use XPath that matches text containing "Availability"
        WebElement availabilityElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Availability')]")
                )
        );

        String rawText = availabilityElement.getText().trim();

        // Normalize: remove "Availability:" if present
        if (rawText.contains(":")) {
            rawText = rawText.split(":", 2)[1].trim();
        }

        return rawText;  // "In Stock", "Out Of Stock", etc.
    }



    // Locator for price with tax
    private By priceWithTax = By.xpath("//ul[@class='list-unstyled']//h2");

    // Locator for price without tax (Ex Tax)
    private By priceExTax = By.xpath("//ul[@class='list-unstyled']/li[contains(text(),'Ex Tax')]");

    // Method to get Price With Tax
    public String getPriceWithTax() {
        return driver.findElement(priceWithTax).getText();
    }

    // Method to get Price Ex Tax
    public String getPriceExTax() {
        return driver.findElement(priceExTax).getText();
    }

    // Inside ProductDisplayPage.java
    @FindBy(xpath = "//a[normalize-space()='Description']")
    private WebElement tabDescription;

    @FindBy(xpath = "//div[@id='tab-description']")
    private WebElement productDescriptionText;

    // Method to click on Description tab
    public void clickDescriptionTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabDescription)).click();
    }

    // Method to get product description text
    public String getProductDescription() {
        return wait.until(ExpectedConditions.visibilityOf(productDescriptionText)).getText().trim();
    }

    // ProductDisplayPage.java

    @FindBy(xpath = "//table[@class='table table-bordered']//tr")
    private java.util.List<WebElement> bulkPriceRows;


    public void clickShoppingCartLinkInSuccessMessage() {
        By cartLinkLocator = By.xpath("/html/body/div[2]/div[1]/a[2]");
        try {
            // Wait for the element to be present and visible
            WebElement cartLink = wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkLocator));

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartLink);

            // Attempt normal click
            try {
                cartLink.click();
            } catch (Exception e) {
                // Fallback to JavaScript click if normal click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartLink);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'shopping cart' link in success message: " + e.getMessage());
        }
    }
    @FindBy(xpath = "//button[@id='button-cart']")
    private WebElement btnAddToCart;


    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart)).click();
    }


    // PDP title (h1)
    @FindBy(css = "#content h1")
    private WebElement pdpTitle;

    // Success alert after adding to cart
    private By successAlert = By.xpath("/html/body/div[2]/div[1]");

    // "shopping cart" link inside success alert
    private By shoppingCartLinkInAlert = By.xpath("//div[contains(@class,'alert-success')]//a[contains(.,'shopping cart')]");

    /** Get PDP header/title text */
    public String getPdpTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pdpTitle)).getText().trim();
    }


    /** Wait for success message and return its full text */
    public String getSuccessMessageText() {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
        return alert.getText().trim();
    }

    /** Click the 'shopping cart' link in success message */
    public void clickShoppingCartLinkInSuccess() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLinkInAlert));
        link.click();
        // Optional: wait until URL contains checkout/cart
        wait.until(ExpectedConditions.urlContains("route=checkout/cart"));

    }


    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'checkout/checkout')]")
    private WebElement checkoutLinkInSuccessMessage;

    // The addToCart() method should already be implemented
    public void addToCart() {
        addToCartBtn.click();
        logger.info("Product added to cart.");
    }

    // Update the goToCheckout method to wait for the element
    public void goToCheckout() {
        logger.info("Attempting to click Checkout link from success notification...");
        // Use WebDriverWait to explicitly wait for the checkout link in the success banner
        wait.until(ExpectedConditions.visibilityOf(checkoutLinkInSuccessMessage)).click();
        logger.info("Clicked the Checkout link.");
    }


    // --- Related Products Section ---


    // Heading
    public By relatedProductsHeading = By.xpath("/html/body/div[2]/div/div/h3");



    @FindBy(id = "button-cart")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    private WebElement checkoutLink;


    public void addRelatedProductToCartByIndex(int index) {
        // Ensure products have loaded before finding them
        if (!hasRelatedProducts()) {
            throw new IllegalArgumentException("Invalid related product index: " + index +
                    ". Related Products section did not load or is empty.");
        }

        List<WebElement> cards = driver.findElements(relatedProductCards);

        if (index >= cards.size()) {
            throw new IllegalArgumentException("Invalid related product index: " + index +
                    ". Found only " + cards.size() + " products.");
        }

        WebElement productCard = cards.get(index);

        // Scroll the specific card into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", productCard);

        // Locate and click the Add to Cart button
        WebElement addBtn = productCard.findElement(By.xpath(".//button[contains(@onclick,'cart.add')]"));
        waitShort().until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }



    public String getRelatedProductName(int index) {
        // Ensure products have loaded before finding them
        if (!hasRelatedProducts()) {
            throw new IllegalArgumentException("Invalid related product index: " + index +
                    ". Related Products section did not load or is empty.");
        }

        // Find elements again (they should be attached now after the wait)
        List<WebElement> cards = driver.findElements(relatedProductCards);

        if (index >= cards.size()) {
            throw new IllegalArgumentException("Invalid related product index: " + index +
                    ". Found only " + cards.size() + " products.");
        }

        // Locate the name link within the specific card
        return cards.get(index).findElement(By.cssSelector(".caption h4 a")).getText().trim();
    }



    // Locators
    private final By mainProductName = By.xpath("//div[@id='content']//h1");
    private final By bulkPriceTableLocator = By.xpath("//div[@id='product']//table[@class='table table-bordered']");


    public boolean isOnProductDisplayPage() {
        try {
            return driver.findElement(mainProductName).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public boolean isBulkPriceTablePresent() {
        try {
            // Check if the table element is present and displayed
            return driver.findElement(bulkPriceTableLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public String getBulkPrice(int quantity) {

        String xpath = bulkPriceTableLocator.toString().replace("By.xpath: ", "") +
                "//tr/td[contains(text(), '" + quantity + "+')]/following-sibling::td[1]";


        WebElement priceElement = driver.findElement(By.xpath(xpath));

        if (priceElement != null) {
        }

        return priceElement.getText().trim();
    }

    public void clickProductFromSearchResults(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By productLinkLocator = By.linkText(productName);

        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productLinkLocator));

        try {
            productLink.click();
        } catch (Exception e) {
            // Fallback to JS click if standard click fails (due to potential overlay issues)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);
        }
    }


    // New locator for the static header element
    public final By relatedProductsHeader = By.xpath("//h3[normalize-space()='Related Products']");
    // Existing locator for the dynamic product cards
    public final By relatedProductCards = By.xpath("//h3[normalize-space()='Related Products']/following-sibling::div//div[contains(@class,'product-layout')]");


    public boolean hasRelatedProducts() {
        try {
            // 1. Find the static header element
            WebElement header = driver.findElement(relatedProductsHeader);

            // 2. Scroll the HEADER element into view, ensuring the entire section is visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", header);

            // --- FIX: Use a dedicated longer wait (30 seconds) for the dynamic content ---
            // This addresses the TimeoutException caused by slow asynchronous product loading.
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // 3. Wait explicitly for at least ONE dynamic product card to appear
            // This confirms the section loaded and populated.
            longWait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(relatedProductCards, 0)
            );

            // Re-find and check if the list is not empty
            List<WebElement> cards = this.driver.findElements(relatedProductCards);
            return !cards.isEmpty();

        } catch (TimeoutException | NoSuchElementException e) {
            // Use logger.error or logger.info based on your standard, but warn is fine
            logger.warn("Related Products section or products did not load.", e);
            return false;
        }
    }

    public void clickfirstRelatedAddToCartBtn(){
        firstRelatedAddToCartBtn.click();
    }

}




