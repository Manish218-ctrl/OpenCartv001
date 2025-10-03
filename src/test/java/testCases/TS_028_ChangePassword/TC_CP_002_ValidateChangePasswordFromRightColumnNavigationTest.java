package testCases.TS_028_ChangePassword;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_CP_002_ValidateChangePasswordFromRightColumnNavigationTest extends BaseClass {

        @Test
        public void validateChangePasswordNavigation() {
            logger.info("***** Starting TC_CP_002_ChangePasswordNavigationTest *****");

            try {
                // Step 1: Perform Login
                Homepage home = new Homepage(driver);
                home.clickMyAccount();
                home.clickLogin();

                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(username, password);
                logger.info("User logged in successfully.");

                // Step 2: Navigate to Change Password Page
                MyAccountPage myAccount = new MyAccountPage(driver);
                Assert.assertTrue(myAccount.isUserLoggedIn(), "Login was not successful, Logout link missing.");

                myAccount.clickChangeYourPassword();
                logger.info("Clicked on 'Change your password' link.");

                // Step 3: Validate navigation
                String breadcrumb = myAccount.getBreadcrumb();
                Assert.assertEquals(breadcrumb, "Change Password",
                        "User was not navigated to 'Change Password' page. Found breadcrumb: " + breadcrumb);

                logger.info("Successfully navigated to 'Change Password' page.");
            } catch (Exception e) {
                logger.error("Test Failed due to exception: ", e);
                Assert.fail("Test execution failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_CP_002_ChangePasswordNavigationTest *****");
        }
    }

