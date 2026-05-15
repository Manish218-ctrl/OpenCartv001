package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {

        super(driver);

        
    }

    // LOCATORS

    @FindBy(id = "input-payment-firstname")
    private WebElement txtPaymentFirstName;

    @FindBy(id = "input-payment-lastname")
    private WebElement txtPaymentLastName;

    @FindBy(id = "input-payment-address-1")
    private WebElement txtPaymentAddress1;

    @FindBy(id = "input-payment-city")
    private WebElement txtPaymentCity;

    @FindBy(id = "input-payment-postcode")
    private WebElement txtPaymentPostcode;

    @FindBy(id = "input-payment-country")
    private WebElement dropdownPaymentCountry;

    @FindBy(id = "input-payment-zone")
    private WebElement dropdownPaymentZone;

    @FindBy(id = "input-shipping-firstname")
    private WebElement txtShippingFirstName;

    @FindBy(id = "input-shipping-lastname")
    private WebElement txtShippingLastName;

    @FindBy(id = "input-shipping-address-1")
    private WebElement txtShippingAddress1;

    @FindBy(id = "input-shipping-city")
    private WebElement txtShippingCity;

    @FindBy(id = "input-shipping-postcode")
    private WebElement txtShippingPostcode;

    @FindBy(xpath =
            "//input[@name='payment_address' and @value='new']")
    private WebElement radioNewBillingAddress;

    @FindBy(id = "button-payment-address")
    private WebElement btnBillingAddressContinue;

    @FindBy(id = "button-shipping-address")
    private WebElement btnShippingAddressContinue;

    @FindBy(id = "button-shipping-method")
    private WebElement btnShippingMethodContinue;

    @FindBy(xpath =
            "//input[@type='checkbox' and @name='agree']")
    private WebElement chkTermsConditions;

    @FindBy(id = "button-payment-method")
    private WebElement btnPaymentMethodContinue;

    @FindBy(id = "button-confirm")
    private WebElement btnConfirmOrder;

    @FindBy(xpath =
            "//div[@id='content']//h1[contains(normalize-space(),'Your order has been placed')]")
    private WebElement headingOrderSuccess;

    @FindBy(xpath =
            "//div[@id='content']//p[1]")
    private WebElement txtOrderSuccess;

    @FindBy(xpath =
            "//div[@id='content']//a[contains(@class,'btn-primary') and normalize-space()='Continue']")
    private WebElement btnOrderSuccessContinue;

    // ACTION METHODS

    public void selectNewBillingAddress() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        radioNewBillingAddress
                )
        ).click();
    }

    public void fillExistingBillingDetails() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtPaymentFirstName
                )
        ).clear();

        txtPaymentFirstName.sendKeys("John");

        txtPaymentLastName.clear();

        txtPaymentLastName.sendKeys("Doe");

        txtPaymentAddress1.clear();

        txtPaymentAddress1.sendKeys("123 Main St");

        txtPaymentCity.clear();

        txtPaymentCity.sendKeys("New York");

        txtPaymentPostcode.clear();

        txtPaymentPostcode.sendKeys("10001");
    }

    public void fillExistingDeliveryDetails() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtShippingFirstName
                )
        ).clear();

        txtShippingFirstName.sendKeys("John");

        txtShippingLastName.clear();

        txtShippingLastName.sendKeys("Doe");

        txtShippingAddress1.clear();

        txtShippingAddress1.sendKeys("123 Main St");

        txtShippingCity.clear();

        txtShippingCity.sendKeys("New York");

        txtShippingPostcode.clear();

        txtShippingPostcode.sendKeys("10001");
    }

    public void enterNewBillingAddress(
            String firstName,
            String lastName,
            String address1,
            String city,
            String postcode,
            String country,
            String region
    ) {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtPaymentFirstName
                )
        ).sendKeys(firstName);

        txtPaymentLastName.sendKeys(lastName);

        txtPaymentAddress1.sendKeys(address1);

        txtPaymentCity.sendKeys(city);

        txtPaymentPostcode.sendKeys(postcode);

        Select countryDropdown =
                new Select(dropdownPaymentCountry);

        countryDropdown.selectByVisibleText(country);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        dropdownPaymentZone
                )
        );

        Select zoneDropdown =
                new Select(dropdownPaymentZone);

        zoneDropdown.selectByVisibleText(region);
    }

    public void fillBillingDetails(
            String firstName,
            String lastName,
            String address,
            String city,
            String postcode,
            String country,
            String region
    ) {

        enterNewBillingAddress(
                firstName,
                lastName,
                address,
                city,
                postcode,
                country,
                region
        );

        continueBillingAddress();
    }

    public void selectRandomRegion() {

        Select select =
                new Select(
                        wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        dropdownPaymentZone
                                )
                        )
                );

        select.selectByIndex(1);
    }

    public void continueBillingAddress() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnBillingAddressContinue
                )
        ).click();
    }

    public void continueBillingDetails() {

        continueBillingAddress();
    }

    public void continueShippingAddress() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnShippingAddressContinue
                )
        ).click();
    }

    public void continueDeliveryDetails() {

        continueShippingAddress();
    }

    public void continueShippingMethod() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnShippingMethodContinue
                )
        ).click();
    }

    public void continueDeliveryMethod() {

        continueShippingMethod();
    }

    public void acceptTermsAndConditions() {

        WebElement checkbox =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                chkTermsConditions
                        )
                );

        if (!checkbox.isSelected()) {

            checkbox.click();
        }
    }

    public void continuePaymentMethod() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnPaymentMethodContinue
                )
        ).click();
    }

    public void acceptTermsAndContinuePayment() {

        acceptTermsAndConditions();

        continuePaymentMethod();
    }

    public void confirmOrder() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnConfirmOrder
                )
        ).click();
    }

    public void completeCheckout() {

        continueBillingAddress();

        continueShippingAddress();

        continueShippingMethod();

        acceptTermsAndConditions();

        continuePaymentMethod();

        confirmOrder();
    }

    public boolean isOrderSuccessDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(
                            headingOrderSuccess
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isOrderSuccessPageDisplayed() {

        return isOrderSuccessDisplayed();
    }

    public String getOrderSuccessText() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        txtOrderSuccess
                )
        ).getText();
    }

    public void clickOrderSuccessContinueButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnOrderSuccessContinue
                )
        ).click();
    }
}