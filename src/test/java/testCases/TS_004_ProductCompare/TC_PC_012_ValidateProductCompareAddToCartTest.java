package testCases.TS_004_ProductCompare;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.ProductComparisonPage;
import testBase.BaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;
import org.openqa.selenium.WebElement; // Re-added for scrolling functionality
import org.openqa.selenium.JavascriptExecutor; // Re-added for scrolling functionality

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

            // Step 1: Search and Add product 1 to compare
            SearchPage sp = new SearchPage(driver);
            sp.enterSearchKeyword(productName1);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(productName1),
                    "ERROR: '" + productName1 + "' not displayed in search results.");
            sp.clickCompareIconForProduct(productName1);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for first product comparison success message

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));

            // Step 2: Search and Add product 2 to compare
            sp.enterSearchKeyword(productName2);
            sp.clickSearchButton();
            Assert.assertTrue(sp.isProductDisplayed(productName2),
                    "ERROR: '" + productName2 + "' not displayed in search results.");
            sp.clickCompareIconForProduct(productName2);

            // Wait for second product comparison success message
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));

            // Step 3: Click comparison link from success message
            sp.clickComparisonLinkFromSuccessMessage();

            // This ensures that the comparison table has fully rendered its dynamic content.
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            By firstProductLinkInTable = By.xpath("//table[@class='table table-bordered']//a[normalize-space()='" + productName1 + "']");
            WebElement productHeaderElement = longWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductLinkInTable));

            Assert.assertTrue(cmp.isOnComparisonPage(),
                    "ERROR: Did not navigate to Product Comparison page.");

            By addToCartButtonLocator = By.xpath("/html/body/div[2]/div/div/table/tbody[4]/tr/td[2]/input");

            WebElement addToCartButtonElement = longWait.until(ExpectedConditions.elementToBeClickable(addToCartButtonLocator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButtonElement);
            logger.info("Scrolled down to ensure 'Add to Cart' button for product '{}' is visible using absolute XPath.", productName1);

            // Step 4: Add product to cart from comparison page (Restored original call)
            cmp.clickaddtocartbtn1();
            cmp.clickaddtocartbtn2();

            Thread.sleep(3000);

            cmp.clickcartlinkinsuccessmsg();

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
