package testCases.TS_008_ShoppingCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ShoppingCartPage;
import pageObjects.SiteMapPage;
import testBase.BaseClass;

public class TC_SC_003_ValidateNavigateToCartFromSiteMapTest extends BaseClass {

    @Test
    public void test_NavigateToCart_From_SiteMap() {

        String productName = "HP LP3065";

        try {

            getDriver().get(appURL);

            logger.info(
                    "Application opened at URL: " + appURL
            );

            HomePage homePage =
                    new HomePage(getDriver());

            LoginPage loginPage =
                    new LoginPage(getDriver());

            homePage.clickMyAccount();

            logger.info(
                    "Clicked My Account link on homepage."
            );

            homePage.clickLogin();

            logger.info(
                    "Clicked Login link."
            );

            if (loginPage.isLoginPageDisplayed()) {

                loginPage.login(
                        username,
                        password
                );

                logger.info(
                        "User logged in successfully."
                );
            }

            HomePage home =
                    new HomePage(getDriver());

            home.enterSearchText(productName);

            home.clickSearchButton();

            home.addProductToCart(productName);

            home.clickaddtocart();

            home.clickshoppingcartbtnmsg();

            logger.info(
                    "Clicked Add to Cart for product: "
                            + productName
            );

            home.clickSiteMapFooterLink();

            logger.info(
                    "Clicked Site Map footer link."
            );

            SiteMapPage siteMapPage =
                    new SiteMapPage(getDriver());

            Assert.assertTrue(
                    siteMapPage.isOnSiteMapPage(),
                    "Not on Site Map page."
            );

            logger.info(
                    "Verified Site Map page is displayed."
            );

            siteMapPage.clickShoppingCartLink();

            logger.info(
                    "Clicked Shopping Cart link from Site Map."
            );

            ShoppingCartPage cartPage =
                    new ShoppingCartPage(getDriver());

            Assert.assertTrue(
                    cartPage.isOnShoppingCartPage(),
                    "Shopping Cart page is not displayed."
            );

            logger.info(
                    "Test TC_SC_003 completed successfully."
            );

        } catch (Exception e) {

            logger.error(
                    "Test TC_SC_003 failed due to exception: ",
                    e
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}