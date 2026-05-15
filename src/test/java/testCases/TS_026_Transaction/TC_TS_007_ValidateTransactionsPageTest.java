package testCases.TS_026_Transaction;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_TS_007_ValidateTransactionsPageTest extends BaseClass {

    @Test
    public void validateTransactionsPage() {
        logger.info("***** Starting TC_TS_007 Validate Transactions Page *****");

        try {
            performLogin();
            logger.info("User logged in successfully.");

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']//h2[text()='My Account']")));

            HomePage home = new HomePage(getDriver());
            home.clicktransactionsrightcolumn();
            logger.info("Navigated to Transactions page.");

            TransactionsPage transactionsPage = new TransactionsPage(getDriver());

            String breadcrumb = transactionsPage.getBreadcrumbText();
            Assert.assertEquals(breadcrumb, "Account Your Transactions", "Breadcrumb mismatch!");

            Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                    "Your Transactions heading not displayed.");

            String balanceText = transactionsPage.getBalanceText();
            Assert.assertTrue(balanceText.contains("Your current balance"),
                    "Balance text not displayed correctly.");

            String[] headers = transactionsPage.getTableHeaders();
            Assert.assertEquals(headers[0], "Date Added", "First column should be Date Added");
            Assert.assertEquals(headers[1], "Description", "Second column should be Description");
            Assert.assertEquals(headers[2], "Amount (USD)", "Third column should be Amount");

            int rowCount = transactionsPage.getTableRowCount();
            Assert.assertTrue(rowCount > 0, "No transactions found in table!");
            logger.info("Transactions page validated successfully with " + rowCount + " rows.");

            transactionsPage.clickContinueButton();
            logger.info("Clicked Continue button on Transactions page.");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_TS_007 Validate Transactions Page *****");
    }
}