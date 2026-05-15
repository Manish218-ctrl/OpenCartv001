package testCases.TS_026_Transaction;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

public class TC_TS_004_ValidateTransactionsNavigationBeforeLoginTest extends BaseClass {

    @Test(description = "Validate navigating to Your Transactions page via Right Column before login")
    public void verifyTransactionsNavigation() {
        try {
            logger.info("********* Starting TC_TS_004_TransactionsTest *********");

            HomePage home = new HomePage(getDriver());

            home.clickMyAccount();
            home.clickRegister();
            logger.info("Clicked My Account > Register");

            home.clicktransactionsrightcolumn();
            logger.info("Clicked Transactions from Right Column");

            LoginPage loginPage = new LoginPage(getDriver());
            String loginTitle = loginPage.getPageTitle();
            Assert.assertTrue(loginTitle.contains("Account Login"),
                    "User is not on Login page! Actual Title: " + loginTitle);
            logger.info("Validated: User redirected to Login page");

            loginPage.login(username, password);
            logger.info("Entered credentials and logged in");

            TransactionsPage txPage = new TransactionsPage(getDriver());

            Assert.assertTrue(txPage.isTransactionsHeadingDisplayed(),
                    "Transactions heading not visible after login!");

            String breadcrumb = txPage.getBreadcrumbTextt();
            Assert.assertEquals(breadcrumb, "Your Transactions",
                    "Breadcrumb mismatch! Expected Your Transactions but found: " + breadcrumb);
            logger.info("Validated: User landed on Your Transactions page successfully");

            logger.info("********* Finished TC_TS_004_TransactionsTest *********");

        } catch (Exception e) {
            logger.error("Test case failed due to exception: " + e.getMessage());
            Assert.fail("Test case execution failed.");
        }
    }
}