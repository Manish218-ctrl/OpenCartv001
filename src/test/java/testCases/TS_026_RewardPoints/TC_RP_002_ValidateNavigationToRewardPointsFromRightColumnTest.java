package testCases.TS_026_RewardPoints;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_RP_002_ValidateNavigationToRewardPointsFromRightColumnTest extends BaseClass {

        @Test
        public void verifyNavigationToRewardPoints() {
            // Step 1: Login to the application
            performLogin();

            // Step 2: Click on 'Reward Points' from Right Column options
            Homepage home = new Homepage(driver);
            home.clickRewardPoints();

            // Step 3: Verify navigation to 'Your Reward Points' page
            String expectedTitle = "Your Reward Points"; // adjust exact title if needed
            String actualTitle = home.getPageTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Navigation failed! Expected page title: " + expectedTitle + " but found: " + actualTitle);

            logger.info("TC_RP_002 passed: Navigated to 'Your Reward Points' page successfully.");
        }
    }

