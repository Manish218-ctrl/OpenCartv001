package testCases.TS_026_Transaction;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_TS_002_ValidateNavigationToTransactionsPageFromMyAccountDropMenuTest extends BaseClass {

        @Test(groups = {"Regression", "Sanity"})
        public void verifyNavigateToTransactionsPage() {
            logger.info("***** Starting TC_TS_002 Transactions Test *****");

            try {
                // Step 1: Login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to 'My Account' dropdown
                HomePage homePage = new HomePage(driver);
                homePage.clickMyAccount();
                logger.info("Clicked on 'My Account' dropmenu.");

                // Step 3: Select 'Transactions' option
                homePage.clickTransactions();
                logger.info("Clicked on 'Transactions' option from dropdown.");
                Thread.sleep(2000);

                // Step 4: Validate navigation - check breadcrumb or page title
                String breadcrumb = homePage.getBreadcrumb();
                Assert.assertTrue(
                        breadcrumb.contains("Transactions"),
                        "Breadcrumb does not contain 'Transactions'. Actual: " + breadcrumb
                );

                logger.info("User successfully navigated to 'Your Transactions' page.");
            } catch (Exception e) {
                logger.error("Test Case Failed due to exception: " + e.getMessage());
                Assert.fail("Test Case Failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_TS_002 Transactions Test *****");
        }
    }

