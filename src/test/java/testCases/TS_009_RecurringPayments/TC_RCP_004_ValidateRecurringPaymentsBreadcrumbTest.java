package testCases.TS_009_RecurringPayments;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RecurringPaymentsPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_RCP_004_ValidateRecurringPaymentsBreadcrumbTest extends BaseClass {

    @Test
    public void validateBreadcrumbOnRecurringPaymentsPage() {
        logger.info("***** Starting TC_RCP_006 - Validate Breadcrumb on Recurring Payments Page *****");

        try {
            performLogin();

            HomePage homepage = new HomePage(getDriver());
            homepage.clickMyAccount();
            homepage.clickMyAccountFromDropdown();
            logger.info("Navigated to My Account page from the dropdown.");
            homepage.clickMyAccount();

            homepage.clickRecurringPaymentsLink();
            logger.info("Navigated to the Recurring Payments page.");

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[text()='Recurring Payments']")));

            RecurringPaymentsPage recurringPaymentsPage = new RecurringPaymentsPage(getDriver());
            Assert.assertTrue(recurringPaymentsPage.isBreadcrumbDisplayed(),
                    "Breadcrumb is not displayed on the Recurring Payments page.");
            logger.info("Breadcrumb is displayed on the Recurring Payments page.");

            String breadcrumbText = recurringPaymentsPage.getBreadcrumbText();
            Assert.assertTrue(breadcrumbText.contains("Recurring Payments"),
                    "Breadcrumb text is not correct. Expected: Recurring Payments");
            logger.info("Breadcrumb text is correct: " + breadcrumbText);

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_RCP_006 - Validate Breadcrumb on Recurring Payments Page *****");
    }
}