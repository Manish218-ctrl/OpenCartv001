package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageObjects.HomePage.logger;

public class OrderInformationPage extends BasePage {

    public OrderInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // “Return” icon/button on Order Information page
    @FindBy(xpath = "//a[@data-original-title='Return' or contains(@href,'return/add')]")
    private WebElement returnIcon;

    public void clickReturnIcon() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(returnIcon))
                .click();
    }


    @FindBy(xpath = "/html/body/div[2]/ul/li[4]/a")
    private WebElement pageTitle;

    @FindBy(xpath = "/html/body/div[2]/div/div/table[1]/thead/tr/td")
    private WebElement orderDetailsSection;


    // Method to get the page title
    public String getPageTitle() {
        return pageTitle.getText();
    }

    // Method to extract order details text
    public String getOrderDetailsText() {
        return orderDetailsSection.getText();
    }


    // Web elements for Order Details
    @FindBy(xpath = "/html/body/div[2]/div/div/table[1]/tbody/tr/td[1]/b[1]")
    private WebElement orderId;

    @FindBy(xpath = "/html/body/div[2]/div/div/table[1]/tbody/tr/td[2]/b[1]")
    private WebElement paymentMethod;

    @FindBy(xpath = "/html/body/div[2]/div/div/table[1]/tbody/tr/td[1]/b[2]")
    private WebElement dateAdded;

    @FindBy(xpath = "/html/body/div[2]/div/div/table[1]/tbody/tr/td[2]/b[2]")
    private WebElement shippingMethod;

    // Address sections
    @FindBy(xpath = "/html/body/div[2]/div/div/table[2]/thead/tr/td[1]")
    private WebElement paymentAddress;

    @FindBy(xpath = "/html/body/div[2]/div/div/table[2]/thead/tr/td[2]")
    private WebElement shippingAddress;

    // Product details section
    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/thead/tr/td[1]")
    private WebElement productDetails;



    // Order History section
    @FindBy(xpath = "/html/body/div[2]/div/div/h3")
    private WebElement orderHistoryStatus;

    private By pageTitleElementLocator = By.xpath("//h1[normalize-space()='Order Information']");

    private By continueButtonLocator = By.xpath("//*[@id='content']/div[2]/div/a");


    // Method to get Order ID from the page
    public String getOrderId() {
        return orderId.getText();
    }

    // Method to get Payment Method from the page
    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

    // Method to get Date Added from the page
    public String getDateAdded() {
        return dateAdded.getText();
    }

    // Method to get Shipping Method from the page
    public String getShippingMethod() {
        return shippingMethod.getText();
    }

    // Method to get Payment Address
    public String getPaymentAddress() {
        return paymentAddress.getText();
    }

    // Method to get Shipping Address
    public String getShippingAddress() {
        return shippingAddress.getText();
    }

    // Method to get Product Details (can be expanded to get specific product details)
    public String getProductDetails() {
        return productDetails.getText();
    }

    // Method to get Order History Status
    public String getOrderHistoryStatus() {
        return orderHistoryStatus.getText();
    }

    // Method to get Product Name
    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/thead/tr/td[1]")
    private WebElement productName;

    public String getProductName() {
        return productName.getText();  // Return the product name text
    }

    // Method to get Product Model
    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/thead/tr/td[2]")
    private WebElement productModel;

    public String getProductModel() {
        return productModel.getText();  // Return the product model text
    }

    // Method to get Product Quantity
    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[3]")
    private WebElement productQuantity;

    public String getProductQuantity() {
        return productQuantity.getText();  // Return the product quantity text
    }

    // Method to get Product Price
    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/thead/tr/td[4]")
    private WebElement productPrice;

    public String getProductPrice() {
        return productPrice.getText();  // Return the product price text
    }

    // Method to get Product Total
    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/thead/tr/td[5]")
    private WebElement productTotal;

    public String getProductTotal() {
        return productTotal.getText();  // Return the product total text
    }

    @FindBy(xpath = "//a[@data-original-title='Reorder']")
    private WebElement reorderIcon;

    public void clickReorderIcon() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(reorderIcon))
                .click();
    }

    // OrderInformationPage.java
    @FindBy(xpath = "//*[@id='content']/div[2]/div/a")
    public WebElement continueButton;

    public void clickContinueButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(continueButton))
                .click();
    }

    private void waitForPageToLoad() {
        try {
            Thread.sleep(2000);  // Simple sleep for 2 seconds to wait for the page load
            logger.info("Waiting for the Order History page to load...");
        } catch (InterruptedException e) {
            logger.error("Error while waiting for page to load: " + e.getMessage());
        }
    }


    // Breadcrumb Elements
    @FindBy(xpath = "//ul[@class='breadcrumb']")  // Adjust the XPath based on actual HTML structure
    private WebElement breadcrumb;

    @FindBy(xpath = "//a[text()='Home']")  // Breadcrumb link for Home
    private WebElement breadcrumbHome;

    @FindBy(xpath = "/html/body/div[2]/ul/li[3]/a")  // Breadcrumb link for Order History
    private WebElement breadcrumbOrderHistory;

    @FindBy(xpath = "//a[text()='Order Information']")  // Breadcrumb link for Order Information
    private WebElement breadcrumbOrderInformation;

    // Method to get the breadcrumb text
    public String getBreadcrumbText() {
        return breadcrumb.getText();
    }

    // Method to click on the Home breadcrumb
    public void clickBreadcrumbHome() {
        breadcrumbHome.click();
    }

    // Method to click on the Order History breadcrumb
    public void clickBreadcrumbOrderHistory() {
        breadcrumbOrderHistory.click();


    }

    public boolean isOrderInformationPageDisplayed() {
        return driver.getTitle().contains("Order Information");
    }



}

