package testCases.TS_016_OrderInformation;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrderInformationPage;
import testBase.BaseClass;

    public class TC_OI_002_ValidateOrderInformationReorderTest extends BaseClass {

        @Test
        public void validateReorderFunctionalityTest() {
            // Step 1: Open the Application URL and login
            performLogin();  // This method is in BaseClass and will log the user in

            // Step 2: Navigate to 'Order History' page
            HomePage homepage = new HomePage(driver);
            homepage.clickMyAccount();
            homepage.clickOrderHistory();  // Click on 'Order History' link from My Account

            // Step 3: Click 'View' on the first order
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.clickFirstOrderViewIcon();

            // Step 4: Click 'Reorder' icon in the Order Information page
            OrderInformationPage orderInfoPage = new OrderInformationPage(driver);

            // Click on the 'Reorder' icon
            orderInfoPage.clickReorderIcon();  // Assuming the 'Reorder' icon is present

            // Step 5: Validate success message
            WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-success')]"));
            Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed after clicking 'Reorder'");
            Assert.assertTrue(successMessage.getText().contains("Success: You have added HP LP3065 to your shopping cart!"),
                    "Incorrect success message: " + successMessage.getText());

            // Step 6: Click on 'Shopping Cart' link in the success message
            WebElement shoppingCartLink = driver.findElement(By.xpath("//a[contains(@href, 'cart')]"));
            shoppingCartLink.click();

            // Step 7: Verify that the product appears in the shopping cart
            WebElement cartProduct = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/table/tbody/tr/td[2]/a"));
            Assert.assertTrue(cartProduct.isDisplayed(), "Product is not displayed in the shopping cart.");

            logger.info("Reorder functionality validated successfully.");
        }
    }



