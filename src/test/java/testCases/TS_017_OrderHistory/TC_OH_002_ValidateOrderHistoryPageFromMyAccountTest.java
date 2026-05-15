package testCases.TS_017_OrderHistory;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_OH_002_ValidateOrderHistoryPageFromMyAccountTest extends BaseClass {

    private HomePage homepage;
    private OrderHistoryPage orderHistoryPage;

    private WebDriverWait wait;

    @BeforeClass
    public void setup() {

        homepage = new HomePage(getDriver());

        orderHistoryPage =
                new OrderHistoryPage(getDriver());

        wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        performLogin();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']//h2[text()='My Account']")
                )
        );

        logger.info(
                "Logged in successfully."
        );
    }

    @Test(priority = 1)
    public void verifyOrderHistoryPageTitle() {

        homepage.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        homepage.clickOrderHistory();

        logger.info(
                "Navigated to Order History Page."
        );

        String pageTitle =
                orderHistoryPage.getTitle();

        Assert.assertEquals(
                pageTitle,
                "Order History",
                "Order History Page title is incorrect."
        );

        logger.info(
                "Order History Page title is validated."
        );
    }

    @Test(priority = 2)
    public void verifyAllOrdersDisplayed() {

        homepage.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        homepage.clickOrderHistory();

        logger.info(
                "Navigated to Order History Page."
        );

        boolean isFirstOrderVisible =
                orderHistoryPage.isFirstOrderViewIconVisible();

        Assert.assertTrue(
                isFirstOrderVisible,
                "Order History is empty or first order is not visible."
        );

        logger.info(
                "First orders View icon visibility checked."
        );
    }

    @Test(priority = 3)
    public void verifyOrderDetails() {

        homepage.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        homepage.clickOrderHistory();

        logger.info(
                "Navigated to Order History Page."
        );

        orderHistoryPage.clickFirstOrderViewIcon();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']/h1")
                )
        );

        String actualOrderId =
                orderHistoryPage.getOrderId();

        logger.info(
                "Actual Order ID fetched: "
                        + actualOrderId
        );

        String expectedOrderId =
                "Order ID:";

        Assert.assertEquals(
                actualOrderId,
                expectedOrderId,
                "Order ID does not match."
        );

        logger.info(
                "Order ID validation successful: "
                        + actualOrderId
        );
    }
}