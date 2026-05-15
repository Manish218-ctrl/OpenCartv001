package testCases.TS_025_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RewardPointsPage;
import testBase.BaseClass;

    public class TC_RP_005_ValidateContinueButtonOnRewardPointsPageTest extends BaseClass {

        @Test(priority = 1)
        public void validateContinueButtonOnRewardPointsPage() {

            //Login to the application
            performLogin();

            HomePage home = new HomePage(getDriver());

            //Navigate to Your Reward Points from Right Column
            home.clickRewardPoints();

            RewardPointsPage rewardPointsPage = new RewardPointsPage(getDriver());

            try {
                String pointsText = rewardPointsPage.getTotalRewardPointsText();
                logger.info("Total Reward Points Text: " + pointsText);
            } catch (Exception e) {
                String noPointsMsg = rewardPointsPage.getNoRewardPointsMessage();
                logger.info("No Reward Points Message: " + noPointsMsg);
            }

            //Click Continue button
            rewardPointsPage.clickContinueRewardPoints();

            //Verify user is redirected to My Account page
            String actualTitle = getDriver().getTitle();
            String expectedTitle = "My Account";

            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "User is not navigated to My Account page. Current page title: " + actualTitle);

            logger.info("Test Case TC_RP_005 Passed: User navigated to My Account after clicking Continue.");
        }
    }

