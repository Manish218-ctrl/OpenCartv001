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


public class SearchPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(name = "search")
    private WebElement txtGlobalSearchInput;

    // Locator for all products in the search results
    @FindBy(css = "div.product-layout")
    public List<WebElement> resultCards;

    public By firstProductCard = By.xpath("//div[@class='product-layout'][1]");

    // Button to switch to List View
    @FindBy(xpath = "//button[@id='list-view']")
    private WebElement btnListView;

    // Locator for the 'Compare this Product' button/link in the List View (targets the first product card)
    // The link should be inside the product-layout div. This relies on the common OpenCart structure.
    public By compareButtonFirstProductLocator = By.xpath("(//div[contains(@class,'product-layout')])[1]//button[contains(@onclick, 'compare.add')]");

    // Locator for the dynamic success/alert message
    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    public WebElement successAlert;

    // Locator for the 'product comparison' link within the success message
    @FindBy(xpath = "//div[contains(@class, 'alert-success')]/a[contains(text(), 'product comparison')]")
    public WebElement linkProductComparison;

    //private final By productTitle = By.xpath("//div[@class='product-layout'][1]//div[@class='caption']//h4/a");

    public final By expectedProductTitle = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a");




    @FindBy(xpath = "//span[@class='input-group-btn']/button[@type='button']")
    public WebElement btnGlobalSearchIcon;

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

    @FindBy(xpath = "//div[@id='content']//h2 | //div[@id='content']//h1")
    public WebElement headingSearchResults;

    @FindBy(xpath = "//div[contains(@class,'product-layout')]")
    public List<WebElement> productResultCards;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    public WebElement noProductMessage;

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



    @FindBy(id = "input-search")
    public WebElement txtSearchCriteria;

    @FindBy(name = "category_id")
    public WebElement dropdownCategory;

    @FindBy(id = "description")
    public WebElement chkSearchInProductDescription;

    @FindBy(id = "sub_category")
    public WebElement chkSearchInSubcategories;

    @FindBy(id = "button-search")
    public WebElement btnSearchPageSearchButton;

    @FindBy(xpath = "//div[@class='product-layout'][1]//div[@class='caption']//h4/a")
    public WebElement firstProductTitle;

    @FindBy(xpath = "//*[@id=\"grid-view\"]")
    public WebElement gridview;

    public String getSearchCriteriaPlaceholder() {
        waitShort().until(ExpectedConditions.visibilityOf(txtSearchCriteria));
        String placeholder = txtSearchCriteria.getAttribute("placeholder");
        logger.info("Advanced search criteria placeholder retrieved: '{}'", placeholder);
        return placeholder;
    }



    public String getProductTitleFromResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='product-layout'][1]//div[@class='caption']//h4/a")
        ));
        return titleElement.getText();
    }


    public void clickgridview(){
        gridview.click();
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

    public void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public By cartProductNames = By.cssSelector("table.table.table-bordered td.text-left a");



    public WebDriverWait waitLong() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)); // Use 15 seconds for critical clicks
    }

    public void clickComparisonLinkFromSuccessMessage() {
        // Use the longer wait and wait for the specific link text to be clickable.
        WebElement cmpLink = waitLong().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'product comparison')]")));

        // Add a scroll/click robustness layer
        try {
            cmpLink.click();
        } catch (Exception e) {
            // Fallback to JS click if the link is intercepted (e.g., by a sticky header)
            jsClick(cmpLink);
        }
        logger.info("Clicked 'Product Comparison' link from success message.");
    }

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//h4/a)")
    public WebElement firstProductName;

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


    /**
     * Clicks the 'product comparison' link found within the success message.
     */
    public void clickProductComparisonLinkFromSuccessMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkProductComparison)).click();
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



    public boolean hasResults() {
        return !resultCards.isEmpty();
    }

    /** Click a product by its exact visible name in search results */
    public void openProductByName(String productName) {
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'product-thumb')]//a[normalize-space()='" + productName + "']")));
        productLink.click();
    }




    public void clickCompareProductForFirstProduct() {
        WebElement compareButton = wait.until(
                ExpectedConditions.elementToBeClickable(compareButtonFirstProductLocator)
        );
        compareButton.click();
        logger.info("Clicked 'Compare this Product' for the first product.");
    }

    /**
     * Retrieves the tooltip text for the 'Compare this Product' button of the first product.
     * This uses the 'title' attribute typically found on the button/link element.
     */


    public String getCompareTooltipForFirstProduct() {
        WebElement compareButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(compareButtonFirstProductLocator)
        );
        String tooltip = compareButton.getAttribute("title");

        // 2. If empty, check the Bootstrap tooltip attribute (often used by OpenCart)
        if (tooltip == null || tooltip.isEmpty()) {
            tooltip = compareButton.getAttribute("data-original-title");
        }

        logger.info("Retrieved compare button tooltip: " + (tooltip != null ? tooltip : "null"));
        return (tooltip != null) ? tooltip.trim() : "";
    }


    public By getFirstProductCardLocator() {
        return firstProductCard; // Or return the By object directly
    }




    public boolean isGridViewActive() {
        // If the List View button is NOT active, the page is in Grid View.
        boolean isActive = !isListViewActive();
        logger.info("Is Grid View active? (Inverted List Check) {}", isActive);
        return isActive;
    }


    // Keep this scrollHover method, it is the one you should use:
    public void scrollHover(WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
        try {
            new Actions(driver).moveToElement(el).perform();
        } catch (Exception ignore) {}
    }



    public void clickCompareIconForProduct(String productName) {
        By compareBtnLocator = By.xpath(
                "//a[text()='" + productName + "']/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'compare.add')]"
        );

        // Use explicit wait to ensure the element is clickable before performing the action.
        WebElement compareBtn = waitShort().until(ExpectedConditions.elementToBeClickable(compareBtnLocator));

        // Add scroll to ensure it's in the viewport (optional, but good practice)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", compareBtn);

        try {
            compareBtn.click();
        } catch (ElementClickInterceptedException e) {
            // Fallback for click interception issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", compareBtn);
        }

        logger.info("Clicked 'Compare this Product' for '{}'", productName);
    }

    public String getSearchProductTitle(long timeoutInSeconds) {
        // Use Explicit Wait for the first product title to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(expectedProductTitle));

        return element.getText();
    }

    // Add a check for no results (optional but good practice)
    public boolean isProductFound(long timeoutInSeconds) {
        try {
            // Wait for the product title. If found, return true.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(expectedProductTitle));
            return true;
        } catch (Exception e) {
            // If the product title is not found within the timeout, assume no result.
            // You can add a specific check for the "no results" message here if needed.
            return false;
        }
    }

      }







