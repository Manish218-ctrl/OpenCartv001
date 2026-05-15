package testCases.TS_015_ReturnsPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TC_PR_007_ValidateOrderDateFieldTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC_PR_007_ValidateOrderDateFieldTest.class);

    @Test
    public void validateOrderDateFieldTest() {
        try {
            logger.info("===== TC_PR_007: Validate Order Date field in Product Returns Page =====");

            //Navigate to homepage and login
            getDriver().get(appURL);
            logger.info("Navigated to Application URL: " + appURL);
            performLogin();
            logger.info("Login successful for user: " + username);

            //Navigate to Order History page
            HomePage homepage = new HomePage(getDriver());
            homepage.clickMyAccount();
            homepage.clickOrderHistory();
            logger.info("Navigated to Order History page");

            //Click on View icon for the first order
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
            orderHistoryPage.clickFirstOrderViewIcon();
            logger.info("Clicked View for the first order");

            //Click on Return icon
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
            WebElement returnIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,return) and contains(@data-original-title,Return)]")
            ));
            returnIcon.click();
            logger.info("Clicked Return icon");

            //Enter future date in Order Date field
            ProductReturnsPage productReturnsPage = new ProductReturnsPage(getDriver());
            String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            productReturnsPage.orderDateField.clear();
            productReturnsPage.orderDateField.sendKeys(futureDate);
            logger.info("Entered future date in Order Date field: " + futureDate);

            //Submit the Return form
            productReturnsPage.clickSubmit();
            logger.info("Clicked Submit button on Product Returns page");

            //Wait for and validate error message
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.text-danger, span.text-danger")
            ));

            String actualError = errorMessage.getText().trim();
            Assert.assertTrue(actualError.toLowerCase().contains("future"),
                    "Error message does not mention future. Actual: " + actualError);

            logger.info("Error message displayed correctly: " + actualError);
            logger.info("===== TC_PR_007 completed successfully =====");

        } catch (Exception e) {
            logger.error("TC_PR_007 failed due to an exception: " + e.getMessage(), e);
            Assert.fail("Test TC_PR_007 failed due to exception: " + e.getMessage());
        }
    }
}
