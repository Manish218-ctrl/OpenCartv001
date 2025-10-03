package testCases.TS_027_Transaction;




import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_TS_001_ValidateNavigationToYourTransactionsTest extends BaseClass {

        @Test
        public void verifyNavigateToYourTransactions() {
            logger.info("***** Starting TC_TS_001 - Validate Transactions Navigation *****");

            try {
                // Step 1: Perform login
                performLogin();
                logger.info("User logged in successfully");

                // Step 2: Navigate to "My Account"
                Homepage home = new Homepage(driver);
                home.clickMyAccount();

                // Step 3: Click "Order History" or "Transactions" link
                //  Create a method in Homepage.java if not already
                home.clickOrderHistory();  // Adjust if your UI has "Transactions" link differently
                logger.info("Clicked on 'Your Transactions' link");

                // Step 4: Validate navigation
                String actualBreadcrumb = home.getBreadcrumb();  // method already in Homepage.java
                logger.info("Breadcrumb found: " + actualBreadcrumb);

                Assert.assertTrue(actualBreadcrumb.contains("Transaction")
                                || actualBreadcrumb.contains("Order History"),
                        "User is NOT on Transactions page!");

                logger.info("***** Finished TC_TS_001 - Test Passed *****");

            } catch (Exception e) {
                logger.error("Test Failed due to exception: " + e.getMessage());
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
    }

