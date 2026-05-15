package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_007_ValidateProductSpecificationsTest extends BaseClass {

    @Test
    public void validateProductSpecifications() {

        logger.info("======= Starting TC_PDP_009 =======");

        HomePage home = new HomePage(getDriver());
        ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

        home.enterSearchText(productName);

        logger.info("Entered product name in search box: {}", productName);

        home.clickSearchButton();

        logger.info("Clicked on Search button");

        home.clickProductByName(productName);

        logger.info("Clicked on product link: {}", productName);

        pdp.clickDescriptionTab();

        logger.info("Clicked on Product Specification tab");

        String actualProductName = pdp.getProductName();
        String actualBrand = pdp.getProductBrand();
        String actualProductCode = pdp.getProductCode();
        String actualAvailability = pdp.getProductAvailability();
        String actualPriceWithTax = pdp.getPriceWithTax();
        String actualPriceExTax = pdp.getPriceExTax();

        Assert.assertEquals(actualProductName, productName,
                "Product Name mismatch!");

        Assert.assertNotNull(actualBrand,
                "Brand should not be null");

        Assert.assertNotNull(actualProductCode,
                "Product Code should not be null");

        Assert.assertTrue(
                actualAvailability.equalsIgnoreCase("In Stock")
                        || actualAvailability.equalsIgnoreCase("Out Of Stock"),
                "Invalid Availability value"
        );

        Assert.assertNotNull(actualPriceWithTax,
                "Price with tax should be displayed");

        Assert.assertNotNull(actualPriceExTax,
                "Price ex-tax should be displayed");

        logger.info("Product specifications validated successfully for: {}",
                productName);

        logger.info("======= TC_PDP_009 Completed =======");
    }
}