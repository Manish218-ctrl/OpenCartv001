package testCases.TS_025_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_RP_001_ValidateRewardPointsNavigationTest extends BaseClass {

        @Test
        public void validateRewardPointsNavigation() {
            //Login
            performLogin();

            //Click "Your Reward Points"
            HomePage home = new HomePage(getDriver());
            home.clickRewardPoints();

            //Validate navigation
            String expectedTitle = "Your Reward Points";  // Adjust based on actual page title
            String actualTitle = getDriver().getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Navigation to Your Reward Points page failed");
        }
    }

