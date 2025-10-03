package testCases.TS_018_OrderHistory;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

    public class TC_OH_008_ValidateOrderInformationNavigationFromOrderHistoryTest extends BaseClass {

        @Test(groups = {"Regression", "OrderHistory"})
        public void validateOrderInformationNavigationFromOrderHistory() {
            logger.info("***** Starting TC_OH_008_ValidateOrderInformationNavigationFromOrderHistoryTest *****");

            try {
                // Step 1: Perform Login
                performLogin();
                logger.info("Login successful.");

                // Step 2: Navigate to 'Order History'
                MyAccountPage myAccountPage = new MyAccountPage(driver);
                myAccountPage.clickOrderHistory();
                logger.info("Clicked on 'Order History' link.");

                // Step 3: Verify Order History page is displayed
                OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
                String orderHistoryTitle = orderHistoryPage.getTitle();
                Assert.assertTrue(orderHistoryTitle.contains("Order History"),
                        "Order History page title is incorrect. Found: " + orderHistoryTitle);
                logger.info("Successfully landed on Order History page.");

                // Step 4: Check if 'View' icon is visible for first order
                Assert.assertTrue(orderHistoryPage.isFirstOrderViewIconVisible(),
                        "View icon is not visible for the first order.");
                logger.info("View icon is visible for the first order.");

                // Step 5: Click on 'View' icon
                orderHistoryPage.clickFirstOrderViewIcon();
                logger.info("Clicked on 'View' icon for first order.");

                // Step 6: Validate user is navigated to 'Order Information' page
                String orderId = orderHistoryPage.getOrderId();
                Assert.assertTrue(orderId.startsWith("Order ID"),
                        "Failed to navigate to Order Information page. Found: " + orderId);
                logger.info("User successfully navigated to Order Information page. Order ID: " + orderId);

            } catch (Exception e) {
                logger.error("Test Case TC_OH_008 failed due to exception: " + e.getMessage());
                Assert.fail("Test Case TC_OH_008 failed. " + e.getMessage());
            }

            logger.info("***** Finished TC_OH_008_OrderHistoryNavigationTest *****");
        }
    }
