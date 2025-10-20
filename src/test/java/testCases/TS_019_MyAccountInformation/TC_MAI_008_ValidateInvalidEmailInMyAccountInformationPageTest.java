package testCases.TS_019_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_008_ValidateInvalidEmailInMyAccountInformationPageTest extends BaseClass {

        @Test
        public void verifyInvalidEmailValidationMessage() {

            // Step 1: Login
            performLogin();

            // Initialize page objects
            HomePage home = new HomePage(driver);
            MyAccountPage myAccount = new MyAccountPage(driver);

            // Step 2: Navigate to "Edit your account information"
            home.clickMyAccount();
            myAccount.clickEditAccountInformation();

            // Step 3: Verify "My Account Information" page is displayed
            Assert.assertTrue(myAccount.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed.");

            // Step 4: Test multiple invalid email formats
            String[] invalidEmails = {"test@ex", "testauto@com", "automa@gmail.com", "automation@gmail."};

            for (String email : invalidEmails) {
                myAccount.clearAllFields(); // Clear previous values
                myAccount.txtEmail.sendKeys(email); // Set invalid email
                myAccount.clickContinue(); // Submit

                // Step 5: Verify validation message is displayed
                Assert.assertTrue(myAccount.isValidationMessageDisplayed(),
                        "Validation message not displayed for email: " + email);

                logger.info("Validation message displayed correctly for invalid email: " + email);
            }
        }
    }



