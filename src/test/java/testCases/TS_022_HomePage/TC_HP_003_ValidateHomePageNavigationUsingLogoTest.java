package testCases.TS_022_HomePage;




import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_HP_003_ValidateHomePageNavigationUsingLogoTest extends BaseClass {

        @Test
        public void verifyHomePageNavigationUsingLogo() {
            try {
                logger.info("********** Starting TC_HP_003_ValidateHomeNavigation **********");

                Homepage home = new Homepage(driver);

                // Step 1: Navigate to another page (e.g., Login Page)
                home.clickMyAccount();
                home.clickLogin();
                logger.info("Navigated to Login Page.");

                // Capture the title of Login Page
                String loginPageTitle = driver.getTitle();
                logger.info("Login Page Title: " + loginPageTitle);

                // Step 2: Click on Logo "Your Store" (Assuming Logo is present in Homepage.java)
                // You need to define Logo locator in Homepage.java if not already done
                home.clickLogo();
                logger.info("Clicked on 'Your Store' Logo.");

                // Step 3: Validate navigation to Home Page
                String expectedTitle = "Your Store";  // Update if your homepage has a different title
                String actualTitle = home.getPageTitle();

                Assert.assertEquals(actualTitle, expectedTitle, "User is not navigated to Home Page!");

                logger.info("Successfully navigated to Home Page. Test Passed.");
            }
            catch (Exception e) {
                logger.error("Test Failed due to exception: " + e.getMessage());
                Assert.fail("Test Failed due to exception: " + e.getMessage());
            }
        }
    }

