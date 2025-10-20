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


                // Step 1: Navigate to a different page (e.g., Order History after login)
                performLogin();
                HomePage homePage = new HomePage(driver);

                homePage.clickMyAccount();
                homePage.clickOrderHistory();
                logger.info("Navigated to Order History page.");


                // Step 2: Click on Home breadcrumb
                driver.findElement(org.openqa.selenium.By.xpath("/html/body/div[2]/ul/li[1]/a/i")).click();
                logger.info("Clicked on Home breadcrumb.");

                // Step 3: Validate that we navigated back to Home page
                String actualTitle = homePage.getPageTitle();
                logger.info("Current Page Title after clicking Home: " + actualTitle);

                Assert.assertTrue(actualTitle.contains("Your Store") || actualTitle.equalsIgnoreCase("Home"),
                        "User should be navigated to Home page but found: " + actualTitle);

                logger.info("***** Finished TC_HP_008_ValidateHomeBreadcrumb Successfully *****");

            } catch (Exception e) {
                logger.error("Test Case Failed: " + e.getMessage(), e);
                Assert.fail("Exception in test case: " + e.getMessage());
            }
        }
    }

