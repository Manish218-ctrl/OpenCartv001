package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
        
    }

    //LOCATORS

    @FindBy(xpath = "//table[contains(@class,'table-bordered') and contains(@class,'table-hover')]//td[contains(@class,'text-left')]/a")
    private List<WebElement> wishListProducts;

    @FindBy(xpath = "//div[@id='content']//h2[normalize-space()='My Wish List']")
    private WebElement headingMyWishList;

    @FindBy(xpath = "//div[contains(@class,'alert') and contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@id='account-wishlist']//div[contains(@class,'alert-success')]")
    private WebElement successmessage;

    @FindBy(xpath = "//a[@title='Shopping Cart']")
    private WebElement linkShoppingCartHeader;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr[1]//button[contains(@onclick,'cart.add')]")
    private WebElement addtocartbtnfromwishlist;

    @FindBy(xpath = "//a[@id='wishlist-total']//span[contains(normalize-space(),'Wish List')]")
    private WebElement wishlistHeader;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr")
    private List<WebElement> productRows;

    @FindBy(xpath = "//a[contains(@href,'account/wishlist') and normalize-space()='Wish List']")
    private WebElement wishListHeaderLink;

    @FindBy(xpath = "//div[@id='content']//h2")
    private WebElement headingLocator;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr[1]//button[contains(@onclick,'cart.add')]")
    private WebElement addToCartButtonInRow;

    //DYNAMIC LOCATORS

    private By productNameCell(String productName) {
        return By.xpath("//div[@id='content']//table//tbody//tr//td/a[normalize-space()='" + productName + "']");
    }

    private By addToCartButtonForProduct(String productName) {
        return By.xpath("//div[@id='content']//table//tbody//tr[td/a[normalize-space()='" + productName + "']]//button[contains(@onclick,'cart.add')]");
    }

    private By removeButtonForProduct(String productName) {
        return By.xpath("//div[@id='content']//table//tbody//tr[td/a[normalize-space()='" + productName + "']]//a[@data-toggle='tooltip' and @title='Remove']");
    }

    //ACTION METHODS

    public boolean isOnWishListPage() {

        try {
            return wait.until(ExpectedConditions.visibilityOf(headingMyWishList)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void removeFirstProduct() {

        try {

            if (!productRows.isEmpty()) {

                WebElement firstProduct = productRows.get(0).findElement(By.xpath(".//td/a"));

                String productName = firstProduct.getText().trim();

                WebElement removeBtn = productRows.get(0).findElement(By.xpath(".//a[@title='Remove']"));

                wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();

                logger.info("Removed product from wishlist: {}", productName);

            } else {

                logger.info("No products found in wishlist to remove.");
            }

        } catch (Exception e) {

            logger.error("Error while removing product: {}", e.getMessage());
        }
    }

    public void clearWishList() {

        try {

            List<WebElement> removeButtons =
                    driver.findElements(By.xpath("//a[@title='Remove']"));

            for (WebElement btn : removeButtons) {
                wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
            }

            logger.info("Cleared wishlist successfully.");

        } catch (Exception e) {

            logger.error("Error while clearing wishlist: {}", e.getMessage());
        }
    }

    public int getTotalProductsInWishList() {

        try {
            return productRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void hoverOnProduct(String productName) {
        hoverElement(driver.findElement(productNameCell(productName)));
    }

    public void clickProductLink(String productName) {
        clickElement(driver.findElement(productNameCell(productName)));
    }

    public void clickAddToCartIcon(String productName) {

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonInRow)).click();

        logger.info("Clicked Add to Cart for product: {}", productName);
    }

    public void clickWishListLinkFromSuccessMessage() {

        WebElement wishListLink =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'alert-success')]//a[contains(normalize-space(),'wish list')]")
                        )
                );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishListLink);

        logger.info("Forced click on wish list link using JavaScript.");
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
        clickElement(linkShoppingCartHeader);
    }

    public void clickWishListHeader() {

        try {

            clickElement(wishListHeaderLink);

            logger.info("Navigated to the Wish List page.");

        } catch (Exception e) {

            logger.error("Error while clicking the Wish List header: {}", e.getMessage());
        }
    }

    public String getWishListPageHeading() {

        try {

            String heading = wait.until(ExpectedConditions.visibilityOf(headingLocator)).getText();

            logger.info("Successfully captured Wish List page heading: {}", heading);

            return heading;

        } catch (Exception e) {

            logger.error("Error while capturing the Wish List page heading: {}", e.getMessage());

            return null;
        }
    }

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

        logger.warn(
                "Product {} not found in wishlist. Available: {}",
                productName,
                wishListProducts.stream().map(WebElement::getText).toList()
        );

        return false;
    }

    public void clickRemoveButtonForProduct(String productName) {

        try {

            WebElement removeBtn =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    removeButtonForProduct(productName)
                            )
                    );

            removeBtn.click();

            logger.info("Clicked Remove button for product: {}", productName);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error: Could not click remove button for product " + productName,
                    e
            );
        }
    }

    public void waitForModificationSuccessMessage() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'alert-success') and (contains(normalize-space(),'modified your wish list') or contains(normalize-space(),'removed from your wish list'))]")
                )
        );
    }

    public boolean isWishListEmptyMessageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']//p[contains(normalize-space(),'Your wish list is empty')]")
                )
        ).isDisplayed();
    }

    public void removeAllProductsIndividually() {

        try {

            List<WebElement> removeButtons;

            do {

                removeButtons =
                        driver.findElements(
                                By.xpath("//div[@id='content']//table//tbody//tr//a[@title='Remove']")
                        );

                if (!removeButtons.isEmpty()) {

                    WebElement btn = removeButtons.get(0);

                    wait.until(ExpectedConditions.elementToBeClickable(btn)).click();

                    logger.info("Removed one product from wishlist.");
                }

            } while (!removeButtons.isEmpty());

        } catch (Exception e) {

            logger.error(
                    "Error while removing all products from wishlist: {}",
                    e.getMessage()
            );
        }
    }

    //BACKWARD COMPATIBILITY METHODS

    public void clickaddtocartbtnfromwishlist() {
        addtocartbtnfromwishlist.click();
    }
}