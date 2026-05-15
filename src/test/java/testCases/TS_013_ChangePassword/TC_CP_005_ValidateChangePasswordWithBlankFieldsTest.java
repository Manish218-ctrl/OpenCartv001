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

public class TC_CP_005_ValidateChangePasswordWithBlankFieldsTest extends BaseClass {

    @Test
    public void validateChangePasswordWithBlankFields() {
        logger.info("***** Starting TC_CP_005_ValidateChangePasswordWithBlankFieldsTest *****");

        try {
            //Login to application
            logger.info("Logging in...");
            performLogin(); // BaseClass method
            logger.info("Login successful.");

            //Navigate to Change Password page
            logger.info("Navigating to Change Password page...");
            HomePage home = new HomePage(getDriver());
            home.clickMyAccountFromDropdown();
            logger.info("Clicked My Account dropdown.");
            home.clickChangePasswordLink();
            logger.info("Clicked Change Password link.");

            //Initialize ChangePasswordPage
            ChangePasswordPage changePasswordPage = new ChangePasswordPage(getDriver());
            logger.info("Initialized ChangePasswordPage object.");

            //Wait for password fields to be visible
            logger.info("Waiting for password fields to be visible...");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement newPasswordField = wait.until(
                    ExpectedConditions.visibilityOf(changePasswordPage.getNewPasswordField())
            );
            WebElement confirmPasswordField = wait.until(
                    ExpectedConditions.visibilityOf(changePasswordPage.getConfirmPasswordField())
            );
            logger.info("Password fields are visible.");

            //Leave fields blank and click Continue
            logger.info("Leaving fields blank and clicking Continue.");
            newPasswordField.clear();
            confirmPasswordField.clear();
            changePasswordPage.clickContinue();
            logger.info("Continue clicked with blank fields.");

            //Validate warning message
            logger.info("Waiting for warning message...");
            WebElement warningElement = wait.until(
                    ExpectedConditions.visibilityOf(changePasswordPage.getWarningMessageElement())
            );
            String warningMsg = warningElement.getText();
            logger.info("Warning Message displayed: " + warningMsg);
            Assert.assertTrue(warningMsg.contains("Password must be between 4 and 20 characters!"),
                    "Warning message not displayed correctly");

        } catch (Exception e) {
            logger.error("Test failed due to exception: ", e);
            Assert.fail("Test execution failed: " + e.getMessage());
        }

        logger.info("***** Finished TC_CP_005_ValidateChangePasswordWithBlankFieldsTest *****");
    }
}
