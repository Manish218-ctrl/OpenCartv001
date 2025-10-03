package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Billing & Delivery using existing address ----------
    public void fillExistingBillingDetails() {
        driver.findElement(By.id("input-payment-firstname")).clear();
        driver.findElement(By.id("input-payment-firstname")).sendKeys("John");
        driver.findElement(By.id("input-payment-lastname")).clear();
        driver.findElement(By.id("input-payment-lastname")).sendKeys("Doe");
        driver.findElement(By.id("input-payment-address-1")).clear();
        driver.findElement(By.id("input-payment-address-1")).sendKeys("123 Main St");
        driver.findElement(By.id("input-payment-city")).clear();
        driver.findElement(By.id("input-payment-city")).sendKeys("New York");
        driver.findElement(By.id("input-payment-postcode")).clear();
        driver.findElement(By.id("input-payment-postcode")).sendKeys("10001");
    }

    public void fillExistingDeliveryDetails() {
        driver.findElement(By.id("input-shipping-firstname")).clear();
        driver.findElement(By.id("input-shipping-firstname")).sendKeys("John");
        driver.findElement(By.id("input-shipping-lastname")).clear();
        driver.findElement(By.id("input-shipping-lastname")).sendKeys("Doe");
        driver.findElement(By.id("input-shipping-address-1")).clear();
        driver.findElement(By.id("input-shipping-address-1")).sendKeys("123 Main St");
        driver.findElement(By.id("input-shipping-city")).clear();
        driver.findElement(By.id("input-shipping-city")).sendKeys("New York");
        driver.findElement(By.id("input-shipping-postcode")).clear();
        driver.findElement(By.id("input-shipping-postcode")).sendKeys("10001");
    }

    // ---------- Region selection ----------
    public void selectRandomRegion() {
        WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-payment-zone")));
        Select select = new Select(regionDropdown);
        select.selectByIndex(1); // Choose first available region
    }

    // ---------- Continue buttons ----------
    public void clickOrderSuccessContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/div/a"))).click();
    }

    public void continueBillingDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"button-payment-address\"]"))).click();
    }

    public void continueDeliveryDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"button-shipping-address\"]"))).click();
    }

    public void continueDeliveryMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"button-shipping-method\"]"))).click();
    }

    public void acceptTermsAndConditions() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[2]/div/input[1]")));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void continuePaymentMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"button-payment-method\"]"))).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"button-confirm\"]"))).click();
    }

    // ---------- Order success ----------
    public boolean isOrderSuccessDisplayed() {
        try {
            return driver.findElement(By.xpath("/html/body/div[2]/div/div/h1")).getText().contains("Your order has been placed!");
        } catch (Exception e) {
            return false;
        }
    }

    public String getOrderSuccessText() {
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/p[1]")).getText();
    }





    // Step 2: Billing Details
    public void fillBillingDetails(String firstName, String lastName, String address,
                                   String city, String postcode, String country, String region) {
        driver.findElement(By.id("input-payment-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-payment-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-payment-address-1")).sendKeys(address);
        driver.findElement(By.id("input-payment-city")).sendKeys(city);
        driver.findElement(By.id("input-payment-postcode")).sendKeys(postcode);
        new Select(driver.findElement(By.id("input-payment-country"))).selectByVisibleText(country);
        new Select(driver.findElement(By.id("input-payment-zone"))).selectByVisibleText(region);

        driver.findElement(By.id("button-payment-address")).click();
    }





    // Step 5: Payment Method
    public void acceptTermsAndContinuePayment() {
        WebElement termsCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("agree"))
        );
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("button-payment-method"))
        );
        continueBtn.click();
    }



    public void completeCheckout() {
        // Fill in address/shipping/payment and confirm
        // You may need waits here depending on your site
        driver.findElement(By.id("button-payment-address")).click();
        driver.findElement(By.id("button-shipping-address")).click();
        driver.findElement(By.id("button-shipping-method")).click();
        driver.findElement(By.name("agree")).click(); // agree to terms
        driver.findElement(By.id("button-payment-method")).click();
        driver.findElement(By.id("button-confirm")).click();
    }





}

