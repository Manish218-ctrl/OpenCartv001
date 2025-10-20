package testCases.TS_026_Transaction;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

    public class TC_TS_003_ValidateTransactionsNavigationUsingRightColumnTest extends BaseClass {

        @Test
        public void verifyTransactionsNavigation() {
            logger.info("***** Starting TC_TS_003 Transactions Test *****");

            try {
                // Step 1: Perform login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Click on 'Transactions' from right column
                HomePage home = new HomePage(driver);
                home.clickMyAccount();
                logger.info("Clicked on 'My Account' dropmenu.");

                Thread.sleep(2000);
                home.clickTransactions();
                logger.info("Clicked on 'Transactions' option from right column.");

                // Step 3: Switch to Transactions Page
                TransactionsPage transactionsPage = new TransactionsPage(driver);

                // Step 4: Verify breadcrumb & heading
                String breadcrumb = transactionsPage.getBreadcrumbText();
                logger.info("Breadcrumb captured: " + breadcrumb);

                Assert.assertTrue(breadcrumb.contains("Transactions"),
                        " Breadcrumb does not show 'Transactions'.");

                Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                        " 'Your Transactions' heading not displayed.");

                logger.info(" User successfully navigated to 'Your Transactions' page.");

            } catch (Exception e) {
                logger.error(" Test Failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception.");
            }

            logger.info("***** Finished TC_TS_003 Transactions Test *****");
        }
    }
