package testCases.TS_019_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_008_ValidateInvalidEmailInMyAccountInformationPageTest extends BaseClass {

        @Test
        public void verifyInvalidEmailValidationMessage() {

            //Login
            performLogin();

            // Initialize page objects
            HomePage home = new HomePage(getDriver());
            MyAccountPage myAccount = new MyAccountPage(getDriver());

            //Navigate to "Edit your account information"
            home.clickMyAccount();
            myAccount.clickEditAccountInformation();

            //Verify "My Account Information" page is displayed
            Assert.assertTrue(myAccount.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed.");

            //Test multiple invalid email formats
            String[] invalidEmails = {"test@ex", "testauto@com", "automa@gmail.com", "automation@gmail."};

            for (String email : invalidEmails) {
                myAccount.clearAllFields(); // Clear previous values
                myAccount.txtEmail.sendKeys(email); // Set invalid email
                myAccount.clickContinue(); // Submit

                //Verify validation message is displayed
                Assert.assertTrue(myAccount.isValidationMessageDisplayed(),
                        "Validation message not displayed for email: " + email);

                logger.info("Validation message displayed correctly for invalid email: " + email);
            }
        }
    }



