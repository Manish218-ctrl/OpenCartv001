package testCases.TS_015_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_PR_004_ValidateProductReturnsPlaceholdersTest extends BaseClass {

    @Test
    public void validatePlaceholdersForProductReturnsPage() {

        logger.info(
                "***** Starting TC_PR_004_ValidateProductReturnsPlaceholdersTest *****"
        );

        try {

            performLogin();

            logger.info(
                    "User logged in successfully."
            );

            HomePage home =
                    new HomePage(getDriver());

            home.clickMyAccount();

            logger.info(
                    "Navigated to My Account."
            );

            MyAccountPage myAccountPage =
                    new MyAccountPage(getDriver());

            myAccountPage.clickOrderHistory();

            logger.info(
                    "Opened Order History page."
            );

            OrderHistoryPage orderHistoryPage =
                    new OrderHistoryPage(getDriver());

            orderHistoryPage.clickFirstOrderViewIcon();

            logger.info(
                    "Clicked on View button of an order."
            );

            ProductReturnsPage productReturnsPage =
                    new ProductReturnsPage(getDriver());

            productReturnsPage.clickReturnIcon();

            logger.info(
                    "Clicked on Return button to open Product Returns page."
            );

            String orderIdPlaceholder =
                    productReturnsPage.getOrderIDPlaceholder();

            String orderDatePlaceholder =
                    productReturnsPage.getOrderDatePlaceholder();

            String productNamePlaceholder =
                    productReturnsPage.getProductNamePlaceholder();

            String productCodePlaceholder =
                    productReturnsPage.getProductCodePlaceholder();

            String quantityPlaceholder =
                    productReturnsPage.getQuantityPlaceholder();

            String faultDetailsPlaceholder =
                    productReturnsPage.getFaultDetailsPlaceholder();

            logger.info(
                    "Captured placeholders from Product Returns page."
            );

            Assert.assertEquals(
                    orderIdPlaceholder,
                    "Order ID",
                    "Order ID placeholder mismatch!"
            );

            Assert.assertEquals(
                    orderDatePlaceholder,
                    "Order Date",
                    "Order Date placeholder mismatch!"
            );

            Assert.assertEquals(
                    productNamePlaceholder,
                    "Product Name",
                    "Product Name placeholder mismatch!"
            );

            Assert.assertEquals(
                    productCodePlaceholder,
                    "Product Code",
                    "Product Code placeholder mismatch!"
            );

            Assert.assertEquals(
                    quantityPlaceholder,
                    "Quantity",
                    "Quantity placeholder mismatch!"
            );

            Assert.assertEquals(
                    faultDetailsPlaceholder,
                    "Faulty or other details",
                    "Fault Details placeholder mismatch!"
            );

            logger.info(
                    "All placeholders validated successfully."
            );

        } catch (Exception e) {

            logger.error(
                    "Test failed due to exception: "
                            + e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }

        logger.info(
                "***** Finished TC_PR_004_ValidateProductReturnsPlaceholdersTest *****"
        );
    }
}