package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductReturnsPage extends BasePage {

        public ProductReturnsPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);  // Initialize elements
        }


        @FindBy(xpath = "/html/body/div[2]/div/div/p[1]")
WebElement Returnsucessmsg;



        @FindBy(xpath = "//*[@id=\"input-order-id\"]")
        public WebElement orderIDField;

        @FindBy(xpath = "//*[@id=\"input-date-ordered\"]")
        public WebElement orderDateField;

        @FindBy(xpath = "//*[@id=\"input-product\"]")
        public WebElement productNameField;

        @FindBy(xpath = "//*[@id=\"input-model\"]")
        public WebElement productCodeField;

        @FindBy(xpath = "//*[@id='input-quantity']")
        public WebElement quantityField;

        @FindBy(xpath = "/html/body/div[2]/div/div/form/fieldset[2]/div[4]/div/div[2]/label/input")
        WebElement reasonField;

           // Submit Button
             @FindBy(xpath = "//input[@value='Submit']")
             WebElement submitButton;

           // Error messages (text-danger class is standard in OpenCart/TutorialsNinja)
            private By errorMessages = By.cssSelector(".text-danger");



        @FindBy(xpath = "/html/body/div[2]/div/div/form/fieldset[2]/div[5]/div/label[1]")
        WebElement productOpenedField;

        @FindBy(xpath = "//*[@id=\"input-comment\"]")
        public WebElement faultDetailsField;

        @FindBy(xpath = "//input[@value='Submit']")
        WebElement submitButtonrp;

        public void fillProductReturnForm( String orderID,
                                          String orderDate, String productName, String productCode, String quantity,
                                          String reason, boolean productOpened, String faultDetails) {

            orderIDField.sendKeys(orderID);
            orderDateField.sendKeys(orderDate);
            productNameField.sendKeys(productName);
            productCodeField.sendKeys(productCode);
            quantityField.sendKeys(quantity);
            reasonField.click();
            productOpenedField.sendKeys(productOpened ? "Yes" : "No");
            faultDetailsField.sendKeys(faultDetails);
        }

        public void clickSubmit() {
            submitButton.click();
        }

        public String getPageTitle() {
            return pageTitle.getText();
        }

        public String getReturnsucessmsg(){
            return Returnsucessmsg.getText();
        }


          public void clickSubmitrp() {
        submitButtonrp.click();
    }

    public boolean isValidationErrorDisplayed() {
        List<WebElement> errors = driver.findElements(errorMessages);
        return errors.size() > 0;
    }



    @FindBy(id = "input-order-id")
    public WebElement orderIDFieldp;

    @FindBy(id = "input-date-ordered")
    public WebElement orderDateFieldp;

    @FindBy(id = "input-product")
    public WebElement productNameFieldp;

    @FindBy(id = "input-model")
    public WebElement productCodeFieldp;

    @FindBy(id = "input-quantity")
    public WebElement quantityFieldp;

    @FindBy(id = "input-comment")
    public WebElement faultDetailsFieldp;

    @FindBy(xpath = "//a[normalize-space()='Back' and contains(@class,'btn')] | //input[@value='Back'] | //button[normalize-space()='Back']")
    private WebElement backButton;

    @FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")  // Example XPath for breadcrumb
    public WebElement breadcrumbElement;



    public String getOrderIDPlaceholder() {
        return orderIDFieldp.getAttribute("placeholder");
    }

    public String getOrderDatePlaceholder() {
        return orderDateFieldp.getAttribute("placeholder");
    }

    public String getProductNamePlaceholder() {
        return productNameFieldp.getAttribute("placeholder");
    }

    public String getProductCodePlaceholder() {
        return productCodeFieldp.getAttribute("placeholder");
    }

    public String getQuantityPlaceholder() {
        return quantityFieldp.getAttribute("placeholder");
    }

    public String getFaultDetailsPlaceholder() {
        return faultDetailsFieldp.getAttribute("placeholder");
    }

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement returnMessage;

    public void clickBack() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/h1")
    WebElement pageTitle;


    public String getReturnMessage() {
        return returnMessage.getText();
    }

}


