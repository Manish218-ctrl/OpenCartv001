package testCases.TS_020_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_005_ValidateEmptyFieldsInMyAccountInfoTest extends BaseClass {

        @Test
        public void validateEmptyFieldsInMyAccountInfo() {
            // Step 1: Login
            performLogin();

            Homepage home = new Homepage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            // Step 2: Navigate to Edit Account Information
            home.clickMyAccount();
            myAccount.clickEditAccountInformation();

            MyAccountPage accountInfoPage = new MyAccountPage(driver);

            // Verify page is displayed
            Assert.assertTrue(accountInfoPage.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed.");

            // Step 3: Clear all fields
            accountInfoPage.clearAllFields();

            // Step 4: Click Continue
            accountInfoPage.clickContinue();

            // Step 5: Validate field level error messages
            Assert.assertTrue(accountInfoPage.isValidationMessageDisplayed(),
                    "Validation messages for empty fields are not displayed.");
        }
    }
