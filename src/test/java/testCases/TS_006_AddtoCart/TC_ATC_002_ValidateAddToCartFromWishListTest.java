package testCases.TS_006_AddtoCart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.WishListPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_ATC_002_ValidateAddToCartFromWishListTest extends BaseClass {

    @BeforeMethod
    public void loginPrerequisite() {
        performLogin();
    }

    @Test
    public void verifyAddToCartFromWishList() {
        logger.info("***** Starting TC_ATC_002_ValidateAddToCartFromWishListTest *****");

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

            wait.until(ExpectedConditions.urlContains("account"));

            String expectedProduct = "MacBook";
            HomePage home = new HomePage(getDriver());

            home.searchProduct(expectedProduct);
            logger.info("Searched for product: " + expectedProduct);

            home.clickAddToWishList(expectedProduct);
            logger.info("Clicked Add to Wish List for product: " + expectedProduct);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.alert-success")));

            String wishListSuccessMsg = home.getSuccessMessage();
            Assert.assertTrue(wishListSuccessMsg.contains("Success: You have added"),
                    "Success message for adding to Wish List not displayed.");

            home.clickWishListLinkFromSuccessMessage();
            logger.info("Navigated to Wish List page from success message.");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='wishlist-total']")));

            WishListPage wishListPage = new WishListPage(getDriver());

            wishListPage.clickAddToCartIcon(expectedProduct);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.alert-success")));

            wishListPage.clickShoppingCartHeader();

            String pageTitle = new BasePage(getDriver()).getPageTitle();
            Assert.assertTrue(pageTitle.contains("Shopping Cart"),
                    "Shopping Cart page not opened.");
            Assert.assertTrue(getDriver().getPageSource().contains(expectedProduct),
                    "Product " + expectedProduct + " not found in Shopping Cart.");

            logger.info("Product successfully moved from Wish List to Shopping Cart.");

        } catch (Exception e) {
            logger.error("Test case failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }

        logger.info("***** Finished TC_ATC_002_ValidateAddToCartFromWishListTest *****");
    }
}