package testCases.TS_023_MyAccount;




import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SiteMapPage;
import testBase.BaseClass;

    public class TC_MA_005_ValidateNavigateToMyAccountViaSiteMapTest extends BaseClass {

        @Test
        public void validateNavigationToMyAccountViaSiteMap() {
            logger.info("=== Test Case TC_MA_005 STARTED: Validate navigation to 'My Account' via Site Map ===");

            try {
                // Step 1: Login to the application
                logger.info("Step 1: Logging into the application...");
                performLogin();
                logger.info("Login successful.");

                // Step 2: Navigate to Site Map page via footer
                HomePage home = new HomePage(driver);
                logger.info("Step 2: Clicking on 'Site Map' link in the footer...");
                home.clickFooterLink("Site Map");

                SiteMapPage siteMapPage = new SiteMapPage(driver);
                Assert.assertTrue(siteMapPage.isOnSiteMapPage(), "'Site Map' page is not displayed.");
                logger.info("'Site Map' page opened successfully.");

                // Step 3: Click 'My Account' link in Site Map page
                logger.info("Step 3: Clicking on 'My Account' link in Site Map page...");
                siteMapPage.clickAccountInformation(); // Using POM method for account navigation

                // Step 4: Validate navigation to 'My Account' page
                String expectedTitle = "My Account";  // Update based on actual title
                String actualTitle = home.getPageTitle();
                logger.info("Validating page title. Expected: '" + expectedTitle + "', Actual: '" + actualTitle + "'");

                Assert.assertTrue(actualTitle.contains(expectedTitle),
                        "Navigation to 'My Account' page failed. Expected: " + expectedTitle + ", but found: " + actualTitle);

                logger.info("Navigation to 'My Account' page validated successfully.");

            } catch (Exception e) {
                logger.error("ERROR in TC_MA_005: " + e.getMessage(), e);
                Assert.fail("Test Case failed due to exception: " + e.getMessage());
            } finally {
                logger.info("=== Test Case TC_MA_005 COMPLETED ===");
            }
        }
    }

