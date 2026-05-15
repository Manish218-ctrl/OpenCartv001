package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_012_ValidateProductDisplayPageOptionsTest extends BaseClass {

    @Test
    public void validateProductDisplayPageOptions() {

        logger.info(
                "=== TC_PDP_012: Product Display Page Test Started ==="
        );

        try {

            // Login
            performLogin();

            logger.info(
                    "Login successful"
            );

            // Search product
            HomePage home =
                    new HomePage(getDriver());

            home.enterSearchText(productName);

            home.clickSearchButton();

            logger.info(
                    "Searched for product: "
                            + productName
            );

            // Product Display Page
            ProductDisplayPage pdp =
                    new ProductDisplayPage(getDriver());

            // Open product from search results
            pdp.clickProductFromSearchResults(productName);

            logger.info(
                    "Opened Product Display Page for: "
                            + productName
            );

            // Validate Product Name
            String actualProductName =
                    pdp.getProductName();

            Assert.assertEquals(
                    actualProductName,
                    productName,
                    "Product Name does not match!"
            );

            logger.info(
                    "Product Name validated: "
                            + actualProductName
            );

            // Validate Brand
            String productBrand =
                    pdp.getProductBrand();

            logger.info(
                    "Product Brand: "
                            + productBrand
            );

            Assert.assertFalse(
                    productBrand.isEmpty(),
                    "Product brand is empty!"
            );

            // Validate Product Code
            String productCode =
                    pdp.getProductCode();

            logger.info(
                    "Product Code: "
                            + productCode
            );

            Assert.assertFalse(
                    productCode.isEmpty(),
                    "Product code is empty!"
            );

            // Validate Availability
            String availability =
                    pdp.getProductAvailability();

            logger.info(
                    "Availability: "
                            + availability
            );

            Assert.assertTrue(
                    availability.equalsIgnoreCase("In Stock")
                            || availability.equalsIgnoreCase("Out Of Stock"),
                    "Product availability not displayed correctly!"
            );

            // Validate Prices
            String priceWithTax =
                    pdp.getPriceWithTax();

            String priceExTax =
                    pdp.getPriceExTax();

            logger.info(
                    "Price With Tax: "
                            + priceWithTax
            );

            logger.info(
                    "Price Ex Tax: "
                            + priceExTax
            );

            Assert.assertFalse(
                    priceWithTax.isEmpty(),
                    "Price with tax is empty!"
            );

            Assert.assertFalse(
                    priceExTax.isEmpty(),
                    "Ex Tax price is empty!"
            );

            // Validate Description
            pdp.clickDescriptionTab();

            String description =
                    pdp.getProductDescription();

            Assert.assertFalse(
                    description.isEmpty(),
                    "Product description is empty!"
            );

            logger.info(
                    "Product Description validated"
            );

            // Validate Add To Cart
            pdp.clickAddToCartButton();

            logger.info(
                    "Clicked Add to Cart button successfully"
            );

            Assert.assertTrue(
                    pdp.isSuccessMessageDisplayed(),
                    "Success message not displayed after Add To Cart!"
            );

            // Validate Add To Wishlist
            pdp.clickAddToWishListButton();

            logger.info(
                    "Clicked Add to Wish List button successfully"
            );

            logger.info(
                    "=== TC_PDP_012: Product Display Page Test Completed ==="
            );

        } catch (Exception e) {

            logger.error(
                    "Test case failed due to exception: "
                            + e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test failed due to exception: "
                            + e.getMessage()
            );
        }
    }
}