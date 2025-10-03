package testCases.TS_024_MyAccount;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

    public class TC_MA_004_ValidateNavigateToMyAccountViaRightColumnTest extends BaseClass {

        @Test
        public void validateNavigationToMyAccountViaRightColumn() {
            logger.info("=== TC_MA_004 STARTED: Navigate to 'My Account' via Right Column ===");

            try {
                // Step 1: Login to the application
                logger.info("Step 1: Performing login...");
                performLogin();
                logger.info("Login successful.");

                // Step 2: Navigate to Order History page using Right Column
                Homepage home = new Homepage(driver);
                logger.info("Step 2: Clicking on 'Order History' link from Right Column...");
                home.clickOrderHistory(); // Using Homepage method that navigates to Order History page

                // Initialize OrderHistoryPage
                OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);

                // Validate that Order History page is displayed
                String orderHistoryTitle = orderHistoryPage.getTitle();
                logger.info("Order History page title: " + orderHistoryTitle);
                Assert.assertTrue(orderHistoryTitle.contains("Order History"),
                        "Failed to navigate to Order History page!");

                // Step 3: Click on 'My Account' link from Right Column
                logger.info("Step 3: Clicking 'My Account' link from Right Column...");
                home.clickMyAccountFromDropdown(); // Clicks 'My Account' from right column dropdown

                // Step 4: Validate navigation to 'My Account' page
                String expectedTitle = "My Account";
                String actualTitle = home.getPageTitle();
                logger.info("Validating page title. Expected: '" + expectedTitle + "', Actual: '" + actualTitle + "'");

                Assert.assertTrue(actualTitle.contains(expectedTitle),
                        "Navigation to 'My Account' failed! Expected: " + expectedTitle + ", but got: " + actualTitle);

                logger.info("TC_MA_004 PASSED: Navigated to 'My Account' successfully via Right Column.");

            } catch (Exception e) {
                logger.error("TC_MA_004 FAILED: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            } finally {
                logger.info("=== TC_MA_004 COMPLETED ===");
            }
        }
    }

