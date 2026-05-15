package testCases.TS_026_Transaction;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_TS_006_ValidateContinueButtonTest extends BaseClass {

    @BeforeMethod
    public void loginBeforeEachTest() {
        performLogin();
        logger.info("User logged in before test method.");
    }

    @Test
    public void validateContinueButtonInTransactionsPage() {
        logger.info("***** Starting TC_TS_006_ValidateContinueButton *****");

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']//h2[text()='My Account']")));

        HomePage home = new HomePage(getDriver());
        home.clicktransactionsrightcolumn();
        logger.info("Navigated to Your Transactions page.");

        TransactionsPage transactionsPage = new TransactionsPage(getDriver());
        Assert.assertTrue(transactionsPage.isTransactionsHeadingDisplayed(),
                "Transactions heading not displayed!");
        logger.info("Verified Your Transactions heading is visible.");

        transactionsPage.clickContinueButton();
        logger.info("Clicked on Continue button.");

        String breadcrumb = transactionsPage.getBreadcrumbText();
        Assert.assertEquals(breadcrumb, "Account",
                "User was not redirected to My Account page after clicking Continue!");
        logger.info("Successfully redirected to My Account page.");

        logger.info("***** Finished TC_TS_006_ValidateContinueButton *****");
    }
}