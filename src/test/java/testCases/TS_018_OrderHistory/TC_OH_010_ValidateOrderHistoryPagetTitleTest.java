package testCases.TS_018_OrderHistory;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

    public class TC_OH_010_ValidateOrderHistoryPagetTitleTest extends BaseClass {

        @Test
        public void validateOrderHistoryPage() {
            // Initialize homepage and login
            Homepage home = new Homepage(driver);
            home.clickMyAccount();
            home.clickLogin();

            // Perform login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            logger.info("Login successful.");

            // Navigate to My Account Page
            MyAccountPage myAccount = new MyAccountPage(driver);
            myAccount.clickOrderHistory();
            logger.info("Clicked on Order History link.");

            // Initialize OrderHistoryPage object
            OrderHistoryPage orderHistory = new OrderHistoryPage(driver);

            // Validate Page Title
            String expectedPageTitle = "Order History";
            String actualPageTitle = orderHistory.getTitle();
            logger.info("Actual Page Title: " + actualPageTitle);
            Assert.assertEquals(actualPageTitle, expectedPageTitle, "Page Title mismatch.");

            // Validate URL
            String currentURL = driver.getCurrentUrl();
            String expectedURL = rb.getString("appURL") + "index.php?route=account/order"; // Adjust if needed
            logger.info("Actual Page URL: " + currentURL);
            Assert.assertEquals(currentURL, expectedURL, "Page URL mismatch.");

            // Optional: Validate Page Heading (if different from title)
            // WebElement pageHeading = driver.findElement(By.xpath("//h1[text()='Order History']"));
            // Assert.assertTrue(pageHeading.isDisplayed(), "Page Heading not displayed.");

            logger.info("Order History page validated successfully.");
        }
    }



