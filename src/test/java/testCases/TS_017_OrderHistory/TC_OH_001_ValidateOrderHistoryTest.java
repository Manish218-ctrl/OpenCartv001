package testCases.TS_017_OrderHistory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

public class TC_OH_001_ValidateOrderHistoryTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_OH_001_ValidateOrderHistoryTest.class);

    HomePage home;
    LoginPage loginPage;
    OrderHistoryPage orderHistoryPage;

    @BeforeMethod
    public void setUp() {
        home = new HomePage(driver);
        loginPage = new LoginPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);

        logger.info("Initializing test setup and performing login.");
        // Perform login using valid credentials
        performLogin();
        logger.info("Login successful.");
    }

    @Test(description = "Validate navigating to 'Order History' page from 'My Account' page")
    public void validateNavigateToOrderHistoryPage() throws InterruptedException {

        HomePage.clickLogo();
        home.enterSearchText("HP LP3065");

        home.clickSearchButton();
        home.clickaddtocart0();
        home.clickaddtocarthpbtn();


        // Step 1: Click on "My Account" dropdown in the homepage
        logger.info("Step 1: Clicking on 'My Account' dropdown.");
        home.clickMyAccount();
        home.clickMyAccountFromDropdown();


        // Step 2: Click on "Order History" link from the dropdown
        logger.info("Step 2: Clicking on 'Order History' link.");
        home.clickOrderHistory();


        // Step 3: Wait for the "Order History" page to load and verify the page title
        Thread.sleep(20000);
        logger.info("Step 3: Waiting for the 'Order History' page to load.");
        String actualTitle = orderHistoryPage.getTitle();
        String expectedTitle = "Order History";  // Assuming this is the expected title

        // Step 4: Assert that the actual title matches the expected title
        logger.info("Step 4: Asserting that the page title matches the expected title.");
        Assert.assertEquals(actualTitle, expectedTitle, "Order History page title does not match.");


        logger.info("Test completed successfully.");
    }
}
