package testCases.TS_002_LoginFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

    public class TC_LF_005_ValidateLoginWithoutCredentialsTest extends BaseClass {

        @Test(groups = {"Regression", "Master"})
        public void verify_login_without_credentials() {
            logger.info("***** Starting TC_LF_005_ValidateLoginWithoutCredentialsTest *****");

            try {
                // Navigate to Login Page
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                // LoginPage
                LoginPage lp = new LoginPage(driver);

                // Do not enter email & password (leave blank)
                lp.clickLogin();  // click Login directly

                // Validate expected warning
                String actWarning = lp.getWarningMessage();
                String expWarning = "Warning: No match for E-Mail Address and/or Password.";

                Assert.assertTrue(
                        actWarning.contains(expWarning),
                        "Expected warning message not displayed for empty login."
                );

            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail();
            }

            logger.info("***** Finished TC_LF_005_ValidateLoginWithoutCredentialsTest *****");
        }
    }



