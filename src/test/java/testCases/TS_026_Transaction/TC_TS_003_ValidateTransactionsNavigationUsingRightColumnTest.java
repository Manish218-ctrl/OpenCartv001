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

public class TC_TS_003_ValidateTransactionsNavigationUsingRightColumnTest extends BaseClass {

    @Test
    public void verifyTransactionsNavigation() {
        logger.info("***** Starting TC_TS_003 Transactions Test *****");

        try {
            performLogin();
            logger.info("User logged in successfully.");

            HomePage home = new HomePage(getDriver());
            home.clickMyAccount();
            logger.info("Clicked on My Account dropmenu.");

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

            home.clickTransactions();
            logger.info("Clicked on Transactions option from right column.");

            TransactionsPage transactionsPage = new TransactionsPage(getDriver());

            String breadcrumb = transactionsPage.getBreadcrumbText();
            logger.info("Breadcrumb captured: " + breadcrumb);

            Assert.assertTrue(breadcrumb.contains("Transactions"),
                    "Breadcrumb does not show Transactions.");

            Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                    "Your Transactions heading not displayed.");

            logger.info("User successfully navigated to Your Transactions page.");

        } catch (Exception e) {
            logger.error("Test Failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception.");
        }

        logger.info("***** Finished TC_TS_003 Transactions Test *****");
    }
}