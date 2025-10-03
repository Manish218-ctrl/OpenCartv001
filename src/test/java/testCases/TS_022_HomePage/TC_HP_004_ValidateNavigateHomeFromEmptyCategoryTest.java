package testCases.TS_022_HomePage;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryPage;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_HP_004_ValidateNavigateHomeFromEmptyCategoryTest extends BaseClass {

        @Test(description = "Validate navigating to Home page from an empty Category page (PC(0))")
        public void testNavigateToHomePageFromEmptyCategory() {
            Homepage home = new Homepage(driver);

            // Step 1: Hover on 'Desktops' and select 'PC(0)'
            home.navigateToEmptyPCCategory();

            CategoryPage category = new CategoryPage(driver);

            // Step 2: Click on "Continue" button in empty category page
            category.clickContinue();

            // Step 3: Validate navigation back to Home page
            String actualTitle = driver.getTitle();
            String expectedTitle = "Your Store"; // homepage title

            Assert.assertEquals(actualTitle, expectedTitle,
                    "User was not redirected to Home page after clicking Continue on empty category.");
        }
    }

