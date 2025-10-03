package testCases.TS_026_RewardPoints;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_RP_003_ValidateRewardPointsNavigationBeforeLoginTest extends BaseClass {

    @Test
    public void validateRewardPointsNavigationBeforeLogin() {
        logger.info("Starting TC_RP_003: Validate Reward Points Navigation Before Login");

        // 1. Initialize Homepage
        Homepage home = new Homepage(driver);

        // 2. Click on 'My Account' drop-down
        home.clickMyAccount();
        logger.info("Clicked on 'My Account'");

        // 3. Select 'Register' option (to go to Register page)
        home.clickRegister();
        logger.info("Clicked on 'Register'");

        // 4. Click 'Your Reward Points' from Right Column on the Register page
        home.clickRewardPoints();
        logger.info("Clicked on 'Your Reward Points' link from Right Column");

        // 5. Verify that user is redirected to Login page
        String loginPageTitle = driver.getTitle();
        Assert.assertTrue(loginPageTitle.contains("Account Login") || loginPageTitle.contains("Login"),
                "User is NOT on Login page after clicking 'Reward Points'");
        logger.info("User is on Login page as expected");

        // 6. Perform Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        logger.info("User logged in successfully");

        home.clickRewardPoints();



        // 7. Verify that 'Your Reward Points' page is displayed
        String rewardPageBreadcrumb = home.getBreadcrumb();
        Assert.assertTrue(rewardPageBreadcrumb.contains("Reward Points"),
                "User is NOT on 'Your Reward Points' page after login");
        logger.info("User is on 'Your Reward Points' page successfully");
    }
}
