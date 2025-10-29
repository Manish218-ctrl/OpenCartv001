package testCases.TS_012_SpecialOffers;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ProductComparisonPage;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;


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
            specialOffersPage.clickSpecialsLink();
            logger.info("Navigated to Special Offers page.");

            logger.info("Attempting robust click on 'Product Compare' link...");

            Thread.sleep(2800);

            ProductComparisonPage productComparisonpage=new ProductComparisonPage(driver);

            productComparisonpage.clickproductcomparelink();

           SpecialOffersPage specialoffers=new SpecialOffersPage(driver);

           specialoffers.clickproductcomparisonmsg();


            logger.info("Clicked 'Product Compare' using JavaScript.");

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("product/compare"), "The user was not redirected to the Product Comparison page. Current URL: " + currentUrl);

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