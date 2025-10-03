package testCases.TS_002_LoginFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import testBase.BaseClass;

    public class TC_LF_002_ValidateInvalidLoginTest extends BaseClass {

        @Test
        public void verify_invalid_login() {
            logger.info("***** Starting TC_LF_002_ValidateInvalidLoginTest *****");

            try {
                driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(driver);
                lp.setEmail("xyzabc123@gmail.com");   // Invalid email
                lp.setPassword("xyzabc123");          // Invalid password
                lp.clickLogin();

                String warning = lp.getWarningMessage();
                logger.info("Captured Warning Message: " + warning);

                Assert.assertTrue(
                        warning.contains("Warning: No match for E-Mail Address and/or Password."),
                        "Expected warning message not displayed"
                );
            } catch (Exception e) {
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }

            logger.info("***** Finished TC_LF_002_ValidateInvalidLoginTest *****");
        }
    }


