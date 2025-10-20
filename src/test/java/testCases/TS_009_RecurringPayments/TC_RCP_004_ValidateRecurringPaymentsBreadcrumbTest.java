package testCases.TS_009_RecurringPayments;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

    public class TC_RCP_004_ValidateRecurringPaymentsBreadcrumbTest extends BaseClass {

        @Test
        public void validateBreadcrumbOnRecurringPaymentsPage() {
            logger.info("***** Starting TC_RCP_006 - Validate Breadcrumb on Recurring Payments Page *****");

            try {

                // Step 1: Login to the application (already handled in BaseClass)
                performLogin();

                // Step 2: Navigate to the "My Account" dropdown and click "My Account"
                HomePage homepage = new HomePage(driver);
                homepage.clickMyAccount();               // Click on 'My Account' link
                homepage.clickMyAccountFromDropdown();  // Click 'My Account' from dropdown menu
                logger.info("Navigated to My Account page from the dropdown.");
                homepage.clickMyAccount();

                homepage.clickRecurringPaymentsLink();
                logger.info("Navigated to the Recurring Payments page.");
                Thread.sleep(20000);

                // Step 3: Verify that the breadcrumb is displayed
                RecurringPaymentsPage recurringPaymentsPage = new RecurringPaymentsPage(driver);
                Assert.assertTrue(recurringPaymentsPage.isBreadcrumbDisplayed(), "Breadcrumb is not displayed on the Recurring Payments page.");
                logger.info("Breadcrumb is displayed on the Recurring Payments page.");

                // Step 4: Verify the breadcrumb text
                String breadcrumbText = recurringPaymentsPage.getBreadcrumbText();
                Assert.assertTrue(breadcrumbText.contains("Recurring Payments"), "Breadcrumb text is not correct. Expected: 'Recurring Payments'");
                logger.info("Breadcrumb text is correct: " + breadcrumbText);

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_RCP_006 - Validate Breadcrumb on Recurring Payments Page *****");
        }
    }

