package testCases.TS_009_RecurringPayments;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

    public class TC_RCP_002_ValidateRecurringPaymentsNavigationUsingRightColumnTest extends BaseClass {

        @Test
        public void verifyRecurringPaymentsNavigation() {

            // Step 1: Perform login using BaseClass method
            performLogin();

            // Step 2: Navigate to My Account page (optional validation)
            MyAccountPage myAccount = new MyAccountPage(driver);
            Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page not displayed after login");

            // Step 3: Click on 'Recurring Payments' from Right Column
            myAccount.clickRecurringPayments();

            // Step 4: Verify 'Recurring Payments' page is displayed
            RecurringPaymentsPage recurringPage = new RecurringPaymentsPage(driver);
            Assert.assertTrue(recurringPage.isRecurringPaymentsPageDisplayed(), "'Recurring Payments' page heading not displayed");

            Assert.assertTrue(recurringPage.isRecurringPaymentsTableDisplayed(), "'Recurring Payments' table not displayed");
        }
    }



