package testCases.TS_023_MyAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_MA_003_ValidateNavigateToMyAccountTest extends BaseClass {

    @Test(description = "Validate navigating to My Account page using My Account option")
    public void verifyNavigateToMyAccount() {
        performLogin();

        HomePage home = new HomePage(getDriver());

        home.clickMyAccount();
        logger.info("Clicked My Account menu");

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));

        home.clickMyAccountFromDropdown();
        logger.info("Clicked My Account from dropdown");

        MyAccountPage myAcc = new MyAccountPage(getDriver());
        boolean atMyAccount = myAcc.isAt();

        Assert.assertTrue(atMyAccount,
                "User should be navigated to My Account page but was not. Breadcrumb: "
                        + myAcc.getBreadcrumbText());
    }
}