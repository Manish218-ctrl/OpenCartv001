package testCases.TS_019_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_001_ValidateMyAccountInformationTest extends BaseClass {

        @Test
        public void verifyNavigationToMyAccountInformationPage() {
            logger.info("***** Starting TC_MAI_001_ValidateMyAccountInformationTest *****");

            try {
                //Login
                performLogin();
                logger.info("User logged in successfully.");

                //Navigate to My Account page
                HomePage home = new HomePage(getDriver());
                home.clickMyAccount();   // Click dropdown
                logger.info("Clicked on My Account dropdown.");

                home.lnkMyAccount.click();  // Click My Account link
                logger.info("Navigated to My Account page.");

                //Click Edit your account information
                MyAccountPage myAccPage = new MyAccountPage(getDriver());
                myAccPage.clickEditAccountInformation();

                //Validate navigation
                boolean result = myAccPage.isMyAccountInformationPageDisplayed();
                Assert.assertTrue(result, "Failed to navigate to My Account Information page.");

                logger.info("***** TC_MAI_001_ValidateMyAccountInformationTest Passed *****");

            } catch (Exception e) {
                logger.error("Test case failed: " + e.getMessage());
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }
    }




