package testCases.TS_020_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_001_ValidateMyAccountInformationTest extends BaseClass {

        @Test
        public void verifyNavigationToMyAccountInformationPage() {
            logger.info("***** Starting TC_MAI_001_ValidateMyAccountInformationTest *****");

            try {
                // Step 1: Login
                performLogin();
                logger.info("User logged in successfully.");

                // Step 2: Navigate to My Account page
                Homepage home = new Homepage(driver);
                home.clickMyAccount();   // Click dropdown
                logger.info("Clicked on My Account dropdown.");

                home.lnkMyAccount.click();  // Click My Account link
                logger.info("Navigated to My Account page.");

                // Step 3: Click 'Edit your account information'
                MyAccountPage myAccPage = new MyAccountPage(driver);
                myAccPage.clickEditAccountInformation();

                // Step 4: Validate navigation
                boolean result = myAccPage.isMyAccountInformationPageDisplayed();
                Assert.assertTrue(result, "Failed to navigate to My Account Information page.");

                logger.info("***** TC_MAI_001_ValidateMyAccountInformationTest Passed *****");

            } catch (Exception e) {
                logger.error("Test case failed: " + e.getMessage());
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
    }




