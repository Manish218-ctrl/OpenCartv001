package testCases.TS_019_MyAccountInformation;




import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;

    public class TC_MAI_007_ValidateBackButtonInMyAccountInformationPageTest extends BaseClass {

        @Test
        public void verifyBackButtonInMyAccountInformationPage() {

            // Step 1: Login
            performLogin();

            HomePage home = new HomePage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            // Step 2: Navigate to "Edit your account information"
            myAccount.clickEditAccountInformation();

            // Step 3: Verify "My Account Information" page is displayed
            Assert.assertTrue(myAccount.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed.");

            // Step 4: Update fields with random data
            String randomFirstName = randomString();
            String randomLastName = randomString();
            String randomEmail = randomAlphaNumeric() + "@mail.com";
            String randomPhone = randomNumber();

            myAccount.clearAllFields();
            driver.findElement(By.id("input-firstname")).sendKeys(randomFirstName);
            driver.findElement(By.id("input-lastname")).sendKeys(randomLastName);
            driver.findElement(By.id("input-email")).sendKeys(randomEmail);
            driver.findElement(By.id("input-telephone")).sendKeys(randomPhone);

            // Step 5: Click Back button (browser back)
            driver.navigate().back();

// Step 6: Validate user is redirected to "My Account" page
            Assert.assertTrue(myAccount.isMyAccountPageExists(), "User is not navigated back to My Account page.");

// Step 7: Navigate again to "Edit your account information"
            myAccount.clickEditAccountInformation();

// Step 8: Refresh the page to ensure fields are empty
            driver.navigate().refresh();

// Step 9: Verify previously entered values are lost

            Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']")).getAttribute("value"), "",
                    "Email field is not empty after back navigation.");
            Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']")).getAttribute("value"), "",
                    "Telephone field is not empty after back navigation.");

        }

    }