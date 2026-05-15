package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductComparisonPage extends BasePage {

    public ProductComparisonPage(WebDriver driver) {

        super(driver);

        

        wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }

    //LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Product Comparison']")
    private WebElement headingComparePage;

    @FindBy(xpath = "//a[contains(normalize-space(),'Continue')]")
    private WebElement btnContinue;

    @FindBy(xpath = "//button[contains(@onclick,'compare.add')]")
    private WebElement btnProductCompare;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(normalize-space(),'shopping cart')]")
    private WebElement linkShoppingCartInSuccessMessage;

    @FindBy(xpath = "//div[@id='content']//p[contains(normalize-space(),'You have not chosen any products to compare')]")
    private WebElement txtEmptyComparisonMessage;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr[1]//td[position()>1]//strong")
    private List<WebElement> comparedProductNames;

    @FindBy(xpath = "//a[contains(normalize-space(),'Remove')]")
    private List<WebElement> removeLinks;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]")
    private WebElement comparisonTable;

    //DYNAMIC LOCATORS

    private By productNameBy(String productName) {

        return By.xpath(
                "//div[@id='content']//table//tbody//tr[1]" +
                        "//strong[normalize-space()='"
                        + productName +
                        "']"
        );
    }

    private By addToCartButtonByProduct(String productName) {

        return By.xpath(
                "//div[@id='content']//table//tbody//tr[td[normalize-space()='Product']]" +
                        "//td[.//strong[normalize-space()='"
                        + productName +
                        "']]" +
                        "/ancestor::table//tr[td[normalize-space()='']]" +
                        "//td[count(//tr[td[normalize-space()='Product']]//td[.//strong[normalize-space()='"
                        + productName +
                        "']]/preceding-sibling::td)+1]" +
                        "//input[@value='Add to Cart']"
        );
    }

    private By removeButtonByProduct(String productName) {

        return By.xpath(
                "//div[@id='content']//table//tbody//tr[td[normalize-space()='Product']]" +
                        "//td[.//strong[normalize-space()='"
                        + productName +
                        "']]" +
                        "/ancestor::table//tr[td[normalize-space()='']]" +
                        "//td[count(//tr[td[normalize-space()='Product']]//td[.//strong[normalize-space()='"
                        + productName +
                        "']]/preceding-sibling::td)+1]" +
                        "//a[contains(normalize-space(),'Remove')]"
        );
    }

    private By cartProductByName(String productName) {

        return By.xpath(
                "//div[@id='content']//table//a[normalize-space()='"
                        + productName +
                        "']"
        );
    }

    //ACTION METHODS

    public void clickContinue() {

        WebElement continueButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                btnContinue
                        )
                );

        try {

            continueButton.click();

        } catch (Exception e) {

            jsClick(continueButton);
        }

        logger.info("Clicked Continue button");
    }

    public void clickProductCompareLink() {

        WebElement compareLink =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                btnProductCompare
                        )
                );

        try {

            compareLink.click();

        } catch (Exception e) {

            jsClick(compareLink);
        }

        logger.info("Clicked Product Compare link");
    }

    public void clickShoppingCartLinkInSuccessMessage() {

        WebElement cartLink =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                linkShoppingCartInSuccessMessage
                        )
                );

        try {

            cartLink.click();

        } catch (Exception e) {

            jsClick(cartLink);
        }

        logger.info(
                "Clicked Shopping Cart link from success message"
        );
    }

    public boolean isOnComparisonPage() {

        try {

            boolean headingDisplayed =
                    wait.until(
                            ExpectedConditions.visibilityOf(
                                    headingComparePage
                            )
                    ).isDisplayed();

            boolean urlContains =
                    driver.getCurrentUrl()
                            .contains("route=product/compare");

            logger.info(
                    "Comparison page validation result: {}",
                    headingDisplayed && urlContains
            );

            return headingDisplayed && urlContains;

        } catch (Exception e) {

            logger.error(
                    "Failed to validate Product Comparison page: {}",
                    e.getMessage()
            );

            return false;
        }
    }

    public boolean isOnHomePage() {

        try {

            return wait.until(
                    ExpectedConditions.urlContains(
                            "route=common/home"
                    )
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to validate Home page navigation: {}",
                    e.getMessage()
            );

            return false;
        }
    }

    public void waitForComparisonTableToLoad() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        comparisonTable
                )
        );

        logger.info(
                "Comparison table loaded successfully"
        );
    }

    public boolean waitForProductToBeListed(String productName) {

        try {

            waitForComparisonTableToLoad();

            WebElement product =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    productNameBy(productName)
                            )
                    );

            return product.isDisplayed();

        } catch (TimeoutException e) {

            logger.error(
                    "Timed out waiting for product {} in comparison table",
                    productName
            );

            return false;

        } catch (Exception e) {

            logger.error(
                    "Error validating product {} in comparison table: {}",
                    productName,
                    e.getMessage()
            );

            return false;
        }
    }

    public boolean isProductPresent(String productName) {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfAllElements(
                            comparedProductNames
                    )
            );

            for (WebElement product : comparedProductNames) {

                String actualName =
                        product.getText().trim();

                logger.info(
                        "Compared product found: {}",
                        actualName
                );

                if (actualName.equalsIgnoreCase(productName)) {

                    return true;
                }
            }

        } catch (Exception e) {

            logger.error(
                    "Error while checking compared product: {}",
                    e.getMessage()
            );
        }

        return false;
    }

    public boolean isProductInComparisonTable(String productName) {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameBy(productName)
                    )
            );

            logger.info(
                    "Product {} found in comparison table",
                    productName
            );

            return true;

        } catch (Exception e) {

            logger.warn(
                    "Product {} NOT found in comparison table",
                    productName
            );

            return false;
        }
    }

    public int getComparedProductCount() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfAllElements(
                            comparedProductNames
                    )
            );

            int count =
                    comparedProductNames.size();

            logger.info(
                    "Compared product count: {}",
                    count
            );

            return count;

        } catch (Exception e) {

            logger.error(
                    "Failed to get compared product count: {}",
                    e.getMessage()
            );

            return 0;
        }
    }

    public void addProductToCart(String productName) {

        logger.info(
                "Attempting to add product {} to cart",
                productName
        );

        WebElement addToCartButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                addToCartButtonByProduct(productName)
                        )
                );

        scrollIntoView(addToCartButton);

        try {

            addToCartButton.click();

        } catch (Exception e) {

            logger.warn(
                    "Normal click failed for Add to Cart of {}. Using JS click.",
                    productName
            );

            jsClick(addToCartButton);
        }

        logger.info(
                "Successfully added product {} to cart",
                productName
        );
    }

    public boolean isProductInCart(String productName) {

        try {

            boolean urlCheck =
                    driver.getCurrentUrl()
                            .contains("checkout/cart");

            boolean productPresent =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    cartProductByName(productName)
                            )
                    ).isDisplayed();

            logger.info(
                    "Cart validation for {} : {}",
                    productName,
                    urlCheck && productPresent
            );

            return urlCheck && productPresent;

        } catch (Exception e) {

            logger.error(
                    "Failed cart validation for {} : {}",
                    productName,
                    e.getMessage()
            );

            return false;
        }
    }

    public void removeProduct(String productName) {

        WebElement removeButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                removeButtonByProduct(productName)
                        )
                );

        scrollIntoView(removeButton);

        try {

            removeButton.click();

        } catch (Exception e) {

            logger.warn(
                    "Normal remove click failed for {}. Using JS click.",
                    productName
            );

            jsClick(removeButton);
        }

        logger.info(
                "Removed product {} from comparison",
                productName
        );
    }

    public void clearAllComparedProducts() {

        if (!isOnComparisonPage()) {

            logger.warn(
                    "Cannot clear products because current page is not Product Comparison page"
            );

            return;
        }

        wait.until(
                ExpectedConditions.visibilityOfAllElements(
                        removeLinks
                )
        );

        while (!removeLinks.isEmpty()) {

            WebElement removeButton =
                    removeLinks.get(0);

            scrollIntoView(removeButton);

            try {

                removeButton.click();

            } catch (Exception e) {

                logger.warn(
                        "Normal remove click failed. Using JS click."
                );

                jsClick(removeButton);
            }

            wait.until(
                    ExpectedConditions.stalenessOf(
                            removeButton
                    )
            );
        }

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtEmptyComparisonMessage
                )
        );

        logger.info(
                "All compared products removed successfully"
        );
    }

    public void logAllComparedProducts() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfAllElements(
                            comparedProductNames
                    )
            );

            logger.info(
                    "Products available in comparison table:"
            );

            for (WebElement product : comparedProductNames) {

                logger.info(
                        "Compared Product : {}",
                        product.getText().trim()
                );
            }

        } catch (Exception e) {

            logger.error(
                    "Failed to log compared products: {}",
                    e.getMessage()
            );
        }
    }

    public String getEmptyComparisonMessage() {

        String message =
                wait.until(
                        ExpectedConditions.visibilityOf(
                                txtEmptyComparisonMessage
                        )
                ).getText().trim();

        logger.info(
                "Empty comparison message captured: {}",
                message
        );

        return message;
    }
}