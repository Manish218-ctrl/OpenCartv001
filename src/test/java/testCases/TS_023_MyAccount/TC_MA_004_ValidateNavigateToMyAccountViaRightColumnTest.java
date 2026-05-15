package testCases.TS_023_MyAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_MA_004_ValidateNavigateToMyAccountViaRightColumnTest extends BaseClass {

    @Test
    public void validateNavigationToMyAccountViaRightColumn() {
        logger.info("=== TC_MA_004 STARTED: Navigate to My Account via Right Column ===");

        try {
            HomePage home = new HomePage(getDriver());

            home.clickMyAccount();

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

            home.clickMyAccountFromDropdown();
            logger.info("Performing login...");

            MyAccountPage myAccountPage = new MyAccountPage(getDriver());
            myAccountPage.clickrightclmnmyaccount();

            performLogin();
            logger.info("Login successful.");

            logger.info("Clicking My Account link from Right Column...");

            String expectedTitle = "My Account";
            String actualTitle = home.getPageTitle();
            logger.info("Validating page title. Expected: " + expectedTitle + ", Actual: " + actualTitle);

            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Navigation to My Account failed! Expected: " + expectedTitle + ", but got: " + actualTitle);

            logger.info("TC_MA_004 PASSED: Navigated to My Account successfully via Right Column.");

        } catch (Exception e) {
            logger.error("TC_MA_004 FAILED: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        } finally {
            logger.info("=== TC_MA_004 COMPLETED ===");
        }
    }
}