package testCases.TS_021_HomePage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HP_007_ValidateHomeBreadcrumbNavigationTest extends BaseClass {

    @Test
    public void verifyHomeBreadcrumbNavigation() {
        try {
            logger.info("***** Starting TC_HP_007_ValidateHomeBreadcrumbNavigationTest *****");

            performLogin();
            HomePage homePage = new HomePage(getDriver());

            homePage.clickMyAccount();
            homePage.clickOrderHistory();
            logger.info("Navigated to Order History page.");

            homePage.clickBreadcrumbHome();
            logger.info("Clicked on Home breadcrumb.");

            String actualTitle = homePage.getPageTitle();
            logger.info("Current Page Title after clicking Home: " + actualTitle);

            Assert.assertTrue(
                    actualTitle.contains("Your Store") || actualTitle.equalsIgnoreCase("Home"),
                    "User should be navigated to Home page but found: " + actualTitle);

            logger.info("***** Finished TC_HP_008_ValidateHomeBreadcrumb Successfully *****");

        } catch (Exception e) {
            logger.error("Test Case Failed: " + e.getMessage(), e);
            Assert.fail("Exception in test case: " + e.getMessage());
        }
    }
}