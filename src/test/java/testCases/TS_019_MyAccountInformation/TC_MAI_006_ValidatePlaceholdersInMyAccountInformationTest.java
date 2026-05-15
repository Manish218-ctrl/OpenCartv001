package testCases.TS_019_MyAccountInformation;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

    public class TC_MAI_006_ValidatePlaceholdersInMyAccountInformationTest extends BaseClass {

        @Test
        public void validatePlaceholdersInMyAccountInformation() {
            //Login
            performLogin();

            //Navigate to My Account Information page
            HomePage home = new HomePage(getDriver());
            home.clickMyAccount();

            MyAccountPage myAccount = new MyAccountPage(getDriver());
            myAccount.clickEditAccountInformation();

            Assert.assertTrue(myAccount.isMyAccountInformationPageDisplayed(),
                    "My Account Information page is not displayed");

            //Clear all fields
            myAccount.clearAllFields();

            //Validate placeholders
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

