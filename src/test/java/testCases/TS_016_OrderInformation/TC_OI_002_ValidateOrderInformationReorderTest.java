package testCases.TS_016_OrderInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_OI_002_ValidateOrderInformationReorderTest extends BaseClass {

    @Test
    public void validateReorderFunctionalityTest() {

        // Open application and login
        performLogin();

        // Navigate to Order History page
        HomePage homepage =
                new HomePage(getDriver());

        homepage.clickMyAccount();

        homepage.clickOrderHistory();

        // Click View on first order
        OrderHistoryPage orderHistoryPage =
                new OrderHistoryPage(getDriver());

        orderHistoryPage.clickFirstOrderViewIcon();

        // Order Information Page
        OrderInformationPage orderInfoPage =
                new OrderInformationPage(getDriver());

        // Click Reorder icon
        orderInfoPage.clickReorderIcon();

        // Validate success message
        Assert.assertTrue(
                orderInfoPage.isReorderSuccessMessageDisplayed(),
                "Success message is not displayed after clicking Reorder"
        );

        Assert.assertTrue(
                orderInfoPage.getReorderSuccessMessage()
                        .contains(
                                "Success: You have added HP LP3065 to your shopping cart!"
                        ),
                "Incorrect success message: "
                        + orderInfoPage.getReorderSuccessMessage()
        );

        // Click Shopping Cart link
        orderInfoPage.clickShoppingCartLinkFromSuccessMessage();

        // Verify product in cart
        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(getDriver());

        Assert.assertTrue(
                shoppingCartPage.isProductDisplayedInCart(
                        "HP LP3065"
                ),
                "Product is not displayed in shopping cart."
        );

        logger.info(
                "Reorder functionality validated successfully."
        );
    }
}