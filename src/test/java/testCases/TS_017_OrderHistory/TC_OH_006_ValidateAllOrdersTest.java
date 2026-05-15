package testCases.TS_017_OrderHistory;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OH_006_ValidateAllOrdersTest extends BaseClass {

    private OrderHistoryPage orderHistoryPage;

    @BeforeMethod
    public void setUp() {
        logger.info("***** Setting up TC_OH_006_ValidateAllOrdersTest *****");
        performLogin();
        logger.info("Login successful.");

        MyAccountPage myAccPage = new MyAccountPage(getDriver());
        myAccPage.clickOrderHistory();
        logger.info("Navigated to Order History page.");

        orderHistoryPage = new OrderHistoryPage(getDriver());
    }

    @Test
    public void validateOrderHistoryPage() {
        logger.info("***** Starting TC_OH_006_ValidateAllOrdersTest *****");

        try {
            String pageTitle = orderHistoryPage.getTitle();
            Assert.assertTrue(pageTitle.contains("Order History"), "Page title is incorrect!");
            logger.info("Order History page loaded successfully. Title: " + pageTitle);

            boolean firstOrderVisible = orderHistoryPage.isFirstOrderViewIconVisible();
            Assert.assertTrue(firstOrderVisible, "No orders found in Order History!");
            logger.info("At least one order is present in the Order History table.");

            orderHistoryPage.clickFirstOrderViewIcon();

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[normalize-space()='Order Information']")));

            String actualOrderId = orderHistoryPage.getOrderId();
            logger.info("Actual Order ID fetched: " + actualOrderId);

            String orderId = orderHistoryPage.getOrderId();
            Assert.assertNotNull(orderId, "Order Id is missing in order details!");
            logger.info("Order Id found: " + orderId);

            logger.info("***** TC_OH_006_ValidateAllOrdersTest Passed *****");

        } catch (Exception e) {
            logger.error("Test case TC_OH_006_ValidateAllOrdersTest Failed", e);
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
}
