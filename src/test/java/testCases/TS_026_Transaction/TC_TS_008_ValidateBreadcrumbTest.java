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

public class TC_TS_008_ValidateBreadcrumbTest extends BaseClass {

    @Test
    public void validateBreadcrumbOnTransactionsPage() {
        logger.info("***** Starting TC_TS_008 Validate Breadcrumb on Your Transactions Page *****");

        try {
            performLogin();
            logger.info("User logged in successfully.");

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='content']//h2[text()='My Account']")));

            HomePage home = new HomePage(getDriver());
            home.clicktransactionsrightcolumn();
            logger.info("Navigated to Your Transactions page.");

            TransactionsPage transactionsPage = new TransactionsPage(getDriver());
            String breadcrumbText = transactionsPage.getBreadcrumbText();
            Assert.assertTrue(breadcrumbText.contains("Your Transactions"),
                    "Breadcrumb text is not matching the expected Your Transactions.");

            logger.info("Breadcrumb validation passed with text: " + breadcrumbText);

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_TS_008 Validate Breadcrumb on Your Transactions Page *****");
    }
}