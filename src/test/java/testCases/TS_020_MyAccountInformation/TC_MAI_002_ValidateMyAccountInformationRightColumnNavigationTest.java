package testCases.TS_020_MyAccountInformation;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_002_ValidateMyAccountInformationRightColumnNavigationTest extends BaseClass {

        @Test
        public void verifyMyAccountInformationNavigation() {
            logger.info("***** Starting TC_MI_001_MyAccountInformationNavigationTest *****");

            try {
                // Step 1: Perform login
                Homepage home = new Homepage(driver);
                home.clickMyAccount();
                home.clickLogin();

                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(username, password);  // username & password from config.properties

                // Step 2: Navigate to My Account Page
                MyAccountPage myAccountPage = new MyAccountPage(driver);

                // Click on "Edit your account information" link in Right Column
                myAccountPage.clickEditAccountInformation();

                // Step 3: Validate navigation to "My Account Information" page
                boolean isInfoPageDisplayed = myAccountPage.isMyAccountInformationPageDisplayed();
                Assert.assertTrue(isInfoPageDisplayed,
                        "User is NOT navigated to 'My Account Information' page!");

                logger.info("User successfully navigated to 'My Account Information' page.");
            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test Case failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_MI_001_MyAccountInformationNavigationTest *****");
        }
    }



