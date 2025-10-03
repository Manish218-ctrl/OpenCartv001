package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

    public class RewardPointsPage extends BasePage {

        public RewardPointsPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//div[@id='content']//p[contains(text(),'Your total number of reward points is:')]")
        public WebElement txtTotalRewardPoints;

        @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/table/tbody/tr/td")
        public WebElement txtNoRewardPointsMessage;

        // Validate total reward points text
        public String getTotalRewardPointsText() {
            wait.until(ExpectedConditions.visibilityOf(txtTotalRewardPoints));
            return txtTotalRewardPoints.getText();
        }

        // Validate "no reward points" message
        public String getNoRewardPointsMessage() {
            wait.until(ExpectedConditions.visibilityOf(txtNoRewardPointsMessage));
            return txtNoRewardPointsMessage.getText();
        }

        // Verify table columns are displayed
        @FindBy(xpath = "//table[@class='table table-bordered table-hover']//th")
        public java.util.List<WebElement> tableColumns;

        public boolean areTableColumnsDisplayed() {
            for (WebElement col : tableColumns) {
                if (!col.isDisplayed()) {
                    return false;
                }
            }
            return true;
        }

        @FindBy(xpath = "//a[normalize-space()='Continue']") // Adjust XPath
        public WebElement btnContinueRewardPoints;

        public void clickContinueRewardPoints() {
            wait.until(ExpectedConditions.elementToBeClickable(btnContinueRewardPoints)).click();
        }

    }

