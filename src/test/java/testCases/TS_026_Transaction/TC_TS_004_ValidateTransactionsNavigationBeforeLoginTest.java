package testCases.TS_026_Transaction;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

    public class TC_TS_004_ValidateTransactionsNavigationBeforeLoginTest extends BaseClass {

        @Test(description = "Validate navigating to 'Your Transactions' page via Right Column before login")
        public void verifyTransactionsNavigation() {
            try {
                logger.info("********* Starting TC_TS_004_TransactionsTest *********");

                // Step 1: Load HomePage (already done in BaseClass setup)
                HomePage home = new HomePage(driver);

                // Step 2: Go to My Account -> Register
                home.clickMyAccount();
                home.clickRegister();
                logger.info("Clicked My Account > Register");


                // Step 3: Click Transactions from Right Column
                home.clicktransactionsrightcolumn();
                logger.info("Clicked 'Transactions' from Right Column");

                // Expected Result 1: User should be redirected to Login page
                LoginPage loginPage = new LoginPage(driver);
                String loginTitle = loginPage.getPageTitle();
                Assert.assertTrue(loginTitle.contains("Account Login"),
                        "User is not on Login page! Actual Title: " + loginTitle);
                logger.info("Validated: User redirected to Login page");

                // Step 4: Enter valid credentials and Login
                loginPage.login(username, password);
                logger.info("Entered credentials and logged in");


                // Expected Result 2: User should land on 'Your Transactions' page
                TransactionsPage txPage = new TransactionsPage(driver);

                Assert.assertTrue(txPage.isTransactionsHeadingDisplayed(),
                        "Transactions heading not visible after login!");


                String breadcrumb = txPage.getBreadcrumbTextt();
                Assert.assertEquals(breadcrumb, "Your Transactions",
                        "Breadcrumb mismatch! Expected 'Your Transactions' but found: " + breadcrumb);
                logger.info("Validated: User landed on 'Your Transactions' page successfully");

                logger.info("********* Finished TC_TS_004_TransactionsTest *********");
                Thread.sleep(20000);

            } catch (Exception e) {
                logger.error("Test case failed due to exception: " + e.getMessage());
                Assert.fail("Test case execution failed.");
            }
        }
    }
