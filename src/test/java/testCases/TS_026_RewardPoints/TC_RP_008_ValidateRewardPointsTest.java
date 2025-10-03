package testCases.TS_026_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.RewardPointsPage;
import testBase.BaseClass;

    public class TC_RP_008_ValidateRewardPointsTest extends BaseClass {

        @Test
        public void validateRewardPointsPage() {
            // Step 1: Login to the application
            performLogin();

            // Step 2: Navigate to Reward Points page
            Homepage home = new Homepage(driver);
            home.clickRewardPoints();
            logger.info("Navigated to 'Your Reward Points' page");

            // Step 3: Initialize RewardPointsPage
            RewardPointsPage rewardPage = new RewardPointsPage(driver);

            // Step 4: Validate Page URL
            String currentURL = driver.getCurrentUrl();
            logger.info("Current URL: " + currentURL);
            Assert.assertTrue(currentURL.contains("reward"), "URL does not contain 'reward'");

            // Step 5: Validate Page Title
            String pageTitle = rewardPage.getPageTitle();
            logger.info("Page Title: " + pageTitle);
            Assert.assertEquals(pageTitle, "Your Reward Points", "Page title mismatch");

            // Step 6: Validate Page Heading
            String pageHeading = rewardPage.getTotalRewardPointsText(); // or use custom heading element
            logger.info("Page Heading: " + pageHeading);
            Assert.assertTrue(pageHeading.contains("Your total number of reward points"), "Page heading mismatch");

            // Optional: Validate Table Columns if table exists
            boolean columnsDisplayed = rewardPage.areTableColumnsDisplayed();
            Assert.assertTrue(columnsDisplayed, "Reward Points table columns are not displayed");

            logger.info("Reward Points page validation completed successfully.");
        }
    }

