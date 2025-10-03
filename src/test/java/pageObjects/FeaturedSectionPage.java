package pageObjects;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class FeaturedSectionPage extends BasePage {

        WebDriver driver;
        WebDriverWait wait;

        public FeaturedSectionPage(WebDriver driver) {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        // First Featured Product
        @FindBy(xpath = "(//div[@id='content']//div[contains(@class,'product-layout')])[1]")
        private WebElement firstFeaturedProduct;

        @FindBy(xpath = "(//div[@id='content']//div[contains(@class,'product-layout')])[1]//h4/a")
        private WebElement firstFeaturedProductName;

        @FindBy(xpath = "(//div[@id='content']//div[contains(@class,'product-layout')])[1]//button[@data-original-title='Compare this Product'] | " +
                "(//div[@id='content']//div[contains(@class,'product-layout')])[1]//button[contains(@aria-label,'Compare')]")
        private WebElement btnCompareFirstFeatured;

        @FindBy(xpath = "//div[contains(@class,'alert-success')]")
        private WebElement successMessage;

        @FindBy(xpath = "//div[contains(@class,'alert-success')]//a[contains(@href,'compare')]")
        private WebElement linkProductComparison;

        // --- Actions ---
        public String getFirstFeaturedProductName() {
            return wait.until(ExpectedConditions.visibilityOf(firstFeaturedProductName)).getText().trim();
        }

        public void hoverOnCompareButton() {
            wait.until(ExpectedConditions.visibilityOf(btnCompareFirstFeatured));
            new Actions(driver).moveToElement(btnCompareFirstFeatured).perform();
        }

        public boolean isCompareTooltipDisplayed() {
            try {
                String tooltipText = btnCompareFirstFeatured.getAttribute("data-original-title");
                return tooltipText != null && tooltipText.trim().equalsIgnoreCase("Compare this Product");
            } catch (Exception e) {
                return false;
            }
        }

        public void clickCompareButton() {
            wait.until(ExpectedConditions.elementToBeClickable(btnCompareFirstFeatured)).click();
        }

        public String getSuccessMessage() {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText().trim();
        }

        public void clickProductComparisonLink() {
            wait.until(ExpectedConditions.elementToBeClickable(linkProductComparison)).click();
        }
    }



