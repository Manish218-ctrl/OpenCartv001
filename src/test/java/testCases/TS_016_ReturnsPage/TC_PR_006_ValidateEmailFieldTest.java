package testCases.TS_016_ReturnsPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PR_006_ValidateEmailFieldTest extends BaseClass {

    // Logger for logging detailed info
    private static final Logger logger = LogManager.getLogger(TC_PR_006_ValidateEmailFieldTest.class);

    @Test
    public void validateEmailFieldTest() {
        try {
            logger.info("Test started: validateEmailFieldTest");

            // 1. Open the Application URL and Login
            logger.info("Opening the Application URL: https://tutorialsninja.com/demo/index.php?route=common/home");
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            performLogin(); // From BaseClass to log in

            // 2. Navigate to Order History page
            logger.info("Navigating to Order History page...");
            Homepage homepage = new Homepage(driver);
            homepage.clickMyAccount();
            homepage.clickOrderHistory();

            // 3. Click on View icon of any order
            logger.info("Clicking on the first order view icon...");
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.clickFirstOrderViewIcon();

            // 4. Click on Return icon on the Order Information page
            logger.info("Clicking on the Return icon for the selected order...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement returnIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/a[2]")));
            returnIcon.click();

            // 5. Enter invalid email addresses and check validation message
            String[] invalidEmails = {
                    "testauto@.com",
                    "automation@gmail",
                    "testautomation@gmail."
            };

            for (String email : invalidEmails) {
                logger.info("Testing with invalid email: " + email);
                WebElement emailField = driver.findElement(By.name("email"));
                emailField.clear();
                emailField.sendKeys(email);

                // 6. Click Submit button
                logger.info("Clicking the Submit button...");
                WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));  // Adjust XPath if needed
                submitButton.click();

                // 7. Validate error message for invalid email
                WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset[1]/div[3]/div/div"));
                if (errorMessage.isDisplayed()) {
                    logger.info("Error message displayed: " + errorMessage.getText());
                } else {
                    logger.error("Error message not displayed for email: " + email);
                }

                // Assert if the error message contains the expected text
                Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for invalid email: " + email);
                Assert.assertTrue(errorMessage.getText().contains("E-Mail Address does not appear to be valid!"), "Error message does not contain 'Invalid email address' for email: " + email);
            }

            logger.info("Test completed successfully.");

        } catch (Exception e) {
            logger.error("Test failed due to an exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}
