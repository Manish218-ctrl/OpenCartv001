package testCases.TS_007_WishList;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.WishListPage;
import testBase.BaseClass;

    public class TC_WL_008_ValidateNavigateToWishListFromRightColumnTest extends BaseClass {

        @Test(description = "Validate navigating to 'My Wish List' from the Right Column options")
        public void test_NavigateToWishList_From_RightColumn() {
            logger.info("***** Starting TC_WL_008: Navigate to My Wish List from Right Column *****");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            String email = rb.getString("email");      // ensure present in config.properties
            String password = rb.getString("password");// ensure present in config.properties
            lp.login(email, password);
            logger.info("Logged in successfully.");

            MyAccountPage my = new MyAccountPage(driver);
            Assert.assertTrue(my.isMyAccountPageExists(), "My Account page was not displayed after login.");

            //Click "Wish List" from the Right Column
            my.clickWishListFromMyAccount();
            logger.info("Clicked Right Column -> Wish List");

            //ER: User should be taken to My Wish List page
            WishListPage wl = new WishListPage(driver);

            boolean onWishList = wl.isOnWishListPage();


            if (!onWishList) {
                String title = driver.getTitle();
                boolean titleOk = title != null && title.toLowerCase().contains("wish list");
                boolean urlOk = driver.getCurrentUrl().toLowerCase().contains("wishlist");
                onWishList = titleOk || urlOk;
            }

            Assert.assertTrue(onWishList, "User was not navigated to 'My Wish List' page.");

            logger.info("***** TC_WL_008 PASSED *****");
        }
    }



