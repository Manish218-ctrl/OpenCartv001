package testCases.TS_019_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_005_ValidateEmptyFieldsInMyAccountInfoTest extends BaseClass {

        @Test
        public void validateEmptyFieldsInMyAccountInfo() {
            //Login
            performLogin();

            HomePage home = new HomePage(getDriver());
            MyAccountPage myAccount = new MyAccountPage(getDriver());

            //Navigate to Edit Account Information
            home.clickMyAccount();
            myAccount.clickEditAccountInformation();

            MyAccountPage accountInfoPage = new MyAccountPage(getDriver());

            // Verify page is displayed
            Assert.assertTrue(accountInfoPage.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed.");

            //Clear all fields
            accountInfoPage.clearAllFields();

            //Click Continue
            accountInfoPage.clickContinue();

            //Validate field level error messages
            Assert.assertTrue(accountInfoPage.isValidationMessageDisplayed(),
                    "Validation messages for empty fields are not displayed.");
        }
    }
