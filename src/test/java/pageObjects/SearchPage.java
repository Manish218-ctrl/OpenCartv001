package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/*public class SearchPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //  Centralized wait helper
    private WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Global search
    @FindBy(name = "search")
    private WebElement txtGlobalSearchInput;

    @FindBy(xpath = "//span[@class='input-group-btn']/button[@type='button']")
    private WebElement btnGlobalSearchIcon;

    // Search results page
    @FindBy(xpath = "//div[@id='content']//h2 | //div[@id='content']//h1")
    private WebElement headingSearchResults;

    @FindBy(xpath = "//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']")
    public List<WebElement> productResultCards;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;

    @FindBy(id = "list-view")
    private WebElement btnListView;

    @FindBy(id = "grid-view")
    private WebElement btnGridView;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//img)")
    private WebElement firstProductImage;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//h4/a)")
    private WebElement firstProductName;

    // Advanced search
    @FindBy(xpath = "//input[@id='input-search']")
    private WebElement txtSearchCriteria;

    @FindBy(xpath = "//select[@name='category_id']")
    private WebElement dropdownCategory;

    @FindBy(xpath = "//input[@id='description']")
    private WebElement chkSearchInProductDescription;

    @FindBy(xpath = "//input[@id='sub_category']")
    private WebElement chkSearchInSubcategories;

    @FindBy(xpath = "//button[@id='button-search']")
    private WebElement btnSearchPageSearchButton;

    // Sort/Show dropdowns
    private final By sortByDropdown = By.id("input-sort");
    private final By showDropdown = By.id("input-limit");

    // Compare link
    private final By productCompareLink = By.xpath("//a[contains(@href,'compare') and contains(.,'Product Compare')]");

    // ----- Core Actions -----
    public void enterSearchKeyword(String keyword) {
        waitShort().until(ExpectedConditions.visibilityOf(txtGlobalSearchInput)).clear();
        txtGlobalSearchInput.sendKeys(keyword);
        logger.info("Entered search keyword: '{}'", keyword);
    }

    public void clickSearchButton() {
        waitShort().until(ExpectedConditions.elementToBeClickable(btnGlobalSearchIcon)).click();
        logger.info("Clicked global search icon.");
    }

    public boolean isProductDisplayed(String productName) {
        try {
            waitShort().until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfAllElements(productResultCards),
                    ExpectedConditions.visibilityOf(noProductMessage)
            ));
        } catch (TimeoutException e) {
            return false;
        }

        for (WebElement productCard : productResultCards) {
            try {
                WebElement productLink = productCard.findElement(By.linkText(productName));
                if (productLink.isDisplayed()) return true;
            } catch (NoSuchElementException ignore) {}
        }
        return false;
    }

    public boolean isNoProductMessageDisplayed() {
        try {
            return waitShort().until(ExpectedConditions.visibilityOf(noProductMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Scroll + hover helper
    private void scrollHover(WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
        try {
            new Actions(driver).moveToElement(el).perform();
        } catch (Exception ignore) {}
    }

    // ✅ fixed jsClick
    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // Add to Compare (from results)
    public void clickCompareIconForProduct(String productName) {
        WebElement compareBtn = driver.findElement(
                By.xpath("//a[text()='" + productName + "']/../..//button[contains(@onclick,'compare.add')]"));
        waitShort().until(ExpectedConditions.elementToBeClickable(compareBtn)).click();
        logger.info("Clicked 'Compare this Product' for '{}'", productName);
    }

    public void clickComparisonLinkFromSuccessMessage() {
        WebElement cmpLink = waitShort().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'product comparison')]")));
        cmpLink.click();
        logger.info("Clicked 'Product Comparison' link from success message.");
    }

    public void clickFirstProductName() {
        WebElement name = waitShort().until(ExpectedConditions.elementToBeClickable(firstProductName));
        scrollHover(name);
        try {
            name.click();
        } catch (Exception e) {
            jsClick(name);
        }
        logger.info("Clicked on the first product name.");
    }

    // Compare page helpers
    public void clickProductCompareLink() {
        WebElement link = waitShort().until(ExpectedConditions.elementToBeClickable(productCompareLink));
        link.click();
        logger.info("Clicked 'Product Compare' link from search results.");
    }

    public boolean isOnProductComparePage() {
        try {
            return waitShort().until(ExpectedConditions.or(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.xpath("//div[@id='content']//h1"), "Product Comparison"),
                    ExpectedConditions.urlContains("compare")
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public String getSearchResultsHeading() {
        return waitShort().until(ExpectedConditions.visibilityOf(headingSearchResults)).getText();
    }

    public void clickListView() {
        waitShort().until(ExpectedConditions.elementToBeClickable(btnListView)).click();
        waitShort().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'product-layout')]//div[contains(@class,'product-thumb')]")
        ));
        logger.info("Clicked List View button.");
    }

    //  exists
    public boolean isListViewActive() {
        try {
            return waitShort().until(ExpectedConditions.attributeContains(btnListView, "class", "active"));
        } catch (Exception e) {
            logger.error("List View is not active or button not found: {}", e.getMessage());
            return false;
        }
    }

    //  exists
    public WebElement getFirstProductCard() {
        return waitShort().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[contains(@class,'product-layout')]//div[contains(@class,'product-thumb')])")));
    }

    //  exists
    public void addFirstProductToCompare() {
        WebElement card = getFirstProductCard();
        scrollHover(card);
        By[] candidates = new By[]{
                By.xpath(".//button[@data-original-title='Compare this Product']"),
                By.xpath(".//button[contains(@aria-label,'Compare')]"),
                By.xpath(".//button[contains(@onclick,'compare')]"),
                By.xpath(".//i[contains(@class,'exchange') or contains(@class,'random')]/parent::button")
        };
        clickFirstMatching(card, candidates, "Compare this Product");
        logger.info("Clicked 'Compare this Product' for the first product.");
    }

    private void clickFirstMatching(WebElement scope, By[] locators, String actionName) {
        WebDriverWait w = waitShort();
        for (By by : locators) {
            List<WebElement> found = scope.findElements(by);
            if (!found.isEmpty()) {
                WebElement target = found.get(0);
                try {
                    w.until(ExpectedConditions.elementToBeClickable(target)).click();
                } catch (Exception e) {
                    jsClick(target);
                }
                return;
            }
        }
        throw new NoSuchElementException(
                "Could not find button for action: " + actionName + " within the first product card."
        );
    }


        public String getGlobalSearchInputPlaceholder() {
            return driver.findElement(By.id("search-input")).getAttribute("placeholder");
        }




        // Click the Grid View button
        public void clickGridView() {
            driver.findElement(By.id("grid-view-button")).click();
        }

        // Check if Grid View is active
        public boolean isGridViewActive() {
            WebElement gridButton = driver.findElement(By.id("grid-view-button"));
            return gridButton.getAttribute("class").contains("active");
        }
    }*/










