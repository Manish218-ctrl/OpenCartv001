package testCases.TS_002_LoginFunctionality;




import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import testBase.BaseClass;

    public class TC_LF_003_ValidateInvalidEmailValidPasswordTest extends BaseClass {

        @Test(groups = {"Regression","Master"})
        public void verify_login_with_invalid_email_valid_password() {
            logger.info("***** Starting TC_LF_003_ValidateInvalidEmailValidPasswordTest *****");

            try {
                // Navigate to Login page
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                // Login Page
                LoginPage lp = new LoginPage(driver);
                lp.setEmail("xyzabc123@gmail.com");   // invalid email
                lp.setPassword("12345");              // valid password
                lp.clickLogin();

                // Capture warning message
                String warningMsg = lp.getWarningMessage();
                logger.info("Captured Warning: " + warningMsg);

                Assert.assertTrue(
                        warningMsg.contains("Warning: No match for E-Mail Address and/or Password."),
                        "Expected warning message not displayed!"
                );
            }
            catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage());
                Assert.fail("Test failed due to exception.");
            }

            logger.info("***** Finished TC_LF_003_ValidateInvalidEmailValidPasswordTest *****");
        }
    }



