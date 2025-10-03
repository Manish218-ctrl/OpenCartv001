package testCases.TS_028_ChangePassword;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

    public class TC_CP_011_ValidateChangePasswordPageDetailsTest extends BaseClass {

        @Test
        public void validateChangePasswordPageDetails() {
            logger.info("===== Starting Test: TC_CP_011 – Change Password Page Details =====");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to Change Password page
            ChangePasswordPage cpPage = new ChangePasswordPage(driver);
            driver.get(appURL + "/index.php?route=account/password"); // Adjust URL if required
            logger.info("Navigated to Change Password page.");

            // Step 3: Validate Page Title
            String expectedTitle = "Change Password"; // Replace with actual expected title
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Page title is incorrect!");
            logger.info("Page Title validated: " + actualTitle);

            // Step 4: Validate Page URL
            String expectedURL = appURL + "/index.php?route=account/password"; // Replace with actual URL
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(actualURL, expectedURL, "Page URL is incorrect!");
            logger.info("Page URL validated: " + actualURL);

            // Step 5: Validate Page Heading
            WebElement headingElement = driver.findElement(By.cssSelector("div#content h1")); // Adjust selector if needed
            String expectedHeading = "Change Password"; // Replace with actual heading text
            String actualHeading = headingElement.getText();
            Assert.assertEquals(actualHeading, expectedHeading, "Page heading is incorrect!");
            logger.info("Page Heading validated: " + actualHeading);

            logger.info("===== Test Completed Successfully – Page Details validated =====");
        }
    }

