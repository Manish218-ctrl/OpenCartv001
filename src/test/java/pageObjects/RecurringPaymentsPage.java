package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecurringPaymentsPage extends BasePage {

    public RecurringPaymentsPage(WebDriver driver) {
        super(driver);
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h1[contains(normalize-space(),'Recurring Payments')]")
    private WebElement headingRecurringPayments;

    @FindBy(xpath = "//div[@id='content']//p[contains(normalize-space(),'No recurring payments found')]")
    private WebElement tblRecurringPayments;

    @FindBy(xpath = "//div[contains(@class,'buttons')]//a[contains(@class,'btn-primary') and normalize-space()='Continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]")
    public WebElement breadcrumbElement;

    // ACTION METHODS

    public boolean isRecurringPaymentsPageDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(headingRecurringPayments)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isRecurringPaymentsTableDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(tblRecurringPayments)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void clickContinueButton() {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(btnContinue)
            ).click();

            System.out.println("Clicked on Continue button.");

        } catch (Exception e) {

            System.out.println(
                    "Continue button not found: " + e.getMessage()
            );
        }
    }

    public boolean isBreadcrumbDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(breadcrumbElement)
            );

            return breadcrumbElement.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public String getBreadcrumbText() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(breadcrumbElement)
            );

            return breadcrumbElement.getText();

        } catch (Exception e) {

            return "";
        }
    }

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getPageHeading() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(
                            headingRecurringPayments
                    )
            );

            return headingRecurringPayments.getText().trim();

        } catch (Exception e) {

            logger.error(
                    "Error getting page heading: " + e.getMessage()
            );

            return "";
        }
    }
}