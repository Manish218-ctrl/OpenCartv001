package testCases.TS_012_SpecialOffers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ProductComparisonPage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class TC_SPO_006_ValidateSpecialOffersPageNavigationFromSpecialsTest extends BaseClass {

    private SpecialOffersPage specialOffersPage;

    @BeforeClass
    public void setup() {
        // Initialize the SpecialOffersPage page object
        specialOffersPage = new SpecialOffersPage(driver);
    }

    @Test
    public void validateProductCompareLinkNavigation() {
        logger.info("Starting TC_SPO_006_ValidateSpecialOffersPageNavigationFromSpecialsTest...");

        try {
            // 1. Navigate to Special Offers page (This is assumed to work fine)
            specialOffersPage.clickSpecialsLink();
            logger.info("Navigated to Special Offers page.");

            logger.info("Attempting robust click on 'Product Compare' link...");



            Thread.sleep(2800);



            ProductComparisonPage productComparisonpage=new ProductComparisonPage(driver);

            productComparisonpage.clickproductcomparelink();

           SpecialOffersPage specialoffers=new SpecialOffersPage(driver);

           specialoffers.clickproductcomparisonmsg();




            // Use JavaScript to click the element to bypass clickability issues
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", productCompareLink);

            logger.info("Clicked 'Product Compare' using JavaScript.");

            // 3. Verify that the user is redirected to the Product Comparison page

            // Wait for the URL to contain the destination
          //  wait.until(ExpectedConditions.urlContains("product-comparison"));

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("product/compare"), "The user was not redirected to the Product Comparison page. Current URL: " + currentUrl);

            // Optionally, verify the title or other page elements to confirm the page is loaded correctly
            String pageTitle = driver.getTitle();
            Assert.assertTrue(pageTitle.contains("Product Comparison"), "The page title does not contain 'Product Comparison'. Actual title: " + pageTitle);

            logger.info("Product Compare link navigation validated successfully.");
            logger.info("***** TC_SPO_006_ValidateSpecialOffersPageNavigationFromSpecialsTest Passed *****");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}