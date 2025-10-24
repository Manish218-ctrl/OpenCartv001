
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

/**
 * TC_LG_003: Session Persistence Test (Intentional Failure Demo)
 *
 * Purpose: This test is designed to FAIL intentionally to demonstrate:
 * 1. Screenshot capture on test failure
 * 2. ExtentReports failure logging with screenshots
 * 3. Error handling and logging mechanisms
 *
 * Expected Behavior:
 * - Test will FAIL because OpenCart does not maintain session after browser restart
 * - Screenshot will be captured automatically on failure
 * - Error will be logged in ExtentReports with screenshot attachment
 *
 * Test Scenario:
 * 1. Login to application
 * 2. Save session cookies
 * 3. Close browser
 * 4. Reopen browser and restore cookies
 * 5. Verify if session persists (WILL FAIL - intended)
 *
 * @category Negative Testing / Screenshot Demo
 */
public class TC_LG_003_ValidateSessionPersistenceTest extends BaseClass {

    @Test(groups = {"Regression", "ScreenshotDemo"},
            description = "Intentional failure test to demonstrate screenshot capture functionality")
    public void test_session_persistence_after_browser_close() {

        logger.info("TC_LG_003: Session Persistence Test (Intentional Failure)");
        logger.info("NOTE: This test is EXPECTED to FAIL to demonstrate screenshot functionality");

        try {
            // Step 1: Login to Application
            logger.info("Step 1: Logging in user...");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked My Account dropdown");

            hp.clickLogin();
            logger.info("Clicked Login link");

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(rb.getString("email"));
            logger.info("Entered Email: " + rb.getString("email"));

            lp.setPassword(rb.getString("password"));
            logger.info("Entered Password");

            lp.clickLogin();
            logger.info("Clicked Login button");

            // Verify successful login
            MyAccountPage macc = new MyAccountPage(driver);
            Assert.assertTrue(macc.isAt(), "Login failed - Not on My Account page");
            logger.info("User successfully logged in");

            // Step 2: Save Session Cookies
            logger.info("\nStep 2: Saving session cookies...");
            Set<Cookie> cookies = driver.manage().getCookies();
            logger.info("Saved " + cookies.size() + " session cookies");

            // Log cookie details for debugging
            for (Cookie cookie : cookies) {
                logger.debug("Cookie: " + cookie.getName() + " = " + cookie.getValue());
            }

            // Step 3: Close Browser (Terminate Session)
            logger.info("\nStep 3: Closing browser to simulate session termination...");
            String currentURL = driver.getCurrentUrl();
            logger.info("Current URL before closing: " + currentURL);

            driver.quit();
            driver = null;
            logger.info("Browser closed - Session terminated");

            // Add delay to ensure clean termination
            Thread.sleep(2000);

            // Step 4: Reopen Browser (New Session)
            logger.info("Step 4: Reopening browser (new session)...");
            initializeDriver(browserName, rb.getString("appURL"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(rb.getString("appURL"));
            logger.info("Browser reopened and navigated to: " + rb.getString("appURL"));

            // Step 5: Attempt to Restore Session Cookies
            logger.info("Step 5: Attempting to restore session cookies...");
            int restoredCount = 0;
            for (Cookie cookie : cookies) {
                try {
                    driver.manage().addCookie(cookie);
                    restoredCount++;
                } catch (Exception e) {
                    logger.warn("Could not restore cookie: " + cookie.getName() + " - " + e.getMessage());
                }
            }
            logger.info("Restored " + restoredCount + " out of " + cookies.size() + " cookies");

            // Refresh to apply cookies
            driver.navigate().refresh();
            logger.info("Page refreshed to apply restored cookies");

            Thread.sleep(2000); // Wait for page to load

            // Step 6: Verify Session Persistence (EXPECTED TO FAIL)
            logger.info("Step 6: Verifying if session persisted...");
            HomePage hpAfterReopen = new HomePage(driver);
            hpAfterReopen.clickMyAccount();

            MyAccountPage maccAfterReopen = new MyAccountPage(driver);
            boolean isUserStillLoggedIn = maccAfterReopen.isUserLoggedIn();

            logger.info("Session persistence check result: " + isUserStillLoggedIn);

            Assert.assertTrue(isUserStillLoggedIn,
                    "EXPECTED FAILURE: User session was NOT maintained after closing and reopening the browser. " +
                            "This is normal behavior for OpenCart as server-side sessions are cleared on browser close.");

            logger.info("TC_LG_003 Passed (Unexpected - session persisted)");

        } catch (AssertionError ae) {
            // Expected failure path - log detailed information
            logger.error("TC_LG_003 FAILED (Expected Behavior)");
            logger.error("Assertion Error: " + ae.getMessage());
            logger.error("Reason: OpenCart does not maintain sessions across browser restarts");
            logger.error("Screenshot has been captured and attached to the report");

            // Re-throw to mark test as failed
            throw ae;

        } catch (Exception e) {
            // Unexpected exception
            logger.error("TC_LG_003 FAILED: Unexpected Exception");
            logger.error("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            logger.error("Screenshot captured for debugging");
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());

        } finally {
            logger.info("TC_LG_003 Execution Completed");
        }
    }
}
