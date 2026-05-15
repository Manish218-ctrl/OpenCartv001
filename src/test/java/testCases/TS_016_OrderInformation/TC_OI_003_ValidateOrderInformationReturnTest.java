package testCases.TS_016_OrderInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_OI_003_ValidateOrderInformationReturnTest extends BaseClass {

    @Test
    public void validateReturnFunctionalityTest() {

        logger.info(
                "Starting test: validateReturnFunctionalityTest"
        );

        try {

            // Login
            performLogin();

            logger.info(
                    "Login successful."
            );

            // Navigate to Order History
            HomePage homepage =
                    new HomePage(getDriver());

            homepage.clickMyAccount();

            logger.info(
                    "Clicked My Account."
            );

            homepage.clickOrderHistory();

            logger.info(
                    "Navigated to Order History page."
            );

            // Open first order
            OrderHistoryPage orderHistoryPage =
                    new OrderHistoryPage(getDriver());

            orderHistoryPage.clickFirstOrderViewIcon();

            logger.info(
                    "Clicked View icon on first order."
            );

            // Click Return icon
            OrderInformationPage orderInfoPage =
                    new OrderInformationPage(getDriver());

            orderInfoPage.clickReturnIcon();

            logger.info(
                    "Clicked Return icon."
            );

            // Validate Product Returns page
            ProductReturnsPage productReturnsPage =
                    new ProductReturnsPage(getDriver());

            Assert.assertTrue(
                    productReturnsPage.isProductReturnsPageDisplayed(),
                    "Product Returns page is not displayed."
            );

            logger.info(
                    "Product Returns page displayed successfully."
            );

            Assert.assertEquals(
                    productReturnsPage.getPageTitle(),
                    "Product Returns",
                    "Incorrect Product Returns page title."
            );

            logger.info(
                    "Product Returns page title validated successfully."
            );

            logger.info(
                    "Return functionality validated successfully."
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
    }
}