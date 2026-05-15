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

    private static final Logger logger =
            LogManager.getLogger(SearchPage.class);

    private WebDriver driver;

    public SearchPage(WebDriver driver) {

        super(driver);

        this.driver = driver;

        
    }

    //LOCATORS

    @FindBy(name = "search")
    private WebElement txtGlobalSearchInput;

    @FindBy(xpath = "//span[@class='input-group-btn']/button[@type='button']")
    private WebElement btnGlobalSearchIcon;

    @FindBy(xpath = "//div[@id='content']//h2 | //div[@id='content']//h1")
    private WebElement headingSearchResults;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;

    @FindBy(css = "div.product-layout")
    private List<WebElement> resultCards;

    @FindBy(xpath = "//div[contains(@class,'product-layout')]")
    public List<WebElement> productResultCards;

    @FindBy(xpath = "//div[@class='product-layout'][1]//div[@class='caption']//h4/a")
    private WebElement firstProductTitle;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//h4/a)[1]")
    private WebElement firstProductName;

    @FindBy(id = "list-view")
    private WebElement btnListView;

    @FindBy(id = "grid-view")
    private WebElement btnGridView;

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

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successAlert;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(text(),'product comparison')]")
    private WebElement linkProductComparison;

    //DYNAMIC LOCATORS

    private By firstProductCard =By.xpath(
            "(//div[contains(@class,'product-layout')])[1]"
    );

    private By compareButtonFirstProductLocator =By.xpath(
            "(//div[contains(@class,'product-layout')])[1]" +
                    "//button[contains(@onclick,'compare.add')]"
    );

    private By expectedProductTitle =By.xpath(
            "//div[@id='content']//div[contains(@class,'product-layout')][1]" +
                    "//div[contains(@class,'caption')]//h4/a"
    );

    private By cartProductNames =By.cssSelector(
            "table.table.table-bordered td.text-left a"
    );

    private By compareButtonByProduct(String productName) {

        return By.xpath(
                "//div[contains(@class,'product-thumb')]" +
                        "[.//a[normalize-space()='"
                        + productName +
                        "']]//button[contains(@onclick,'compare.add')]"
        );
    }

    private By addToWishlistButton(String productName) {

        return By.xpath(
                "//div[contains(@class,'product-thumb')]" +
                        "[.//a[normalize-space()='"
                        + productName +
                        "']]//button[contains(@onclick,'wishlist.add')]"
        );
    }

    private By addToCartButton(String productName) {

        return By.xpath(
                "//div[contains(@class,'product-thumb')]" +
                        "[.//a[normalize-space()='"
                        + productName +
                        "']]//button[contains(@onclick,'cart.add')]"
        );
    }

    private By productLinkByName(String productName) {

        return By.xpath(
                "//div[contains(@class,'product-thumb')]" +
                        "//a[normalize-space()='"
                        + productName +
                        "']"
        );
    }

    //ACTION METHODS

    public void scrollHover(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                element
        );

        try {

            new Actions(driver)
                    .moveToElement(element)
                    .perform();

        } catch (Exception ignored) {
        }
    }

    public String getGlobalSearchInputPlaceholder() {

        waitShort().until(
                ExpectedConditions.visibilityOf(txtGlobalSearchInput)
        );

        String placeholder =
                txtGlobalSearchInput.getAttribute("placeholder");

        logger.info(
                "Global search placeholder retrieved: {}",
                placeholder
        );

        return placeholder;
    }

    public void enterSearchKeyword(String keyword) {

        waitShort().until(
                ExpectedConditions.visibilityOf(txtGlobalSearchInput)
        ).clear();

        txtGlobalSearchInput.sendKeys(keyword);

        logger.info(
                "Entered search keyword: {}",
                keyword
        );
    }

    public void clickSearchButton() {

        waitShort().until(
                ExpectedConditions.elementToBeClickable(btnGlobalSearchIcon)
        ).click();

        logger.info("Clicked global search icon");
    }

    public String getSearchResultsHeading() {

        return waitShort().until(
                ExpectedConditions.visibilityOf(headingSearchResults)
        ).getText();
    }

    public boolean isNoProductMessageDisplayed() {

        try {

            return waitShort().until(
                    ExpectedConditions.visibilityOf(noProductMessage)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public String getSearchCriteriaPlaceholder() {

        waitShort().until(
                ExpectedConditions.visibilityOf(txtSearchCriteria)
        );

        String placeholder =
                txtSearchCriteria.getAttribute("placeholder");

        logger.info(
                "Advanced search criteria placeholder retrieved: {}",
                placeholder
        );

        return placeholder;
    }

    public String getProductTitleFromResult() {

        return waitShort().until(
                ExpectedConditions.visibilityOf(firstProductTitle)
        ).getText();
    }

    public void clickgridview() {

        waitShort().until(
                ExpectedConditions.elementToBeClickable(btnGridView)
        ).click();

        logger.info("Clicked Grid View");
    }

    public void enterSearchCriteria(String keyword) {

        waitShort().until(
                ExpectedConditions.visibilityOf(txtSearchCriteria)
        ).clear();

        txtSearchCriteria.sendKeys(keyword);

        logger.info(
                "Entered keyword in Advanced Search criteria: {}",
                keyword
        );
    }

    public void clickAdvancedSearchButton() {

        waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        btnSearchPageSearchButton
                )
        ).click();

        logger.info("Clicked Advanced Search button");
    }

    public void clickComparisonLinkFromSuccessMessage() {

        WebElement cmpLink = waitLong().until(
                ExpectedConditions.elementToBeClickable(
                        linkProductComparison
                )
        );

        try {

            cmpLink.click();

        } catch (Exception e) {

            jsClick(cmpLink);
        }

        logger.info(
                "Clicked Product Comparison link from success message"
        );
    }

    public void clickFirstProductName() {

        WebElement name = waitShort().until(
                ExpectedConditions.elementToBeClickable(firstProductName)
        );

        scrollHover(name);

        try {

            name.click();

        } catch (Exception e) {

            jsClick(name);
        }

        logger.info("Clicked on the first product name");
    }

    public boolean isOnProductComparePage() {

        try {

            return waitShort().until(
                    ExpectedConditions.or(
                            ExpectedConditions.textToBePresentInElementLocated(
                                    By.xpath("//div[@id='content']//h1"),
                                    "Product Comparison"
                            ),
                            ExpectedConditions.urlContains("compare")
                    )
            );

        } catch (Exception e) {

            logger.error(
                    "Error checking if on Product Compare page: {}",
                    e.getMessage()
            );

            return false;
        }
    }

    public void clickProductCompareLink() {

        waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        linkProductComparison
                )
        ).click();

        logger.info(
                "Clicked Product Compare link from success message"
        );
    }

    public void clickListView() {

        waitShort().until(
                ExpectedConditions.elementToBeClickable(btnListView)
        ).click();

        logger.info("Clicked List View button");
    }

    public boolean isListViewActive() {

        try {

            return btnListView.getAttribute("class")
                    .contains("active");

        } catch (Exception e) {

            logger.error(
                    "List View button not active: {}",
                    e.getMessage()
            );

            return false;
        }
    }

    public WebElement getFirstProductCard() {

        return waitShort().until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstProductCard
                )
        );
    }

    public void addFirstProductToCompare() {

        WebElement compareButton = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        compareButtonFirstProductLocator
                )
        );

        try {

            compareButton.click();

        } catch (Exception e) {

            jsClick(compareButton);
        }

        logger.info(
                "Clicked Compare this Product for first product"
        );
    }

    public String getCompareTooltipForFirstProduct() {

        WebElement compareButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        compareButtonFirstProductLocator
                )
        );

        String tooltip =
                compareButton.getAttribute("title");

        if (tooltip == null || tooltip.isEmpty()) {

            tooltip =
                    compareButton.getAttribute(
                            "data-original-title"
                    );
        }

        logger.info(
                "Retrieved compare button tooltip: {}",
                tooltip
        );

        return tooltip != null
                ? tooltip.trim()
                : "";
    }

    public String getCompareTooltipText(String productName) {

        WebElement compareButton = waitShort().until(
                ExpectedConditions.visibilityOfElementLocated(
                        compareButtonByProduct(productName)
                )
        );

        String tooltip =
                compareButton.getAttribute("title");

        if (tooltip == null || tooltip.isEmpty()) {

            tooltip =
                    compareButton.getAttribute(
                            "data-original-title"
                    );
        }

        logger.info(
                "Retrieved compare tooltip for {} : {}",
                productName,
                tooltip
        );

        return tooltip != null
                ? tooltip.trim()
                : "";
    }

    public void addProductToCompare(String productName) {

        WebElement compareBtn = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        compareButtonByProduct(productName)
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                compareBtn
        );

        try {

            compareBtn.click();

        } catch (ElementClickInterceptedException e) {

            jsClick(compareBtn);
        }

        logger.info(
                "Clicked Compare this Product for {}",
                productName
        );
    }

    public void clickGridView() {

        waitShort().until(
                ExpectedConditions.elementToBeClickable(btnGridView)
        ).click();

        logger.info("Clicked Grid View button");
    }

    public void clickAddToWishListIconForProduct(String productName) {

        WebElement button = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        addToWishlistButton(productName)
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button
        );

        try {

            button.click();

        } catch (Exception e) {

            jsClick(button);
        }

        logger.info(
                "Clicked Add to Wish List for {}",
                productName
        );
    }

    public void clickProductComparisonLinkFromSuccessMessage() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        linkProductComparison
                )
        ).click();

        logger.info(
                "Clicked Product Comparison link from success message"
        );
    }

    public String getSuccessMessage() {

        try {

            return waitShort().until(
                    ExpectedConditions.visibilityOf(successAlert)
            ).getText().trim();

        } catch (Exception e) {

            logger.error(
                    "Unable to capture success message: {}",
                    e.getMessage()
            );

            return "";
        }
    }

    public void clickWishListLinkInSuccessMessage() {

        WebElement link = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//div[contains(@class,'alert-success')]//a[contains(normalize-space(),'wish list')]"
                        )
                )
        );

        try {

            link.click();

        } catch (Exception e) {

            jsClick(link);
        }

        logger.info(
                "Clicked wish list link from success message"
        );
    }

    public void clickAddToCartFromSearchResults(String productName) {

        WebElement button = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton(productName)
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button
        );

        try {

            button.click();

        } catch (Exception e) {

            jsClick(button);
        }

        logger.info(
                "Clicked Add to Cart for product: {}",
                productName
        );
    }

    public void clickShoppingCartHeaderLink() {

        WebElement link = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@title='Shopping Cart']")
                )
        );

        link.click();

        logger.info(
                "Clicked Shopping Cart header link"
        );
    }

    public boolean isProductInCart(String productName) {

        try {

            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            cartProductNames
                    )
            );

            for (WebElement product : products) {

                String actualName =
                        product.getText().trim();

                logger.info(
                        "Found product in cart: {}",
                        actualName
                );

                if (actualName.equalsIgnoreCase(productName)
                        || actualName.toLowerCase().contains(
                        productName.toLowerCase()
                )) {

                    return true;
                }
            }

        } catch (Exception e) {

            logger.error(
                    "Exception in isProductInCart: {}",
                    e.getMessage()
            );

            return false;
        }

        return false;
    }

    public boolean hasResults() {

        return !resultCards.isEmpty();
    }

    public void openProductByName(String productName) {

        WebElement productLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        productLinkByName(productName)
                )
        );

        productLink.click();

        logger.info(
                "Opened product by name: {}",
                productName
        );
    }

    public void clickCompareProductForFirstProduct() {

        WebElement compareButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        compareButtonFirstProductLocator
                )
        );

        try {

            compareButton.click();

        } catch (Exception e) {

            jsClick(compareButton);
        }

        logger.info(
                "Clicked Compare this Product for first product"
        );
    }

    public By getFirstProductCardLocator() {

        return firstProductCard;
    }

    public boolean isGridViewActive() {

        boolean isActive = !isListViewActive();

        logger.info(
                "Is Grid View active? {}",
                isActive
        );

        return isActive;
    }

    public void clickCompareIconForProduct(String productName) {

        WebElement compareBtn = waitShort().until(
                ExpectedConditions.elementToBeClickable(
                        compareButtonByProduct(productName)
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                compareBtn
        );

        try {

            compareBtn.click();

        } catch (ElementClickInterceptedException e) {

            jsClick(compareBtn);
        }

        logger.info(
                "Clicked Compare this Product for {}",
                productName
        );
    }

    public String getSearchProductTitle(long timeoutInSeconds) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(timeoutInSeconds)
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        expectedProductTitle
                )
        ).getText();
    }

    public boolean isProductFound(long timeoutInSeconds) {

        try {

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(timeoutInSeconds)
                    );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            expectedProductTitle
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public String getActualProductTitleFromResults(String productName) {

        try {

            waitShort().until(
                    ExpectedConditions.visibilityOfAllElements(
                            productResultCards
                    )
            );

            for (WebElement productCard : productResultCards) {

                try {

                    WebElement productLink =
                            productCard.findElement(
                                    By.xpath(
                                            ".//div[contains(@class,'caption')]//a[contains(text(),'"
                                                    + productName +
                                                    "')]"
                                    )
                            );

                    if (productLink.isDisplayed()) {

                        String actualTitle =
                                productLink.getText().trim();

                        logger.info(
                                "Found product title: {}",
                                actualTitle
                        );

                        return actualTitle;
                    }

                } catch (NoSuchElementException ignored) {
                }
            }

            logger.warn(
                    "Product title not found for: {}",
                    productName
            );

        } catch (Exception e) {

            logger.error(
                    "Error getting product title: {}",
                    e.getMessage()
            );
        }

        return "";
    }

    public boolean isProductDisplayed(String productName) {

        try {

            waitShort().until(
                    ExpectedConditions.or(
                            ExpectedConditions.visibilityOfAllElements(
                                    productResultCards
                            ),
                            ExpectedConditions.visibilityOf(
                                    noProductMessage
                            )
                    )
            );

        } catch (TimeoutException e) {

            logger.warn(
                    "Timeout waiting for search results for: {}",
                    productName
            );

            return false;
        }

        try {

            if (noProductMessage.isDisplayed()) {

                logger.info(
                        "No products found message displayed"
                );

                return false;
            }

        } catch (Exception ignored) {
        }

        for (WebElement productCard : productResultCards) {

            try {

                WebElement productLink =
                        productCard.findElement(
                                By.xpath(
                                        ".//div[contains(@class,'caption')]//a[contains(text(),'"
                                                + productName +
                                                "')]"
                                )
                        );

                if (productLink.isDisplayed()) {

                    logger.info(
                            "Product {} found: {}",
                            productName,
                            productLink.getText()
                    );

                    return true;
                }

            } catch (NoSuchElementException ignored) {
            }
        }

        logger.warn(
                "Product {} not found in {} search results",
                productName,
                productResultCards.size()
        );

        return false;
    }

    public String getFirstProductName() {

        String productName = waitShort().until(
                ExpectedConditions.visibilityOf(firstProductName)
        ).getText().trim();

        logger.info(
                "First product name captured: {}",
                productName
        );

        return productName;
    }

    public String getCompareTooltipForProduct(String productName) {

        WebElement compareButton = waitShort().until(
                ExpectedConditions.visibilityOfElementLocated(
                        compareButtonByProduct(productName)
                )
        );

        String tooltip =
                compareButton.getAttribute("data-original-title");

        if (tooltip == null || tooltip.isEmpty()) {

            tooltip =
                    compareButton.getAttribute("title");
        }

        logger.info(
                "Compare tooltip for {} : {}",
                productName,
                tooltip
        );

        return tooltip != null
                ? tooltip.trim()
                : "";
    }
}