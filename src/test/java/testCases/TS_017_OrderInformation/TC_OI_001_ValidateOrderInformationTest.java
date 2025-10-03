package testCases.TS_017_OrderInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.CheckoutPage;
import testBase.BaseClass;

public class TC_OI_001_ValidateOrderInformationTest extends BaseClass {

    @Test
    public void validateOrderInformationPageTest() throws InterruptedException {
        logger.info("Starting test: validateOrderInformationPageTest");

        // Step 1: Login
        performLogin();

        Homepage homepage = new Homepage(driver);
        CheckoutPage checkoutPage=new CheckoutPage(driver);

        homepage.clickLogo();
        logger.info("Login successful and navigated to Homepage");

        // Step 1.5: Ensure there is at least one order
        //createNewOrder("MacBook");
        homepage.searchProduct("HP LP3065");
        homepage.clickaddtocart0();
        homepage.clickaddtocarthpbtn();
        homepage.clickCartBlock();
        homepage.clickbtnCheckout();
        checkoutPage.completeCheckout();

        Thread.sleep(20000);

        // Step 2: Navigate to 'Order History'
        homepage.clickMyAccount();

        homepage.clickMyAccountFromDropdown();
        homepage.clickOrderHistory();
        logger.info("Navigated to Order History page");

        // Step 3: Open first order
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.clickFirstOrderViewIcon();
        logger.info("Opened first order from history");

        // Step 4: Validate Order Information page
        OrderInformationPage orderInfoPage = new OrderInformationPage(driver);
        String pageTitle = orderInfoPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Order Information"),
                "Expected 'Order Information' page but got: " + pageTitle);

        // Validations
        Assert.assertTrue(orderInfoPage.getOrderId().length() > 0, "Order ID missing");
        Assert.assertTrue(orderInfoPage.getProductName().length() > 0, "Product Name missing");
        Assert.assertTrue(orderInfoPage.getProductModel().length() > 0, "Product Model missing");
        Assert.assertTrue(Integer.parseInt(orderInfoPage.getProductQuantity()) > 0, "Product Quantity invalid");
        Assert.assertTrue(orderInfoPage.getProductPrice().length() > 0, "Product Price missing");
        Assert.assertTrue(orderInfoPage.getProductTotal().length() > 0, "Product Total missing");

        logger.info("Order Information page validated successfully.");
    }

    // 🔹 Reusable method for order creation
    private void createNewOrder(String productName) {
        Homepage homepage = new Homepage(driver);

        // Use homepage method → handles product click + checkout flow
        homepage.createNewOrder(productName);

        logger.info("New order created successfully for product: " + productName);
    }
}
