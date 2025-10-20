package testCases.TS_023_MyAccount;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MA_002_ValidateMyAccountPageDetailsTest extends BaseClass {

        @Test
        public void verifyMyAccountPageDetails() {
            logger.info("***** Starting TC_MA_007_MyAccountTest *****");

            try {
                // Login
                performLogin();
                logger.info("Login successful.");

                //Navigate to My Account page
                HomePage home = new HomePage(driver);
                home.clickMyAccount(); // open dropdown
                home.clickMyAccountFromDropdown(); // click My Account link

                //Create MyAccountPage object
                MyAccountPage myAccountPage = new MyAccountPage(driver);

                //Validate Page Title
                String actualTitle = myAccountPage.getPageTitle();
                String expectedTitle = "My Account"; // Adjust according to AUT
                Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch!");

                //Validate Page URL
                String actualUrl = myAccountPage.getCurrentUrl();
                Assert.assertTrue(actualUrl.contains("route=account/account"), "Page URL mismatch!");

                //Validate Page Heading
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

