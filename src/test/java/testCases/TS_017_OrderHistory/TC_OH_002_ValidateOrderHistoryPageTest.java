package testCases.TS_017_OrderHistory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

public class TC_OH_002_ValidateOrderHistoryPageTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_OH_002_ValidateOrderHistoryPageTest.class);

    private HomePage homepage;
    private OrderHistoryPage orderHistoryPage;

    @BeforeClass
    public void setup() {
        // Initializing the HomePage and OrderHistoryPage
        homepage = new HomePage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);

        // Login to the application
        performLogin();
        logger.info("Logged in successfully.");
    }

    @Test(priority = 1)
    public void verifyOrderHistoryPageTitle() {
        homepage.clickMyAccount();
        homepage.clickOrderHistory();  // Navigate to Order History Page
        logger.info("Navigated to Order History Page.");

        String pageTitle = orderHistoryPage.getTitle();

        // Validate the title of the Order History page
        Assert.assertEquals(pageTitle, "Order History", "Order History Page title is incorrect.");
        logger.info("Order History Page title is validated.");
    }

    @Test(priority = 2)
    public void verifyAllOrdersDisplayed() {
        homepage.clickMyAccount();
        homepage.clickOrderHistory();  // Navigate to Order History Page
        logger.info("Navigated to Order History Page.");

        // Check if the first order's 'View' icon is visible
        boolean isFirstOrderVisible = orderHistoryPage.isFirstOrderViewIconVisible();

        // Assert that the first order is visible (indicating orders are displayed)
        Assert.assertTrue(isFirstOrderVisible, "Order History is empty or first order is not visible.");
        logger.info("First order's 'View' icon visibility checked.");
    }

    @Test(priority = 3)
    public void verifyOrderDetails() throws InterruptedException {
        homepage.clickMyAccount();
        homepage.clickOrderHistory();  // Navigate to Order History Page
        logger.info("Navigated to Order History Page.");

        // Click the first order's view icon
        orderHistoryPage.clickFirstOrderViewIcon();

        // Add a brief delay to allow the page to load (not ideal, but necessary for some applications)
        Thread.sleep(20000);

        // Fetch the actual order ID
        String actualOrderId = orderHistoryPage.getOrderId();  // Fetch order ID from the page
        logger.info("Actual Order ID fetched: " + actualOrderId);

        // Validate the details of the order in the 'Order History'
        String expectedOrderId = "Order ID:";  // Example expected order ID
        Assert.assertEquals(actualOrderId, expectedOrderId, "Order ID does not match.");
        logger.info("Order ID validation successful: " + actualOrderId);
    }
}