public class SearchPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ------------------- Wait Helper -------------------
    private WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ------------------- Global Search (Homepage & Header) -------------------
    @FindBy(name = "search")
    private WebElement txtGlobalSearchInput;

    @FindBy(xpath = "//span[@class='input-group-btn']/button[@type='button']")
    private WebElement btnGlobalSearchIcon;

    public String getGlobalSearchInputPlaceholder() {
        waitShort().until(ExpectedConditions.visibilityOf(txtGlobalSearchInput));
        String placeholder = txtGlobalSearchInput.getAttribute("placeholder");
        logger.info("Global search placeholder retrieved: '{}'", placeholder);
        return placeholder;
    }

    public void enterSearchKeyword(String keyword) {
        waitShort().until(ExpectedConditions.visibilityOf(txtGlobalSearchInput)).clear();
        txtGlobalSearchInput.sendKeys(keyword);
        logger.info("Entered search keyword: '{}'", keyword);
    }

    public void clickSearchButton() {
        waitShort().until(ExpectedConditions.elementToBeClickable(btnGlobalSearchIcon)).click();
        logger.info("Clicked global search icon.");
    }

    // ------------------- Search Results -------------------
    @FindBy(xpath = "//div[@id='content']//h2 | //div[@id='content']//h1")
    private WebElement headingSearchResults;

    @FindBy(xpath = "//div[contains(@class,'product-layout')]")
    public List<WebElement> productResultCards;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;

    public String getSearchResultsHeading() {
        return waitShort().until(ExpectedConditions.visibilityOf(headingSearchResults)).getText();
    }

    public boolean isProductDisplayed(String productName) {
        try {
            waitShort().until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfAllElements(productResultCards),
                    ExpectedConditions.visibilityOf(noProductMessage)
            ));
        } catch (TimeoutException e) {
            return false;
        }

        for (WebElement productCard : productResultCards) {
            try {
                WebElement productLink = productCard.findElement(By.linkText(productName));
                if (productLink.isDisplayed()) return true;
            } catch (NoSuchElementException ignore) {}
        }
        return false;
    }

    public boolean isNoProductMessageDisplayed() {
        try {
            return waitShort().until(ExpectedConditions.visibilityOf(noProductMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ------------------- Advanced Search -------------------
    @FindBy(id = "input-search")
    private WebElement txtSearchCriteria;

    @FindBy(name = "category_id")
    private WebElement dropdownCategory;

    @FindBy(id = "description")
    private WebElement chkSearchInProductDescription;

    @FindBy(id = "sub_category")
    private WebElement chkSearchInSubcategories;

    @FindBy(id = "button-search")
    private WebElement btnSearchPageSearchButton;

    @FindBy(xpath = "//div[@class='product-layout'][1]//div[@class='caption']//h4/a")
    private WebElement firstProductTitle;

    public String getSearchCriteriaPlaceholder() {
        waitShort().until(ExpectedConditions.visibilityOf(txtSearchCriteria));
        String placeholder = txtSearchCriteria.getAttribute("placeholder");
        logger.info("Advanced search criteria placeholder retrieved: '{}'", placeholder);
        return placeholder;
    }

    // @return The product name (String) of the first result.
                public String getProductTitleFromResult() {
        // This line gets the visible text from the element.
        return firstProductTitle.getText();
    }


    public void enterSearchCriteria(String keyword) {
        waitShort().until(ExpectedConditions.visibilityOf(txtSearchCriteria)).clear();
        txtSearchCriteria.sendKeys(keyword);
        logger.info("Entered keyword in Advanced Search criteria: '{}'", keyword);
    }

    public void clickAdvancedSearchButton() {
        waitShort().until(ExpectedConditions.elementToBeClickable(btnSearchPageSearchButton)).click();
        logger.info("Clicked Advanced Search button.");
    }

    // ------------------- Utilities -------------------
    private void scrollHover(WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
        try {
            new Actions(driver).moveToElement(el).perform();
        } catch (Exception ignore) {}
    }

    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    private By cartProductNames = By.cssSelector("table.table.table-bordered td.text-left a");

    // Add to Compare (from results)
    public void clickCompareIconForProduct(String productName) {
        WebElement compareBtn = driver.findElement(
                By.xpath("//a[text()='" + productName + "']/../..//button[contains(@onclick,'compare.add')]"));
        waitShort().until(ExpectedConditions.elementToBeClickable(compareBtn)).click();
        logger.info("Clicked 'Compare this Product' for '{}'", productName);
    }

    public void clickComparisonLinkFromSuccessMessage() {
        WebElement cmpLink = waitShort().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'product comparison')]")));
        cmpLink.click();
        logger.info("Clicked 'Product Comparison' link from success message.");
    }

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//h4/a)")
    private WebElement firstProductName;

    public void clickFirstProductName() {
        WebElement name = waitShort().until(ExpectedConditions.elementToBeClickable(firstProductName));
        scrollHover(name);
        try {
            name.click();
        } catch (Exception e) {
            jsClick(name);
        }
        logger.info("Clicked on the first product name.");
    }

    public boolean isOnProductComparePage() {
        try {
            return waitShort().until(ExpectedConditions.or(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.xpath("//div[@id='content']//h1"), "Product Comparison"),
                    ExpectedConditions.urlContains("compare")
            ));
        } catch (Exception e) {
            logger.error("Error checking if on Product Compare page: {}", e.getMessage());
            return false;
        }
    }

    public void clickProductCompareLink() {
        WebElement link = waitShort().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'compare') and contains(.,'Product Compare')]")));
        link.click();
        logger.info("Clicked 'Product Compare' link from search results.");
    }

    public void clickListView() {
        waitShort().until(ExpectedConditions.elementToBeClickable(
                By.id("list-view")
        )).click();
        logger.info("Clicked List View button.");
    }

    public boolean isListViewActive() {
        try {
            WebElement listViewButton = driver.findElement(By.id("list-view"));
            return listViewButton.getAttribute("class").contains("active");
        } catch (Exception e) {
            logger.error("List View button not found or not active: {}", e.getMessage());
            return false;
        }
    }

    public WebElement getFirstProductCard() {
        return waitShort().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[contains(@class,'product-layout')]//div[contains(@class,'product-thumb')])[1]")
        ));
    }


    public void addFirstProductToCompare() {
        WebElement card = getFirstProductCard();
        scrollHover(card);

        By[] candidates = new By[]{
                By.xpath(".//button[@data-original-title='Compare this Product']"),
                By.xpath(".//button[contains(@aria-label,'Compare')]"),
                By.xpath(".//button[contains(@onclick,'compare')]"),
                By.xpath(".//i[contains(@class,'exchange') or contains(@class,'random')]/parent::button")
        };

        clickFirstMatching(card, candidates, "Compare this Product");
        logger.info("Clicked 'Compare this Product' for the first product.");
    }

    private void clickFirstMatching(WebElement scope, By[] locators, String actionName) {
        WebDriverWait w = waitShort();
        for (By by : locators) {
            List<WebElement> found = scope.findElements(by);
            if (!found.isEmpty()) {
                WebElement target = found.get(0);
                try {
                    w.until(ExpectedConditions.elementToBeClickable(target)).click();
                } catch (Exception e) {
                    jsClick(target);
                }
                return;
            }
        }
        throw new NoSuchElementException(
                "Could not find button for action: " + actionName + " within the first product card."
        );
    }


    // Click the Grid View button
    public void clickGridView() {
        WebElement gridButton = driver.findElement(By.id("grid-view-button"));
        scrollHover(gridButton);
        try {
            gridButton.click();
        } catch (Exception e) {
            jsClick(gridButton);
        }
        logger.info("Clicked Grid View button.");
    }

    // Check if Grid View is active
    public boolean isGridViewActive() {
        WebElement gridButton = driver.findElement(By.id("grid-view-button"));
        boolean active = gridButton.getAttribute("class").contains("active");
        logger.info("Is Grid View active? {}", active);
        return active;
    }

    // Add to Wish List (from Search Results)
    public void clickAddToWishListIconForProduct(String productName) {
        By addToWishlistBtn = By.xpath(
                "//a[text()='" + productName + "']/ancestor::div[@class='product-thumb']//button[contains(@onclick,'wishlist.add')]"
        );
        WebElement btn = waitShort().until(ExpectedConditions.elementToBeClickable(addToWishlistBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
        logger.info("Clicked 'Add to Wish List' for '{}'", productName);
    }



    public String getSuccessMessage() {
        try {
            WebElement msg = waitShort().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'alert-success')]")));
            return msg.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Click "wish list!" link inside success message
    public void clickWishListLinkInSuccessMessage() {
        WebElement link = waitShort().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='wish list']")));
        link.click();
        logger.info("Clicked 'wish list!' link from success message.");
    }

    // Click "Add to Cart" for a product from search results
    public void clickAddToCartFromSearchResults(String productName) {
        By addToCartBtn = By.xpath(
                "//a[text()='" + productName + "']/ancestor::div[@class='product-thumb']//button[contains(@onclick,'cart.add')]"
        );
        WebElement btn = waitShort().until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
        logger.info("Clicked 'Add to Cart' for product: {}", productName);
    }

    // Click Shopping Cart link from header
    public void clickShoppingCartHeaderLink() {
        WebElement link = waitShort().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@title='Shopping Cart']")));
        link.click();
        logger.info("Clicked 'Shopping Cart' header link.");
    }

    public boolean isProductInCart(String productName) {
        try {
            // wait until all product links inside the shopping cart table are present
            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(cartProductNames)
            );

            // debug log to see actual product names
            for (WebElement product : products) {
                String actualName = product.getText().trim();
                System.out.println("DEBUG - Found product in cart: " + actualName);

                // match either exact or partial (case-insensitive)
                if (actualName.equalsIgnoreCase(productName) ||
                        actualName.toLowerCase().contains(productName.toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("DEBUG - Exception in isProductInCart: " + e.getMessage());
            return false;
        }

        return false;
    }


    // Each product card in search results (OpenCart style)
    @FindBy(css = "div.product-layout")
    private List<WebElement> resultCards;

    public boolean hasResults() {
        return !resultCards.isEmpty();
    }

    /** Click a product by its exact visible name in search results */
    public void openProductByName(String productName) {
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'product-thumb')]//a[normalize-space()='" + productName + "']")));
        productLink.click();
    }









}






