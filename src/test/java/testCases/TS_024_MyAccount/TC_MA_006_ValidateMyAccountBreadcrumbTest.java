package testCases.TS_024_MyAccount;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_MA_006_ValidateMyAccountBreadcrumbTest extends BaseClass {

        @Test
        public void validateMyAccountBreadcrumb() {
            logger.info("***** Starting TC_MA_006 – Validate Breadcrumb in 'My Account' page *****");

            try {
                // Step 1: Perform login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to My Account page
                Homepage home = new Homepage(driver);
                home.clickMyAccount();               // Open dropdown
                home.clickMyAccountFromDropdown();   // Click on 'My Account'
                logger.info("Navigated to 'My Account' page.");

                // Step 3: Capture breadcrumb
                String actualBreadcrumb = home.getBreadcrumb();
                logger.info("Breadcrumb found: " + actualBreadcrumb);

                // Step 4: Validate breadcrumb
                String expectedBreadcrumb = "Account";  // adjust if actual text differs
                Assert.assertEquals(actualBreadcrumb, expectedBreadcrumb,
                        "Breadcrumb validation failed!");

                logger.info("***** TC_MA_006 passed successfully *****");

            } catch (Exception e) {
                logger.error("Test Case TC_MA_006 failed → " + e.getMessage());
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
    }

