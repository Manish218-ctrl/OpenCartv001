package testCases.TS_019_MyAccountInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_MAI_007_ValidateBackButtonInMyAccountInformationPageTest extends BaseClass {

    @Test
    public void verifyBackButtonInMyAccountInformationPage() {

        logger.info("Starting TC_MAI_007_ValidateBackButtonInMyAccountInformationPageTest");

        performLogin();

        HomePage home = new HomePage(getDriver());

        MyAccountPage myAccount = new MyAccountPage(getDriver());

        myAccount.clickEditAccountInformation();

        Assert.assertTrue(
                myAccount.isMyAccountInformationPageDisplayed(),
                "My Account Information page is not displayed."
        );

        String randomFirstName =
                randomString();

        String randomLastName =
                randomString();

        String randomEmail =
                randomAlphaNumeric() + "@mail.com";

        String randomPhone =
                randomNumber();

        myAccount.clearAllFields();

        myAccount.enterFirstName(randomFirstName);

        myAccount.enterLastName(randomLastName);

        myAccount.enterEmail(randomEmail);

        myAccount.enterTelephone(randomPhone);

        logger.info("Entered random account information data.");

        getDriver().navigate().back();

        Assert.assertTrue(
                myAccount.isMyAccountPageExists(),
                "User is not navigated back to My Account page."
        );

        myAccount.clickEditAccountInformation();

        getDriver().navigate().refresh();

        Assert.assertEquals(
                myAccount.getEmailFieldValue(),
                "",
                "Email field is not empty after back navigation."
        );

        Assert.assertEquals(
                myAccount.getTelephoneFieldValue(),
                "",
                "Telephone field is not empty after back navigation."
        );

        logger.info("TC_MAI_007 executed successfully");
    }
}