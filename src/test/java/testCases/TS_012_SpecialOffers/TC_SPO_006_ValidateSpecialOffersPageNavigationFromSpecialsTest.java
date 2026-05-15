package testCases.TS_012_SpecialOffers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.ProductComparisonPage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_SPO_006_ValidateSpecialOffersPageNavigationFromSpecialsTest extends BaseClass {

    private SpecialOffersPage specialOffersPage;

    private WebDriverWait wait;

    @BeforeClass
    public void setup() {

        specialOffersPage =
                new SpecialOffersPage(getDriver());

        wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(20));
    }

    @Test
    public void validateProductCompareLinkNavigation() {

        logger.info(
                "Starting TC_SPO_006_ValidateSpecialOffersPageNavigationFromSpecialsTest..."
        );

        try {

            specialOffersPage.clickSpecialsLink();

            logger.info(
                    "Navigated to Special Offers page."
            );

            logger.info(
                    "Attempting robust click on Product Compare link..."
            );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'alert-success')]")
                    )
            );

            ProductComparisonPage productComparisonpage =
                    new ProductComparisonPage(getDriver());

            productComparisonpage.clickProductCompareLink();

            SpecialOffersPage specialoffers =
                    new SpecialOffersPage(getDriver());

            specialoffers.clickproductcomparisonmsg();

            logger.info(
                    "Clicked Product Compare using JavaScript."
            );

            String currentUrl =
                    getDriver().getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("product/compare"),
                    "The user was not redirected to the Product Comparison page. Current URL: "
                            + currentUrl
            );

            String pageTitle =
                    getDriver().getTitle();

            Assert.assertTrue(
                    pageTitle.contains("Product Comparison"),
                    "The page title does not contain Product Comparison. Actual title: "
                            + pageTitle
            );

            logger.info(
                    "Product Compare link navigation validated successfully."
            );

            logger.info(
                    "TC_SPO_006_ValidateSpecialOffersPageNavigationFromSpecialsTest Passed"
            );

        } catch (Exception e) {

            logger.error(
                    "Test failed due to exception: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}