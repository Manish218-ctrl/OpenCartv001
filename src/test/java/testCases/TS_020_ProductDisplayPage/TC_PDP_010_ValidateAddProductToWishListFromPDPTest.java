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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_PDP_010_ValidateAddProductToWishListFromPDPTest extends BaseClass {

    @Test
    public void addProductToWishListFromPDP() {
        logger.info("====== TC_PDP_019: Validate Add to Wish List from PDP ======");

        try {
            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Search for product
            HomePage homePage = new HomePage(driver);
            homePage.enterSearchText(productName); // productName from config.properties
            homePage.clickSearchButton();
            logger.info("Searched for product: " + productName);



            // Step 3: Click on product from search results (Simplified call to the POM)
            ProductDisplayPage pdp = new ProductDisplayPage(driver); // Re-use the pdp variable
            pdp.clickProductFromSearchResults(productName);
            logger.info("Clicked on product link: " + productName);

            // Step 4: Initialize PDP and Click 'Add to Wish List'
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
