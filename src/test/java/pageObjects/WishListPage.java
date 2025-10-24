package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WishListPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(WishListPage.class);

    public WishListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        final Logger logger = LoggerFactory.getLogger(WishListPage.class);
    }

    //  Locators
    private By wishlistHeader = By.xpath("/html/body/nav/div/div[2]/ul/li[3]/a/span");
    private By productRows = By.xpath("//table[contains(@class,'table')]/tbody/tr");


    private By productNameCell(String productName) {
        return By.xpath("//table[contains(@class,'table')]/tbody/tr/td/a[text()='" + productName + "']");
    }

    private By addToCartButtonForProduct(String productName) {
        return By.xpath("//div[@id='content']//table/tbody/tr[td/a[text()='" + productName + "']]/td[6]/button");
    }

    private By removeButtonForProduct(String productName) {
        return By.xpath("//a[text()='" + productName + "']/../..//button[@data-original-title='Remove']");
    }

    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//td[@class='text-left']/a")
    public List<WebElement> wishListProducts;

    @FindBy(xpath = "/html/body/div[2]/div/div/h2")
    public WebElement headingMyWishList;

    @FindBy(xpath = "/html/body/div[2]/div[1]")
    public WebElement successMessage;

    @FindBy(xpath ="//*[@id='account-wishlist']/div[1]")
    public WebElement successmessage;

    @FindBy(xpath = "//a[@title='Shopping Cart']")
    public WebElement linkShoppingCartHeader;





    public boolean isOnWishListPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(headingMyWishList)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void removeFirstProduct() {
        try {
            List<WebElement> rows = driver.findElements(productRows);
            if (!rows.isEmpty()) {
                WebElement firstProduct = rows.get(0).findElement(By.xpath(".//a"));
                String productName = firstProduct.getText().trim();
                WebElement removeBtn = rows.get(0).findElement(By.xpath(".//button[@data-original-title='Remove']"));
                wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
                System.out.println("Removed product from wishlist: " + productName);
            } else {
                System.out.println("No products found in wishlist to remove.");
            }
        } catch (Exception e) {
            System.out.println("Error while removing product: " + e.getMessage());
        }
    }

    public void clearWishList() {
        try {
            List<WebElement> removeButtons = driver.findElements(By.xpath("//button[@data-original-title='Remove']"));
            for (WebElement btn : removeButtons) {
                wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
                Thread.sleep(500); // allow DOM refresh
            }
        } catch (Exception e) {
            System.out.println("Error while clearing wishlist: " + e.getMessage());
        }
    }


    public int getTotalProductsInWishList() {
        try {
            List<WebElement> rows = driver.findElements(productRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void hoverOnProduct(String productName) {
        WebElement productElement = driver.findElement(productNameCell(productName));
        new Actions(driver).moveToElement(productElement).perform();
    }

    public void clickProductLink(String productName) {
        driver.findElement(productNameCell(productName)).click();
    }

    public By addToCartButtonInRow = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/button/i");

    public void clickAddToCartIcon(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonInRow)).click();
        logger.info("Clicked Add to Cart for product: {}", productName);
    }

    protected WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void clickWishListLinkFromSuccessMessage() {
        WebElement wishListLink = waitShort().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='wish list']"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishListLink);

        logger.info("Forced click on 'wish list' link using JavaScript.");
    }

    public String getSuccessMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSuccessmessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successmessage)).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickShoppingCartHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(linkShoppingCartHeader)).click();
    }

    // Locator for the 'Wish List' link in the navigation
    private By wishListHeaderLink = By.xpath("//a[normalize-space()='Wish List']");

    // Locator for the heading of the Wish List page (capture the page title heading)
    private By wishListPageHeading = By.xpath("//*[@id=\"content\"]/h2");

    // Method to click the 'Wish List' header link and navigate to the Wish List page
    public void clickWishListHeader() {
        try {
            // Wait for the Wish List link to be clickable and then click
            WebElement wishListHeader = wait.until(ExpectedConditions.elementToBeClickable(wishListHeaderLink));
            wishListHeader.click();
            logger.info("Navigated to the Wish List page.");
        } catch (Exception e) {
            logger.error("Error while clicking the Wish List header: " + e.getMessage());
        }
    }

    // Locator for the Wish List page heading
    private By headingLocator = By.xpath("//*[@id='content']/h2");

    // Method to get the Wish List page heading
    public String getWishListPageHeading() {
        try {
            // Ensure the page is fully loaded and the heading is visible
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));
            logger.info("Successfully captured Wish List page heading: " + heading.getText());
            return heading.getText();
        } catch (Exception e) {
            logger.error("Error while capturing the Wish List page heading: " + e.getMessage());
            return null;
        }
    }

    // Optional: If the element is inside an iframe, switch to it before accessing
    public void switchToIframe(String iframeLocator) {
        driver.switchTo().frame(driver.findElement(By.xpath(iframeLocator)));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }


    public boolean isProductInWishList(String productName) {
        for (WebElement product : wishListProducts) {
            if (product.getText().trim().equalsIgnoreCase(productName.trim())) {
                return true;
            }
        }
        logger.warn("Product '{}' not found in wishlist. Available: {}",
                productName,
                wishListProducts.stream().map(WebElement::getText).toList());
        return false;
    }


    public void clickRemoveButtonForProduct(String productName) {
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    //By.xpath("//a[text()='" + productName + "']/ancestor::tr//a[contains(@data-original-title,'Remove') or contains(@title,'Remove')]")
                    By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[1]/td[6]/a")
            ));
            removeBtn.click();
            logger.info("Clicked Remove button for product: " + productName);
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not click remove button for product '" + productName + "'", e);
        }
    }


    public void waitForModificationSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-success') and (contains(.,'modified your wish list') or contains(.,'removed from your wish list'))]")
        ));
    }


    public boolean isWishListEmptyMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='content']//p[contains(text(),'Your wish list is empty')]"))).isDisplayed();
    }



    public void removeAllProductsIndividually() {
        try {
            List<WebElement> removeButtons;
            do {
                removeButtons = driver.findElements(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[*]/td[6]/a"));
                if (!removeButtons.isEmpty()) {
                    WebElement btn = removeButtons.get(0);
                    wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
                    logger.info("Removed one product from wishlist.");
                    Thread.sleep(500); // Allow DOM refresh
                }
            } while (!removeButtons.isEmpty());
        } catch (Exception e) {
            logger.error("Error while removing all products from wishlist: {}", e.getMessage());
        }
    }

    @FindBy (xpath ="/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/button")

    public WebElement addtocartbtnfromwishlist;

    public void clickaddtocartbtnfromwishlist(){
        addtocartbtnfromwishlist.click();
    }

}
