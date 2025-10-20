package testCases.TS_012_SpecialOffers;

import org.testng.annotations.Test;
import pageObjects.SpecialOffersPage;
import testBase.BaseClass;


import org.testng.Assert;

public class TC_SPO_005_ValidateSpecialOffersPageDisplayedProductsinGridViewTest extends BaseClass {

        @Test
        public void validateSpecialOffersGridView() {


            SpecialOffersPage specialOffersPage = new SpecialOffersPage(driver);

            // Open the Special Offers page
            specialOffersPage.clickSpecialsLink();

            // Select Grid view option
            specialOffersPage.selectGridView();

            // Validate that products are displayed in Grid view
            boolean isGridView = specialOffersPage.areProductsInGridView();
            Assert.assertTrue(isGridView, "Products are not displayed in Grid view!");

            // Log the result
            logger.info("TC_SPO_005: Special Offers page displayed products in Grid view successfully.");
        }
    }




