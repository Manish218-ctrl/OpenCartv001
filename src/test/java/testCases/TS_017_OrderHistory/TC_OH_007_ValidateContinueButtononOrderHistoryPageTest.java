package testCases.TS_017_OrderHistory;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

public class TC_OH_007_ValidateContinueButtononOrderHistoryPageTest extends BaseClass {

    @Test(groups = {"Regression", "OrderHistory"})
    public void validateContinueButtonFromOrderHistory() {

        logger.info("***** Starting TC_OH_007_ValidateContinueButtononOrderHistoryPageTest *****");

        try {

            // Perform Login
            performLogin();

            logger.info("User logged in successfully.");

            // Navigate to Order History
            MyAccountPage myAccountPage = new MyAccountPage(getDriver());

            myAccountPage.clickOrderHistory();

            logger.info("Navigated to Order History page.");

            // Initialize OrderHistoryPage
            OrderHistoryPage orderHistoryPage =
                    new OrderHistoryPage(getDriver());

            // Click Continue button
            orderHistoryPage.clickContinueButton();

            logger.info("Clicked Continue button on Order History page.");

            // Validate navigation back to My Account page
            Assert.assertTrue(
                    myAccountPage.isMyAccountPageExists(),
                    "User is NOT redirected to My Account page after clicking Continue!"
            );

            logger.info("User successfully redirected to My Account page after clicking Continue.");

            logger.info("***** Finished TC_OH_007_ValidateContinueButtonTest *****");

        } catch (Exception e) {

            logger.error(
                    "Test Case Failed due to exception: {}",
                    e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test Case Failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}