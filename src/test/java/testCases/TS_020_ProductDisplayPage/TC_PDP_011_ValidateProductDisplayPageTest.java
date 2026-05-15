package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_011_ValidateProductDisplayPageTest extends BaseClass {

    @Test
    public void validateProductDisplayPage() {

        logger.info("=== TC_PDP_035 Started ===");

        HomePage home = new HomePage(getDriver());
        ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

        String productToSearch = p.getProperty("productName");

        logger.info("Searching for product: {}", productToSearch);

        home.enterSearchText(productToSearch);
        home.clickSearchButton();
        home.clickProductByName(productToSearch);

        logger.info("Clicked on product link: {}", productToSearch);

        String actualTitle = pdp.getPageTitle();

        logger.info("Page Title: {}", actualTitle);

        Assert.assertTrue(actualTitle.contains(productToSearch),
                "Page title does not contain product name.");

        String actualHeading = pdp.getProductName();

        logger.info("Page Heading: {}", actualHeading);

        Assert.assertEquals(actualHeading, productToSearch,
                "Product heading mismatch.");

        String currentURL = pdp.getCurrentPageURL();

        logger.info("Current Page URL: {}", currentURL);

        Assert.assertTrue(currentURL.contains("product_id"),
                "URL does not contain product_id.");

        logger.info("=== TC_PDP_035 Completed Successfully ===");
    }
}