package testCases.TS_017_OrderHistory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

public class TC_OH_003_ValidateOrderHistoryPageRightColumnNavigationTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_OH_003_ValidateOrderHistoryPageRightColumnNavigationTest.class);

    private HomePage homepage;
    private MyAccountPage myAccountPage;
    private OrderHistoryPage orderHistoryPage;

    @BeforeClass
    public void setup() {
        // Initialize Page Objects
        homepage = new HomePage(driver);
        myAccountPage = new MyAccountPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);

        // Login to the application
        performLogin();
        logger.info("User logged in successfully.");
    }

    @Test(priority = 1)
    public void verifyNavigationToOrderHistoryPageFromRightColumn() {
        // Step 1: Click on 'Order History' link from My Account right column
        myAccountPage.clickOrderHistory();
        logger.info("Clicked on 'Order History' link from My Account right column.");

        // Step 2: Validate navigation to Order History Page
        String pageTitle = orderHistoryPage.getTitle();
        Assert.assertEquals(pageTitle, "Order History", "User is not navigated to Order History page.");
        logger.info("Navigation to Order History page validated successfully.");
    }
}
