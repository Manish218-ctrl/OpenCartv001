package testCases.TS_026_Transaction;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

    public class TC_TS_009_ValidateTransactionsPageURLTitleHeadingTest extends BaseClass {

        @Test
        public void validateTransactionsPageURLTitleHeading() {
            logger.info("***** Starting TC_TS_009 Validate Page URL, Title, and Heading *****");

            try {
                // Step 1: Login to the application
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to 'Your Transactions' page
                HomePage home = new HomePage(driver);
                Thread.sleep(20000);
                home.clicktransactionsrightcolumn();
                logger.info("Navigated to 'Your Transactions' page.");

                // Step 3: Validate the Page URL
                String currentURL = driver.getCurrentUrl();
                Assert.assertTrue(currentURL.contains("transaction"), "Page URL does not contain 'transactions'. Current URL: " + currentURL);
                logger.info("Page URL is valid: " + currentURL);

                // Step 4: Validate the Page Title
                String pageTitle = driver.getTitle();
                Assert.assertTrue(pageTitle.contains("Your Transactions"), "Page title does not contain 'Your Transactions'. Current Title: " + pageTitle);
                logger.info("Page Title is valid: " + pageTitle);

                // Step 5: Validate the Page Heading
                TransactionsPage transactionsPage = new TransactionsPage(driver);
                String pageHeading = transactionsPage.headingYourTransactions.getText().trim();
                Assert.assertEquals(pageHeading,  "Your Transactions", "Page Heading mismatch. Expected: 'Your Transactions', Found: " + pageHeading);
                logger.info("Page Heading is valid: " + pageHeading);

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_TS_009 Validate Page URL, Title, and Heading *****");
        }
    }
