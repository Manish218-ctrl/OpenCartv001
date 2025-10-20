package testCases.TS_018_AddressBook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_AB_007_ValidateNewBillingAddressAddedToAddressBookTest extends BaseClass {

    @Test
    public void validateNewBillingAddressAddedToAddressBook() {
        logger.info("===== TC_AB_015: Validate New Billing Address in Address Book =====");

        try {
            // Step 1: Login
            logger.info("Step 1: Logging in with user: " + username);
            performLogin();
            logger.info("Login successful.");

            // Step 2: Search product and add to cart
            HomePage home = new HomePage(driver);


            Thread.sleep(3000);

            home.enterSearchText(productName);
            home.clickSearchButton();
            home.addProductToCart(productName);
            home.selectListView();
            home.clickaddtocart();
            home.clickshoppingcartbtnmsg();
            home.clickcheckoutfromcart();
            logger.info("Navigated to checkout page");

            logger.info("Proceeding to Checkout");
           // home.clickCheckout();
            logger.info("Checkout page loaded.");

            // Step 3.5: Use new billing address
            logger.info("Step 3.5: Selecting 'I want to use a new address'");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement newAddressRadio = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div/form/div[3]/label/input")));
            newAddressRadio.click();
            logger.info("'I want to use a new address' selected successfully.");

            // Step 4: Enter billing address
            logger.info("Step 4: Entering new billing address");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
            driver.findElement(By.id("input-payment-firstname")).sendKeys("John");
            driver.findElement(By.id("input-payment-lastname")).sendKeys("Doe");
            driver.findElement(By.id("input-payment-address-1")).sendKeys("123 New Street");
            driver.findElement(By.id("input-payment-city")).sendKeys("New York");
            driver.findElement(By.id("input-payment-postcode")).sendKeys("10001");
            logger.info("Text fields filled.");

            Select countrySelect = new Select(driver.findElement(By.id("input-payment-country")));
            countrySelect.selectByVisibleText("United States");
            logger.info("Country selected: United States");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("input-payment-zone")));
            Select zoneSelect = new Select(driver.findElement(By.id("input-payment-zone")));
            zoneSelect.selectByVisibleText("New York");
            logger.info("State/Zone selected: New York");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-address")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
            continueButton.click();
            logger.info("Clicked Continue on Billing Details");

            // Step 5: Accept terms & confirm order
            logger.info("Step 5: Proceeding with shipping, payment, and confirming order");

            // Step 5.1: Continue Delivery Address
            WebElement deliveryContinue = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-address")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deliveryContinue);
            deliveryContinue.click();
            logger.info("Delivery Address step completed.");

            // Step 5.2: Continue Delivery Method
            WebElement shippingMethodContinue = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shippingMethodContinue);
            shippingMethodContinue.click();
            logger.info("Delivery Method step completed.");

            // Step 5.3: Accept Terms & Conditions
            WebElement termsCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@type='checkbox' and (@name='agree' or @id='input-agree')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", termsCheckbox);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
            logger.info("Terms and Conditions accepted successfully.");

            Thread.sleep(3000);

            // Step 5.4: Continue Payment Method
            WebElement paymentMethodContinue = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='button-payment-method']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentMethodContinue);
            paymentMethodContinue.click();
            logger.info("Clicked Continue on Payment Method.");

            // Step 5.5: Confirm Order
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='button-confirm']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmButton);
            confirmButton.click();
            logger.info("Order confirmed successfully.");

            // Step 6: Navigate to My Account -> Address Book
            logger.info("Step 6: Navigating to My Account -> Address Book");
            Thread.sleep(3000);
            home.clickMyAccount();
            logger.info("Clicked My Account menu");
            Thread.sleep(3000); // Short wait for dropdown animation
            home.clickMyAccountFromDropdown();
            logger.info("Clicked 'My Account' from dropdown");
            MyAccountPage myAccount = new MyAccountPage(driver);
            Thread.sleep(3000);
            myAccount.clickAddressBookLink();
            logger.info("Address Book link clicked.");

            // Step 7: Verify new address
            AddressBookPage addressBook = new AddressBookPage(driver);
            addressBook.verifyAddressBookPage();
            logger.info("Address Book page verified.");

            Thread.sleep(3000);

            addressBook.clickeditaddressbook();

            boolean isDefaultSelected = addressBook.isDefaultAddressSelected();
            if (isDefaultSelected) {
                logger.info("Newly added address is set as Default Address.");
            } else {
                logger.info("Newly added address is present but NOT set as Default Address.");
            }

            logger.info("===== TC_AB_015 completed successfully =====");

        } catch (Exception e) {
            logger.error("TC_AB_015 failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test TC_AB_015 failed due to exception: " + e.getMessage());
        }
    }
}
