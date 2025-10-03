package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewsletterPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public NewsletterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page title
    @FindBy(xpath = "//h1[normalize-space()='Newsletter Subscription']")
    private WebElement pageTitle;

    // Radio buttons
    @FindBy(css = "input[name='newsletter'][value='1']")
    private WebElement optNewsletterYes;

    @FindBy(css = "input[name='newsletter'][value='0']")
    private WebElement optNewsletterNo;

    // Continue button
    @FindBy(css = "input[type='submit'][value='Continue']")
    private WebElement btnContinue;

    // --- METHODS ---

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText().trim();
    }

    public boolean isYesOptionSelected() {
        wait.until(ExpectedConditions.visibilityOf(optNewsletterYes));
        return optNewsletterYes.isSelected();
    }

    public boolean isNoOptionSelected() {
        wait.until(ExpectedConditions.visibilityOf(optNewsletterNo));
        return optNewsletterNo.isSelected();
    }

    public void selectYesOption() {
        wait.until(ExpectedConditions.elementToBeClickable(optNewsletterYes));
        if (!optNewsletterYes.isSelected()) {
            optNewsletterYes.click();
        }
    }

    public void selectNoOption() {
        wait.until(ExpectedConditions.elementToBeClickable(optNewsletterNo));
        if (!optNewsletterNo.isSelected()) {
            optNewsletterNo.click();
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

    // Optional: verify page heading
    public String getPageHeading() {
        return getPageTitle();
    }


    // NewsletterPage.java

    // Click the Back button (if present on the page)
    @FindBy(css = "a.btn.btn-default")  // adjust selector if needed
    private WebElement btnBack;

    public void clickBackButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnBack)).click();
    }

    // Get the success message after updating newsletter preference
    @FindBy(css = "div.alert-success")
    private WebElement successMessage;

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText().trim();
    }

    // Verify if the selected option has been updated (Yes/No)
    public boolean isSelectedOptionUpdated(String expectedOption) {
        if (expectedOption.equalsIgnoreCase("Yes")) {
            return isYesOptionSelected();
        } else if (expectedOption.equalsIgnoreCase("No")) {
            return isNoOptionSelected();
        } else {
            throw new IllegalArgumentException("Invalid option: " + expectedOption);
        }
    }

    @FindBy(id = "newsletter-yes")
    private WebElement yesOption;

    @FindBy(id = "newsletter-no")
    private WebElement noOption;


}
