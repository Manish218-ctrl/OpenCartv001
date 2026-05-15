package testCases.TS_026_Transaction;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_TS_002_ValidateNavigationToTransactionsPageFromMyAccountDropMenuTest extends BaseClass {

    @Test(groups = {"Regression", "Sanity"})
    public void verifyNavigateToTransactionsPage() {

        logger.info(
                "***** Starting TC_TS_002 Transactions Test *****"
        );

        try {

            WebDriverWait wait =
                    new WebDriverWait(getDriver(), Duration.ofSeconds(20));

            performLogin();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']//h2[text()='My Account']")
                    )
            );

            logger.info(
                    "User logged in successfully."
            );

            HomePage homePage =
                    new HomePage(getDriver());

            homePage.clickMyAccount();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                    )
            );

            logger.info(
                    "Clicked on My Account dropmenu."
            );

            homePage.clickTransactions();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']/h1")
                    )
            );

            logger.info(
                    "Clicked on Transactions option from dropdown."
            );

            String breadcrumb =
                    homePage.getBreadcrumb();

            Assert.assertTrue(
                    breadcrumb.contains("Transactions"),
                    "Breadcrumb does not contain Transactions. Actual: "
                            + breadcrumb
            );

            logger.info(
                    "User successfully navigated to Your Transactions page."
            );

        } catch (Exception e) {

            logger.error(
                    "Test Case Failed due to exception: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test Case Failed due to exception: "
                            + e.getMessage()
            );
        }

        logger.info(
                "***** Finished TC_TS_002 Transactions Test *****"
        );
    }
}