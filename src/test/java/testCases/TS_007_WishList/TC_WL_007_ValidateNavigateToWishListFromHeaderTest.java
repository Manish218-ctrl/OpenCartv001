package testCases.TS_007_WishList;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_WL_007_ValidateNavigateToWishListFromHeaderTest extends BaseClass {

        @Test(description = "Validate navigating to My Wish List page using the header option after adding product")
        public void test_NavigateToWishListFromHeader() {
            final String productName = "iMac";
            logger.info("***** Starting TC_WL_007 Navigate to My Wish List from header *****");

            try {
                // 1) Login
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(driver);
                lp.login(rb.getString("email"), rb.getString("password"));
                logger.info("Logged in successfully.");

                // 2) Search for product
                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword(productName);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName),
                        "Expected product not displayed in search results: " + productName);

                // 3) Open PDP
                sp.clickFirstProductName();
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                Assert.assertTrue(pdp.isOnProductDisplayPage(), "Not on Product Display Page as expected");

                // 4) Add product to Wish List
                pdp.clickAddToWishListButton();
                String successMsg = pdp.getSuccessMessage();
                Assert.assertTrue(successMsg.contains("Success"), "Success message not shown after Add to Wish List");
                logger.info("Product added to Wish List. Success Msg: {}", successMsg);

                // 5) Click on "Wish List" header option
                WishListPage wlp = new WishListPage(driver);
                wlp.clickWishListHeader();
                logger.info("Clicked Wish List header option.");

                // 6) Verify My Wish List page is displayed
                String title = wlp.getPageTitle();
                Assert.assertTrue(title.toLowerCase().contains("wish list"),
                        "Not navigated to Wish List page. Actual title: " + title);

                Assert.assertTrue(wlp.isProductInWishList(productName),
                        "Product not found in My Wish List: " + productName);
                logger.info("Validation Passed: '{}' is present in My Wish List", productName);

            } catch (Exception e) {
                logger.error("Test Failed due to exception: {}", e.getMessage());
                Assert.fail("Test failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_WL_007 Navigate to My Wish List from header *****");
        }
    }



