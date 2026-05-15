package testCases.TS_011_HeaderMenuFooterOptions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;
import pageObjects.WishListPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_HMF_004_ValidateAddProductFromWishListToCartTest extends BaseClass {

    private final String PRODUCT_NAME =
            "HP LP3065";

    @Test(groups = {"master", "regression"})
    public void test_addProduct_from_wishlist_to_cart() {

        logger.info(
                "Starting TC_HMF_004_ValidateAddProductFromWishListToCartTest Validate adding a product from Wish List to Shopping Cart."
        );

        try {

            WebDriverWait wait =
                    new WebDriverWait(getDriver(), Duration.ofSeconds(20));

            HomePage hp =
                    new HomePage(getDriver());

            HomePage home =
                    new HomePage(getDriver());

            logger.info(
                    "Performing user login."
            );

            hp.clickMyAccount();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                    )
            );

            hp.clickLogin();

            performLogin();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']//h2[text()='My Account']")
                    )
            );

            logger.info(
                    "Login successful. Navigating to HomePage."
            );

            logger.info(
                    "Searching for product: " + PRODUCT_NAME
            );

            hp.enterSearchText(PRODUCT_NAME);

            hp.clickSearchButton();

            home.clickAddToWishListIconForProduct();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.alert-success")
                    )
            );

            hp.clickFooterLink("Wish List");

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']/h2")
                    )
            );

            WishListPage wlp =
                    new WishListPage(getDriver());

            Assert.assertTrue(
                    wlp.isOnWishListPage(),
                    "Failed to navigate to the Wish List page."
            );

            logger.info(
                    "Successfully navigated to My Wish List page."
            );

            logger.info(
                    "Attempting to move product " +
                            PRODUCT_NAME +
                            " to Shopping Cart."
            );

            wlp.clickaddtocartbtnfromwishlist();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.alert-success")
                    )
            );

            home.clickaddtocart();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.alert-success")
                    )
            );

            wlp.clickShoppingCartHeader();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']/h1")
                    )
            );

            ShoppingCartPage scp =
                    new ShoppingCartPage(getDriver());

            Assert.assertTrue(
                    scp.isProductInCart(PRODUCT_NAME),
                    "Verified ER-2: Product " +
                            PRODUCT_NAME +
                            " is NOT found in the Shopping Cart."
            );

            logger.info(
                    "Verified FR-2: Product " +
                            PRODUCT_NAME +
                            " is successfully added to the Shopping Cart."
            );

            scp.removeProductFromCart(PRODUCT_NAME);

            logger.info(
                    "Clean up: Removed product from cart."
            );

            logger.info(
                    "TC_WL_001 Add Product from Wish List to Cart test Passed."
            );

        } catch (Exception e) {

            logger.error(
                    "TC_WL_001 Add Product from Wish List to Cart test Failed: "
                            + e.getMessage()
            );

            captureScreenshot("TC_WL_001_Failed");

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}