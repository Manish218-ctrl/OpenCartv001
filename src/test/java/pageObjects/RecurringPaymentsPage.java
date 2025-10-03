package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageObjects.Homepage.logger;

public class RecurringPaymentsPage extends BasePage {

        public RecurringPaymentsPage(WebDriver driver) {
            super(driver);
        }

        // Page heading
        @FindBy(xpath = "//h1[text()='Recurring Payments']")
        private WebElement headingRecurringPayments;

        // Example: Table of recurring payments (if exists)
        @FindBy(xpath = "/html/body/div[2]/div/div/p")
        private WebElement tblRecurringPayments;

        /**
         * Verify that the 'Recurring Payments' page is displayed
         */
        public boolean isRecurringPaymentsPageDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement heading = wait.until(ExpectedConditions.visibilityOf(headingRecurringPayments));
                return heading.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        /**
         * Verify that the recurring payments table exists
         */
        public boolean isRecurringPaymentsTableDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement table = wait.until(ExpectedConditions.visibilityOf(tblRecurringPayments));
                return table.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        @FindBy(xpath = "//a[normalize-space()='Continue']")
        private WebElement btnContinue;

        public void clickContinueButton() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
                continueBtn.click();
                System.out.println("Clicked on Continue button.");
            } catch (Exception e) {
                System.out.println("Continue button not found: " + e.getMessage());
            }
        }

        @FindBy(xpath = "/html/body/div[2]/ul/li[3]/a")
        public WebElement breadcrumbElement;







        // Method to check if the breadcrumb is displayed


        public boolean isBreadcrumbDisplayed() {
            try {
                // Wait for the breadcrumb to be visible
                wait.until(ExpectedConditions.visibilityOf(breadcrumbElement));
                return breadcrumbElement.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }


        // Method to get the breadcrumb text
        public String getBreadcrumbText() {
            try {
                // Wait for breadcrumb element to be visible and get its text
                wait.until(ExpectedConditions.visibilityOf(breadcrumbElement));
                return breadcrumbElement.getText();
            } catch (Exception e) {
                return "";  // Return empty string if breadcrumb text is not found
            }
        }



        // Page Title (already available from BasePage)
        public String getPageTitle() {
            return driver.getTitle();
        }



        public String getPageHeading() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(headingRecurringPayments));
                return headingRecurringPayments.getText().trim();  // Ensure that any extra whitespace is removed
            } catch (Exception e) {
                logger.error("Error getting page heading: " + e.getMessage());
                return "";  // Return empty string if heading is not found
            }
        }



    }



