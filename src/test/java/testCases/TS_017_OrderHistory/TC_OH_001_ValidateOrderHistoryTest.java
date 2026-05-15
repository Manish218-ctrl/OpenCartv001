package testCases.TS_017_OrderHistory;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OH_001_ValidateOrderHistoryTest extends BaseClass {

    HomePage home;

    LoginPage loginPage;

    OrderHistoryPage orderHistoryPage;

    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        home = new HomePage(getDriver());

        loginPage = new LoginPage(getDriver());

        orderHistoryPage = new OrderHistoryPage(getDriver());

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        logger.info(
                "Initializing test setup and performing login."
        );

        performLogin();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']//h2[text()='My Account']")
                )
        );

        logger.info(
                "Login successful."
        );
    }

    @Test(
            description = "Validate navigating to Order History page from My Account page"
    )
    public void validateNavigateToOrderHistoryPage() {

        home.clickLogo();

        home.enterSearchText("HP LP3065");

        home.clickSearchButton();

        home.clickaddtocart0();

        home.clickaddtocarthpbtn();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.alert-success")
                )
        );

        logger.info(
                "Clicking on My Account dropdown."
        );

        home.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        home.clickMyAccountFromDropdown();

        logger.info(
                "Clicking on Order History link."
        );

        home.clickOrderHistory();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']/h1")
                )
        );

        logger.info(
                "Waiting for the Order History page to load."
        );

        String actualTitle =
                orderHistoryPage.getTitle();

        String expectedTitle =
                "Order History";

        logger.info(
                "Asserting that the page title matches the expected title."
        );

        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Order History page title does not match."
        );

        logger.info(
                "Test completed successfully."
        );
    }
}