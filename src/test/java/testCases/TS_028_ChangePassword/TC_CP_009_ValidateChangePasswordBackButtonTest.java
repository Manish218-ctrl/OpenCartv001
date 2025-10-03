package testCases.TS_028_ChangePassword;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

    public class TC_CP_009_ValidateChangePasswordBackButtonTest extends BaseClass {

        @Test
        public void validateBackButtonFunctionality() {
            logger.info("===== Starting Test: TC_CP_009 – Change Password Back Button =====");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to 'Change Password' page
            ChangePasswordPage cpPage = new ChangePasswordPage(driver);
            driver.get(appURL + "/index.php?route=account/password"); // Adjust if needed
            logger.info("Navigated to Change Password page.");

            // Step 3: Enter new password and confirm password
            String newPassword = "Test@123";
            cpPage.setNewPassword(newPassword);
            cpPage.setConfirmPassword(newPassword);
            logger.info("Entered new password and confirm password.");

            // Step 4: Click Back button
            WebElement backButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/a")); // Update XPath if needed
            backButton.click();
            logger.info("Clicked on Back button.");

            // Step 5: Validate navigation to 'My Account' page
            String expectedTitle = "My Account"; // Update to actual title of My Account page
            String actualTitle = driver.getTitle();
            logger.info("Actual page title after Back button: " + actualTitle);
            Assert.assertTrue(actualTitle.contains(expectedTitle), "Back button did not navigate to My Account page!");

            // Step 6: Navigate back to Change Password page
            driver.get(appURL + "/index.php?route=account/password"); // Or click Change Password link from My Account page
            logger.info("Navigated back to Change Password page.");

            // Step 7: Validate password fields are cleared
            Assert.assertEquals(cpPage.getNewPasswordField().getAttribute("value"), "", "New Password field is not empty!");
            Assert.assertEquals(cpPage.getConfirmPasswordField().getAttribute("value"), "", "Confirm Password field is not empty!");

            logger.info("===== Test Completed Successfully – Back button functionality validated =====");
        }
    }

