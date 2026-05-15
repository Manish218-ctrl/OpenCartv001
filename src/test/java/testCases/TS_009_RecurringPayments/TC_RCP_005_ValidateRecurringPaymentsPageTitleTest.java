package testCases.TS_009_RecurringPayments;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

    public class TC_RCP_005_ValidateRecurringPaymentsPageTitleTest extends BaseClass {

        @Test
        public void validateRecurringPaymentsPageDetails() {
            logger.info("***** Starting TC_RCP_007 - Validate Page URL, Page Title and Page Heading of Recurring Payments Page *****");

            try {
                //Login to the application (already handled in BaseClass)
                performLogin();

                //Navigate to "My Account" dropdown and click "Recurring Payments"
                HomePage homepage = new HomePage(getDriver());
                homepage.clickMyAccount();
                homepage.clickMyAccountFromDropdown();
                logger.info("Navigated to My Account page from the dropdown.");

                // Click on the "Recurring Payments" link
                homepage.clickRecurringPaymentsLink();
                logger.info("Navigated to the Recurring Payments page.");

                //Verify that the page URL is correct
                String currentURL = getDriver().getCurrentUrl();
                Assert.assertTrue(currentURL.contains("recurring"), "Incorrect URL for Recurring Payments page. Current URL: " + currentURL);
                logger.info("Page URL is correct: " + currentURL);

                //Verify the page title
                RecurringPaymentsPage recurringPaymentsPage = new RecurringPaymentsPage(getDriver());
                String pageTitle = recurringPaymentsPage.getPageTitle();
                Assert.assertEquals(pageTitle, "Recurring Payments", "Page Title is incorrect. Expected: Recurring Payments, but found: " + pageTitle);
                logger.info("Page title is correct: " + pageTitle);



            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_RCP_007 - Validate Page URL, Page Title of Recurring Payments Page *****");
        }

    }