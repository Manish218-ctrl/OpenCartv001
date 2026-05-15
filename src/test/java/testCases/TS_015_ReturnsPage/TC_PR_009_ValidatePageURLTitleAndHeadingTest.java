package testCases.TS_015_ReturnsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PR_009_ValidatePageURLTitleAndHeadingTest extends BaseClass {

    @Test
    public void validatePageURLTitleAndHeadingTest() {
        logger.info("===== TC_PR_009: Validate Page URL, Title, and Heading =====");

        try {
            logger.info("Logging in with user: " + username);
            performLogin();
            logger.info("Login successful.");

            HomePage home = new HomePage(getDriver());
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

            logger.info("Navigating to My Account dropdown");
            home.clickMyAccount();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

            home.clickMyAccountFromDropdown();
            logger.info("Clicked My Account from dropdown");

            MyAccountPage myAccount = new MyAccountPage(getDriver());

            WebElement orderHistoryLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Order History")));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", orderHistoryLink);
            orderHistoryLink.click();
            logger.info("Clicked Order History link");

            String currentURL = getDriver().getCurrentUrl();
            String expectedURL = p.getProperty("appURL") + "index.php?route=account/order";
            logger.info("Validating URL. Actual: " + currentURL + " | Expected: " + expectedURL);
            Assert.assertEquals(currentURL, expectedURL, "URL validation failed");

            String pageTitle = getDriver().getTitle();
            String expectedTitle = "Order History";
            logger.info("Validating Page Title. Actual: " + pageTitle + " | Expected: " + expectedTitle);
            Assert.assertEquals(pageTitle, expectedTitle, "Page Title validation failed");

            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
            String headingText = orderHistoryPage.getPageHeading();
            String expectedHeading = "Order History";
            logger.info("Validating Page Heading. Actual: " + headingText + " | Expected: " + expectedHeading);
            Assert.assertEquals(headingText, expectedHeading, "Page Heading validation failed");

            logger.info("===== TC_PR_009 completed successfully =====");

        } catch (Exception e) {
            logger.error("TC_PR_009 failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test TC_PR_009 failed due to exception: " + e.getMessage());
        }
    }
}