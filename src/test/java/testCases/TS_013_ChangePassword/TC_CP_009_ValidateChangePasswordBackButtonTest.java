package testCases.TS_013_ChangePassword;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

public class TC_CP_009_ValidateChangePasswordBackButtonTest extends BaseClass {

    @Test
    public void validateBackButtonFunctionality() {

        logger.info("===== Starting Test: TC_CP_009 – Change Password Back Button =====");

        // Login
        performLogin();

        logger.info("User logged in successfully.");

        // Navigate to Change Password page
        getDriver().get(appURL + "/index.php?route=account/password");

        ChangePasswordPage cpPage =
                new ChangePasswordPage(getDriver());

        logger.info("Navigated to Change Password page.");

        // Enter password values
        String newPassword = "Test@123";

        cpPage.setNewPassword(newPassword);

        cpPage.setConfirmPassword(newPassword);

        logger.info("Entered new password and confirm password.");

        // Click Back button
        cpPage.clickBackButton();

        logger.info("Clicked on Back button.");

        // Validate navigation to My Account page
        String actualTitle =
                getDriver().getTitle();

        logger.info(
                "Actual page title after Back button: "
                        + actualTitle
        );

        Assert.assertTrue(
                actualTitle.contains("My Account"),
                "Back button did not navigate to My Account page!"
        );

        // Navigate back to Change Password page
        getDriver().get(appURL + "/index.php?route=account/password");

        logger.info("Navigated back to Change Password page.");

        // Reinitialize page object after navigation
        cpPage =
                new ChangePasswordPage(getDriver());

        // Validate fields are cleared
        Assert.assertEquals(
                cpPage.getNewPasswordField().getAttribute("value"),
                "",
                "New Password field is not empty!"
        );

        Assert.assertEquals(
                cpPage.getConfirmPasswordField().getAttribute("value"),
                "",
                "Confirm Password field is not empty!"
        );

        logger.info(
                "===== Test Completed Successfully – Back button functionality validated ====="
        );
    }
}