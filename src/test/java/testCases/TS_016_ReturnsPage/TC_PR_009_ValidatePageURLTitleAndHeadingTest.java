package testCases.TS_016_ReturnsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PR_009_ValidatePageURLTitleAndHeadingTest extends BaseClass {

    @Test
    public void validatePageURLTitleAndHeadingTest() {
        logger.info("===== TC_PR_009: Validate Page URL, Title, and Heading =====");

        try {
            // Step 1: Login
            logger.info("Step 1: Logging in with user: " + username);
            performLogin();
            logger.info("Login successful.");

            // Step 2: Navigate to My Account -> Order History (or Returns)
            Homepage home = new Homepage(driver);
            logger.info("Step 2: Navigating to My Account dropdown");
            home.clickMyAccount();
            Thread.sleep(2000); // dropdown animation wait
            home.clickMyAccountFromDropdown();
            logger.info("Clicked 'My Account' from dropdown");

            MyAccountPage myAccount = new MyAccountPage(driver);

            // Wait until Order History link is visible & clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement orderHistoryLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Order History"))
            );

            // Scroll and click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderHistoryLink);
            orderHistoryLink.click();
            logger.info("Clicked 'Order History' link");

            // Step 3: Validate URL
            String currentURL = driver.getCurrentUrl();
            String expectedURL = rb.getString("appURL") + "index.php?route=account/order"; // adjust if needed
            logger.info("Validating URL. Actual: " + currentURL + " | Expected: " + expectedURL);
            Assert.assertEquals(currentURL, expectedURL, "URL validation failed");

            // Step 4: Validate Page Title
            String pageTitle = driver.getTitle();
            String expectedTitle = "Order History"; // adjust for Returns page if needed
            logger.info("Validating Page Title. Actual: " + pageTitle + " | Expected: " + expectedTitle);
            Assert.assertEquals(pageTitle, expectedTitle, "Page Title validation failed");

            // Step 5: Validate Page Heading (h1)
            WebElement headingElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/h1"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", headingElement);
            String headingText = headingElement.getText().trim();
            String expectedHeading = "Order History"; // adjust for Returns page if needed
            logger.info("Validating Page Heading. Actual: " + headingText + " | Expected: " + expectedHeading);
            Assert.assertEquals(headingText, expectedHeading, "Page Heading validation failed");

            logger.info("===== TC_PR_009 completed successfully =====");

        } catch (Exception e) {
            logger.error("TC_PR_009 failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test TC_PR_009 failed due to exception: " + e.getMessage());
        }
    }
}
