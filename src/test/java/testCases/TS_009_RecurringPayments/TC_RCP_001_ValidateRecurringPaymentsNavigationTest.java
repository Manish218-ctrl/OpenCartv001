package testCases.TS_009_RecurringPayments;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

    public class TC_RCP_001_ValidateRecurringPaymentsNavigationTest extends BaseClass {

        @Test
        public void verifyRecurringPaymentsNavigation() {
            try {
                logger.info("***** Starting TC_RCP_001: Recurring Payments Navigation *****");

                // Step 1: Login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to 'My Account' page
                MyAccountPage myAccountPage = new MyAccountPage(driver);
                Assert.assertTrue(myAccountPage.isMyAccountPageExists(), "My Account page is displayed.");

                // Step 3: Click on 'Recurring Payments'
                myAccountPage.clickRecurringPayments();

                // Step 4: Verify 'Recurring Payments' page
                RecurringPaymentsPage recurringPage = new RecurringPaymentsPage(driver);
                Assert.assertTrue(recurringPage.isRecurringPaymentsPageDisplayed(),
                        "'Recurring Payments' page is displayed successfully.");

                // Optional: Verify recurring payments table exists
                Assert.assertTrue(recurringPage.isRecurringPaymentsTableDisplayed(),
                        "Recurring Payments table is displayed.");

                logger.info("Recurring Payments page verified successfully.");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Test Failed: " + e.getMessage());
            }
        }
    }



