package testCases.TS_026_Transaction;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

    public class TC_TS_008_ValidateBreadcrumbTest extends BaseClass {

        @Test
        public void validateBreadcrumbOnTransactionsPage() {
            logger.info("***** Starting TC_TS_008 Validate Breadcrumb on 'Your Transactions' Page *****");

            try {
                // Step 1: Login to the application
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to 'Transactions' page
                HomePage home = new HomePage(driver);
                Thread.sleep(20000);
                home.clicktransactionsrightcolumn();
                logger.info("Navigated to 'Your Transactions' page.");

                // Step 3: Validate the breadcrumb
                TransactionsPage transactionsPage = new TransactionsPage(driver);
                String breadcrumbText = transactionsPage.getBreadcrumbText();
                Assert.assertTrue(breadcrumbText.contains("Your Transactions"),
                        "Breadcrumb text is not matching the expected 'Your Transactions'.");

                logger.info("Breadcrumb validation passed with text: " + breadcrumbText);

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_TS_008 Validate Breadcrumb on 'Your Transactions' Page *****");
        }
    }
