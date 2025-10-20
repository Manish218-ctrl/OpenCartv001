package testCases.TS_003_LogOutFunctionality;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.Set;

public class TC_LG_003_ValidateSessionPersistenceTest extends BaseClass {

    @Test(groups = {"sanity", "regression"})
    public void test_session_persistence_after_browser_close() {
        logger.info("Starting TC_LG_003 Session Persistence Test after closing browser.");

        try {
            // Step 1: Login user
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account dropdown.");
            hp.clickLogin();
            logger.info("Clicked on Login link.");

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(rb.getString("email"));
            logger.info("Provided Email Address: " + rb.getString("email"));
            lp.setPassword(rb.getString("password"));
            logger.info("Provided Password");
            lp.clickLogin();
            logger.info("Clicked on Login button.");

            MyAccountPage macc = new MyAccountPage(driver);
            Assert.assertTrue(macc.isAt(), "Not on My Account page after login.");

            logger.info("User successfully logged in.");

            // Step 2: Save cookies before quitting
            Set<Cookie> cookies = driver.manage().getCookies();
            logger.info("Saved session cookies: " + cookies.size());

            // Step 3: Quit browser
            logger.info("Closing the browser without logging out...");
            driver.quit();
            driver = null;
            logger.info("Browser closed.");

            // Step 4: Relaunch browser
            logger.info("Opening the browser again...");
            initializeDriver(browserName, rb.getString("appURL"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(rb.getString("appURL"));
            logger.info("Navigated to the application URL again in new session.");

            // Step 5: Restore cookies
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }
            logger.info("Restored cookies into the new browser session.");
            driver.navigate().refresh(); // refresh to apply cookies

            // Step 6: Verify session persistence
            HomePage hpAfterReopen = new HomePage(driver);
            hpAfterReopen.clickMyAccount();

            MyAccountPage maccAfterReopen = new MyAccountPage(driver);
            boolean isUserStillLoggedIn = maccAfterReopen.isUserLoggedIn();

            Assert.assertTrue(isUserStillLoggedIn,
                    "User session was NOT maintained after closing and reopening the browser.");
            logger.info("Verified: User logged-in session is maintained after closing and reopening the browser.");

            logger.info("TC_LG_003 Session Persistence Test Passed.");

        } catch (Exception e) {
            logger.error("TC_LG_003 Session Persistence Test Failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
