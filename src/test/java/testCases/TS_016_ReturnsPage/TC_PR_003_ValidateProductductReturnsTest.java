package testCases.TS_016_ReturnsPage;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

    public class TC_PR_003_ValidateProductductReturnsTest extends BaseClass {

        @Test
        public void validateMandatoryFieldsOnProductReturnsPage() {
            // 1. Perform login using credentials from config.properties
            performLogin();

            // 2. Navigate to Order History -> View -> Return
            Homepage home = new Homepage(driver);
            home.clickMyAccount();

            driver.findElement(By.linkText("Order History")).click();

            // Click 'View' for the first order
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr[1]/td[7]/a")).click();



            // Click 'Return' button
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/a[2]")).click();

            // Click 'View' for the second order
           // driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr[2]/td[7]/a")).click();

            // Click 'Return' button
           // driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/a[2]")).click();



            // 3. On Product Returns Page, clear all fields
            ProductReturnsPage returnsPage = new ProductReturnsPage(driver);

            // Clear all mandatory fields
            driver.findElement(By.id("input-order-id")).clear();
            driver.findElement(By.id("input-date-ordered")).clear();
            driver.findElement(By.id("input-product")).clear();
            driver.findElement(By.id("input-model")).clear();
            driver.findElement(By.id("input-quantity")).clear();
            driver.findElement(By.id("input-comment")).clear();

            // 4. Click Submit
            returnsPage.clickSubmitrp();

            driver.findElement(By.linkText("Order History")).click();


            // Click 'View' for the second order
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr[2]/td[7]/a")).click();

            // Click 'Return' button
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[6]/a[2]")).click();

            // 3. On Product Returns Page, clear all fields
            ProductReturnsPage returnsPage1 = new ProductReturnsPage(driver);

            // Clear all mandatory fields
            driver.findElement(By.id("input-order-id")).clear();
            driver.findElement(By.id("input-date-ordered")).clear();
            driver.findElement(By.id("input-product")).clear();
            driver.findElement(By.id("input-model")).clear();
            driver.findElement(By.id("input-quantity")).clear();
            driver.findElement(By.id("input-comment")).clear();

            // 4. Click Submit
            returnsPage1.clickSubmitrp();


            // 5. Validate error messages
            boolean isErrorDisplayed = driver.findElements(By.cssSelector(".text-danger")).size() > 0;
            Assert.assertTrue(isErrorDisplayed, "Mandatory field validation errors should appear");

            logger.info("Mandatory field validation verified successfully using credentials from config.properties");
        }
    }


