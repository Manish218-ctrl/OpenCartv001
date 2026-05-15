package testCases.TS_022_CheckOut;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_CO_004_ValidateCheckoutFromCartBlockTest extends BaseClass {

    @Test(groups = {"Regression", "Checkout"})
    public void verifyCheckoutFromCartBlock() {
        logger.info("========== Starting TC_CO_004_VerifyCheckoutFromCartBlock ==========");

        try {
            HomePage home = new HomePage(getDriver());

            logger.info("Searching product: " + productName);
            home.enterSearchText(productName);
            logger.info("Entered product name in search box: " + productName);

            home.clickSearchButton();
            logger.info("Clicked on Search button.");

            home.addProductToCart(productName);

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.alert-success")));

            home.clickaddtocarthpbtn();
            logger.info("Successfully added product to cart: " + productName);

            logger.info("Expanding cart dropdown block.");
            home.clickCartBlock();
            logger.info("Cart dropdown expanded successfully.");

            logger.info("Clicking Checkout option inside Cart block.");
            home.clickCheckout();
            logger.info("Clicked Checkout option, waiting for Checkout page to load...");

            String breadcrumb = home.getBreadcrumb();
            logger.info("Captured breadcrumb text: " + breadcrumb);

            Assert.assertTrue(
                    breadcrumb.contains("Checkout"),
                    "User is NOT navigated to Checkout page! Expected breadcrumb to contain Shopping Cart."
            );

            logger.info("Assertion passed: User successfully navigated to Checkout page.");
            logger.info("========== Finished TC_CO_004_VerifyCheckoutFromCartBlock SUCCESSFULLY ==========");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test Case Failed due to Exception: " + e.getMessage());
        }
    }
}