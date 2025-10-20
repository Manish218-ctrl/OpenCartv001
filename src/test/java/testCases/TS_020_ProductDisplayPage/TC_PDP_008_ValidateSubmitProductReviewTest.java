package testCases.TS_020_ProductDisplayPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.List;

public class TC_PDP_008_ValidateSubmitProductReviewTest extends BaseClass {

    @Test
    public void submitProductReview() {
        logger.info("========== STARTING TC_PDP_009: Submit Product Review Test ==========");

        try {
            // Step 1: Navigate to HomePage
            logger.info("Navigating to HomePage URL: " + appURL);
            HomePage home = new HomePage(driver);

            // Step 2: Search for the Product
            logger.info("Entering product name in search box: " + productName);
            home.enterSearchText(productName);
            logger.info("Clicking on Search button");
            home.clickSearchButton();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Step 3: Click on Product from search results
            logger.info("Waiting for product link to be clickable: " + productName);
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName)));
            productLink.click();
            logger.info("Clicked on product link: " + productName);

            // Step 4: Initialize Product Display Page
            ProductDisplayPage pdp = new ProductDisplayPage(driver);

            // Step 5: Handle potential popups or overlays
            List<WebElement> closeButtons = driver.findElements(By.cssSelector(".modal .close, .popup-close"));
            if (!closeButtons.isEmpty()) {
                closeButtons.get(0).click();
                logger.info("Closed interfering modal/popup");
            }

            // Step 6: Click on 'Reviews' tab (Changed to XPath)
            logger.info("Clicking on 'Reviews' tab");
            WebElement reviewsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@href='#tab-review']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reviewsTab);
            wait.until(ExpectedConditions.elementToBeClickable(reviewsTab));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reviewsTab);
            logger.info("'Reviews' tab clicked successfully");

            // Step 7: Enter Name, Review, Rating
            String reviewName = "TestUser_" + randomString();
            String reviewText = "This is a test review for automation " + randomString();

            logger.info("Entering Name: " + reviewName);
            driver.findElement(By.id("input-name")).sendKeys(reviewName);

            logger.info("Entering Review Text: " + reviewText);
            driver.findElement(By.id("input-review")).sendKeys(reviewText);

            logger.info("Selecting 5-star rating");
            WebElement ratingRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='5']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ratingRadio);

            // Step 8: Click 'Continue' button
            logger.info("Clicking 'Continue' button to submit review");
            WebElement continueBtn = driver.findElement(By.id("button-review"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);

            // Step 9: Validate success message
            logger.info("Waiting for success message...");
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));
            String actualMessage = successMsg.getText().trim();
            String expectedMessage = "Thank you for your review. It has been submitted to the webmaster for approval.";

            logger.info("Validating success message");
            logger.debug("Expected Message: " + expectedMessage);
            logger.debug("Actual Message: " + actualMessage);
            Assert.assertTrue(actualMessage.contains(expectedMessage), "Review submission message validation failed!");

            logger.info("Review submission success message validated successfully");
            logger.info("TC_PDP_009 COMPLETED SUCCESSFULLY");

        } catch (Exception e) {
            logger.error("ERROR occurred while submitting review: " + e.getMessage(), e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}
