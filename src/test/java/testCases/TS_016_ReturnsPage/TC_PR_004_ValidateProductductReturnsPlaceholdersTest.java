package testCases.TS_016_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TC_PR_004_ValidateProductductReturnsPlaceholdersTest extends BaseClass {

    @Test
    public void validatePlaceholdersForProductReturnsPage() {
        logger.info("***** Starting TC_PR_004_ValidateProductductReturnsPlaceholdersTest *****");

        try {
            // Step 1: Perform login
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Navigate to 'Order History'
            Homepage home = new Homepage(driver);
            home.clickMyAccount();
            logger.info("Navigated to My Account.");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickOrderHistory();
            logger.info("Opened 'Order History' page.");

            // Step 3: Click on "View" button of a product
            WebElement viewButton = driver.findElement(By.xpath("//a[@data-original-title='View']"));
            viewButton.click();
            logger.info("Clicked on 'View' button of an order.");

            // Step 4: Click on "Return" button from Order Info page
            WebElement returnButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/a[2]/i"));
            returnButton.click();
            logger.info("Clicked on 'Return' button to open Product Returns page.");

            // Step 5: Open Product Returns page object
            ProductReturnsPage productReturnsPage = new ProductReturnsPage(driver);

            // Step 6: Validate placeholders for all fields
            String orderIdPlaceholder = productReturnsPage.getOrderIDPlaceholder();
            String orderDatePlaceholder = productReturnsPage.getOrderDatePlaceholder();
            String productNamePlaceholder = productReturnsPage.getProductNamePlaceholder();
            String productCodePlaceholder = productReturnsPage.getProductCodePlaceholder();
            String quantityPlaceholder = productReturnsPage.getQuantityPlaceholder();
            String faultDetailsPlaceholder = productReturnsPage.getFaultDetailsPlaceholder();

            logger.info("Captured placeholders from Product Returns page.");

            // Step 7: Assertions
            Assert.assertEquals(orderIdPlaceholder, "Order ID", "Order ID placeholder mismatch!");
            Assert.assertEquals(orderDatePlaceholder, "Order Date", "Order Date placeholder mismatch!");
            Assert.assertEquals(productNamePlaceholder, "Product Name", "Product Name placeholder mismatch!");
            Assert.assertEquals(productCodePlaceholder, "Product Code", "Product Code placeholder mismatch!");
            Assert.assertEquals(quantityPlaceholder, "Quantity", "Quantity placeholder mismatch!");
            Assert.assertEquals(faultDetailsPlaceholder, "Faulty or other details", "Fault Details placeholder mismatch!");

            logger.info("All placeholders validated successfully.");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_PR_004_ValidateProductductReturnsPlaceholdersTest *****");
    }
}
