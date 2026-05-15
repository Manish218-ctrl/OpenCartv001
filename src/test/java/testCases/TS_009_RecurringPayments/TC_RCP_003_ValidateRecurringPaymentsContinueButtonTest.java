package testCases.TS_009_RecurringPayments;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

public class TC_RCP_003_ValidateRecurringPaymentsContinueButtonTest extends BaseClass {

    @Test
    public void verifyContinueButtonInRecurringPayments() {

        // Perform login
        performLogin();

        // Verify My Account page
        MyAccountPage myAccount = new MyAccountPage(getDriver());
        Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page not displayed after login");

        // Click on Recurring Payments
        myAccount.clickRecurringPayments();

        //Verify Recurring Payments page is displayed
        RecurringPaymentsPage recurringPage = new RecurringPaymentsPage(getDriver());
        Assert.assertTrue(recurringPage.isRecurringPaymentsPageDisplayed(),
                "Recurring Payments page heading not displayed");

        //Click Continue button
        recurringPage.clickContinueButton();

        //Verify user is redirected to My Account page
        Assert.assertTrue(myAccount.isMyAccountPageExists(),
                "User was not redirected to My Account page after clicking Continue");
    }
}
