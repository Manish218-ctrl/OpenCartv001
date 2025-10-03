package testCases.TS_009_RecurringPayments;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

    public class TC_RCP_005_ValidateRecurringPaymentsPageTest extends BaseClass {

        @Test
        public void validateRecurringPaymentsPageDetails() {
            logger.info("***** Starting TC_RCP_007 - Validate Page URL, Page Title and Page Heading of Recurring Payments Page *****");

            try {
                // Step 1: Login to the application (already handled in BaseClass)
                performLogin();

                // Step 2: Navigate to "My Account" dropdown and click "Recurring Payments"
                Homepage homepage = new Homepage(driver);
                homepage.clickMyAccount();  // Click on 'My Account' link
                homepage.clickMyAccountFromDropdown();  // Click 'My Account' from dropdown menu
                logger.info("Navigated to My Account page from the dropdown.");

                // Click on the "Recurring Payments" link
                homepage.clickRecurringPaymentsLink();
                logger.info("Navigated to the Recurring Payments page.");

                // Step 3: Verify that the page URL is correct
                String currentURL = driver.getCurrentUrl();
                Assert.assertTrue(currentURL.contains("recurring"), "Incorrect URL for Recurring Payments page. Current URL: " + currentURL);
                logger.info("Page URL is correct: " + currentURL);

                // Step 4: Verify the page title
                RecurringPaymentsPage recurringPaymentsPage = new RecurringPaymentsPage(driver);
                String pageTitle = recurringPaymentsPage.getPageTitle();
                Assert.assertEquals(pageTitle, "Recurring Payments", "Page Title is incorrect. Expected: 'Recurring Payments', but found: " + pageTitle);
                logger.info("Page title is correct: " + pageTitle);



            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_RCP_007 - Validate Page URL, Page Title of Recurring Payments Page *****");
        }

    }