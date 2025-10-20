package testCases.TS_027_ChangePassword;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

    public class TC_CP_008_ValidateChangePasswordHideTextTest extends BaseClass {

        @Test
        public void validatePasswordFieldMasking() {
            logger.info("===== Starting Test: TC_CP_008 – Change Password Field Masking =====");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to 'Change Password' page
            ChangePasswordPage cpPage = new ChangePasswordPage(driver);
            driver.get(appURL + "/index.php?route=account/password"); // Adjust if needed
            logger.info("Navigated to Change Password page.");

            // Step 3: Enter sample password in New Password and Confirm Password
            String samplePassword = "Test@123";
            cpPage.setNewPassword(samplePassword);
            cpPage.setConfirmPassword(samplePassword);
            logger.info("Entered sample password into both fields.");

            // Step 4: Validate that text is hidden (masked)
            WebElement newPasswordField = cpPage.getNewPasswordField();
            WebElement confirmPasswordField = cpPage.getConfirmPasswordField();

            // Get type attribute; should be "password" to hide the text
            String newPasswordType = newPasswordField.getAttribute("type");
            String confirmPasswordType = confirmPasswordField.getAttribute("type");

            logger.info("New Password field type attribute: " + newPasswordType);
            logger.info("Confirm Password field type attribute: " + confirmPasswordType);

            Assert.assertEquals(newPasswordType, "password", "New Password field is not masked!");
            Assert.assertEquals(confirmPasswordType, "password", "Confirm Password field is not masked!");

            logger.info("===== Test Completed Successfully – Password fields are masked =====");
        }
    }

