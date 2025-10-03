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

public class TC_PDP_010_ValidateAddProductToWishListFromPDPTest extends BaseClass {

    @Test
    public void addProductToWishListFromPDP() {
        logger.info("====== TC_PDP_019: Validate Add to Wish List from PDP ======");

        try {
            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

           // Thread.sleep(20000);
            // Step 2: Search for product
            Homepage homePage = new Homepage(driver);
            homePage.enterSearchText(productName); // productName from config.properties
            homePage.clickSearchButton();
            logger.info("Searched for product: " + productName);

            // Step 3: Click on product from search results
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            By productLocator = By.xpath("//a[text()='" + productName + "']");

            logger.info("Waiting for product link to be visible and clickable: " + productName);
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productLocator));

            try {
                productLink.click();
                logger.info("Clicked on product using standard Selenium click: " + productName);
            } catch (Exception e) {
                logger.warn("Standard click failed, trying JS click for product: " + productName);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);
                logger.info("Clicked on product using JavaScript click: " + productName);
            }

            // Step 4: Initialize PDP and Click 'Add to Wish List'
            ProductDisplayPage pdp = new ProductDisplayPage(driver);
            pdp.clickAddToWishListButton();
            logger.info("Clicked on 'Add to Wish List' button.");

            // Step 5: Validate success message
            String expectedMessage = "Success: You have added " + productName + " to your wish list!";
            String actualMessage = pdp.getSuccessMessage();

            logger.info("Validating success message...");
            logger.debug("Expected: " + expectedMessage);
            logger.debug("Actual: " + actualMessage);

            Assert.assertTrue(actualMessage.contains(expectedMessage),
                    "Expected success message not displayed. Actual: " + actualMessage);
            logger.info("Success message validated: " + actualMessage);

            // Step 6: Click 'wish list' link in success message
            pdp.clickWishListLink();
            logger.info("Navigated to Wish List page via success message link.");

            // Step 7: Validate product is displayed in Wish List
            boolean isProductInWishList = driver.getPageSource().contains(productName);
            Assert.assertTrue(isProductInWishList, productName + " is not present in Wish List page.");
            logger.info(productName + " is displayed in the Wish List page.");

            logger.info("====== TC_PDP_019 Execution Completed Successfully ======");

        } catch (Exception e) {
            logger.error("Test case failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
