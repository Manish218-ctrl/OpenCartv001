package testCases.TS_026_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;
import pageObjects.Homepage;

    public class TC_RP_001_ValidateRewardPointsNavigationTest extends BaseClass {

        @Test
        public void validateRewardPointsNavigation() {
            // Step 1: Login
            performLogin();

            // Step 2: Click "Your Reward Points"
            Homepage home = new Homepage(driver);
            home.clickRewardPoints();

            // Step 3: Validate navigation
            String expectedTitle = "Your Reward Points";  // Adjust based on actual page title
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Navigation to 'Your Reward Points' page failed");
        }
    }

