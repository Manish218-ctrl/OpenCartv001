package testCases.TS_013_SpecialOffers;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;

import java.util.List;

    public class TC_SPO_007_ValidateSpecialOffersPageSortingTest extends BaseClass {

        WebDriver driver;
        SpecialOffersPage specialOffersPage;

        @BeforeClass
        public void setup() {
            driver = this.driver;  // Inherit the driver from BaseClass
            specialOffersPage = new SpecialOffersPage(driver);
        }

        @Test
        public void validateSortingOfSpecialOffers() {
            // Step 1: Navigate to 'Specials' page
            specialOffersPage.clickSpecialsLink(); // Assuming the Specials page is already open or navigated through a link

            // Step 2: Select an option from the 'Sort By' field
            String sortOption = "Price (Low > High)";  // You can change this based on your test scenario
            specialOffersPage.selectSortByOption(sortOption);

            // Step 3: Get the list of product titles after sorting
            List<WebElement> productTitles = specialOffersPage.getProductTitles();

            // Step 4: Validate if products are sorted correctly (based on the sorting option)
            boolean isSorted = specialOffersPage.areProductsSorted(productTitles);
            Assert.assertTrue(isSorted, "The products are not sorted correctly after applying the 'Sort By' option: " + sortOption);
        }
    }




