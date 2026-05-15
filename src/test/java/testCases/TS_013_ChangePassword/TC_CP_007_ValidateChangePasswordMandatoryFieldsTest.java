package testCases.TS_013_ChangePassword;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

public class TC_CP_007_ValidateChangePasswordMandatoryFieldsTest extends BaseClass {

    @Test
    public void validateMandatoryPasswordFields() {

        logger.info(
                "===== Starting Test: TC_CP_007 – Change Password Mandatory Fields ====="
        );

        // Login
        performLogin();

        logger.info(
                "User logged in successfully."
        );

        // Navigate to Change Password page
        getDriver().get(
                appURL + "/index.php?route=account/password"
        );

        ChangePasswordPage cpPage =
                new ChangePasswordPage(getDriver());

        logger.info(
                "Navigated to Change Password page."
        );

        cpPage.clickContinue();

        // Validate Password field mandatory
        boolean isNewPasswordMandatory =
                cpPage.isNewPasswordFieldMandatory();

        logger.info(
                "New Password field mandatory: "
                        + isNewPasswordMandatory
        );

        Assert.assertTrue(
                isNewPasswordMandatory,
                "New Password field is not marked as mandatory!"
        );

        // Validate Confirm Password field mandatory
        boolean isConfirmPasswordMandatory =
                cpPage.isConfirmPasswordFieldMandatory();

        logger.info(
                "Confirm Password field mandatory: "
                        + isConfirmPasswordMandatory
        );

        Assert.assertTrue(
                isConfirmPasswordMandatory,
                "Confirm Password field is not marked as mandatory!"
        );

        logger.info(
                "===== Test Completed Successfully ====="
        );
    }
}