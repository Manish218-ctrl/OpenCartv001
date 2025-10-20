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
    public WebElement headingComparePage;

    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    public WebElement btnContinue;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div[2]/div[2]/button[3]")
    public WebElement productcomparelink;

    @FindBy (xpath = "/html/body/div[2]/div/div/table/tbody[4]/tr/td[2]/input")
    public WebElement addtocartbtn1;

    @FindBy (xpath = "/html/body/div[2]/div/div/table/tbody[4]/tr/td[3]/input")
    public WebElement addtocartbtn2;

    @FindBy (xpath = "/html/body/div[2]/div[1]/a[2]")
    public WebElement cartlinkinsuccessmsg;



    // Locator for all product names in the comparison table
    public By productNameLinks = By.xpath(
            "//*[@id=\"content\"]/table/tbody[1]/tr[1]/td[2]/a/strong");

    public By removeLinks = By.xpath("//a[text()='Remove']");


    public static final String PRODUCT_NAME_LINK_XPATH =
            "//*[@id='content']/table/tbody[1]/tr[1]/td[2]/a/strong";


    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

    public void clickproductcomparelink(){
        productcomparelink.click();
    }

    public void clickaddtocartbtn1(){
        addtocartbtn1.click();
    }

    public void clickaddtocartbtn2(){
        addtocartbtn2.click();
    }

    public void clickcartlinkinsuccessmsg(){
        cartlinkinsuccessmsg.click();
    }




    public boolean waitForProductToBeListed(String productName) {
        try {
            // 1. Ensure the table structure is present first
            waitForComparisonTableToLoad();

            // 2. Wait for the specific product link to become visible using the targeted XPath
            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(String.format(PRODUCT_NAME_LINK_XPATH, productName))
            ));

            // Check that the product link is both visible and enabled
            return product != null && product.isDisplayed();

        } catch (TimeoutException e) {
            // Log the failure to debug
            logger.error("Timed out (20s) waiting for product to be listed in comparison: " + productName);
            return false;
        } catch (Exception e) {
            // Catch any other exceptions (e.g., NoSuchElementException)
            logger.error("Error checking for product in comparison: " + productName + ". Message: " + e.getMessage());
            return false;
        }
    }

    public void waitForComparisonTableToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//table[contains(@class,'table') and contains(@class,'table-bordered')]")));
    }


    // Method to check product presence (used by many tests)
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

    // Method to get count (used by TC_PC_014, TC_PC_013)
    public int getComparedProductCount() {
        try {
            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(productNameLinks));
            return products.size();
        } catch (Exception e) {
            return 0;
        }
    }



    public boolean isOnComparisonPage() {
        try {
            // Use a high-visibility element unique to the Comparison page (the main heading)
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='content']//h1[normalize-space()='Product Comparison']")
            ));

            // Also check the URL as a secondary confirmation
            boolean urlCheck = driver.getCurrentUrl().contains("route=product/compare");

            return heading.isDisplayed() && urlCheck;

        } catch (Exception e) {
            logger.error("Failed to confirm navigation to Product Comparison page: {}", e.getMessage());
            return false;
        }
    }



    // Method to check if navigated back to the homepage
    public boolean isOnHomePage() {
        try {
            return wait.until(ExpectedConditions.urlMatches(".*/index.php.*"));
        } catch (Exception e) {
            return false;
        }
    }

    public void logAllComparedProducts() {
        try {
            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(productNameLinks)
            );

            logger.info("Products listed in comparison table:");
            for (WebElement product : products) {
                String name = product.getText().trim();
                logger.info(" - " + name);
            }
        } catch (Exception e) {
            logger.error("Failed to log compared products: " + e.getMessage());
        }
    }



    public boolean isProductInComparisonTable(String productName) {
        // Construct the full dynamic XPath
        By productLinkLocator = By.xpath(String.format(PRODUCT_NAME_LINK_XPATH, productName));

        try {
            // Wait for the specific product link to be visible in the table.
            wait.until(ExpectedConditions.visibilityOfElementLocated(productLinkLocator));
            logger.info("Product '{}' successfully found in the comparison table.", productName);
            return true;
        } catch (Exception e) {
            // If not found after the timeout, return false.
            logger.warn("Product '{}' NOT found in the comparison table after waiting.", productName);
            return false;
        }
    }


    public void addProductToCart(String productName) {
        logger.info("Attempting to add product '{}' to cart from comparison page.", productName);

        // 1. Locate the product link element in the table header (<th> or <td>)
        // This helps us find its column.
        By productHeaderLinkLocator = By.xpath(
                "//table[@class='table table-bordered']//thead//a[normalize-space()='" + productName + "']"
        );
        WebElement productLinkInHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(productHeaderLinkLocator));

        // 2. Find the immediate parent TH (table header) of the product link.
        WebElement headerCell = productLinkInHeader.findElement(By.xpath("./ancestor::th[1]"));

        // 3. Find all preceding TH elements in that same header row to determine the 1-based column index.
        // This calculates the position of the product's column.
        int columnIndex = driver.findElements(By.xpath(
                "//table[@class='table table-bordered']//thead//th[preceding-sibling::th]"
        )).size() + 1; // Start with 1-based index

        List<WebElement> allHeaderThs = driver.findElements(By.xpath("//table[@class='table table-bordered']//thead//th"));
        int productActualColumnIndex = -1;
        for (int i = 0; i < allHeaderThs.size(); i++) {
            if (allHeaderThs.get(i).equals(headerCell)) {
                productActualColumnIndex = i + 1; // 1-based index of the product's TH
                break;
            }
        }

        if (productActualColumnIndex == -1) {
            throw new NoSuchElementException("Failed to find column index for product: " + productName + " in the comparison table header.");
        }


        By addToCartBtnLocator = By.xpath(
                "//td[normalize-space()='Add to Cart']/following-sibling::td[" + (productActualColumnIndex -1) + "]//button[contains(@onclick, 'cart.add')]"
        );


        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtnLocator));

        // 5. Scroll the 'Add to Cart' button into view before clicking.
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", addToCartBtn
        );
        logger.info("Scrolled 'Add to Cart' button for '{}' into view.", productName);

        // 6. Click the button, with a fallback for potential interception issues.
        try {
            addToCartBtn.click();
            logger.info("Successfully clicked 'Add to Cart' for '{}'.", productName);
        } catch (Exception e) {
            logger.warn("Standard click failed for 'Add to Cart' button. Attempting JS click for '{}'. Error: {}", productName, e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
        }
    }
    // --- FIX END: addProductToCart method with robust scrolling ---

    // You might also need the isProductInCart method in ProductComparisonPage:
    public boolean isProductInCart(String productName) {
        // This is typically in the Mini Cart/Shopping Cart page, not Comparison page.
        // Assuming this method checks the actual Shopping Cart page.
        // If it's meant to check a success message, you'll need to adapt.
        // For now, let's assume it checks the final cart.
        logger.warn("isProductInCart on ProductComparisonPage currently assumes checking the actual Shopping Cart page after navigation. Ensure correct context.");
        return driver.getCurrentUrl().contains("checkout/cart") &&
                driver.findElements(By.xpath("//td[@class='text-left']//a[normalize-space()='" + productName + "']")).size() > 0;
    }

    // Example of clearAllComparedProducts method
    public void clearAllComparedProducts() {
        if (isOnComparisonPage()) {
            By removeButtonLocator = By.xpath("//a[contains(@href, 'remove')]");
            List<WebElement> removeButtons = driver.findElements(removeButtonLocator);
            if (!removeButtons.isEmpty()) {
                logger.info("Clearing {} products from comparison page.", removeButtons.size());
                // Click all remove buttons to clear the comparison
                for (WebElement button : removeButtons) {
                    try {
                        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                        // Add a small wait for AJAX updates if necessary
                        Thread.sleep(500); // Bad practice, use explicit waits for visibility of change
                    } catch (Exception e) {
                        logger.warn("Failed to click a remove button, attempting JS click. Error: {}", e.getMessage());
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                        try { Thread.sleep(500); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                    }
                }
                // Wait for the "no products to compare" message to appear
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='You have not chosen any products to compare.']")));
                logger.info("Cleared all products from comparison page.");
            } else {
                logger.info("No products to clear on comparison page.");
            }
        } else {
            logger.warn("Not on Product Comparison page, cannot clear products.");
        }
    }
}

