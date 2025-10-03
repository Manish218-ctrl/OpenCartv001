package testCases.TS_017_OrderInformation;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OI_004_ValidateOrderInformationContinueTest extends BaseClass {

    @Test
    public void validateContinueButtonFunctionalityTest() {
        logger.info("Starting the test: validateContinueButtonFunctionalityTest");

        // Step 1: Login
        performLogin();
        logger.info("Login successful.");

        // Step 2: Navigate to 'Order History' page
        Homepage homepage = new Homepage(driver);
        homepage.clickMyAccount();
        logger.info("Clicked on 'My Account'.");
        homepage.clickOrderHistory();
        logger.info("Navigated to 'Order History' page.");

        // Step 3: Click 'View' on the first order
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.clickFirstOrderViewIcon();
        logger.info("Clicked 'View' icon on the first order.");

        // Step 4: Click 'Continue' button in the Order Information page
        OrderInformationPage orderInfoPage = new OrderInformationPage(driver);
        orderInfoPage.clickContinueButton();
        logger.info("Clicked on 'Continue' button in the Order Information page.");

        // Step 5: Wait until 'Order History' page is loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(orderHistoryPage.getPageTitleElement()));
        logger.info("Order History page loaded.");

        // Step 6: Assert that the user is on 'Order History' page
        String pageTitle = orderHistoryPage.getTitle();
        Assert.assertTrue(pageTitle.contains("Order History"), "Page title is incorrect. Expected 'Order History' but got: " + pageTitle);
        logger.info("User redirected to 'Order History' page. Page title: " + pageTitle);

        logger.info("Continue button functionality validated successfully.");
    }
}
