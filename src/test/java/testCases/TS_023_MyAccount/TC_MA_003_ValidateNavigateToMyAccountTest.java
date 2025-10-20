package testCases.TS_023_MyAccount;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MA_003_ValidateNavigateToMyAccountTest extends BaseClass {

        @Test(description = "Validate navigating to 'My Account' page using 'My Account' option")


        public void verifyNavigateToMyAccount() throws InterruptedException {

            performLogin();

            HomePage home = new HomePage(driver);

            home.clickMyAccount();
            logger.info("Clicked My Account menu");
            Thread.sleep(3000); // Short wait for dropdown animation
            home.clickMyAccountFromDropdown();
            logger.info("Clicked 'My Account' from dropdown");
            MyAccountPage myAccount = new MyAccountPage(driver);

            MyAccountPage myAcc = new MyAccountPage(driver);
            boolean atMyAccount = myAcc.isAt();

            Assert.assertTrue(atMyAccount, "User should be navigated to My Account page but was not. Breadcrumb: " + myAcc.getBreadcrumbText());
        }
    }

