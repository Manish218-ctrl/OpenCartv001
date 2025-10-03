package testCases.TS_024_MyAccount;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MA_002_ValidateMyAccountPageDetailsTest extends BaseClass {

        @Test
        public void verifyMyAccountPageDetails() {
            logger.info("***** Starting TC_MA_007_MyAccountTest *****");

            try {
                // Step 1: Login
                performLogin();
                logger.info("Login successful.");

                // Step 2: Navigate to My Account page
                Homepage home = new Homepage(driver);
                home.clickMyAccount(); // open dropdown
                home.clickMyAccountFromDropdown(); // click My Account link

                // Step 3: Create MyAccountPage object
                MyAccountPage myAccountPage = new MyAccountPage(driver);

                // Step 4: Validate Page Title
                String actualTitle = myAccountPage.getPageTitle();
                String expectedTitle = "My Account"; // Adjust according to AUT
                Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch!");

                // Step 5: Validate Page URL
                String actualUrl = myAccountPage.getCurrentUrl();
                Assert.assertTrue(actualUrl.contains("route=account/account"), "Page URL mismatch!");

                // Step 6: Validate Page Heading
                String actualHeading = myAccountPage.getMyAccountHeading();
                Assert.assertEquals(actualHeading, "My Account", "Page heading mismatch!");

                logger.info("TC_MA_007 - My Account page validation PASSED.");

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_MA_007_MyAccountTest *****");
        }
    }

