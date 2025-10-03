package testCases.TS_016_ReturnsPage;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

    /**
     * TC_PR_005
     * Title: Validate Back button on the 'Product Returns' page
     * Pre-req: User account has a few orders placed already
     * ER: 'Product Returns' request should NOT get processed and the user should be taken to 'My Account' page
     * App URL: https://tutorialsninja.com/demo/index.php?route=common/home
     */
    public class TC_PR_005_ValidateProductductReturnsBackButtonTest extends BaseClass {

        @Test(description = "Validate Back button on Product Returns page navigates to My Account without submitting")
        public void verifyBackButtonOnProductReturnsPage() {

            logger.info("==== TC_PR_005 started ====");

            // 1) Login
            Homepage home = new Homepage(driver);
            home.clickMyAccount();
            home.clickLogin();

            LoginPage login = new LoginPage(driver);
            login.login(username, password);
            logger.info("Logged in as: " + username);

            // 2) Ensure we are on My Account page
            MyAccountPage myAccount = new MyAccountPage(driver);
            Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page was not displayed after login.");

            // 3) Go to Order History (Right column)
            myAccount.clickOrderHistory();
            logger.info("Navigated to Order History page.");

            // 4) Click “View” icon on any order (using first row)
            OrderHistoryPage orderHistory = new OrderHistoryPage(driver);
            orderHistory.clickFirstOrderViewIcon();
            logger.info("Opened Order Information page via first order's View icon.");

            // 5) Click “Return” icon on Order Information page
            OrderInformationPage orderInfo = new OrderInformationPage(driver);
            orderInfo.clickReturnIcon();
            logger.info("Clicked Return icon to open Product Returns page.");

            // 6) (Optional) Type/modify details on Product Returns page (not required for Back behavior)
            ProductReturnsPage returnsPage = new ProductReturnsPage(driver);
            // Example lightweight fill to ensure fields are interactable (SAFE: does not submit)
            try {
                returnsPage.orderIDFieldp.clear();
                returnsPage.orderIDFieldp.sendKeys("12345");
            } catch (Exception ignored) {}

            // 7) Click Back
            returnsPage.clickBack();
            logger.info("Clicked Back on Product Returns page.");

            // 8) Validate: request NOT processed & user is taken to My Account page
            Assert.assertTrue(myAccount.isMyAccountPageExists(),
                    "Expected to be on My Account page after clicking Back from Product Returns, but wasn't.");

            // Additionally ensure we did NOT land on any “success” message page
            // (We only assert absence by positive navigation to My Account above.)
            logger.info("Validation successful: Back did not submit and navigated to My Account.");

            logger.info("==== TC_PR_005 completed ====");
        }
    }



