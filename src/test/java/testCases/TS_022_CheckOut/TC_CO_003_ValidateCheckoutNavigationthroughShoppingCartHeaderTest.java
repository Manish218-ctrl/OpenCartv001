package testCases.TS_022_CheckOut;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_CO_003_ValidateCheckoutNavigationthroughShoppingCartHeaderTest extends BaseClass {

    @Test
    public void validateCheckoutNavigation() {
        logger.info("***** Starting TC_CO_003 - Checkout Test *****");

        try {
            HomePage home = new HomePage(getDriver());

            home.enterSearchText(productName);
            home.clickSearchButton();
            logger.info("Searched for product: " + productName);

            home.addProductToCart(productName);

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.alert-success")));

            home.clickaddtocarthpbtn();
            logger.info("Added product to cart: " + productName);

            home.clickViewCartOption();

            home.clickcheckoutfromcart();

            String actualBreadcrumb = home.getBreadcrumb();
            logger.info("Breadcrumb after Checkout navigation: " + actualBreadcrumb);

            Assert.assertTrue(
                    actualBreadcrumb.contains("Checkout"),
                    "User is not on the Checkout page. Actual breadcrumb: " + actualBreadcrumb
            );

            logger.info("User successfully navigated to Checkout page.");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_CO_003 - Checkout Test *****");
    }
}