package testCases.TS_013_SpecialOffers;




import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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
            try {
                // 1. Navigate to Special Offers page
                specialOffersPage.clickSpecialsLink();

                // 2. Click on the 'Product Compare' link
                specialOffersPage.clickProductPage();  // Use the correct method name for clicking on the product compare link

                // 3. Verify that the user is redirected to the Product Comparison page
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("product-comparison"), "The user was not redirected to the Product Comparison page.");

                // Optionally, verify the title or other page elements to confirm the page is loaded correctly
                String pageTitle = driver.getTitle();
                Assert.assertTrue(pageTitle.contains("Product Comparison"), "The page title does not contain 'Product Comparison'.");

                logger.info("Product Compare link navigation validated successfully.");
            } catch (Exception e) {
                logger.error("Test failed due to exception: " + e.getMessage());
                Assert.fail("Test failed due to exception: " + e.getMessage());
            }
        }
    }




