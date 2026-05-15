package testCases.TS_017_OrderHistory;



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
                //Perform Login
                performLogin();
                logger.info("Login successful.");

                //Navigate to Order History
                MyAccountPage myAccountPage = new MyAccountPage(getDriver());
                myAccountPage.clickOrderHistory();
                logger.info("Clicked on Order History link.");

                //Verify Order History page is displayed
                OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
                String orderHistoryTitle = orderHistoryPage.getTitle();
                Assert.assertTrue(orderHistoryTitle.contains("Order History"),
                        "Order History page title is incorrect. Found: " + orderHistoryTitle);
                logger.info("Successfully landed on Order History page.");

                //Check if View icon is visible for first order
                Assert.assertTrue(orderHistoryPage.isFirstOrderViewIconVisible(),
                        "View icon is not visible for the first order.");
                logger.info("View icon is visible for the first order.");

                //Click on View icon
                orderHistoryPage.clickFirstOrderViewIcon();
                logger.info("Clicked on View icon for first order.");

                //Validate user is navigated to Order Information page
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
