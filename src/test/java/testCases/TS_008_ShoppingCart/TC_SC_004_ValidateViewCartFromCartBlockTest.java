package testCases.TS_008_ShoppingCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;

import java.time.Duration;

public class TC_SC_004_ValidateViewCartFromCartBlockTest extends BaseClass {

    @Test
    public void verifyViewCartFromCartBlock() {
        try {
            logger.info("***** Starting TC_SC_004_ValidateViewCartFromCartBlockTest *****");

            // Step 1: Login to the application
            performLogin();
            logger.info("User logged in successfully.");

            // Step 2: Hover over Cart icon (if View Cart is hidden in a dropdown)
            WebElement cartMenu = driver.findElement(By.xpath("//a[@title='Shopping Cart']"));
            Actions action = new Actions(driver);
            action.moveToElement(cartMenu).perform();
            logger.info("Hovered over the Cart menu.");

            // Step 3: Wait for 'View Cart' button to be clickable and click
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/nav/div/div[2]/ul/li[4]/a/span")));
            viewCart.click();
            logger.info("'View Cart' button clicked successfully.");

            // Step 4: Verify that the Shopping Cart page is displayed
            WebElement cartHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[2]/ul/li[2]/a")));
            Assert.assertTrue(cartHeader.isDisplayed(), "Shopping Cart page is displayed.");
            logger.info("Shopping Cart page is verified successfully.");

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage());
            Assert.fail("Test Failed: " + e.getMessage());
        }
    }
}
