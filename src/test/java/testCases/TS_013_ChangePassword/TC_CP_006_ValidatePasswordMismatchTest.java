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
                // Step 1: Login
                logger.info("Step 1: Logging in with valid credentials...");
                performLogin();  // BaseClass login method
                logger.info("Login successful.");

                // Step 2: Navigate to 'Change Password' page
                logger.info("Step 2: Navigating to Change Password page...");
                HomePage home = new HomePage(driver);
                home.clickMyAccountFromDropdown();
                home.clickChangePasswordLink();
                logger.info("Navigated to Change Password page.");

                // Step 3: Initialize ChangePasswordPage
                ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);

                // Step 4: Enter different passwords
                logger.info("Step 3: Entering mismatched passwords...");
                String newPassword = randomAlphaNumeric();
                String confirmPassword = randomAlphaNumeric();  // intentionally different
                logger.info("New Password: " + newPassword + ", Confirm Password: " + confirmPassword);

                changePasswordPage.setNewPassword(newPassword);
                changePasswordPage.setConfirmPassword(confirmPassword);

                // Step 5: Click 'Continue'
                logger.info("Step 4: Clicking Continue...");
                changePasswordPage.clickContinue();

                // Step 6: Validate warning message
                logger.info("Step 5: Validating warning message for password mismatch...");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

