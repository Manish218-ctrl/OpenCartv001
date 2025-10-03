package testCases.TS_004_ProductCompare;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;

    public class TC_PC_012_ValidateProductCompareAddToCartTest extends BaseClass {

        @Test(groups = {"regression"})
        public void verifyAddToCartFromProductComparisonPage() {
            logger.info("***** Starting TC_PC_012_ValidateProductCompareAddToCartTest *****");

            try {
                String productName1 = "iMac";
                String productName2 = "MacBook";

                // Step 0: Clean up Product Comparison page
                ProductComparisonPage cmp = new ProductComparisonPage(driver);
                cmp.clearAllComparedProducts();

                // Step 1: Search & Add first product to compare
                SearchPage sp = new SearchPage(driver);
                sp.enterSearchKeyword(productName1);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName1),
                        "ERROR: '" + productName1 + "' not displayed in search results.");
                sp.clickCompareIconForProduct(productName1);

                // Step 2: Search & Add second product to compare
                sp.enterSearchKeyword(productName2);
                sp.clickSearchButton();
                Assert.assertTrue(sp.isProductDisplayed(productName2),
                        "ERROR: '" + productName2 + "' not displayed in search results.");
                sp.clickCompareIconForProduct(productName2);

                // Step 3: Click comparison link from success message
                sp.clickComparisonLinkFromSuccessMessage();
                Assert.assertTrue(cmp.isOnComparisonPage(),
                        "ERROR: Did not navigate to Product Comparison page.");

                // Step 4: Add product to cart from comparison page
                cmp.addProductToCart(productName1);
                Assert.assertTrue(cmp.isProductInCart(productName1),
                        "ERROR: Product not added to cart from comparison page.");

                logger.info("Product '{}' successfully added to cart from comparison page.", productName1);

            } catch (Exception e) {
                logger.error("Test Failed due to Exception: " + e.getMessage(), e);
                Assert.fail("Exception occurred during Product Compare Add to Cart test: " + e.getMessage());
            }

            logger.info("***** Finished TC_PC_012_ValidateProductCompareAddToCartTest *****");
        }
    }


