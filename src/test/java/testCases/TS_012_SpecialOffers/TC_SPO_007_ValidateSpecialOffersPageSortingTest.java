package testCases.TS_012_SpecialOffers;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

import java.util.List;

public class TC_SPO_007_ValidateSpecialOffersPageSortingTest extends BaseClass {

    SpecialOffersPage specialOffersPage;

    @BeforeClass
    public void setup() {
        specialOffersPage = new SpecialOffersPage(driver);
    }

    @Test
    public void validateSortingOfSpecialOffers() {
        logger.info("***** Starting TC_SPO_007_ValidateSpecialOffersPageSortingTest *****");
        try {
            // Step 1: Navigate to 'Specials' page
            logger.info("Navigating to Special Offers page.");
            specialOffersPage.clickSpecialsLink();

            // Step 2: Select an option from the 'Sort By' field
            String sortOption = "Price (Low > High)";
            logger.info("Attempting to sort by: " + sortOption);
            specialOffersPage.selectSortByOption(sortOption);

            // Step 3: Get the list of product titles after sorting
            List<WebElement> productTitles = specialOffersPage.getProductTitles();
            logger.info("Retrieved " + productTitles.size() + " product titles.");

            // Step 4: Validate if products are sorted correctly (based on the sorting option)
            boolean isSorted = specialOffersPage.areProductsSorted(productTitles);
            Assert.assertTrue(isSorted, "The products are not sorted correctly after applying the 'Sort By' option: " + sortOption);

            logger.info("Products successfully validated as sorted by: " + sortOption);
            logger.info("***** TC_SPO_007_ValidateSpecialOffersPageSortingTest Passed *****");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}