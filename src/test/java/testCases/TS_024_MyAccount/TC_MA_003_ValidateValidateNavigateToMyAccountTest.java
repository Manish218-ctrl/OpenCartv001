package testCases.TS_024_MyAccount;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MA_003_ValidateValidateNavigateToMyAccountTest extends BaseClass {

        @Test(description = "Validate navigating to 'My Account' page using 'My Account' option")
        public void verifyNavigateToMyAccount() {
            // Reuse driver & setup from BaseClass (BeforeClass opens the app)
            Homepage home = new Homepage(driver);

            // 1) Click the My Account dropmenu to open options
            // There are multiple elements in your Homepage - using the visible top element
            home.clickMyAccount(); // opens dropdown

            // 2) Click the My Account option from dropdown
            // Some code had a method clickMyAccountFromDropdown — but it clicked an aside link.
            // We'll try to click the dropdown item reliably using the dropdown element if present
            try {
                // try PageObject's dropdown link if defined
                home.clickMyAccountFromDropdown();
            } catch (Exception e) {
                // fallback: locate and click via WebDriver
                // try clicking the dropdown menu item by visible text
                try {
                    driver.findElement(org.openqa.selenium.By.xpath("//a[normalize-space()='My Account' and contains(@href,'account')]")).click();
                } catch (Exception ignored) {
                    // final fallback: click the span element that says My Account
                    home.lnkMyAccount.click();
                }
            }

            // 3) Validate we are on My Account page
            MyAccountPage myAcc = new MyAccountPage(driver);
            boolean atMyAccount = myAcc.isAt();

            Assert.assertTrue(atMyAccount, "User should be navigated to My Account page but was not. Breadcrumb: " + myAcc.getBreadcrumbText());
        }
    }

