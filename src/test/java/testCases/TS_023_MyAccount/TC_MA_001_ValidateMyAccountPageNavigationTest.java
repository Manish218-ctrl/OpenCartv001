package testCases.TS_023_MyAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.OrderSuccessPage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_MA_001_ValidateMyAccountPageNavigationTest extends BaseClass {

    private final String PRODUCT_NAME = "HP LP3065";

    @Test(groups = {"Regression"})
    public void validateMyAccountNavigationFromOrderSuccessPage() {
        logger.info("Starting TC_MA_001: Validate navigation to My Account page from Order Success page.");

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

            performLogin();

            HomePage homePage = new HomePage(getDriver());
            ProductDisplayPage productPage = new ProductDisplayPage(getDriver());
            CheckoutPage checkoutPage = new CheckoutPage(getDriver());
            OrderSuccessPage orderSuccessPage = new OrderSuccessPage(getDriver());
            MyAccountPage myAccountPage = new MyAccountPage(getDriver());

            logger.info("Attempting to place an order for product: " + PRODUCT_NAME);

            HomePage home = new HomePage(getDriver());
            home.enterSearchText(productName);
            home.clickSearchButton();
            home.addProductToCart(productName);
            home.clickaddtocart();
            home.clickshoppingcartbtnmsg();
            home.clickcheckoutfromcart();
            logger.info("Navigated to checkout page");

            CheckoutPage checkout = new CheckoutPage(getDriver());
            checkout.continueBillingDetails();
            checkout.continueDeliveryDetails();
            checkout.continueDeliveryMethod();
            checkout.acceptTermsAndConditions();
            checkout.continuePaymentMethod();
            checkout.confirmOrder();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='content']/h1")));

            logger.info("Navigating to My Account -> Address Book");
            home.clickMyAccount();
            logger.info("Clicked My Account menu");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

            home.clickMyAccountFromDropdown();
            logger.info("Clicked My Account from dropdown");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='content']//h2[text()='My Account']")));

            MyAccountPage myAccount = new MyAccountPage(getDriver());

            boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageDisplayed();
            Assert.assertTrue(isMyAccountPageDisplayed,
                    "Test Failed: User was NOT navigated to the My Account page.");

            logger.info("Test Passed: Successfully validated navigation to the My Account page from Order Success page.");
            if (BaseClass.getTest() != null)
                BaseClass.getTest().pass("Navigation to My Account page from Order Success page validated successfully.");
        } catch (Exception e) {
            logger.error("Test Failed: TC_MA_001 failed due to an exception: " + e.getMessage());
            if (BaseClass.getTest() != null)
                BaseClass.getTest().fail("Test Failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}