package testCases.TS_009_RecurringPayments;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

    public class TC_RCP_002_ValidateRecurringPaymentsNavigationUsingRightColumnTest extends BaseClass {

        @Test
        public void verifyRecurringPaymentsNavigation() {

            //Perform login using BaseClass method
            performLogin();

            //Navigate to My Account page (optional validation)
            MyAccountPage myAccount = new MyAccountPage(getDriver());
            Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page not displayed after login");

            //Click on Recurring Payments from Right Column
            myAccount.clickRecurringPayments();

            //Verify Recurring Payments page is displayed
            RecurringPaymentsPage recurringPage = new RecurringPaymentsPage(getDriver());
            Assert.assertTrue(recurringPage.isRecurringPaymentsPageDisplayed(), "Recurring Payments page heading not displayed");

            Assert.assertTrue(recurringPage.isRecurringPaymentsTableDisplayed(), "Recurring Payments table not displayed");
        }
    }



