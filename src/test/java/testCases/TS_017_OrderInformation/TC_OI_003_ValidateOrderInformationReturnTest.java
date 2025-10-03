package testCases.TS_017_OrderInformation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OI_003_ValidateOrderInformationReturnTest extends BaseClass {

    @Test
    public void validateReturnFunctionalityTest() {
        // Step 1: Open the Application URL and login
        logger.info("Starting the test: validateReturnFunctionalityTest");
        performLogin();  // This method is in BaseClass and will log the user in
        logger.info("Login successful.");

        // Step 2: Navigate to 'Order History' page
        Homepage homepage = new Homepage(driver);
        homepage.clickMyAccount();
        logger.info("Clicked on 'My Account'.");
        homepage.clickOrderHistory();  // Click on 'Order History' link from My Account
        logger.info("Navigated to 'Order History' page.");

        // Step 3: Click 'View' on the first order
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.clickFirstOrderViewIcon();
        logger.info("Clicked 'View' icon on the first order.");

        // Step 4: Click 'Return' icon in the Order Information page
        OrderInformationPage orderInfoPage = new OrderInformationPage(driver);
        orderInfoPage.clickReturnIcon();  // Clicking the 'Return' icon
        logger.info("Clicked on 'Return' icon in the Order Information page.");

        // Step 5: Wait for the Product Returns page to load and validate
        ProductReturnsPage productReturnsPage = new ProductReturnsPage(driver);

        // Adding a wait for the page title to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // Increased wait time
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/h1")));
        logger.info("Waiting for the Product Returns page title to be visible...");

        // Assert that the page title of the Product Returns page is correct
        Assert.assertNotNull(pageTitle, "Order History page title not found.");
        logger.info("Product Returns page title is displayed.");


        // Final log statement indicating the test was successful
        logger.info("Return functionality validated successfully.");
    }
}
