package testCases.TS_018_OrderHistory;



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
                // Step 1: Perform Login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to My Account -> Order History
                MyAccountPage myAccountPage = new MyAccountPage(driver);
                myAccountPage.clickOrderHistory();
                logger.info("Navigated to 'Order History' page.");

                // Step 3: Create Page Object for Order History Page
                OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);

                // Click on Continue button
                driver.findElement(org.openqa.selenium.By.xpath("//a[text()='Continue']")).click();
                logger.info("Clicked 'Continue' button on Order History page.");

                // Step 4: Validate user is redirected to My Account page
                boolean myAccountExists = myAccountPage.isMyAccountPageExists();
                Assert.assertTrue(myAccountExists, "User is NOT redirected to My Account page after clicking Continue!");

                logger.info("User successfully redirected to My Account page after clicking Continue.");
                logger.info("***** Finished TC_OH_007_ValidateContinueButtonTest *****");

            } catch (Exception e) {
                logger.error("Test Case Failed due to exception: " + e.getMessage());
                Assert.fail("Test Case Failed due to exception: " + e.getMessage());
            }
        }
    }




