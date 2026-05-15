package testCases.TS_007_WishList;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_WL_009_ValidateNavigateToWishListFromFooterTest extends BaseClass {

        @Test(description = "Validate navigating to My Wish List page from Footer options")
        public void test_NavigateToWishList_From_Footer() {
            logger.info("***** Starting TC_WL_010: Navigate to My Wish List from Footer *****");

            try {
                // 1) Login
                HomePage hp = new HomePage(getDriver());
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(getDriver());
                String email = p.getProperty("email");
                String password = p.getProperty("password");
                lp.login(email, password);
                logger.info("Logged in successfully.");

                // 2) Click "Wish List" in Footer
                hp.clickFooterWishList();
                logger.info("Clicked Footer -> Wish List");

                // 3) Validate: User should land on "My Wish List" page
                WishListPage wl = new WishListPage(getDriver());
                boolean onWishList = wl.isOnWishListPage();

                if (!onWishList) {
                    // fallback check on URL/title
                    String title = getDriver().getTitle();
                    boolean titleOk = title != null && title.toLowerCase().contains("wish list");
                    boolean urlOk = getDriver().getCurrentUrl().toLowerCase().contains("wishlist");
                    onWishList = titleOk || urlOk;
                }

                Assert.assertTrue(onWishList, "User was not navigated to My Wish List page.");
                logger.info("Validation Passed: Navigated to My Wish List page");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Exception occurred: " + e.getMessage());
            }

            logger.info("***** Finished TC_WL_010: Navigate to My Wish List from Footer *****");
        }
    }


