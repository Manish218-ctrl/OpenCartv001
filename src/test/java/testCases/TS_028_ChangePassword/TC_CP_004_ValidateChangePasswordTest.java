package testCases.TS_028_ChangePassword;




import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;




    public class TC_CP_004_ValidateChangePasswordTest extends BaseClass {


        @Test
        public void validateChangePassword() {
            logger.info("***** Starting TC_CP_004_ValidateChangePasswordTest *****");

            try {
                // Step 1: Login
                Homepage home = new Homepage(driver);
                home.clickMyAccount();
                home.clickLogin();

                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(username, password);
                logger.info("User logged in successfully.");

                // Step 2: Navigate to Password page
                MyAccountPage myAccount = new MyAccountPage(driver);
                myAccount.clickPassword();

                ChangePasswordPage changePwdPage = new ChangePasswordPage(driver);

                // Step 3: Enter New Password
                String newPassword = randomAlphaNumeric();  // random new password
                changePwdPage.setNewPassword(newPassword);
                changePwdPage.setConfirmPassword(newPassword);
                changePwdPage.clickContinue();

                // Validate success message
                String successMsg = changePwdPage.getSuccessMessageText();  // use instance method
                Assert.assertTrue(successMsg.contains("Success: Your password has been successfully updated."),
                        "Password change success message not displayed.");
                logger.info("Password updated successfully.");

                // Step 4: Logout
                home.clickMyAccount();
                home.clickLogout();

                // Step 5: Try login with old password (should fail)
                home.clickMyAccount();
                home.clickLogin();
                loginPage.login(username, password); // old password
                String warningMsg = changePwdPage.getWarningMessageText();  // use instance method
                Assert.assertTrue(warningMsg.contains("Warning: No match for E-Mail Address and/or Password."),
                        "Expected warning message not displayed for old password.");

                // Step 6: Login with new password
                home.clickMyAccount();
                home.clickLogin();
                loginPage.login(username, newPassword);

                String pageTitle = driver.getTitle();
                Assert.assertTrue(pageTitle.contains("My Account"), "Login with new password failed.");
                logger.info("User logged in with new password successfully.");

            } catch (Exception e) {
                logger.error("Test Failed due to exception: ", e);
                Assert.fail("Test execution failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_CP_004_ValidateChangePasswordTest *****");
        }

    }
