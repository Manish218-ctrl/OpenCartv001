package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RewardPointsPage extends BasePage {

    public RewardPointsPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//p[contains(normalize-space(),'Your total number of reward points is')]")
    public WebElement txtTotalRewardPoints;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered')]//tbody//td[contains(normalize-space(),'You do not have any reward points')]")
    public WebElement txtNoRewardPointsMessage;

    @FindBy(xpath = "//div[contains(@class,'buttons')]//a[contains(@class,'btn-primary') and normalize-space()='Continue']")
    public WebElement btnContinueRewardPoints;

    @FindBy(xpath = "//div[@id='content']//table[contains(@class,'table-bordered') and contains(@class,'table-hover')]//thead//td")
    public java.util.List<WebElement> tableColumns;

    // ACTION METHODS

    public String getTotalRewardPointsText() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtTotalRewardPoints
                )
        );

        return txtTotalRewardPoints.getText();
    }

    public String getNoRewardPointsMessage() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtNoRewardPointsMessage
                )
        );

        return txtNoRewardPointsMessage.getText();
    }

    public boolean areTableColumnsDisplayed() {

        for (WebElement col : tableColumns) {

            if (!col.isDisplayed()) {

                return false;
            }
        }

        return true;
    }

    public void clickContinueRewardPoints() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnContinueRewardPoints
                )
        ).click();
    }
}