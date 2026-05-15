package testCases.TS_025_RewardPoints;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_RP_002_ValidateNavigationToRewardPointsFromRightColumnTest extends BaseClass {

        @Test
        public void verifyNavigationToRewardPoints() {
            //Login to the application
            performLogin();

            //Click on Reward Points from Right Column options
            HomePage home = new HomePage(getDriver());
            home.clickRewardPoints();

            //Verify navigation to Your Reward Points page
            String expectedTitle = "Your Reward Points"; // adjust exact title if needed
            String actualTitle = home.getPageTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Navigation failed! Expected page title: " + expectedTitle + " but found: " + actualTitle);

            logger.info("TC_RP_002 passed: Navigated to Your Reward Points page successfully.");
        }
    }

