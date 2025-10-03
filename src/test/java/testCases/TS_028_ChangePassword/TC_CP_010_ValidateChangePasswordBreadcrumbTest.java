package testCases.TS_028_ChangePassword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

import java.util.List;

    public class TC_CP_010_ValidateChangePasswordBreadcrumbTest extends BaseClass {

        @Test
        public void validateBreadcrumb() {
            logger.info("===== Starting Test: TC_CP_010 – Change Password Breadcrumb =====");

            // Step 1: Login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to Change Password page
            ChangePasswordPage cpPage = new ChangePasswordPage(driver);
            driver.get(appURL + "/index.php?route=account/password"); // Adjust URL if needed
            logger.info("Navigated to Change Password page.");

            // Step 3: Locate breadcrumb container
            WebElement breadcrumbContainer = driver.findElement(By.cssSelector(".breadcrumb")); // Adjust CSS selector if needed
            Assert.assertTrue(breadcrumbContainer.isDisplayed(), "Breadcrumb is not displayed on Change Password page!");
            logger.info("Breadcrumb is displayed.");

            // Step 4: Validate breadcrumb links (optional)
            List<WebElement> breadcrumbLinks = breadcrumbContainer.findElements(By.tagName("a"));
            logger.info("Breadcrumb links found: " + breadcrumbLinks.size());

            for (WebElement link : breadcrumbLinks) {
                String linkText = link.getText();
                String href = link.getAttribute("href");
                logger.info("Breadcrumb link: " + linkText + " -> " + href);

                // Optionally, validate link navigates properly
                // Uncomment to check navigation (extra validation)
                // link.click();
                // Assert.assertTrue(driver.getCurrentUrl().contains("expected-page-url"));
                // driver.navigate().back();
            }

            logger.info("===== Test Completed Successfully – Breadcrumb validated =====");
        }
    }

