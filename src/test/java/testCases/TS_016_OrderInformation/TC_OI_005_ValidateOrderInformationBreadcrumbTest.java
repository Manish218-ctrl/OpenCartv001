package testCases.TS_016_OrderInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import testBase.BaseClass;

public class TC_OI_005_ValidateOrderInformationBreadcrumbTest extends BaseClass {

    @Test
    public void validateBreadcrumbInOrderInformationPage() {
        logger.info("Test started: TC_OI_005_ValidateOrderInformationBreadcrumbTest");

        //Perform login
        logger.info("Performing login...");
        performLogin();
        logger.info("Login successful. User is logged in.");

        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        myAccountPage.clickOrderHistory();

        //Navigate to Order History page
        logger.info("Navigating to Order History page...");
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
        orderHistoryPage.clickFirstOrderViewIcon();  // Click View icon for the first order
        logger.info("Navigated to Order Information page from Order History.");

        //Navigate to Order Information Page
        logger.info("Verifying breadcrumb on the Order Information page...");
        OrderInformationPage orderInformationPage = new OrderInformationPage(getDriver());

        //Get the breadcrumb and validate its functionality
        String breadcrumbText = orderInformationPage.getBreadcrumbText();
        logger.info("Breadcrumb text retrieved: " + breadcrumbText);

        // Validate breadcrumb is displayed and contains necessary text (e.g., Home -> Order History -> Order Information)
        logger.info("Validating breadcrumb content...");
        try {
            Assert.assertTrue(breadcrumbText.contains("Account"), "Home breadcrumb is missing!");
            logger.info("Home breadcrumb is present.");
            Assert.assertTrue(breadcrumbText.contains("Order History"), "Order History breadcrumb is missing!");
            logger.info("Order History breadcrumb is present.");
            Assert.assertTrue(breadcrumbText.contains("Order Information"), "Order Information breadcrumb is missing!");
            logger.info("Order Information breadcrumb is present.");
        } catch (AssertionError e) {
            logger.error("Breadcrumb validation failed: " + e.getMessage());
            throw e; // Rethrow exception after logging the error
        }



        logger.info("Breadcrumb validation passed successfully on the Order Information page.");
    }
}
