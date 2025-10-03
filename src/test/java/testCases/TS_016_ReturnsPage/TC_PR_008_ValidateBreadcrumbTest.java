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
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

import java.time.Duration;

    public class TC_PR_008_ValidateBreadcrumbTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_PR_008_ValidateBreadcrumbTest.class);

        @Test
        public void validateBreadcrumbTest() {
            try {
                logger.info("Test Started: Validate the Breadcrumb in the 'Product Returns' page");

                // 1. Open the Application URL and Login
                driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
                performLogin(); // From BaseClass to log in

                // 2. Navigate to Order History page
                logger.info("Navigating to Order History page...");
                Homepage homepage = new Homepage(driver);
                homepage.clickMyAccount();
                homepage.clickOrderHistory();

                // 3. Click on View icon of any order
                logger.info("Clicking on the View icon for the first order...");
                OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
                orderHistoryPage.clickFirstOrderViewIcon();

                // 4. Click on Return icon on the Order Information page
                logger.info("Clicking on the Return icon...");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement returnIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/a[2]")));
                returnIcon.click();

                // 5. Validate the Breadcrumb
                logger.info("Validating Breadcrumb...");
                ProductReturnsPage productReturnsPage = new ProductReturnsPage(driver);
                WebElement breadcrumb = productReturnsPage.breadcrumbElement;

                // Verify if Breadcrumb is displayed
                Assert.assertTrue(breadcrumb.isDisplayed(), "Breadcrumb is not displayed.");

                // Verify that the breadcrumb text is correct
                String breadcrumbText = breadcrumb.getText();
                Assert.assertTrue(breadcrumbText.contains("Product Returns"), "Breadcrumb text is incorrect. Expected text: 'Product Returns', but got: " + breadcrumbText);

                // Optionally, verify if clicking on the breadcrumb leads to the correct page
                breadcrumb.click();
                String currentPageTitle = driver.getTitle();
                Assert.assertEquals(currentPageTitle, "Product Returns", "Breadcrumb click did not navigate to the correct page.");

                logger.info("Breadcrumb validated successfully.");

            } catch (Exception e) {
                logger.error("Test failed due to an exception: " + e.getMessage(), e);
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            }
        }
    }



