package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderInformationPage extends BasePage {

    public OrderInformationPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[last()]/a")
    private WebElement pageTitle;

    @FindBy(css = "ul.breadcrumb")
    private WebElement breadcrumb;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[1]/a")
    private WebElement breadcrumbHome;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[3]/a")
    private WebElement breadcrumbOrderHistory;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[4]/a")
    private WebElement breadcrumbOrderInformation;

    @FindBy(xpath = "//div[@id='content']//table[1]/thead/tr/td")
    private WebElement orderDetailsSection;

    @FindBy(xpath = "//div[@id='content']//table[1]/tbody/tr/td[1]/b[1]")
    private WebElement orderId;

    @FindBy(xpath = "//div[@id='content']//table[1]/tbody/tr/td[2]/b[1]")
    private WebElement paymentMethod;

    @FindBy(xpath = "//div[@id='content']//table[1]/tbody/tr/td[1]/b[2]")
    private WebElement dateAdded;

    @FindBy(xpath = "//div[@id='content']//table[1]/tbody/tr/td[2]/b[2]")
    private WebElement shippingMethod;

    @FindBy(xpath = "//div[@id='content']//table[2]/thead/tr/td[1]")
    private WebElement paymentAddress;

    @FindBy(xpath = "//div[@id='content']//table[2]/thead/tr/td[2]")
    private WebElement shippingAddress;

    @FindBy(xpath = "//div[@class='table-responsive']//table/thead/tr/td[1]")
    private WebElement productDetails;

    @FindBy(xpath = "//div[@class='table-responsive']//table/thead/tr/td[1]")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='table-responsive']//table/thead/tr/td[2]")
    private WebElement productModel;

    @FindBy(xpath = "//div[@class='table-responsive']//table/tbody/tr/td[3]")
    private WebElement productQuantity;

    @FindBy(xpath = "//div[@class='table-responsive']//table/thead/tr/td[4]")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='table-responsive']//table/thead/tr/td[5]")
    private WebElement productTotal;

    @FindBy(xpath = "//div[@id='content']/h3")
    private WebElement orderHistoryStatus;

    @FindBy(xpath = "//a[@data-toggle='tooltip' and @title='Return']")
    private WebElement returnIcon;

    @FindBy(xpath = "//a[@data-toggle='tooltip' and @title='Reorder']")
    private WebElement reorderIcon;

    @FindBy(xpath = "//div[@class='buttons clearfix']//a[contains(@class,'btn-primary')]")
    public WebElement continueButton;

    @FindBy(xpath = "//h1[normalize-space()='Order Information']")
    private WebElement pageTitleElement;

    @FindBy(xpath = "//div[contains(@class,'alert') and contains(@class,'alert-success')]")
    private WebElement reorderSuccessMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'checkout/cart')]")
    private WebElement shoppingCartLinkFromSuccessMessage;

    // ACTION METHODS

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public String getOrderDetailsText() {
        return orderDetailsSection.getText();
    }

    public String getOrderId() {
        return orderId.getText();
    }

    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

    public String getDateAdded() {
        return dateAdded.getText();
    }

    public String getShippingMethod() {
        return shippingMethod.getText();
    }

    public String getPaymentAddress() {
        return paymentAddress.getText();
    }

    public String getShippingAddress() {
        return shippingAddress.getText();
    }

    public String getProductDetails() {
        return productDetails.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductModel() {
        return productModel.getText();
    }

    public String getProductQuantity() {
        return productQuantity.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public String getProductTotal() {
        return productTotal.getText();
    }

    public String getOrderHistoryStatus() {
        return orderHistoryStatus.getText();
    }

    public String getBreadcrumbText() {
        return breadcrumb.getText();
    }

    public void clickReturnIcon() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(returnIcon))
                .click();
    }

    public void clickReorderIcon() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(reorderIcon))
                .click();
    }

    public void clickContinueButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(continueButton))
                .click();
    }

    public void clickBreadcrumbHome() {
        breadcrumbHome.click();
    }

    public void clickBreadcrumbOrderHistory() {
        breadcrumbOrderHistory.click();
    }

    public boolean isOrderInformationPageDisplayed() {
        return driver.getTitle().contains("Order Information");
    }

    private void waitForPageToLoad() {
        try {
            Thread.sleep(2000);
            logger.info("Waiting for the Order History page to load...");
        } catch (InterruptedException e) {
            logger.error("Error while waiting for page to load: " + e.getMessage());
        }
    }

    public boolean isReorderSuccessMessageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        reorderSuccessMessage
                )
        ).isDisplayed();
    }

    public String getReorderSuccessMessage() {

        return reorderSuccessMessage.getText();
    }

    public void clickShoppingCartLinkFromSuccessMessage() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        shoppingCartLinkFromSuccessMessage
                )
        ).click();
    }
}