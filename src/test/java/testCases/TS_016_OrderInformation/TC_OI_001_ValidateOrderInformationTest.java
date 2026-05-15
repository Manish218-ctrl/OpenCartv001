package testCases.TS_016_OrderInformation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.CheckoutPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OI_001_ValidateOrderInformationTest extends BaseClass {

    @Test
    public void validateOrderInformationPageTest() {
        logger.info("Starting test: validateOrderInformationPageTest");

        performLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        HomePage homepage = new HomePage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        homepage.clickLogo();
        logger.info("Login successful and navigated to HomePage");

        homepage.searchProduct("HP LP3065");
        homepage.clickaddtocart0();
        homepage.clickaddtocarthpbtn();
        homepage.clickCartBlock();
        homepage.clickbtnCheckout();
        checkoutPage.completeCheckout();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='content']/h1")));

        homepage.clickMyAccount();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

        homepage.clickMyAccountFromDropdown();
        homepage.clickOrderHistory();
        logger.info("Navigated to Order History page");

        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
        orderHistoryPage.clickFirstOrderViewIcon();
        logger.info("Opened first order from history");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Order Information']")));

        OrderInformationPage orderInfoPage = new OrderInformationPage(getDriver());
        String pageTitle = orderInfoPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Order Information"),
                "Expected Order Information page but got: " + pageTitle);

        Assert.assertTrue(orderInfoPage.getOrderId().length() > 0, "Order ID missing");
        Assert.assertTrue(orderInfoPage.getProductName().length() > 0, "Product Name missing");
        Assert.assertTrue(orderInfoPage.getProductModel().length() > 0, "Product Model missing");
        Assert.assertTrue(Integer.parseInt(orderInfoPage.getProductQuantity()) > 0, "Product Quantity invalid");
        Assert.assertTrue(orderInfoPage.getProductPrice().length() > 0, "Product Price missing");
        Assert.assertTrue(orderInfoPage.getProductTotal().length() > 0, "Product Total missing");

        logger.info("Order Information page validated successfully.");
    }

    private void createNewOrder(String productName) {
        HomePage homepage = new HomePage(getDriver());
        homepage.createNewOrder(productName);
        logger.info("New order created successfully for product: " + productName);
    }
}