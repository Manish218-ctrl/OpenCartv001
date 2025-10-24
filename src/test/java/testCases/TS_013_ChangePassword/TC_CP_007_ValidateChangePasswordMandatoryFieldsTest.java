package testCases.TS_013_ChangePassword;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

    public class TC_CP_007_ValidateChangePasswordMandatoryFieldsTest extends BaseClass {

        @Test
        public void validateMandatoryPasswordFields() {
            logger.info("===== Starting Test: TC_CP_007 – Change Password Mandatory Fields =====");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to 'Change Password' page
            ChangePasswordPage cpPage = new ChangePasswordPage(driver);
            driver.get(appURL + "/index.php?route=account/password"); // Adjust if your URL pattern differs
            logger.info("Navigated to Change Password page.");

            cpPage.clickContinue();


            WebElement newPasswordLabel = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[1]/div/div"));

            boolean isNewPasswordMandatory = newPasswordLabel.getText().contains("!");

            logger.info("New Password field mandatory (using asterisk): " + isNewPasswordMandatory);
            Assert.assertTrue(isNewPasswordMandatory, "New Password field is not marked as mandatory!");


            logger.info("New Password field mandatory: " + isNewPasswordMandatory);
            Assert.assertTrue(isNewPasswordMandatory, "New Password field is not marked as mandatory!");

            // Step 4: Validate Confirm Password field is mandatory using label/asterisk
            WebElement confirmPasswordLabel = driver.findElement(By.xpath(""));

            boolean isConfirmPasswordMandatory = confirmPasswordLabel.getText().contains("!");

            logger.info("Confirm Password field mandatory (using asterisk): " + isConfirmPasswordMandatory);
            Assert.assertTrue(isConfirmPasswordMandatory, "Confirm Password field is not marked as mandatory!");

            logger.info("===== Test Completed Successfully =====");
        }
    }

