package testCases.TS_023_MyAccount;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_MA_006_ValidateMyAccountBreadcrumbTest extends BaseClass {

        @Test
        public void validateMyAccountBreadcrumb() {
            logger.info("***** Starting TC_MA_006 – Validate Breadcrumb in 'My Account' page *****");

            try {
                performLogin();
                logger.info("User logged in successfully.");

                HomePage home = new HomePage(driver);
                home.clickMyAccount();               // Open dropdown
                home.clickMyAccountFromDropdown();   // Click on 'My Account'
                logger.info("Navigated to 'My Account' page.");

                logger.info("***** TC_MA_006 passed successfully *****");

            } catch (Exception e) {
                logger.error("Test Case TC_MA_006 failed → " + e.getMessage());
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
    }

