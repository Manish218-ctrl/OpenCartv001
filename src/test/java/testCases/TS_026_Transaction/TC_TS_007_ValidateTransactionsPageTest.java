package testCases.TS_026_Transaction;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

    public class TC_TS_007_ValidateTransactionsPageTest extends BaseClass {

        @Test
        public void validateTransactionsPage() {
            logger.info("***** Starting TC_TS_007 Validate Transactions Page *****");

            try {
                // Step 1: Perform login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to Transactions page
                HomePage home = new HomePage(driver);
                Thread.sleep(20000);
                home.clicktransactionsrightcolumn();
                logger.info("Navigated to Transactions page.");

                // Step 3: Validate Transactions page
                TransactionsPage transactionsPage = new TransactionsPage(driver);

                // Validate breadcrumb
                String breadcrumb = transactionsPage.getBreadcrumbText();
                Assert.assertEquals(breadcrumb, "Account Your Transactions", "Breadcrumb mismatch!");

                // Validate page heading
                Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                        "Your Transactions heading not displayed.");

                // Validate Balance Text
                String balanceText = transactionsPage.getBalanceText();
                Assert.assertTrue(balanceText.contains("Your current balance"),
                        "Balance text not displayed correctly.");

                // Validate Table Headers
                String[] headers = transactionsPage.getTableHeaders();
                Assert.assertEquals(headers[0], "Date Added", "First column should be Date Added");
                Assert.assertEquals(headers[1], "Description", "Second column should be Description");
                Assert.assertEquals(headers[2], "Amount (USD)", "Third column should be Amount");

                // Validate transaction rows present
                int rowCount = transactionsPage.getTableRowCount();
                Assert.assertTrue(rowCount > 0, "No transactions found in table!");

                logger.info("Transactions page validated successfully with " + rowCount + " rows.");

                // Step 4: Continue button check
                transactionsPage.clickContinueButton();
                logger.info("Clicked Continue button on Transactions page.");

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_TS_007 Validate Transactions Page *****");
        }
    }

