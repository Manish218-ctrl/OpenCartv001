package testCases.TS_013_ChangePassword;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

public class TC_CP_011_ValidateChangePasswordPageDetailsTest extends BaseClass {

    @Test
    public void validateChangePasswordPageDetails() {

        logger.info("===== Starting Test: TC_CP_011 – Change Password Page Details =====");

        // Login
        performLogin();

        logger.info("User logged in successfully.");

        ChangePasswordPage cpPage = new ChangePasswordPage(getDriver());

        cpPage.openChangePasswordPage(appURL);

        // Validate Page Title
        String expectedTitle = "Change Password";

        String actualTitle = cpPage.getPageTitle();

        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Page title is incorrect!"
        );

        logger.info("Page Title validated: {}", actualTitle);

        // Validate Page URL
        String expectedURL =
                appURL + "/index.php?route=account/password";

        String actualURL =
                cpPage.getCurrentPageURL();

        Assert.assertEquals(
                actualURL,
                expectedURL,
                "Page URL is incorrect!"
        );

        logger.info("Page URL validated: {}", actualURL);

        // Validate Page Heading
        String expectedHeading = "Change Password";

        String actualHeading = cpPage.getPageHeading();

        Assert.assertEquals(
                actualHeading,
                expectedHeading,
                "Page heading is incorrect!"
        );

        logger.info("Page Heading validated: {}", actualHeading);

        logger.info("===== Test Completed Successfully – Page Details validated =====");
    }
}