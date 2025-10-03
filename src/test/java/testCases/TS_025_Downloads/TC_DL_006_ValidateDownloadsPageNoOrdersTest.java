package testCases.TS_025_Downloads;



import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_DL_006_ValidateDownloadsPageNoOrdersTest extends BaseClass {

        @Test
        public void validateDownloadsPageNoOrders() {
            // Step 1: Login
            performLogin();

            // Step 2: Navigate to My Account page
            MyAccountPage myAccount = new MyAccountPage(driver);

            Assert.assertTrue(myAccount.isMyAccountPageExists(), "My Account page is not displayed.");

            // Step 3: Click on 'Downloads' from Right Column
            myAccount.clickDownloadsFromRightColumn();

            // Step 4: Verify the message when no downloadable orders exist
            String expectedMessage = "You have not made any previous downloadable orders!";
            String actualText = driver.findElement(
                    By.xpath("//div[@id='content']//p[contains(text(),'You have not made any previous downloadable orders!')]")
            ).getText();

            Assert.assertEquals(actualText, expectedMessage, "Downloads page message mismatch.");
        }
    }

