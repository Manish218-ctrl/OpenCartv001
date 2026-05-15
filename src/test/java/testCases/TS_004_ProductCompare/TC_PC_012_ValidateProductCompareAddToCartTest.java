package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductComparisonPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_PC_012_ValidateProductCompareAddToCartTest extends BaseClass {

    @Test(groups = {"regression"})
    public void verifyAddToCartFromProductComparisonPage() {

        logger.info("***** Starting TC_PC_012_ValidateProductCompareAddToCartTest *****");

        try {

            String productName1 = "iMac";
            String productName2 = "MacBook";

            ProductComparisonPage cmp =
                    new ProductComparisonPage(getDriver());

            SearchPage sp =
                    new SearchPage(getDriver());

            //Clear existing compared products if any
            cmp.clearAllComparedProducts();

            //Add first product to comparison
            sp.enterSearchKeyword(productName1);
            sp.clickSearchButton();

            Assert.assertTrue(
                    sp.isProductDisplayed(productName1),
                    "ERROR: " + productName1 + " not displayed in search results."
            );

            sp.clickCompareIconForProduct(productName1);

            Assert.assertTrue(
                    sp.getSuccessMessage().contains("Success"),
                    "Success message not displayed after adding first product to comparison."
            );

            //Add second product to comparison
            sp.enterSearchKeyword(productName2);
            sp.clickSearchButton();

            Assert.assertTrue(
                    sp.isProductDisplayed(productName2),
                    "ERROR: " + productName2 + " not displayed in search results."
            );

            sp.clickCompareIconForProduct(productName2);

            Assert.assertTrue(
                    sp.getSuccessMessage().contains("Success"),
                    "Success message not displayed after adding second product to comparison."
            );

            //Navigate to Product Comparison page
            sp.clickComparisonLinkFromSuccessMessage();

            Assert.assertTrue(
                    cmp.isOnComparisonPage(),
                    "ERROR: Did not navigate to Product Comparison page."
            );

            Assert.assertTrue(
                    cmp.waitForProductToBeListed(productName1),
                    "ERROR: Product not present in comparison table."
            );

            //Add products to cart from comparison page
            cmp.addProductToCart(productName1);

            Assert.assertTrue(
                    sp.getSuccessMessage().contains("Success"),
                    "Success message not displayed after adding first product to cart."
            );

            cmp.addProductToCart(productName2);

            Assert.assertTrue(
                    sp.getSuccessMessage().contains("Success"),
                    "Success message not displayed after adding second product to cart."
            );

            //Navigate to cart
            cmp.clickShoppingCartLinkInSuccessMessage();

            Assert.assertTrue(
                    cmp.isProductInCart(productName1),
                    "ERROR: " + productName1 + " not added to cart from comparison page."
            );

            Assert.assertTrue(
                    cmp.isProductInCart(productName2),
                    "ERROR: " + productName2 + " not added to cart from comparison page."
            );

            logger.info(
                    "Products {} and {} successfully added to cart from comparison page.",
                    productName1,
                    productName2
            );

        } catch (Exception e) {

            logger.error(
                    "Test Failed due to Exception: {}",
                    e.getMessage(),
                    e
            );

            Assert.fail(
                    "Exception occurred during Product Compare Add to Cart test: "
                            + e.getMessage()
            );
        }

        logger.info("***** Finished TC_PC_012_ValidateProductCompareAddToCartTest *****");
    }
}