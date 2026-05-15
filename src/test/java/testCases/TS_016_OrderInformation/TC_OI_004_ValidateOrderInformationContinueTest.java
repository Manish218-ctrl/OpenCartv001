package testCases.TS_016_OrderInformation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OI_004_ValidateOrderInformationContinueTest extends BaseClass {

    @Test
    public void validateContinueButtonFunctionalityTest() {
        logger.info("Starting the test: validateContinueButtonFunctionalityTest");

        // Initialize reusable objects
        HomePage homepage = new HomePage(getDriver());
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
        OrderInformationPage orderInfoPage = new OrderInformationPage(getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));


        //Login
        performLogin();
        logger.info("Login successful.");

        //Navigate to Order History page
        homepage.clickMyAccount();
        logger.info("Clicked on My Account.");
        homepage.clickOrderHistory();
        logger.info("Navigated to Order History page.");

        // Add wait to ensure Order History page is loaded before interacting with it
        wait.until(ExpectedConditions.visibilityOf(orderHistoryPage.getPageTitleElement()));

        //Click View on the first order
        orderHistoryPage.clickFirstOrderViewIcon();
        logger.info("Clicked View icon on the first order.");

        //Click Continue button in the Order Information page


        logger.info("Order Information page loaded and stable.");

        // Scroll down to ensure Continue button is visible
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // This click should now work reliably after the page load is confirmed and the element is in view
        orderInfoPage.clickContinueButton();
        logger.info("Clicked on Continue button in the Order Information page.");

        //Wait until Order History page is loaded (Correct wait for destination page)
        wait.until(ExpectedConditions.visibilityOf(orderHistoryPage.getPageTitleElement()));
        logger.info("Order History page loaded.");

        //Assert that the user is on Order History page
        String pageTitle = orderHistoryPage.getTitle();
        Assert.assertTrue(pageTitle.contains("Order History"), "Page title is incorrect. Expected Order History but got: " + pageTitle);
        logger.info("User redirected to Order History page. Page title: " + pageTitle);

        logger.info("Continue button functionality validated successfully.");
    }


}