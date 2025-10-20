package testCases.TS_016_OrderInformation;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_OI_006_ValidateOrderInformationPageTest extends BaseClass {

        private OrderHistoryPage orderHistoryPage;
        private OrderInformationPage orderInformationPage;

        @BeforeClass
        public void setUp() throws IOException {

            performLogin();


            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickOrderHistory();

            // Initialize the page objects
            orderHistoryPage = new OrderHistoryPage(driver);
            orderInformationPage = new OrderInformationPage(driver);
        }

        @Test(priority = 1, description = "Validate the URL, Page Title, and Page Heading of the 'Order Information' page")
        public void testOrderInformationPageValidation() {
            // Navigate to Order History page
            orderHistoryPage.clickFirstOrderViewIcon();

            // Get the current URL, Title, and Heading of the Order Information Page
            String currentURL = driver.getCurrentUrl();
            String pageTitle = orderInformationPage.getPageTitle();
            String pageHeading = orderInformationPage.getOrderDetailsText(); // Assuming this represents the page heading

            // Validate the URL
            Assert.assertTrue(currentURL.contains("order/info"), "URL validation failed. Current URL: " + currentURL);

            // Validate the Page Title
            Assert.assertEquals(pageTitle, "Order Information", "Page title validation failed. Expected: Order Information, but found: " + pageTitle);

            // Validate the Page Heading
            Assert.assertTrue(pageHeading.contains("Order Details"), "Page heading validation failed. Expected 'Order Details', but found: " + pageHeading);

            // Optionally, you can log results
            logger.info("Order Information Page validated successfully with the correct URL, Title, and Heading.");
        }

        @Test(priority = 2, description = "Validate Order Details Section")
        public void testOrderDetailsSection() {
            // Extract specific order details
            String orderId = orderInformationPage.getOrderId();
            String paymentMethod = orderInformationPage.getPaymentMethod();
            String shippingMethod = orderInformationPage.getShippingMethod();
            String orderHistoryStatus = orderInformationPage.getOrderHistoryStatus();

            // Validate the extracted details (you may have predefined expected values or check if they are not empty)
            Assert.assertNotNull(orderId, "Order ID is null");
            Assert.assertNotNull(paymentMethod, "Payment Method is null");
            Assert.assertNotNull(shippingMethod, "Shipping Method is null");
            Assert.assertNotNull(orderHistoryStatus, "Order History Status is null");

            // Log the order details
            logger.info("Order ID: " + orderId);
            logger.info("Payment Method: " + paymentMethod);
            logger.info("Shipping Method: " + shippingMethod);
            logger.info("Order History Status: " + orderHistoryStatus);
        }
    }



