package testCases.TS_003_LogOutFunctionality;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.Set;

public class TC_LG_003_ValidateSessionPersistenceTest extends BaseClass {

    @Test(
            groups = {"Regression", "ScreenshotDemo"},
            description = "Intentional failure test to demonstrate screenshot capture functionality"
    )
    public void test_session_persistence_after_browser_close() {

        logger.info("TC_LG_003: Session Persistence Test (Intentional Failure)");
        logger.info("NOTE: This test is EXPECTED to FAIL to demonstrate screenshot functionality");

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

            logger.info("Logging in user...");

            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

            logger.info("Clicked My Account dropdown");

            hp.clickLogin();
            logger.info("Clicked Login link");

            LoginPage lp = new LoginPage(getDriver());
            lp.setEmail(p.getProperty("email"));
            logger.info("Entered Email: " + p.getProperty("email"));

            lp.setPassword(p.getProperty("password"));
            logger.info("Entered Password");

            lp.clickLogin();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='content']//h2[text()='My Account']")));

            logger.info("Clicked Login button");

            MyAccountPage macc = new MyAccountPage(getDriver());
            Assert.assertTrue(macc.isAt(), "Login failed - Not on My Account page");
            logger.info("User successfully logged in");

            logger.info("\nSaving session cookies...");
            Set<Cookie> cookies = getDriver().manage().getCookies();
            logger.info("Saved " + cookies.size() + " session cookies");

            for (Cookie cookie : cookies) {
                logger.debug("Cookie: " + cookie.getName() + " = " + cookie.getValue());
            }

            logger.info("\nClosing browser to simulate session termination...");
            String currentURL = getDriver().getCurrentUrl();
            logger.info("Current URL before closing: " + currentURL);

            getDriver().quit();
            logger.info("Browser closed - Session terminated");

            logger.info("Reopening browser (new session)...");

            initializeDriver(browserName);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            getDriver().manage().window().maximize();
            getDriver().get(appURL);

            logger.info("Browser reopened and navigated to: " + appURL);

            WebDriverWait reopenedWait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            reopenedWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            logger.info("Attempting to restore session cookies...");
            int restoredCount = 0;

            for (Cookie cookie : cookies) {
                try {
                    getDriver().manage().addCookie(cookie);
                    restoredCount++;
                } catch (Exception e) {
                    logger.warn("Could not restore cookie: " + cookie.getName() + " - " + e.getMessage());
                }
            }

            logger.info("Restored " + restoredCount + " out of " + cookies.size() + " cookies");

            getDriver().navigate().refresh();
            reopenedWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            logger.info("Page refreshed to apply restored cookies");

            logger.info("Verifying if session persisted...");

            HomePage hpAfterReopen = new HomePage(getDriver());
            hpAfterReopen.clickMyAccount();

            reopenedWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

            MyAccountPage maccAfterReopen = new MyAccountPage(getDriver());
            boolean isUserStillLoggedIn = maccAfterReopen.isUserLoggedIn();
            logger.info("Session persistence check result: " + isUserStillLoggedIn);

            Assert.assertTrue(isUserStillLoggedIn,
                    "EXPECTED FAILURE: User session was NOT maintained after closing and reopening the browser. " +
                            "This is normal behavior for OpenCart as server-side sessions are cleared on browser close.");

            logger.info("TC_LG_003 Passed (Unexpected - session persisted)");

        } catch (AssertionError ae) {
            logger.error("TC_LG_003 FAILED (Expected Behavior)");
            logger.error("Assertion Error: " + ae.getMessage());
            logger.error("Reason: OpenCart does not maintain sessions across browser restarts");
            logger.error("Screenshot has been captured and attached to the report");
            throw ae;

        } catch (Exception e) {
            logger.error("TC_LG_003 FAILED: Unexpected Exception");
            logger.error("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            logger.error("Screenshot captured for debugging");
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());

        } finally {
            logger.info("TC_LG_003 Execution Completed");
        }
    }
}