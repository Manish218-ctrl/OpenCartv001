package testCases.TS_009_RecurringPayments;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

public class TC_RCP_003_ValidateRecurringPaymentsContinueButtonTest extends BaseClass {

    @Test
    public void verifyContinueButtonInRecurringPayments() {

        // Step 1: Perform login
        performLogin();

        // Step 2: Verify My Account page
        MyAccountPage myAccount = new MyAccountPage(driver);
        Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page not displayed after login");

        // Step 3: Click on 'Recurring Payments'
        myAccount.clickRecurringPayments();

        // Step 4: Verify Recurring Payments page is displayed
        RecurringPaymentsPage recurringPage = new RecurringPaymentsPage(driver);
        Assert.assertTrue(recurringPage.isRecurringPaymentsPageDisplayed(),
                "'Recurring Payments' page heading not displayed");

        // Step 5: Click 'Continue' button
        recurringPage.clickContinueButton();

        // Step 6: Verify user is redirected to My Account page
        Assert.assertTrue(myAccount.isMyAccountPageExists(),
                "User was not redirected to My Account page after clicking Continue");
    }
}
