package testCases.TS_026_Transaction;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

    public class TC_TS_005_ValidateTransactionsPageWithoutOrdersTest extends BaseClass {

        @Test
        public void verifyTransactionsPageWithoutOrders() {
            logger.info("***** Starting TC_TS_005_TransactionsTest *****");

            try {
                // Step 1: Login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to Transactions
                HomePage home = new HomePage(driver);
                Thread.sleep(2000);
                home.clicktransactionsrightcolumn();
                logger.info("Clicked on Transactions link.");

                // Step 3: Transactions Page
                TransactionsPage transactionsPage = new TransactionsPage(driver);

                // Validate Heading
                Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                        "'Your Transactions' heading is NOT displayed.");
                logger.info(" 'Your Transactions' heading displayed.");

                // Validate Balance
                String balanceValue = transactionsPage.getBalanceText();
                Assert.assertTrue(balanceValue.contains("$0.00"),
                        "Balance text not correct. Found: " + balanceValue);
                logger.info(" Balance text validated: " + balanceValue);

                // Validate Table Headers
                String[] headers = transactionsPage.getTableHeaders();
                Assert.assertEquals(headers[0], "Date Added", "First header mismatch.");
                Assert.assertEquals(headers[1], "Description", "Second header mismatch.");
                Assert.assertEquals(headers[2], "Amount (USD)", "Third header mismatch.");
                logger.info(" Table headers validated successfully.");

                // Validate No Rows
                int rowCount = transactionsPage.getTableRowCount();
                Assert.assertEquals(rowCount, 1, "Transactions table is NOT empty.");
                logger.info(" No rows found in transactions table. As expected.");

            } catch (Exception e) {
                logger.error(" Test Case Failed: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_TS_005_TransactionsTest *****");
        }
    }
