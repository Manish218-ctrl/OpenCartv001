package testCases.TS_026_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.RewardPointsPage;
import testBase.BaseClass;

    public class TC_RP_004_ValidateRewardPointsPageWithNoPointsTest extends BaseClass {

        @Test
        public void validateRewardPointsPageWithNoPoints() {
            logger.info("====== TC_RP_004: Start Test ======");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            Homepage home = new Homepage(driver);

            // Step 2: Click on 'Reward Points'
            home.clickRewardPoints();
            logger.info("Clicked on 'Reward Points' link.");

            // Step 3: Initialize Reward Points Page
            RewardPointsPage rewardPage = new RewardPointsPage(driver);

            // Step 4: Validate total points = 0
            String expectedPointsText = "Your total number of reward points is: 0.";
            Assert.assertEquals(rewardPage.getTotalRewardPointsText(), expectedPointsText,
                    "Total reward points text mismatch.");
            logger.info("Total reward points text validated.");

            // Step 5: Validate table columns are displayed
            Assert.assertTrue(rewardPage.areTableColumnsDisplayed(), "Reward points table columns are missing.");
            logger.info("Reward points table columns validated.");

            // Step 6: Validate 'no reward points' message
            String expectedNoPointsMessage = "You do not have any reward points!";
            Assert.assertEquals(rewardPage.getNoRewardPointsMessage(), expectedNoPointsMessage,
                    "No reward points message mismatch.");
            logger.info("'No reward points' message validated.");

            logger.info("====== TC_RP_004: Test Completed Successfully ======");
        }
    }

