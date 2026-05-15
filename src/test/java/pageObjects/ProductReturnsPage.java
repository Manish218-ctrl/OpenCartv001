package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductReturnsPage extends BasePage {

    public ProductReturnsPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1[contains(normalize-space(),'Product Returns')]")
    WebElement pageTitle;

    @FindBy(xpath = "//div[@id='content']//p[1]")
    WebElement returnSuccessMsg;

    @FindBy(xpath = "//div[contains(@class,'alert') and contains(@class,'alert-success')]")
    private WebElement returnMessage;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]")
    public WebElement breadcrumbElement;

    @FindBy(id = "input-order-id")
    public WebElement orderIDField;

    @FindBy(id = "input-order-id")
    public WebElement orderIDFieldp;

    @FindBy(id = "input-date-ordered")
    public WebElement orderDateField;

    @FindBy(id = "input-product")
    public WebElement productNameField;

    @FindBy(id = "input-model")
    public WebElement productCodeField;

    @FindBy(id = "input-quantity")
    public WebElement quantityField;

    @FindBy(id = "input-comment")
    public WebElement faultDetailsField;

    @FindBy(xpath = "//fieldset[2]//div[contains(@class,'radio')]//input[@name='return_reason_id']")
    WebElement reasonField;

    @FindBy(xpath = "//fieldset[2]//input[@name='opened']")
    WebElement productOpenedField;

    @FindBy(xpath = "//input[@value='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'buttons')]//a[contains(@class,'btn') and normalize-space()='Back'] | //input[@value='Back'] | //button[normalize-space()='Back']")
    private WebElement backButton;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr[1]//a[@title='View']")
    public WebElement firstOrderViewIcon;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr[2]//a[@title='View']")
    public WebElement secondOrderViewIcon;

    @FindBy(xpath = "//div[@id='content']//table//tbody//tr//td[last()]//a[contains(@href,'return/add')]")
    public WebElement returnIcon;

    @FindBy(name = "email")
    public WebElement emailField;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div[contains(@class,'text-danger')]")
    public WebElement emailValidationMessage;

    @FindBy(xpath = "//div[@id='content']//h1[normalize-space()='Product Returns']")
    private WebElement productReturnsPageTitle;

    private By errorMessages = By.cssSelector(".text-danger");

    // ACTION METHODS

    public void fillProductReturnForm(String orderID,
                                      String orderDate,
                                      String productName,
                                      String productCode,
                                      String quantity,
                                      String reason,
                                      boolean productOpened,
                                      String faultDetails) {

        orderIDField.sendKeys(orderID);

        orderDateField.sendKeys(orderDate);

        productNameField.sendKeys(productName);

        productCodeField.sendKeys(productCode);

        quantityField.sendKeys(quantity);

        reasonField.click();

        productOpenedField.sendKeys(
                productOpened ? "Yes" : "No"
        );

        faultDetailsField.sendKeys(faultDetails);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickBack() {

        wait.until(
                ExpectedConditions.elementToBeClickable(backButton)
        ).click();
    }

    public boolean isValidationErrorDisplayed() {

        return driver.findElements(errorMessages).size() > 0;
    }

    public String getReturnSuccessMsg() {

        return returnSuccessMsg.getText();
    }

    public String getReturnsucessmsg() {

        return getReturnSuccessMsg();
    }

    public void clickSubmitrp() {

        clickSubmit();
    }

    public String getReturnMessage() {

        return returnMessage.getText();
    }

    public String getOrderIDPlaceholder() {

        return orderIDField.getAttribute("placeholder");
    }

    public String getOrderDatePlaceholder() {

        return orderDateField.getAttribute("placeholder");
    }

    public String getProductNamePlaceholder() {

        return productNameField.getAttribute("placeholder");
    }

    public String getProductCodePlaceholder() {

        return productCodeField.getAttribute("placeholder");
    }

    public String getQuantityPlaceholder() {

        return quantityField.getAttribute("placeholder");
    }

    public String getFaultDetailsPlaceholder() {

        return faultDetailsField.getAttribute("placeholder");
    }

    public void clickFirstOrderViewIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(firstOrderViewIcon)).click();
        logger.info("Clicked View icon for first order.");
    }

    public void clickSecondOrderViewIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(secondOrderViewIcon)).click();
        logger.info("Clicked View icon for second order.");
    }

    public void clickReturnIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(returnIcon)).click();
        logger.info("Clicked Return icon.");
    }

    public void clearAllMandatoryFields() {
        orderIDField.clear();
        orderDateField.clear();
        productNameField.clear();
        productCodeField.clear();
        quantityField.clear();
        faultDetailsField.clear();
        logger.info("Cleared all mandatory fields.");
    }

    public void enterEmail(String email) {

        wait.until(
                ExpectedConditions.visibilityOf(emailField)
        );

        emailField.clear();

        emailField.sendKeys(email);
    }

    public boolean isEmailValidationMessageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(emailValidationMessage)
        ).isDisplayed();
    }

    public String getEmailValidationMessage() {

        return wait.until(
                ExpectedConditions.visibilityOf(emailValidationMessage)
        ).getText();
    }

    public boolean isBreadcrumbDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(breadcrumbElement)
        ).isDisplayed();
    }

    public String getBreadcrumbText() {

        return wait.until(
                ExpectedConditions.visibilityOf(breadcrumbElement)
        ).getText().trim();
    }

    public boolean isProductReturnsPageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        productReturnsPageTitle
                )
        ).isDisplayed();
    }

    public String getPageTitle() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        productReturnsPageTitle
                )
        ).getText().trim();
    }
}