package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductDisplayPage extends BasePage {

    public ProductDisplayPage(WebDriver driver) {
        super(driver);
        
    }

    //LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement productName;

    @FindBy(xpath = "//div[@id='content']//ul[contains(@class,'list-unstyled')][1]/li/a")
    private WebElement productBrand;

    @FindBy(xpath = "//div[@id='content']//ul[contains(@class,'list-unstyled')][1]/li[contains(normalize-space(),'Product Code')]")
    private WebElement productCode;

    @FindBy(xpath = "//div[@id='content']//ul[contains(@class,'list-unstyled')][1]/li[contains(normalize-space(),'Availability')]")
    private WebElement productAvailability;

    @FindBy(xpath = "//a[normalize-space()='Description']")
    private WebElement tabDescription;

    @FindBy(id = "tab-description")
    private WebElement productDescriptionText;

    @FindBy(id = "button-cart")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[contains(@href,'checkout/checkout') and contains(text(),'Checkout')]")
    private WebElement checkoutLink;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'checkout/checkout')]")
    private WebElement checkoutLinkInSuccessMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'product/compare')]")
    private WebElement linkProductComparison;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'product/product')]")
    private WebElement linkProductNameInSuccessMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'checkout/cart')]")
    private WebElement linkShoppingCartInSuccessMessage;

    @FindBy(xpath = "//button[contains(@onclick,'compare.add')]")
    private WebElement btnCompareProduct;

    @FindBy(xpath = "//button[contains(@onclick,'wishlist.add')]")
    private WebElement btnAddToWishList;

    @FindBy(xpath = "//a[contains(@href,'account/wishlist')]")
    private WebElement linkWishList;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')])[1]//button[contains(@onclick,'wishlist.add')]")
    private WebElement firstRelatedAddToWishListBtn;

    @FindBy(xpath = "//h3[normalize-space()='Related Products']/following-sibling::div//button[contains(@onclick,'cart.add')][1]")
    private WebElement firstRelatedAddToCartBtn;

    @FindBy(css = "div#content img.img-responsive")
    private WebElement mainThumbnail;

    @FindBy(css = "button.mfp-close")
    private WebElement btnCloseLightbox;

    @FindBy(css = "button.mfp-arrow-left")
    private WebElement btnPrevThumbnail;

    @FindBy(css = "button.mfp-arrow-right")
    private WebElement btnNextThumbnail;

    @FindBy(css = "ul.thumbnails img")
    private List<WebElement> smallThumbnails;

    @FindBy(xpath = "//table[contains(@class,'table-bordered')]//tr")
    private List<WebElement> bulkPriceRows;

    @FindBy(id = "input-quantity")
    private WebElement quantityField;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successAlert;

    @FindBy(xpath = "//div[@id='tab-review']//p[contains(normalize-space(),'There are no reviews for this product.')]")
    private WebElement noReviewsMessage;

    @FindBy(id = "input-quantity")
    private WebElement txtQuantity;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement alertSuccessMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-danger') or contains(@class,'alert-warning')] | //div[@id='product']//div[contains(@class,'input-group')]//span[contains(@class,'text-danger')] | //span[contains(@class,'text-danger')] | //p[contains(@class,'text-danger')] | //div[@class='modal-dialog']//*[contains(@class,'text-danger')]")
    private WebElement quantityValidationMessage;

    @FindBy(xpath = "//div[@id='content']//h3[normalize-space()='Related Products']")
    private WebElement relatedProductsSection;

    @FindBy(xpath = "//h3[normalize-space()='Related Products']/following-sibling::div//h4/a")
    private WebElement firstRelatedProductName;

    @FindBy(xpath = "(//h3[normalize-space()='Related Products']/following-sibling::div//button[contains(@onclick,'wishlist.add')])[1]")
    private WebElement firstRelatedProductWishlistBtn;

    @FindBy(xpath = "//div[@id='content']//ul[contains(@class,'list-unstyled')]//h2")
    private WebElement priceWithTax;

    @FindBy(xpath = "//ul[contains(@class,'list-unstyled')]/li[contains(normalize-space(),'Ex Tax')]")
    private WebElement priceExTax;

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement mainProductName;

    @FindBy(xpath = "//div[@id='product']//table[contains(@class,'table-bordered')]")
    private WebElement bulkPriceTable;

    @FindBy(xpath = "//h3[normalize-space()='Related Products']")
    private WebElement relatedProductsHeader;

    @FindBy(xpath = "//h3[normalize-space()='Related Products']/following-sibling::div//div[contains(@class,'product-layout')]")
    private List<WebElement> relatedProductCards;

    @FindBy(xpath = "//div[@id='content']//h3[normalize-space()='Related Products']")
    private WebElement relatedProductsHeading;

    @FindBy(xpath = "//a[@href='#tab-review']")
    private WebElement tabReviews;

    @FindBy(id = "input-name")
    private WebElement txtReviewName;

    @FindBy(id = "input-review")
    private WebElement txtReviewText;

    @FindBy(xpath = "//input[@type='radio' and @value='5']")
    private WebElement ratingFiveStar;

    @FindBy(id = "button-review")
    private WebElement btnSubmitReview;

    @FindBy(css = "div.alert-success")
    private WebElement reviewSuccessMessage;

    @FindBy(css = ".modal .close, .popup-close")
    private List<WebElement> popupCloseButtons;

    //DYNAMIC LOCATORS

    private By relatedProductWishlistButton(String productName) {
        return By.xpath("//div[contains(@class,'caption')]//h4/a[contains(normalize-space(),'" + productName + "')]/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'wishlist.add')]");
    }

    private By productSearchResult(String productName) {
        return By.xpath("//div[contains(@class,'product-thumb')]//h4/a[normalize-space()='" + productName + "']");
    }

    //ACTION METHODS

    public void addToCart() {
        clickElement(addToCartBtn);
        logger.info("Product added to cart.");
    }

    public void goToCheckout() {
        clickElement(checkoutLinkInSuccessMessage);
        logger.info("Clicked Checkout link.");
    }

    public String getSuccessMessage() {

        try {
            return getElementText(successMessage);
        } catch (Exception e) {
            logger.error("Unable to fetch success message.", e);
            return "";
        }
    }

    public void clickProductNameLinkInSuccessMessage() {
        clickElement(linkProductNameInSuccessMessage);
    }

    public void clickShoppingCartLinkInSuccessMessage() {

        scrollIntoView(linkShoppingCartInSuccessMessage);

        clickElement(linkShoppingCartInSuccessMessage);

        wait.until(ExpectedConditions.urlContains("route=checkout/cart"));
    }

    public void clickCompareThisProduct() {
        clickElement(btnCompareProduct);
    }

    public void clickProductComparisonLink() {
        clickElement(linkProductComparison);
    }

    public void hoverOnCompareButton() {
        hoverElement(btnCompareProduct);
    }

    public boolean isCompareTooltipDisplayed() {

        try {

            String tooltipText = btnCompareProduct.getAttribute("title");

            return tooltipText != null
                    && tooltipText.trim().equalsIgnoreCase("Compare this Product");

        } catch (Exception e) {

            return false;
        }
    }

    public void clickAddToWishListButton() {
        clickElement(btnAddToWishList);
    }

    public void clickWishListLink() {
        clickElement(linkWishList);
    }

    public void addRelatedProductToWishList(String productName) {

        wait.until(ExpectedConditions.visibilityOf(relatedProductsSection));

        clickElement(
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                relatedProductWishlistButton(productName)
                        )
                )
        );
    }

    public void addFirstRelatedProductToWishList() {
        clickElement(firstRelatedAddToWishListBtn);
    }

    public String getProductName() {
        return getElementText(productName);
    }

    public String getProductBrand() {
        return getElementText(productBrand);
    }

    public String getProductCode() {

        return getElementText(productCode)
                .replace("Product Code:", "")
                .trim();
    }

    public String getProductAvailability() {

        String rawText = getElementText(productAvailability);

        if (rawText.contains(":")) {
            rawText = rawText.split(":", 2)[1].trim();
        }

        return rawText;
    }

    public String getPriceWithTax() {
        return getElementText(priceWithTax);
    }

    public String getPriceExTax() {
        return getElementText(priceExTax);
    }

    public void clickDescriptionTab() {
        clickElement(tabDescription);
    }

    public String getProductDescription() {
        return getElementText(productDescriptionText);
    }

    public void openLightboxFromMainThumbnail() {
        clickElement(mainThumbnail);
    }

    public void clickNextThumbnail() {
        clickElement(btnNextThumbnail);
    }

    public void clickPrevThumbnail() {
        clickElement(btnPrevThumbnail);
    }

    public void closeLightbox() {
        clickElement(btnCloseLightbox);
    }

    public void pressEscapeKey() {
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();
    }

    public void clickSmallThumbnail(int index) {

        if (index < smallThumbnails.size()) {
            clickElement(smallThumbnails.get(index));
        } else {
            throw new IllegalArgumentException("Thumbnail index out of range!");
        }
    }

    public boolean isLightboxDisplayed() {

        try {
            return btnCloseLightbox.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getFirstRelatedProductName() {
        return getElementText(firstRelatedProductName);
    }

    public boolean hasRelatedProducts() {

        try {

            wait.until(ExpectedConditions.visibilityOf(relatedProductsHeader));

            scrollIntoView(relatedProductsHeader);

            return !relatedProductCards.isEmpty();

        } catch (Exception e) {

            logger.warn("Related Products section or products did not load.", e);

            return false;
        }
    }

    public String getRelatedProductName(int index) {

        if (!hasRelatedProducts()) {
            throw new IllegalArgumentException("Related products section is empty.");
        }

        if (index >= relatedProductCards.size()) {
            throw new IllegalArgumentException("Invalid related product index: " + index);
        }

        return relatedProductCards.get(index)
                .findElement(By.cssSelector(".caption h4 a"))
                .getText()
                .trim();
    }

    public void addRelatedProductToCartByIndex(int index) {

        if (!hasRelatedProducts()) {
            throw new IllegalArgumentException("Related products section is empty.");
        }

        if (index >= relatedProductCards.size()) {
            throw new IllegalArgumentException("Invalid related product index: " + index);
        }

        WebElement productCard = relatedProductCards.get(index);

        scrollIntoView(productCard);

        WebElement addBtn =
                productCard.findElement(
                        By.xpath(".//button[contains(@onclick,'cart.add')]")
                );

        clickElement(addBtn);
    }

    public boolean isBulkPriceTablePresent() {

        try {
            return wait.until(ExpectedConditions.visibilityOf(bulkPriceTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getBulkPrice(int quantity) {

        String xpath =
                "//div[@id='product']//table[contains(@class,'table-bordered')]//tr/td[contains(normalize-space(),'"
                        + quantity +
                        "')]/following-sibling::td[1]";

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
                .getText()
                .trim();
    }

    public boolean isOnProductDisplayPage() {

        try {
            return wait.until(ExpectedConditions.visibilityOf(mainProductName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickProductFromSearchResults(String productName) {

        WebElement productLink =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                productSearchResult(productName)
                        )
                );

        try {
            productLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);
        }
    }

    public String getProductQuantity() {
        return quantityField.getAttribute("value");
    }

    public void updateProductQuantity(String quantity) {

        quantityField.clear();

        quantityField.sendKeys(quantity);
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(successAlert)).isDisplayed();
    }

    public void clickAddToCartButton() {
        addToCart();
    }

    public String getPdpTitle() {
        return getProductName();
    }

    public String getSuccessMessageText() {
        return getSuccessMessage();
    }

    public void clickShoppingCartLinkInSuccess() {
        clickShoppingCartLinkInSuccessMessage();
    }

    public void clickfirstRelatedAddToCartBtn() {
        clickElement(firstRelatedAddToCartBtn);
    }

    public void closePopupIfPresent() {

        try {

            if (!popupCloseButtons.isEmpty()) {
                clickElement(popupCloseButtons.get(0));
            }

        } catch (Exception e) {

            logger.warn("No popup found to close.");
        }
    }

    public void clickReviewsTab() {

        scrollIntoView(tabReviews);

        clickElement(tabReviews);
    }

    public void enterReviewName(String reviewName) {

        txtReviewName.clear();

        txtReviewName.sendKeys(reviewName);
    }

    public void enterReviewText(String reviewText) {

        txtReviewText.clear();

        txtReviewText.sendKeys(reviewText);
    }

    public void selectFiveStarRating() {
        clickElement(ratingFiveStar);
    }

    public void clickSubmitReview() {
        clickElement(btnSubmitReview);
    }

    public String getReviewSuccessMessage() {
        return getElementText(reviewSuccessMessage);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

    public String getNoReviewsMessage() {
        return getElementText(noReviewsMessage);
    }

    public void updateQuantity(String quantity) {

        txtQuantity.clear();

        if (!quantity.isEmpty()) {
            txtQuantity.sendKeys(quantity);
        }
    }

    public String getSuccessAlertMessage() {

        try {
            return getElementText(alertSuccessMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public String getQuantityValidationMessage() {

        try {
            return getElementText(quantityValidationMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isValidationMessageDisplayed() {

        try {
            return quantityValidationMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}