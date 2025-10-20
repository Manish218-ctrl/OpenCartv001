package testCases.TS_017_OrderHistory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

public class TC_OH_006_ValidateAllOrdersTest extends BaseClass {

    private OrderHistoryPage orderHistoryPage;

    @BeforeMethod
    public void setUp() {
        logger.info("***** Setting up TC_OH_006_ValidateAllOrdersTest *****");
        performLogin();
        logger.info("Login successful.");

        MyAccountPage myAccPage = new MyAccountPage(driver);
        myAccPage.clickOrderHistory();
        logger.info("Navigated to Order History page.");

        orderHistoryPage = new OrderHistoryPage(driver);
    }

    @Test
    public void validateOrderHistoryPage() {
        logger.info("***** Starting TC_OH_006_ValidateAllOrdersTest *****");

        try {
            // Step 1: Verify Order History Page is loaded
            String pageTitle = orderHistoryPage.getTitle();
            Assert.assertTrue(pageTitle.contains("Order History"), "Page title is incorrect!");
            logger.info("Order History page loaded successfully. Title: " + pageTitle);

            // Step 2: Verify at least one order exists
            boolean firstOrderVisible = orderHistoryPage.isFirstOrderViewIconVisible();
            Assert.assertTrue(firstOrderVisible, "No orders found in Order History!");
            logger.info("At least one order is present in the Order History table.");

            // Click the first order's view icon
            orderHistoryPage.clickFirstOrderViewIcon();

            Thread.sleep(20000);


            // Fetch the actual order ID
            String actualOrderId = orderHistoryPage.getOrderId();  // Fetch order ID from the page
            logger.info("Actual Order ID fetched: " + actualOrderId);

            // Step 3: Validate Order Details - Order ID
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
