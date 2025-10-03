package testCases.TS_021_ProductDisplayPage;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.List;

    public class TC_PDP_009_ValidateNoReviewsTest extends BaseClass {

        @Test
        public void validateNoReviewsMessage() {
            logger.info("========== STARTING TC_PDP_011: Validate No Reviews Tab ==========");

            try {
                // Step 1: Open Homepage
                Homepage home = new Homepage(driver);

                // Step 2: Enter Product Name with no reviews
                String productWithoutReviews = "iMac";  // From Test Data
                logger.info("Searching for product: " + productWithoutReviews);
                home.enterSearchText(productWithoutReviews);
                home.clickSearchButton();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                // Step 3: Click on the Product link from search results
                WebElement productLink = wait.until(
                        ExpectedConditions.elementToBeClickable(By.linkText(productWithoutReviews))
                );
                productLink.click();
                logger.info("Clicked on product link: " + productWithoutReviews);

                // Step 4: Initialize Product Display Page
                ProductDisplayPage pdp = new ProductDisplayPage(driver);

                // Step 5: Handle potential modal/popups
                List<WebElement> closeButtons = driver.findElements(By.cssSelector(".modal .close, .popup-close"));
                for (WebElement btn : closeButtons) {
                    if (btn.isDisplayed() && btn.isEnabled()) {
                        btn.click();
                        logger.info("Closed modal/popup");
                        Thread.sleep(500); // small pause after closing
                    }
                }

                // Step 5.1 (Optional): Switch to iframe if Reviews tab is inside one
                // driver.switchTo().frame("frameNameOrId");

                // Step 6: Click on Reviews tab
                By reviewsTabSelector = By.cssSelector("a[href='#tab-review']");
                WebElement reviewsTab = wait.until(ExpectedConditions.elementToBeClickable(reviewsTabSelector));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reviewsTab);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reviewsTab);
                logger.info("Clicked on 'Reviews' tab");

                // Step 7: Validate 'No reviews' message
                By noReviewsMessageSelector = By.xpath("//div[@id='tab-review']//p[contains(text(),'There are no reviews for this product.')]");
                WebElement noReviewsMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(noReviewsMessageSelector));

                String actualMessage = noReviewsMessage.getText().trim();
                String expectedMessage = "There are no reviews for this product.";

                logger.info("Expected message: " + expectedMessage);
                logger.info("Actual message: " + actualMessage);

                Assert.assertEquals(actualMessage, expectedMessage, "Validation of 'No reviews' message failed!");
                logger.info("'No reviews' message is displayed correctly");

                logger.info("========== TC_PDP_011 COMPLETED SUCCESSFULLY ==========");

                // Step 5.2 (Optional): Switch back from iframe
                // driver.switchTo().defaultContent();

            } catch (Exception e) {
                logger.error("ERROR occurred in TC_PDP_011: " + e.getMessage(), e);
                Assert.fail("Test case failed due to exception: " + e.getMessage());
            }
        }
    }