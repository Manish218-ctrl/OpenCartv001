package testCases.TS_019_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_010_ValidateMyAccountInformationPageTest extends BaseClass {

        @Test
        public void validateMyAccountInformationPage() {
            logger.info("=== TS_014: Validate 'My Account Information' Page Heading, Title, and URL ===");

            // Step 1: Login
            performLogin();  // BaseClass method handles login
            logger.info("Login successful.");

            // Step 2: Navigate to 'My Account Information' page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickEditAccountInformation();
            logger.info("Clicked on 'Edit your account information' link.");

            // Step 3: Validate Page Heading
            boolean headingDisplayed = myAccountPage.isMyAccountInformationPageDisplayed();
            Assert.assertTrue(headingDisplayed, "Page Heading 'My Account Information' is NOT displayed.");
            logger.info("Page Heading validated successfully.");

            // Step 4: Validate Page Title
            String actualTitle = driver.getTitle();
            String expectedTitle = "My Account Information"; // Replace with actual expected title
            Assert.assertEquals(actualTitle, expectedTitle, "Page Title mismatch!");
            logger.info("Page Title validated successfully: " + actualTitle);

            // Step 5: Validate Page URL
            String actualURL = driver.getCurrentUrl();
            String expectedURL = rb.getString("appURL") + "index.php?route=account/edit"; // Update with actual URL path
            Assert.assertEquals(actualURL, expectedURL, "Page URL mismatch!");
            logger.info("Page URL validated successfully: " + actualURL);
        }
    }


