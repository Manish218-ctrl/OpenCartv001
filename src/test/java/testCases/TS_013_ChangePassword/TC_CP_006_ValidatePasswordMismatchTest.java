package testCases.TS_013_ChangePassword;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

    public class TC_CP_006_ValidatePasswordMismatchTest extends BaseClass {

        @Test
        public void validatePasswordMismatch() {
            logger.info("***** Starting TC_CP_006_ValidatePasswordMismatchTest *****");

            try {
                //Login
                logger.info("Logging in with valid credentials...");
                performLogin();  // BaseClass login method
                logger.info("Login successful.");

                //Navigate to Change Password page
                logger.info("Navigating to Change Password page...");
                HomePage home = new HomePage(getDriver());
                home.clickMyAccountFromDropdown();
                home.clickChangePasswordLink();
                logger.info("Navigated to Change Password page.");

                //Initialize ChangePasswordPage
                ChangePasswordPage changePasswordPage = new ChangePasswordPage(getDriver());

                //Enter different passwords
                logger.info("Entering mismatched passwords...");
                String newPassword = randomAlphaNumeric();
                String confirmPassword = randomAlphaNumeric();  // intentionally different
                logger.info("New Password: " + newPassword + ", Confirm Password: " + confirmPassword);

                changePasswordPage.setNewPassword(newPassword);
                changePasswordPage.setConfirmPassword(confirmPassword);

                //Click Continue
                logger.info("Clicking Continue...");
                changePasswordPage.clickContinue();

                //Validate warning message
                logger.info("Validating warning message for password mismatch...");
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement warningElement = wait.until(
                        ExpectedConditions.visibilityOf(changePasswordPage.getWarningMessageElement())
                );

                String warningMsg = warningElement.getText();
                logger.info("Warning message displayed: " + warningMsg);
                Assert.assertTrue(
                        warningMsg.contains("Password confirmation does not match password!"),
                        "Expected warning message not displayed."
                );

            } catch (Exception e) {
                logger.error("Test failed due to exception: ", e);
                Assert.fail("Test execution failed: " + e.getMessage());
            }

            logger.info("***** Finished TC_CP_006_ValidatePasswordMismatchTest *****");
        }
    }

