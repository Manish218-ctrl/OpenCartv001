package testCases.TS_026_Transaction;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

public class TC_TS_006_ValidateContinueButtonTest extends BaseClass {

    @BeforeMethod
    public void loginBeforeEachTest() {
        performLogin();  // Login using BaseClass method
        logger.info("User logged in before test method.");
    }

    @Test
    public void validateContinueButtonInTransactionsPage() throws InterruptedException {
        logger.info("***** Starting TC_TS_006_ValidateContinueButton *****");

        // Step 1: Navigate to Transactions Page
        HomePage home = new HomePage(driver);
Thread.sleep(20000);
        home.clicktransactionsrightcolumn();
        logger.info("Navigated to 'Your Transactions' page.");

        // Step 2: Validate Transactions Page Heading
        TransactionsPage transactionsPage = new TransactionsPage(driver);
        Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                "Transactions heading not displayed!");
        logger.info("Verified 'Your Transactions' heading is visible.");

        // Step 3: Click on 'Continue' button
        transactionsPage.clickContinueButton();
        logger.info("Clicked on 'Continue' button.");

        // Step 4: Validate redirection → My Account page
        String breadcrumb = transactionsPage.getBreadcrumbText();
        Assert.assertEquals(breadcrumb, "Account",
                "User was not redirected to My Account page after clicking Continue!");
        logger.info("Successfully redirected to 'My Account' page.");

        logger.info("***** Finished TC_TS_006_ValidateContinueButton *****");
    }
}
