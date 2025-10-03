package testCases.TS_020_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_006_ValidatePlaceholdersInMyAccountInformationTest extends BaseClass {

        @Test
        public void validatePlaceholdersInMyAccountInformation() {
            // Step 1: Login
            performLogin();

            // Step 2: Navigate to My Account Information page
            Homepage home = new Homepage(driver);
            home.clickMyAccount();

            MyAccountPage myAccount = new MyAccountPage(driver);
            myAccount.clickEditAccountInformation();

            Assert.assertTrue(myAccount.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed");

            // Step 3: Clear all fields
            myAccount.clearAllFields();

            // Step 4: Validate placeholders
            String firstNamePlaceholder = myAccount.getFirstNamePlaceholder();
            String lastNamePlaceholder = myAccount.getLastNamePlaceholder();
            String emailPlaceholder = myAccount.getEmailPlaceholder();
            String telephonePlaceholder = myAccount.getTelephonePlaceholder();

            System.out.println("First Name Placeholder: " + firstNamePlaceholder);
            System.out.println("Last Name Placeholder: " + lastNamePlaceholder);
            System.out.println("Email Placeholder: " + emailPlaceholder);
            System.out.println("Telephone Placeholder: " + telephonePlaceholder);

            Assert.assertEquals(firstNamePlaceholder, "First Name", "First Name placeholder mismatch");
            Assert.assertEquals(lastNamePlaceholder, "Last Name", "Last Name placeholder mismatch");
            Assert.assertEquals(emailPlaceholder, "E-Mail", "Email placeholder mismatch");
            Assert.assertEquals(telephonePlaceholder, "Telephone", "Telephone placeholder mismatch");
        }
    }

