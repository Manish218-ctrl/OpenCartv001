package testCases.TS_008_ShoppingCart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_SC_003_ValidateNavigateToCartFromSiteMapTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC_SC_003_ValidateNavigateToCartFromSiteMapTest.class);

    @Test
    public void test_NavigateToCart_From_SiteMap() {
        String productName = "HP LP3065";

        try {
            // --- Step 1: Open application URL ---
            driver.get("https://tutorialsninja.com/demo/");
            log.info("Application opened at URL: https://tutorialsninja.com/demo/");

            // --- Step 2: Login if required ---
            Homepage homePage = new Homepage(driver);
            LoginPage loginPage = new LoginPage(driver);

            homePage.clickMyAccount();
            log.info("Clicked 'My Account' link on homepage.");
            homePage.clickLogin();
            log.info("Clicked 'Login' link.");

            if (loginPage.isLoginPageDisplayed()) {
                loginPage.login("jojol83635@besaies.com", "'nA#$%?w72=!b*7");
                log.info("User logged in successfully.");
            }

            // --- Step 3: Search for product ---
            SearchPage searchPage = new SearchPage(driver);
            searchPage.enterSearchKeyword(productName);
            log.info("Entered product name in search box: {}", productName);
            searchPage.clickSearchButton();
            log.info("Clicked Search button.");

            Assert.assertTrue(searchPage.isProductDisplayed(productName),
                    "Product not displayed in search results: " + productName);
            log.info("Product displayed in search results: {}", productName);

            // --- Step 4: Add product to cart ---
            searchPage.clickAddToCartFromSearchResults(productName);
            log.info("Clicked 'Add to Cart' for product: {}", productName);

            // --- Step 5: Navigate to Site Map ---
            driver.findElement(By.xpath("//footer//a[text()='Site Map']")).click();
            log.info("Clicked 'Site Map' footer link.");

            // --- Step 5: Navigate to Site Map ---
            WebElement siteMapLink = driver.findElement(By.xpath("//footer//a[text()='Site Map']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", siteMapLink);
            siteMapLink.click();




          /*  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Site Map']")));*/

            log.info("Clicked 'Site Map' footer link.");


            // --- Step 6: Click Shopping Cart link ---
            SiteMapPage siteMapPage = new SiteMapPage(driver);
            Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "Not on Site Map page.");
            log.info("Verified Site Map page is displayed.");
            siteMapPage.clickShoppingCartLink();
            log.info("Clicked 'Shopping Cart' link from Site Map.");

            // --- Step 7: Verify Shopping Cart page ---
            ShoppingCartPage cartPage = new ShoppingCartPage(driver);
            Assert.assertTrue(cartPage.isOnShoppingCartPage(), "Not on Shopping Cart page.");
            log.info("Verified Shopping Cart page is displayed.");

            Assert.assertTrue(cartPage.isProductInCart(productName),
                    "Product not found in Shopping Cart: " + productName);
            log.info("Verified product '{}' is present in the Shopping Cart.", productName);

            log.info("Test TC_SC_003 completed successfully.");

        } catch (Exception e) {
            log.error("Test TC_SC_003 failed due to exception: ", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
