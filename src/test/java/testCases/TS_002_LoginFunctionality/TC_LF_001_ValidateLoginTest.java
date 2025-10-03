package testCases.TS_002_LoginFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_LF_001_ValidateLoginTest extends BaseClass {

        @Test
        public void verify_valid_login() {
            logger.info("***** Starting TC_LF_001_ValidateLoginTest *****");

            try {
                // Step 1: Open Homepage
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();
                logger.info("Navigated to Login Page");

                // Step 2: Enter login credentials
                LoginPage lp = new LoginPage(driver);
                lp.setEmail(p.getProperty("email"));   // from config.properties
                lp.setPassword(p.getProperty("password")); // from config.properties
                lp.clickLogin();
                logger.info("Entered valid credentials and clicked Login");

                // Step 3: Validate navigation to My Account Page
                MyAccountPage myAcc = new MyAccountPage(driver);
                boolean targetPage = myAcc.isMyAccountPageExists();

                Assert.assertTrue(targetPage, "Login failed! My Account Page not displayed.");
                logger.info("Login successful. My Account Page displayed.");

            } catch (Exception e) {
                logger.error("Test Failed due to exception: " + e.getMessage());
                Assert.fail();
            }

            logger.info("***** Finished TC_LF_001_ValidateLoginTest *****");
        }
    }



