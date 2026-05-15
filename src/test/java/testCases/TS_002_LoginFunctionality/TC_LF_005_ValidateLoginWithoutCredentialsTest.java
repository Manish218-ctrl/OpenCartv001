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
                HomePage hp = new HomePage(getDriver());
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(getDriver());

                lp.clickLogin();

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



