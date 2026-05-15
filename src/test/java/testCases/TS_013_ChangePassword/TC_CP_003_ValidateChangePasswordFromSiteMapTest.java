package testCases.TS_013_ChangePassword;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SiteMapPage;
import testBase.BaseClass;

public class TC_CP_003_ValidateChangePasswordFromSiteMapTest extends BaseClass {

    @Test
    public void validateChangePasswordFromSiteMap() {
        logger.info("***** Starting TC_CP_003_ValidateChangePasswordFromSiteMapTest *****");

        try {
            //Perform Login
            HomePage home = new HomePage(getDriver());
            home.clickMyAccount();
            home.clickLogin();

            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.login(username, password);
            logger.info("User logged in successfully.");

            //Click on Site Map footer link
            SiteMapPage siteMapPage = new SiteMapPage(getDriver());
            siteMapPage.clickFooterLink("Site Map");
            Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "Site Map page did not open.");
            logger.info("Navigated to Site Map page.");

            //Click on Password link from Site Map
            siteMapPage.clickPasswordLink();
            logger.info("Clicked on Password link from Site Map page.");

            //Validate navigation to Change Password page
            MyAccountPage myAccount = new MyAccountPage(getDriver());
            String breadcrumb = myAccount.getBreadcrumb();
            Assert.assertEquals(breadcrumb, "Change Password",
                    "User was not navigated to Change Password page. Found breadcrumb: " + breadcrumb);

            logger.info("Successfully navigated to Change Password page from Site Map.");
        } catch (Exception e) {
            logger.error("Test Failed due to exception: ", e);
            Assert.fail("Test execution failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_CP_003_ValidateChangePasswordFromSiteMapTest *****");
    }
}
