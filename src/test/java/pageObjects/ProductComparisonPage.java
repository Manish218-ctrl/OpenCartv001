package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class ProductComparisonPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductComparisonPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductComparisonPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ----- WebElements -----
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement headingComparePage;

    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    private WebElement btnContinue;

    private By productNameLinks = By.xpath(
            "//table[contains(@class,'table') and contains(@class,'table-bordered')]//td[1]/a");

    private By removeLinks = By.xpath("//a[text()='Remove']");

    // ----- Page Methods -----

    public boolean isOnComparisonPage() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.textToBePresentInElement(headingComparePage, "Product Comparison"),
                    ExpectedConditions.urlContains("compare")
            ));
        } catch (Exception e) {
            return false;
        }
    }

    //  Single reliable method to check product presence
    public boolean isProductPresent(String productName) {
        try {
            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(productNameLinks));
            for (WebElement product : products) {
                if (product.getText().trim().equalsIgnoreCase(productName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("Error while checking product in comparison: {}", e.getMessage());
        }
        return false;
    }

    public int getComparedProductCount() {
        try {
            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(productNameLinks));
            return products.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clearAllComparedProducts() {
        try {
            List<WebElement> removeBtns = driver.findElements(removeLinks);
            for (WebElement removeBtn : removeBtns) {
                try {
                    removeBtn.click();
                    wait.until(ExpectedConditions.stalenessOf(removeBtn));
                } catch (Exception ignore) {}
            }
        } catch (Exception e) {
            // No products to remove
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

    public boolean isOnHomePage() {
        try {
            return wait.until(ExpectedConditions.urlMatches(".*/index.php.*"));
        } catch (Exception e) {
            return false;
        }
    }

    // Click "Add to Cart" for a given product inside comparison table
    public void addProductToCart(String productName) {
        try {
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td[a/text()='" + productName + "']/following-sibling::td//button[contains(@onclick,'cart.add')]")
            ));
            addToCartBtn.click();
            logger.info("Clicked 'Add to Cart' for product: " + productName);
        } catch (Exception e) {
            throw new NoSuchElementException("Could not find 'Add to Cart' button for product: " + productName, e);
        }
    }

    // Verify product added to cart via success message
    public boolean isProductInCart(String productName) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.cssSelector("div.alert-success"), productName
            ));
        } catch (Exception e) {
            return false;
        }
    }

    // Wait for a product to appear in the comparison list
    public boolean waitForProductToBeListed(String productName) {
        try {
            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//table[contains(@class,'table') and contains(@class,'table-bordered')]//a[text()='" + productName + "']")
            ));
            return product != null;
        } catch (TimeoutException e) {
            logger.error("Timed out waiting for product to be listed in comparison: " + productName);
            return false;
        }
    }

    public void waitForComparisonTableToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//table[contains(@class,'table') and contains(@class,'table-bordered')]")));
    }


}
