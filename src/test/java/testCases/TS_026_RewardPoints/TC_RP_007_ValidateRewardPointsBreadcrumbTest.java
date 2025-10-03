package testCases.TS_026_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.RewardPointsPage;
import testBase.BaseClass;

    public class TC_RP_007_ValidateRewardPointsBreadcrumbTest extends BaseClass {

        @Test
        public void validateRewardPointsBreadcrumb() {
            logger.info("Starting test: Validate breadcrumb on 'Your Reward Points' page.");

            // Step 1: Login
            performLogin();

            Homepage home = new Homepage(driver);

            // Step 2: Click on 'Reward Points' in right column
            home.clickRewardPoints();
            logger.info("Clicked on 'Your Reward Points' link.");

            RewardPointsPage rewardPage = new RewardPointsPage(driver);

            // Step 3: Validate breadcrumb
            String actualBreadcrumb = home.getBreadcrumb();  // Using the method in Homepage
            logger.info("Breadcrumb displayed: " + actualBreadcrumb);

            String expectedBreadcrumb = "Reward Points";  // Expected breadcrumb text
            Assert.assertEquals(actualBreadcrumb.trim(), expectedBreadcrumb,
                    "Breadcrumb is not displayed correctly on 'Your Reward Points' page.");

            logger.info("Breadcrumb validation successful.");

            // Optional: Validate reward points table or message
            if (rewardPage.txtTotalRewardPoints.isDisplayed()) {
                logger.info("Total reward points text: " + rewardPage.getTotalRewardPointsText());
            } else {
                logger.info("No reward points message: " + rewardPage.getNoRewardPointsMessage());
            }

            // Optional: Continue back to homepage
            rewardPage.clickContinueRewardPoints();
        }
    }


