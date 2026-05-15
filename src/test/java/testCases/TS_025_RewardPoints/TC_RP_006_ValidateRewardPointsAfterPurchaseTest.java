package testCases.TS_025_RewardPoints;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.RewardPointsPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_RP_006_ValidateRewardPointsAfterPurchaseTest extends BaseClass {

    @Test
    public void verifyRewardPointsAfterPurchase() {

        logger.info(
                "==== Starting Test: TC_RP_006 - Reward Points ===="
        );

        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        CheckoutPage checkout =
                new CheckoutPage(getDriver());

        performLogin();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']//h2[text()='My Account']")
                )
        );

        logger.info(
                "User logged in successfully"
        );

        HomePage home =
                new HomePage(getDriver());

        home.enterSearchText(productName);

        home.clickSearchButton();

        home.addProductToCart(productName);

        home.clickaddtocart();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.alert-success")
                )
        );

        home.clickshoppingcartbtnmsg();

        home.clickcheckoutfromcart();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']/h1")
                )
        );

        logger.info(
                "Navigated to checkout page"
        );

        checkout.continueBillingDetails();

        checkout.continueDeliveryDetails();

        checkout.continueDeliveryMethod();

        checkout.acceptTermsAndConditions();

        checkout.continuePaymentMethod();

        checkout.confirmOrder();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']/h1")
                )
        );

        logger.info(
                "Order confirmed successfully"
        );

        checkout.clickOrderSuccessContinueButton();

        home.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        home.clickmyaccdpdwn();

        home.clickRewardPoints();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']/h1")
                )
        );

        RewardPointsPage rewardPage =
                new RewardPointsPage(getDriver());

        boolean areColumnsVisible =
                rewardPage.areTableColumnsDisplayed();

        Assert.assertTrue(
                areColumnsVisible,
                "Reward Points table columns are not displayed correctly"
        );

        String rewardText =
                rewardPage.getTotalRewardPointsText();

        Assert.assertTrue(
                rewardText.contains(
                        "Your total number of reward points is"
                ),
                "Total reward points text not displayed"
        );

        rewardPage.clickContinueRewardPoints();

        logger.info(
                "==== Test TC_RP_006 Completed Successfully ===="
        );
    }
}