package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static pageObjects.Homepage.logger;

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
    private WebElement btnCompareProduct;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'compare')]")
    private WebElement linkProductComparison;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[not(contains(@href,'compare'))]")
    private WebElement linkProductNameInSuccessMessage;



    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(text(),'shopping cart')]")
    private WebElement linkShoppingCartInSuccessMessage;


    @FindBy(xpath = "(//div[@class='product-thumb'])[1]//button[contains(@onclick,'wishlist.add')]")
    private WebElement firstRelatedAddToWishListBtn;


    // Link inside success message → "wish list!"
    @FindBy(xpath = "//a[text()='wish list']")
    private WebElement linkWishList;

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

// /html/body/div[2]/div/div/div[2]/div/div/div[3]/button[2]


    // --- New Add to Cart Methods ---





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

    /*
     * Get the price for a specific bulk quantity
     * @param quantity - number of products (10, 20, 30)
     * @return price as String
     */
   /* public String getBulkPrice(int quantity) {
        for (WebElement row : bulkPriceRows) {
            String rowText = row.getText(); // Example: "10 Units $200"
            if (rowText.contains(quantity + " Units")) {
                return rowText.split("\\$")[1].trim(); // Return only price
            }
        }
        return null; // Not found
    }*/



    //  Dummy method for PDP verification (already in your code probably)
    public boolean isOnProductDisplayPage() {
        return driver.getCurrentUrl().contains("product");
    }

    //  Fetch bulk price for a given quantity
   /* public String getBulkPrice(int quantity) {
        String xpath = "//ul[@class='list-unstyled']//li[contains(text(),'" + quantity + "')]";
        WebElement priceElement = driver.findElement(By.xpath(xpath));
        return priceElement.getText();
    }*/

    public String getBulkPrice(int quantity) {
        for (WebElement row : bulkPriceRows) {
            String rowText = row.getText();  // e.g. "10+ Units $100.00"
            if (rowText.contains(String.valueOf(quantity))) {
                return rowText;
            }
        }
        throw new NoSuchElementException("No bulk price found for quantity: " + quantity);
    }


    public void clickProductFromSearchResults(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Locate product by partial text to avoid whitespace issues
            WebElement productLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@class='caption']//a[contains(normalize-space(.),'" + productName + "')]")
                    )
            );
            logger.info(" Found product in search results: " + productName);
            productLink.click();
            logger.info(" Clicked on product link: " + productName);
        } catch (TimeoutException e) {
            logger.error(" Could not find product in search results: " + productName, e);
            throw e; // rethrow so test fails
        }
    }


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

    // Cards
    public
    By relatedProductCards = By.xpath("//h3[normalize-space()='Related Products']/following-sibling::div//div[contains(@class,'product-layout')]");



   /* public boolean hasRelatedProducts() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsHeading));
            List<WebElement> cards = wait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(relatedProductCards, 0)
            );
            return !cards.isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }*/

    public boolean hasRelatedProducts() {
        try {
            // Scroll to bottom or a known section near related products
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,3500)");

            // Wait for the section to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/h3")));

            return section.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }


    public String getRelatedProductName(int index) {
        List<WebElement> cards = driver.findElements(relatedProductCards);
        if (index >= cards.size()) {
            throw new IllegalArgumentException("Invalid related product index: " + index);
        }
        return cards.get(index).findElement(By.cssSelector(".caption a")).getText().trim();
    }

    public void addRelatedProductToCartByIndex(int index) {
        List<WebElement> cards = driver.findElements(relatedProductCards);
        if (index >= cards.size()) {
            throw new IllegalArgumentException("Invalid related product index: " + index);
        }

        WebElement productCard = cards.get(index);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", productCard);

        WebElement addBtn = productCard.findElement(By.xpath(".//button[contains(@onclick,'cart.add')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }


    @FindBy(id = "button-cart")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    private WebElement checkoutLink;














}