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

public class TC_TS_009_ValidateTransactionsPageURLTitleHeadingTest extends BaseClass {

    @Test
    public void validateTransactionsPageURLTitleHeading() {
        logger.info("***** Starting TC_TS_009 Validate Page URL, Title, and Heading *****");

        try {
            performLogin();
            logger.info("User logged in successfully.");

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='content']//h2[text()='My Account']")));

            HomePage home = new HomePage(getDriver());
            home.clicktransactionsrightcolumn();
            logger.info("Navigated to Your Transactions page.");

            String currentURL = getDriver().getCurrentUrl();
            Assert.assertTrue(currentURL.contains("transaction"),
                    "Page URL does not contain transactions. Current URL: " + currentURL);
            logger.info("Page URL is valid: " + currentURL);

            String pageTitle = getDriver().getTitle();
            Assert.assertTrue(pageTitle.contains("Your Transactions"),
                    "Page title does not contain Your Transactions. Current Title: " + pageTitle);
            logger.info("Page Title is valid: " + pageTitle);

            TransactionsPage transactionsPage = new TransactionsPage(getDriver());
            String pageHeading = transactionsPage.headingYourTransactions.getText().trim();
            Assert.assertEquals(pageHeading, "Your Transactions",
                    "Page Heading mismatch. Expected: Your Transactions, Found: " + pageHeading);
            logger.info("Page Heading is valid: " + pageHeading);

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_TS_009 Validate Page URL, Title, and Heading *****");
    }
}